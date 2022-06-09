package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ejercicio2 {
	
	public static Map<Integer,List<String>> versionIterativa(List<List<String>> listas){
		
		Map<Integer,List<String>> res = new HashMap<>(); //inicializo resultado, que sirve de memoria
		Iterator<List<String>> it1 = listas.iterator(); //creo un iterador para recorrer la entrada
		
		while(it1.hasNext()){ //recorro la entrada mientras queden elementos
			
			List<String> lista = it1.next(); //tomo el elemento de la entrada
			Iterator<String> it2 = lista.iterator(); //creo un iterador para el elemento de la entrada
			
			while(it2.hasNext()) { //recorro el elemento mientras le queden subelementos
				String s = it2.next(); //tomo el subelemento
				Integer i = s.length(); //tomo la longitud del subelemento
				
				if(res.containsKey(i)) { //si esta en la memoria resultado
					res.get(i).add(s); //actualizo su lista valor guardando el subelemento
					
				} else { //si no esta en la memoria resultado
					
					List<String> v = new ArrayList<>(); //inicializo su lista valor
					v.add(s); //guardo el subelemento en la lista valor
					res.put(i, v); //guardo en memoria resultado la longitud del subelemento asociada a la lista valor
				}
			}
		}
		
		return res; //retorno resultado
	}
	
	public static Map<Integer,List<String>> versionRecursivaFinal(List<List<String>> listas){
		
		Map<Integer,List<String>> res = new HashMap<>(); //inicializo resultado, que sirve de memoria
		Iterator<List<String>> it1 = listas.iterator(); //creo un iterador para recorrer la entrada
		return auxVersionRecursivaFinal(res, it1); //retorno el resultado del metodo recursivo auxiliar
	}
	
	private static Map<Integer,List<String>> auxVersionRecursivaFinal(Map<Integer,List<String>> res, Iterator<List<String>> it1){
		
		if(it1.hasNext()) { //recorro la entrada mientras queden elementos
			List<String> lista = it1.next(); //tomo el elemento de la entrada
			Iterator<String> it2 = lista.iterator(); //creo un iterador para el elemento de la entrada
			
			while(it2.hasNext()) { //recorro el elemento mientras le queden subelementos
				String s = it2.next(); //tomo el subelemento
				Integer i = s.length(); //tomo la longitud del subelemento
				
				if(res.containsKey(i)) { //si esta en la memoria resultado
					res.get(i).add(s); //actualizo su lista valor guardando el subelemento
					
				} else { //si no esta en la memoria resultado
					
					List<String> v = new ArrayList<>(); //inicializo su lista valor
					v.add(s); //guardo el subelemento en la lista valor
					res.put(i, v); //guardo en memoria resultado la longitud del subelemento asociada a la lista valor
				}
			}
			
			auxVersionRecursivaFinal(res, it1); //llamada recursiva a la funcion auxiliar
		}
		
			return res; //retorno resultado
	}
	
}
