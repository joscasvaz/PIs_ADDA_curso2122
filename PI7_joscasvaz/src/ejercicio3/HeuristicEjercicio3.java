package ejercicio3;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HeuristicEjercicio3 {
	
	public static Double heuristic(ProblemEjercicio3 v1, Predicate<ProblemEjercicio3> goal,
		ProblemEjercicio3 v2) {
		
		if (v1.index() >= DatosEjercicio3.getNumProd()) { return 0.; }
		else {
			
			return IntStream.range(v1.index(), DatosEjercicio3.getNumProd())
					.boxed()
					.mapToDouble(p -> DatosEjercicio3.getPrecioVenta(p) * v1.getRatioUds(p))
					.sum();
		}
	}
	
	public static Double cota(ProblemEjercicio3 v, Integer a) {
		//TODO revisar
		ProblemEjercicio3 auxV = v.neighbor(a);
		return a * DatosEjercicio3.getPrecioVenta(v.index()) + heuristic(auxV,null,null);
	}
}