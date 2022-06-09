package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio1 {
	
	public static <T> List<T> versionRecursiva(Tree<T> arbol, Predicate<T> predicado){
		
		List<T> res = new ArrayList<>();
		
		switch(arbol.getType()) {
		case Empty:
			res.add(null);
			
		case Leaf:
			if(predicado.test(arbol.getLabel())) {res.add(arbol.getLabel());}
			
		case Nary:
			List<Tree<T>> bosque = arbol.getByLevel();
			return auxVersionRecursiva(bosque, predicado, res, 0);
			
		default:
			break;
		}
		
		return res;
	}
	
	private static <T> List<T> auxVersionRecursiva(List<Tree<T>> bosque, Predicate<T> predicado, List<T> res, int i){
		
		if(i < bosque.size()) {
			
			Tree<T> el = bosque.get(i);
			
			if(el.isLeaf()) {
				
				T et = el.getLabel();
				if(predicado.test(et) && !res.contains(et)) { res.add(et); }
			}
			
			i++;
			auxVersionRecursiva(bosque, predicado, res, i);
		}
		
		return res;
	}
	
	public static <T> List<T> versionIterativaFunc(Tree<T> arbol, Predicate<T> predicado){
		
		List<T> res = new ArrayList<>();
		
		switch(arbol.getType()) {
		case Empty:
			res.add(null);
		
		case Leaf:
			if(predicado.test(arbol.getLabel())) {res.add(arbol.getLabel());}
			
		case Nary:
			arbol.getByLevel().stream()
			.filter(a->a.isLeaf())
			.map(Tree::getLabel)
			.filter(a->predicado.test(a) && !res.contains(a))
			.forEach(a -> res.add(a));
			
		default:
			break;
		}
		
		return res;
	}
		
}
