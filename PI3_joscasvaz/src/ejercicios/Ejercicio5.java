package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import util.Paridad;

public class Ejercicio5 {
	
	static Predicate<BinaryTree<Integer>> predicado = x-> {
		
		Boolean res = false;
		
		if(x.getLeft().isEmpty() || x.getRight().isEmpty()) { return res; }
		
		Integer hi = x.getLeft().getLabel();
		Integer hd = x.getRight().getLabel();
		
		if(hi < x.getLabel() && x.getLabel() < hd) { res = true; }
		
		return res;
	};
	
	private static void almacenaParidad(Integer x, Map<Paridad,List<Integer>> mem) {
		
		List<Integer> lista = new ArrayList<>();
		
		if(Math2.esPar(x)) {
			
			if(mem.containsKey(Paridad.PAR)) { mem.get(Paridad.PAR).add(x); }
			else {
				lista.add(x);
				mem.put(Paridad.PAR, lista);
			}
			
		} else {
			
			if(mem.containsKey(Paridad.IMPAR)) { mem.get(Paridad.IMPAR).add(x); }
			else {
				lista.add(x);
				mem.put(Paridad.IMPAR, lista);
			}
		}
	}

	public static Map<Paridad,List<Integer>> versionRecursiva(BinaryTree<Integer> arbol){
		
		Map<Paridad,List<Integer>> res = new HashMap<>();
		
		switch(arbol.getType()) {
		
		case Empty:
			return null;
			
		case Leaf:
			Integer et = arbol.getLabel();
			almacenaParidad(et, res);
			
		case Binary:
			res = auxVersionRecursiva(arbol, res);
			
		default:
			break;
		}
		
		return res;
	}
	
	private static Map<Paridad,List<Integer>> auxVersionRecursiva(BinaryTree<Integer> arbol, Map<Paridad,List<Integer>> res){
		
		if(arbol.isBinary()) {
			
			Integer et = arbol.getLabel();
			if(predicado.test(arbol)) { almacenaParidad(et, res); }
			
			if(!arbol.getLeft().isEmpty()) { auxVersionRecursiva(arbol.getLeft(), res); }
			if(!arbol.getRight().isEmpty()) { auxVersionRecursiva(arbol.getRight(), res); }
		}
		
		return res;
	}
	
	public static Map<Paridad,List<Integer>> versionIterativaFunc(BinaryTree<Integer> arbol){
		
		Map<Paridad,List<Integer>> res = new HashMap<>();
		
		switch(arbol.getType()) {
		
		case Empty:
			return null;
			
		case Leaf:
			Integer et = arbol.getLabel();
			almacenaParidad(et, res);
			
		case Binary:
			
			Consumer<BinaryTree<Integer>> accion = t->{
				if(t.isBinary()) { if(predicado.test(t)) { almacenaParidad(t.getLabel(), res); } } };
			
			arbol.stream()
			.forEach(accion);
			
		default:
			break;
		}
		
		return res;
	}
	
}
