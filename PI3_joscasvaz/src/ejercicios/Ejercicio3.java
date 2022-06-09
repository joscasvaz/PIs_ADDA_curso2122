package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio3 {

	public static Pair<List<Integer>, Integer> versionRecursiva(BinaryTree<Integer> arbol){
		
		List<Integer> lista = new ArrayList<>();
		Pair<List<Integer>, Integer> par = Pair.of(lista, 1);
		
		switch(arbol.getType()) {
		
		case Empty:
			return null;
			
		case Leaf:
			return Pair.of(List.of(arbol.getLabel()), arbol.getLabel());
		
		case Binary:
			
			Integer et = arbol.getLabel();
			
			List<Integer> pLista = par.first();
			pLista.add(et);
			Integer pMult = par.second() * et;
			
			par = auxVersionRecursiva(arbol, Pair.of(pLista, pMult), Pair.of(lista, -1000));
			
			
		default:
			break;
		}
		
		return par;
	}
	
	private static Pair<List<Integer>, Integer> auxVersionRecursiva(BinaryTree<Integer> arbol,
			Pair<List<Integer>, Integer> par, Pair<List<Integer>, Integer> res) {
		
		for(BinaryTree<Integer> t:List.of(arbol.getLeft(), arbol.getRight())) {
			
			if(!t.isEmpty()) {
				
				Integer et = t.getLabel();
				
				List<Integer> pLista = new ArrayList<>(par.first());
				pLista.add(et);
				Integer pMult = par.second() * et;
				
				Pair<List<Integer>, Integer> auxPar = Pair.of(pLista, pMult);
				
				Integer rMult = res.second();
				
				if(t.isLeaf()) { if(rMult < pMult) { res = auxPar; } }
				
				if(t.isBinary()) { res = auxVersionRecursiva(t, auxPar, res); }
			}
		}
		
		return res;
	}
	
}
