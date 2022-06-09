package ejercicio1;

import java.util.function.Predicate;

public class HeuristicEjercicio1 {
	
	public static Double heuristic(VertexEjercicio1 v1, Predicate<VertexEjercicio1> goal,
			VertexEjercicio1 v2) { return (double) (DatosEjercicio1.getNumArch() - v1.index()); }
}
