package ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio1(VertexEjercicio1 source, VertexEjercicio1 target,
		Integer action, Double weight) implements SimpleEdgeAction<VertexEjercicio1,Integer> {
		
	public static EdgeEjercicio1 of(VertexEjercicio1 v1, VertexEjercicio1 v2, Integer acc) {
		
		Double w = 0.;
		if(acc < DatosEjercicio1.getNumArch()) w = 1.;
		
		return new EdgeEjercicio1(v1, v2, acc, w);
	}
}