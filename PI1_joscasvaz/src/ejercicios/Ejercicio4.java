package ejercicios;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import us.lsi.common.Pair;

public class Ejercicio4 {
	
	static Function<Double,Double> cube = x-> Math.pow(x,3); //calcula el cubo
	static Function<Double,Double> roof = x-> Math.sqrt(x) * Math.signum(x); //calcula la raiz cuadrada
	static Function<Pair<Double,Double>,Double> midpoint = p -> (p.first() + p .second())/2; //calcula el punto medio
	
	private record Intervalo(Double i, Double k, Double j) { //clase record
		public Intervalo(Double i, Double j) {
			this(i, midpoint.apply(Pair.of(i, j)), j); //las propiedades son los dos puntos dados y el punto medio
		}

		public static Intervalo of(Double i, Double j) { //constructor
			return new Intervalo(i, j);
		}

		public String toString() {
			return "("+i+","+k+","+j+")";
		}
	}
	
	public static Double versionIterativa(Double numero, Double error) {
		
		Double res = 0.; //inicializo resultado
		Double cubo = cube.apply(res); //elevo al cubo el resultado, que debe ser la raiz cubica del numero dado
		Double fallo = numero - cubo; //calculo el fallo: la diferencia entre el numero y el cubo del resultado
		
		Double techo = roof.apply(numero); //calculo la raiz cuadrada del numero, que sera mayor a la cubica
		Intervalo interv = Intervalo.of(res, techo); //creo el intervalo que va de 0 a la raiz cuadrada
		
		while(!(Math.abs(fallo) < error)) { //mientras el valor absoluto del fallo no sea menor al error dado
			
			Double i = interv.i(); //tomo la i del intervalo
			Double j = interv.j(); //tomo la j del intervalo
			Double k = interv.k(); //tomo la k, el punto medio del intervalo
			
			Double cuboi = cube.apply(i); //elevo la i al cubo
			Double cuboj = cube.apply(j); //elevo la j al cubo
			Double cubok = cube.apply(k); //elevo la k al cubo
			
			Double falloi = numero - cuboi; //calculo fallo en i
			Double falloj = numero - cuboj; //calculo fallo en j
			Double fallok = numero - cubok; //calculo fallo en k
			
			if(fallok < 0) {interv = Intervalo.of(i, k);} //si fallok negativo achico intervalo por la derecha
			else {interv = Intervalo.of(k, j);} //si fallok positivo achico intervalo por la izquierda
			
			if(falloi < falloj && falloi < fallok) { //si el menor fallo es el i
				res = i; //la i es el resultado
				fallo = falloi; //el fallo resultado es el de i
			} else if(falloj < falloi && falloj < fallok) { //si el menor fallo es el j
				res = j; //la j es el resultado
				fallo = falloj; //el fallo resultado es el de j
			} else { //si el menor fallo es el k
				res = k; //k es el resultado
				fallo = fallok; //el fallo resultado sera el de k
			}
		}
		
		return res; //retorno resultado
	}
	
	public static Double versionRecursiva(Double numero, Double error) {
		
		Double res = 0.; //inicializo resultado
		Double cubo = cube.apply(res); //elevo al cubo el resultado, que debe ser la raiz cubica del numero dado
		Double fallo = numero - cubo; //calculo el fallo: la diferencia entre el numero y el cubo del resultado
		
		Double techo = roof.apply(numero); //calculo la raiz cuadrada del numero, que sera mayor a la cubica
		Intervalo interv = Intervalo.of(res, techo); //creo el intervalo que va de 0 a la raiz cuadrada
		
		return auxVersionRecursiva(numero, error, fallo, interv, res); //retorno la funcion auxiliar recursiva
	}
	
