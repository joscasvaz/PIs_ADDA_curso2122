package ejercicios;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio4 {
	
	public static BigInteger versionRecursivaConMemoria(Integer n) {
		
		Map<Integer,Long> memoria = new HashMap<>();
		memoria.putAll(Map.of(0,2L,1,4L,2,6L)); //pongo los valores de partida en la memoria
		
		return auxVersionRecursivaConMemoria(n, memoria, 3); //llamo al algoritmo recursivo auxiliar con memoria, parto del 3 (falta)
	}
	
	private static BigInteger auxVersionRecursivaConMemoria(Integer n, Map<Integer,Long> memoria, Integer i) {
		
		if(memoria.containsKey(n)) { return BigInteger.valueOf(memoria.get(n)); } //si n en memoria, retorna resultado
		else { //si no, lo calcula
			
			Long x = memoria.get(i - 1); //f(i-1)
			Long y = memoria.get(i - 2); //f(i-2)
			Long z = memoria.get(i - 3); //f(i-3)
			
			Long res = 2*x + 4*y + 6*z; //calcula resultado ecuacion de recurrencia a partir de los valores en memoria
			memoria.put(i, res); //actualiza memoria con el nuevo i calculado
			i++; //incremento i
			
			return auxVersionRecursivaConMemoria(n, memoria, i); //llamada recursiva hasta llegar a n
		}
	}
	
	public static BigInteger versionRecursivaSinMemoria(Integer n) { return BigInteger.valueOf(auxVersionRecursivaSinMemoria(n, 3)); }
	
	private static Long auxVersionRecursivaSinMemoria(Integer n, Integer i) { //algoritmo auxiliar recursivo, partimos de 3 (falta)
		
		Long res = 0L;
		
		Long x = 6L; //valores
		Long y = 4L; //de las f(n)
		Long z = 2L; //iniciales
		
		if(n == 0) { res = z; } //caso base 1
		else if(n == 1) { res = y; } //caso base 2
		else if(n == 2) { res = x; } //caso base 3
		
		else { //si no es caso base, calcula
			
			x = auxVersionRecursivaSinMemoria(n - 1, i); //llamada recursiva, f(i-1)
			y = auxVersionRecursivaSinMemoria(n - 2, i); //llamada recursiva f(i-2)
			z = auxVersionRecursivaSinMemoria(n - 3, i); //llamada recursiva f(i-3)
			
			res = 2*x + 4*y + 6*z; //calcula resultado
			
			if(i < n) { i++; } //aumenta la i hasta que llegue a n por recurrencia
		}
		
		return res; //retorna el resultado
	}
	
	public static BigInteger versionIterativa(Integer n) {
		
		Long res = 0L;
		
		Long x = 6L; //valores
		Long y = 4L; //de las f(n)
		Long z = 2L; //iniciales
		
		if(n == 0) { res = z; } //caso base 1
		else if(n == 1) { res = y; } //caso base 2
		else if(n == 2) { res = x; } //caso base 3
		
		else { //si no es caso base, calcula
			
			int i = 3; //partimos de 3 (falta)
			
			while (i <= n) { //bucle hasta que i=n
				
				res = 2*x + 4*y + 6*z; //calcula a partir de los casos base
				
				z = y; //actualizo todos
				y = x; //los casos base
				x = res; //para seguir
				
				i++; //incremento la i para achicar el bucle
			}
		}
		
		return BigInteger.valueOf(res); //retornamos resultado convertido a BigInteger
	}
	
}
