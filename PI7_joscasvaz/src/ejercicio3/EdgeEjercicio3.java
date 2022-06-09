package ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio3 (ProblemEjercicio3 source, ProblemEjercicio3 target, Integer action,
		Double weight) implements SimpleEdgeAction<ProblemEjercicio3, Integer>{
	
	public static EdgeEjercicio3 of(ProblemEjercicio3 v1, ProblemEjercicio3 v2, Integer acc) {
		
		Double w = (double) acc * DatosEjercicio3.productos.get(v1.index()).precio();
		return new EdgeEjercicio3(v1, v2, acc, w);
	}
}