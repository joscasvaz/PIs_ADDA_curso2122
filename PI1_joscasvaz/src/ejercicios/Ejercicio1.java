package ejercicios;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Ejercicio1 {
	
	public static boolean versionIterativa(List<String> ls, Predicate<String> pS,
			Predicate<Integer> pI, Function<String,Integer> f) {
		
		boolean res = false; //inicializo resultado como false
		Iterator<String> it = ls.iterator(); //hago iterador para recorrer ls
		
		while(it.hasNext() && !res) { //recorro ls mientras queden elementos y el resultado sea false
			
			String s = it.next(); //cojo siguiente elemento del iterador
			
			if(pS.test(s)) { //si se cumple el predicado pS para el elemento
				Integer i = f.apply(s); //aplico la funcion f al elemento para hacerlo integer
				res = pI.test(i); //el resultado sale de aplicar el predicado pI al elemento hecho integer
			}
		}
		
		return res; //retorno resultado
	}
	
	public static boolean versionRecursivaFinal(List<String> ls, Predicate<String> pS,
			Predicate<Integer> pI, Function<String,Integer> f) {
		
		boolean res = false; //inicializo resultado como false
		Iterator<String> it = ls.iterator(); //hago iterador para recorrer ls
		
		return auxVersionRecursivaFinal(pS, pI, f, res, it); //retorno el resultado de la funcion auxiliar recursiva
	}
	
	private static boolean auxVersionRecursivaFinal(Predicate<String> pS, Predicate<Integer> pI,
			Function<String,Integer> f, boolean res, Iterator<String> it) {
		
		if(it.hasNext() && !res) { //si quedan elementos en el iterador y el resultado es false
			String s = it.next(); //tomo el siguiente elemento
			
			if(pS.test(s)) { //si el predicado pS se cumple para el elemento
				
				Integer i = f.apply(s); //aplico la funcion f al elemento para hacerlo integer
				res = pI.test(i); //el resultado sale de aplicar el predicado pI al elemento hecho integer
			}
			return auxVersionRecursivaFinal(pS, pI, f, res, it); //retorno el resultado de la funcion auxiliar recursiva
		}
		
		return res; //retorno el resultado cuando deje de hacer llamadas recursivas
	}

}
