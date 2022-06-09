package ejercicios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio2 {

	public static Set<Integer> versionRecursiva(BinaryTree<Integer> arbol, Integer n){
		
		Set<Integer> res = new HashSet<>();
		List<Integer> inOrden = arbol.getInOrder();
		
		switch(arbol.getType()) {
		
		case Empty:
			return null;
			
		case Leaf:
			if(n <= arbol.getLabel()) { res.add(arbol.getLabel()); }
		
		case Binary:
			res = auxVersionRecursiva(inOrden, n, 0, res);
			
		default:
			break;
		}
		
		return res;
	}
	
	public static Set<Integer> auxVersionRecursiva(List<Integer> inOrden, Integer n, int i, Set<Integer> res){
		
		if(i < inOrden.size()) {
			
			if(n <= inOrden.get(i)) { res.add(inOrden.get(i)); }
			auxVersionRecursiva(inOrden, n, i + 1, res);
		}
		
		return res;
	}
	
}
