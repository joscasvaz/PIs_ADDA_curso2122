package ejercicio3;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HeuristicEjercicio3 {
	
	public static Double heuristic(VertexEjercicio3 v1, Predicate<VertexEjercicio3> goal,
			VertexEjercicio3 v2) {
		
		if (v1.index() >= DatosEjercicio3.getNumProd()) { return 0.; }
		else {
			
			return IntStream.range(v1.index(), DatosEjercicio3.getNumProd())
					.boxed()
					.mapToDouble(p -> DatosEjercicio3.getPrecioVenta(p) * v1.getRatioUds(p))
					.sum();
		}
	}
}
