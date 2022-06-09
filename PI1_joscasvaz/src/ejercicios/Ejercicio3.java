package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio3 {
	
	private record Par(Integer v1, Integer v2) { //clase record
		
		public Par(Integer v1, Integer v2) { //las propiedades son los dos valores dados
			this.v1 = v1;
			this.v2 = v2;
		}
		
		public static Par of(Integer v1,Integer v2) { //constructor
			return new Par(v1,v2);
		}
		
		public String toString() {
			return "Par[v1="+v1+", v2="+v2+"]";
		}
	}

	public static String versionIterativa(Integer a, Integer limit) {
		
		List<Par> res = new ArrayList<>(); //inicializo lista resultado
		Par par = Par.of(0, a); //inicializo el par del que parto
		
		while(par.v1() < limit) { //mientras el primer elemento del par sea menor al limite
			
			res.add(par); //guardo el par resultante en la lista resultado
			
			Integer nuevoPrimero = par.v1() + 1; //tomo primer elemento
			Integer nuevoSegundo = par.v2(); //tomo segundo elemento
			
			if(!(par.v1() % 3 == 1)) { //si el primer elemento en mod 3 es distinto de 1
				nuevoSegundo += par.v1(); //le sumo al segundo elemento el primero
			}
			
			par = Par.of(nuevoPrimero, nuevoSegundo); //actualizo los valores del par
			
		}
		
		return res.toString(); //retorno la lista resultado como cadena
	}
	
	public static String versionRecursivaFinal(Integer a, Integer limit) {
		
		List<Par> res = new ArrayList<>(); //inicializo lista resultado
		Par par = Par.of(0, a); //inicializo el par del que parto
		return auxVersionRecursivaFinal(par, limit, res); //retorno el resultado del metodo auxiliar recursivo
	}
	
	private static String auxVersionRecursivaFinal(Par par, Integer limit, List<Par> res) {
		
		if(par.v1() < limit) { //si el primer elemento del par sea menor al limite
			
			res.add(par); //guardo el par resultante en la lista resultado
			
			Integer nuevoPrimero = par.v1() + 1; //tomo primer elemento
			Integer nuevoSegundo = par.v2(); //tomo segundo elemento
			
			if(!(par.v1() % 3 == 1)) { //si el primer elemento en mod 3 es distinto de 1
				nuevoSegundo += par.v1(); //le sumo al segundo elemento el primero
			}
			
			par = Par.of(nuevoPrimero, nuevoSegundo); //actualizo los valores del par
			
			auxVersionRecursivaFinal(par, limit, res); //llamo recursivamente al metodo
		}
		
		return res.toString(); //retorno la lista resultado como cadena cuando deje de hacer recurrencia
	}
	
}
