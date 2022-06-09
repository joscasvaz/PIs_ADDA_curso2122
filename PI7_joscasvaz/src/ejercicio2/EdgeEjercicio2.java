package ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio2(ProblemEjercicio2 source, ProblemEjercicio2 target, Integer action,
		Double weight) implements SimpleEdgeAction<ProblemEjercicio2,Integer> {
	
	public static EdgeEjercicio2 of(ProblemEjercicio2 v1, ProblemEjercicio2 v2, Integer acc) {
		
		Double w = (double) acc * DatosEjercicio2.valorCand(v1.index());
		return new EdgeEjercicio2(v1, v2, acc, w);
	}
}