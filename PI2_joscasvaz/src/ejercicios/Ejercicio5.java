package ejercicios;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import us.lsi.common.IntTrio;
import us.lsi.math.Math2;

public class Ejercicio5 {
	
	static Predicate<IntTrio> caso1 = t->t.first() < 3 || t.second() < 3 || t.third() < 3; //caso base
	static Predicate<IntTrio> caso2 = t->Math2.esDivisible(t.first(), t.second());

	public static Integer versionRecursivaConMemoria(IntTrio abc) {
		
		Map<IntTrio,Integer> memoria = new HashMap<>();
		
		return auxVersionRecursivaConMemoria(abc, memoria); //llamada a algoritmo recursivo auxiliar con memoria
	}
	
	private static Integer auxVersionRecursivaConMemoria(IntTrio abc, Map<IntTrio,Integer> memoria) {
		
		if(memoria.containsKey(abc)) { return memoria.get(abc); } //si la memoria contiene el trio, retorna resultado
		else { //si no, calcula
			
			Integer a = abc.first(); //deserializo los
			Integer b = abc.second();//elementos del
			Integer c = abc.third(); //trio parametro
			
			Boolean c1 = caso1.test(abc); //compruebo
			Boolean c2 = caso2.test(abc); //casos
			
			Integer res = 0;
			
			if(c1 || c2) { //si es uno de los casos
				
				if(c1) { //si se da el caso base
					
					res = a + (b * b) + 2 * c; //calculo resultado
					memoria.put(abc, res); //actualizo memoria
					
					return auxVersionRecursivaConMemoria(abc, memoria); //retorno resultado
				}
				
				else { //si se da caso 2
					
					res = auxVersionRecursivaConMemoria(IntTrio.of(a - 1, b / 2, c / 2), memoria) +
							auxVersionRecursivaConMemoria(IntTrio.of(a - 3, b / 3, c / 3), memoria); //recursion multiple aplicando c2
				}
				
			} else { //en otro caso
				
				res = auxVersionRecursivaConMemoria(IntTrio.of(a / 3, b - 3, c - 3), memoria) +
						auxVersionRecursivaConMemoria(IntTrio.of(a / 2, b - 2, c - 2), memoria); //recursion multiple aplicando otros
			}
			
			memoria.put(abc, res); //actualizo memoria
			return auxVersionRecursivaConMemoria(abc, memoria); //retorno resultado
		}
	}
	
	public static Integer versionRecursivaSinMemoria(IntTrio abc) {
		
		Integer a = abc.first(); //deserializo los
		Integer b = abc.second();//elementos del
		Integer c = abc.third(); //trio parametro
		
		Boolean c1 = caso1.test(abc); //compruebo
		Boolean c2 = caso2.test(abc); //casos
		
		Integer res = 0;
		
		if(c1 || c2) { //si es uno de los casos
			
			if(c1) { return a + (b * b) + 2 * c; } //si se da caso base retorno resultado
			
			else { //si se da caso 2
				
				res = versionRecursivaSinMemoria(IntTrio.of(a - 1, b / 2, c / 2)) +
						versionRecursivaSinMemoria(IntTrio.of(a - 3, b / 3, c / 3)); //recursion multiple aplicando caso2
			}
			
		} else { //en otro caso
			
			res = versionRecursivaSinMemoria(IntTrio.of(a / 3, b - 3, c - 3)) +
					versionRecursivaSinMemoria(IntTrio.of(a / 2, b - 2, c - 2)); //recursion multiple aplicando otros
		}
		
		return res; //retorno resultado
	}
	
	public static Integer versionIterativa(IntTrio abc) {
		
		Integer res = 0;
		Boolean cortocircuito = false; //funcion cortocircuito
		
		Map<IntTrio,Integer> memoria = new HashMap<>(); //uso memoria
		
		while(!memoria.containsKey(abc)) { //mientras no este el trio en memoria
			
			Integer a = abc.first(); //deserializo los
			Integer b = abc.second();//elementos del
			Integer c = abc.third(); //trio parametro
			
			Boolean c1 = caso1.test(abc); //compruebo
			Boolean c2 = caso2.test(abc); //casos
			
			if(c1 || c2) { //si es uno de los casos
				
				if(c1) { //y es caso base
					
					res = a + (b * b) + 2 * c; //calculo resultado
					memoria.put(abc, res); //guardo en memoria
					cortocircuito = true; //activo el cortocircuito
					}
				
				if(c2 && !cortocircuito) { //si se da el caso 2 y no cortocircuito
					
					if(memoria.containsKey(IntTrio.of(a - 1, b / 2, c / 2)) && memoria.containsKey(IntTrio.of(a - 3, b / 3, c / 3))){
						res = memoria.get(IntTrio.of(a - 1, b / 2, c / 2)) +
								 memoria.get(IntTrio.of(a - 3, b / 3, c / 3));
						//si puedo tirar de memoria me ahorro recursion
					} else {
						res = versionIterativa(IntTrio.of(a - 1, b / 2, c / 2)) +
								versionIterativa(IntTrio.of(a - 3, b / 3, c / 3));
						//si no, recursion multiple aplicando el caso 2
					}
					
				}
				
			} else {
				
				if(memoria.containsKey(IntTrio.of(a / 3, b - 3, c - 3)) && memoria.containsKey(IntTrio.of(a - 3, b / 3, c / 3))){
					res = memoria.get(IntTrio.of(a / 3, b - 3, c - 3)) +
							 memoria.get(IntTrio.of(a / 2, b - 2, c - 2));
					//si puedo tirar de memoria me ahorro recursion
				} else {
					res = versionIterativa(IntTrio.of(a / 3, b - 3, c - 3)) +
							versionIterativa(IntTrio.of(a / 2, b - 2, c - 2));
					//si no, recursion multiple aplicando caso alternativo
				}
			}
			
			memoria.put(abc, res); //guardo en memoria el resultado
		}
		
		return memoria.get(abc); //retorno el resultado guardado en memoria
	}


}
