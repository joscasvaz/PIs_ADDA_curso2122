package ejercicios;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Matrix;
import us.lsi.common.View4;

public class Ejercicio2 {
	
	public static List<String> versionRecursiva(Matrix<String> matriz){
		
		List<String> res = new ArrayList<>(); //inicializo lsita que nos hara de acumulador
		return auxVersionRecursiva(matriz, res); //retorno llamada a funcion auxiliar recursiva
	}
	
	private static List<String> auxVersionRecursiva(Matrix<String> matriz, List<String> res){ //funcion auxiliar recursiva
		
		if(1 < matriz.nf() && 1 < matriz.nc()) { //si la matriz es de mas de 1x1 hace recursion
			
			String s = matriz.corners().stream().reduce("", (s1,s2)->s1.concat(s2)); //concatena las cadenas de las esqueinas
			res.add(s); //actualiza lista acumulador
			
			View4<Matrix<String>> vista = matriz.views4(); //obtengo las 4 vistas de la matriz
			auxVersionRecursiva(vista.a(), res); //hago una
			auxVersionRecursiva(vista.b(), res); //llamada recursiva 
			auxVersionRecursiva(vista.c(), res); //para cada
			auxVersionRecursiva(vista.d(), res); //vista
		}
		
		return res; //retorno el acumulador cuando deje de hacer recursion
	}
	
}
