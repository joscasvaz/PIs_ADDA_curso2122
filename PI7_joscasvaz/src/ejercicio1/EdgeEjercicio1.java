package ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio1(ProblemEjercicio1 source, ProblemEjercicio1 target,
		Integer action, Double weight) implements SimpleEdgeAction<ProblemEjercicio1,Integer> {
		
	public static EdgeEjercicio1 of(ProblemEjercicio1 v1, ProblemEjercicio1 v2, Integer acc) {
		
		Double w = 0.;
		if(acc < DatosEjercicio1.getNumArch()) w = 1.;
		
		return new EdgeEjercicio1(v1, v2, acc, w);
	}
}