	private static Double auxVersionRecursiva(Double numero, Double error,
			Double fallo, Intervalo interv, Double res) {
		
		if(!(Math.abs(fallo) < error)) { //si el valor absoluto del fallo no sea menor al error dado
			
			Double i = interv.i(); //tomo la i del intervalo
			Double j = interv.j(); //tomo la j del intervalo
			Double k = interv.k(); //tomo la k, el punto medio del intervalo
			
			Double cuboi = cube.apply(i); //elevo la i al cubo
			Double cuboj = cube.apply(j); //elevo la j al cubo
			Double cubok = cube.apply(k); //elevo la k al cubo
			
			Double falloi = numero - cuboi; //calculo fallo en i
			Double falloj = numero - cuboj; //calculo fallo en j
			Double fallok = numero - cubok; //calculo fallo en k
			
			if(fallok < 0) {interv = Intervalo.of(i, k);} //si fallok negativo achico intervalo por la derecha
			else {interv = Intervalo.of(k, j);} //si fallok positivo achico intervalo por la izquierda
			
			if(falloi < falloj && falloi < fallok) { //si el menor fallo es el i
				
				return auxVersionRecursiva(numero, error, falloi, interv, i); //recurro con res = i fallo = falloi
				
			} else if(falloj < falloi && falloj < fallok) { //si el menor fallo es el j
				
				return auxVersionRecursiva(numero, error, falloj, interv, j); //recurro con res = j fallo = falloj
				
			} else { //si el menor fallo es el k
				
				return auxVersionRecursiva(numero, error, fallok, interv, k); //recurro con res = k fallo = fallok
			}
		}
		
		return res; //retorno el resultado cuando se corte la recurrencia
	}
	
	public static Double versionFuncional(Double numero, Double error) {
		
		Double techo = roof.apply(numero); //calculo la raiz cuadrada del numero, que sera mayor a la cubica
		
		Predicate<Intervalo> parada = interv->!(Math.abs(numero - cube.apply(interv.i())) < error);
		//el predicado de parada para cuando el valor absoluto del numero - cuboi sea menor al error dado
		
		UnaryOperator<Intervalo> fNext = interv -> {
			
			Double i = interv.i(); //tomo la i del intervalo
			Double j = interv.j(); //tomo la j del intervalo
			Double k = interv.k(); //tomo la k, el punto medio del intervalo
			
			Double pi = 0.; //inicializo pi
			Double pj = 0.; //inicializo pj
			
			if(i < j) { //si i menor que j
				pi = i;
				pj = j;
			} else { //si no
				pi = j;
				pj = i;
			}
			//ya tenemos pi y pj ordenados de menor a mayor
			
			Double cuboi = cube.apply(i); //elevo la i al cubo
			Double cuboj = cube.apply(j); //elevo la j al cubo
			Double cubok = cube.apply(k); //elevo la k al cubo
			
			Double falloi = numero - cuboi; //calculo fallo en i
			Double falloj = numero - cuboj; //calculo fallo en j
			Double fallok = numero - cubok; //calculo fallo en k
			
			if(fallok < 0) {pj = k;} //si fallok negativo achico el intervalo por la derecha
			else {pi = k;} //si fallok positivo achico el intervalo por la izquierda
			
			Double nxi = 0.; //inicializo el proximo valor de i
			Double nxj = 0.; //inicializo el proximo valor de j
			
			if(falloi < falloj) { //si el menor fallo es el de i
				nxi = pi; //el proximo i es pi
				nxj = pj; //el proximo j es pj
			} else { //si el fallo de i es mayor al de j
				nxi = pj; //el proximo i es pj
				nxj = pi; //el proximo j es pi
			}
			//ahora siempre la i esta mas cerca de la solucion
			
			return Intervalo.of(nxi, nxj); //retorno el nuevo intervalo
		};
		
		Optional<Intervalo> resultado = Stream
				.iterate(
						Intervalo.of(0., techo), //creo el intervalo
						fNext //le asigno la funcion de next para iterar
						)
				.dropWhile(parada) //hasta que no se de la condicion de parada sigo
				.findFirst(); //tomo el primer elemento cuando se pare

		return resultado.get().i(); //retorno la i, que esta mas cerca de la solucion
		
	}
	
}
