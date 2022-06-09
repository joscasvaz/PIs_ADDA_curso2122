package ejercicios;

import java.util.List;
import java.util.function.Predicate;

import us.lsi.common.IntPair;
import us.lsi.common.IntegerSet;

public class Ejercicio3 {
	
static Predicate<Integer> enRango(IntPair rango){ return x -> rango.first() <= x && x < rango.second(); } //par esta en el rango
static <T> Predicate<Integer> indexEnLista(List<T> lista) { return x -> 0 <= x && x < lista.size();} //indice apunta dentro de la lista
	
	public static IntegerSet versionRecursiva(List<Integer> lista, IntPair rango) {
		
		Integer i = 0;
		Integer j = lista.size() - 1;
		IntPair intervalo = IntPair.of(i, j); //hago un par con los dos indices de los extremos de la lista
		
		return auxVersionRecursiva(lista, rango, intervalo); //llamo a funcion auxiliar recursiva
	}
	
	private static IntegerSet auxVersionRecursiva(List<Integer> lista, IntPair rango, IntPair intervalo) { //funcion auxiliar recursiva
		
		IntegerSet res = IntegerSet.empty();
		
		Integer i = intervalo.first(); //indice extremo inicial
		Integer k = intervalo.center();//indice centro
		Integer j = intervalo.second();//indice extremo final
		
		Integer pri = i - 1; //indice elemento previo al inicial
		Integer nxi = i + 1; //indice elemento siguiente al inicial
		
		Integer prj = j - 1; //indice elemento previo al final
		Integer nxj = j + 1; //indice elemento siguiente al final
		
		Boolean iEnLista = indexEnLista(lista).test(i); //indice elemento inicial apunta dentro de la lista?
		Boolean iDisminuible = indexEnLista(lista).test(pri) && enRango(rango).test(lista.get(pri)); //puedo achicar indice inicial?
		
		Boolean jEnLista = indexEnLista(lista).test(j); //indice elemento final apunta dentro de la lista?
		Boolean jAumentable = indexEnLista(lista).test(nxj) && enRango(rango).test(lista.get(nxj)); //puedo aumentar indice final?
		
		Integer eli = lista.get(i); //elemento iesimo de la lista
		Integer elk = lista.get(k); //elemento kesimo de la lista
		Integer elj = lista.get(j); //elemento jesimo de la lista
		
		Boolean eliEnRango = enRango(rango).test(eli); //elemento iesimo en rango?
		Boolean elkEnRango = enRango(rango).test(elk); //elemento kesimo en rango?
		Boolean eljEnRango = enRango(rango).test(elj); //elemento jesimo en rango?
		
		if(!iEnLista || !jEnLista) { //si algun indice apunta fuera
			
			if(!iEnLista) {i = 0;} //si es el i, i = 0
			else {j = lista.size() - 1;} //en otro caso sera el j, que sera el ultimo de la lista
			
			res.addAll(i, j); //meto sublista en el conjunto
			return res; //retorno conjunto
		}
		
		if(eli < rango.first()) { return res; } //si elemento iesimo < inicio rango, retorno conjunto (vacio)
		
		if(i - j == 0) { //si la i es mayor a la j
			
			res.add(eli); //meto elemento iesimo
			return res; //retorno conjunto resultado
			}
		
		if(j < i) { return res; } //si el indice j es menor a la i, para no dar toda la vuelta a la lista retorno conjunto
		
		if(elkEnRango) { //kesimo en rango
			
			if(eliEnRango) { //e iesimo en rango
				
				if(eljEnRango) { //y jesimo en rango
					
					if(jAumentable) { return auxVersionRecursiva(lista, rango, IntPair.of(i, nxj)); } //intento agrandar el
					else if(iDisminuible) { return auxVersionRecursiva(lista, rango, IntPair.of(pri, j)); } //intervalo si puedo
					
					else { //si no puedo agrandar mas el intervalo
						res.addAll(lista.subList(i, nxj)); //meto todo el intervalo incluyendo el elemento jesimo
						return res; //retorno conjunto resultado
						}
					
				} else { return auxVersionRecursiva(lista, rango, IntPair.of(i, k)); } //elemento jesimo fuera de rango, j=k  y llamo
				
			} else { //kesimo en rango pero iesimo no
				
				if(eljEnRango) { return auxVersionRecursiva(lista, rango, IntPair.of(k, j)); } //pero jesimo en rango, i=k  y llamo
				else { return auxVersionRecursiva(lista, rango, IntPair.of(nxi, prj)); } //y jesimo tampoco, achico intervalo
				}
				
		} else { //kesimo fuera del rango
			
			if(eliEnRango) { return auxVersionRecursiva(lista, rango, IntPair.of(i, k)); } //pero iesimo dentro, j=k y llamo
			
			else { //iesimo tampoco en intervalo
				
				if(eljEnRango) { return auxVersionRecursiva(lista, rango, IntPair.of(nxi, j)); } //pero jesimo dentro, llamo subiendo i
				else { return auxVersionRecursiva(lista, rango, IntPair.of(nxi, prj)); } //jesimo tampoco, llamo achicando intervalo
				}
			}
		}
	}
