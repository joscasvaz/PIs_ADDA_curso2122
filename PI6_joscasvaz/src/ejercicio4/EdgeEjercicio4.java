package ejercicio4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio4 (VertexEjercicio4 source, VertexEjercicio4 target, Integer action,
		Double weight) implements SimpleEdgeAction<VertexEjercicio4, Integer> {

	public static EdgeEjercicio4 of(VertexEjercicio4 v1, VertexEjercicio4 v2, Integer acc) {
		//TODO
		Double w = (double) acc * 0;
		return new EdgeEjercicio4(v1, v2, acc, w);
	}
	
}
