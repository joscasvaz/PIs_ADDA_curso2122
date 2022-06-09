package ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio2(VertexEjercicio2 source, VertexEjercicio2 target, Integer action,
		Double weight) implements SimpleEdgeAction<VertexEjercicio2,Integer> {
	
	public static EdgeEjercicio2 of(VertexEjercicio2 v1, VertexEjercicio2 v2, Integer acc) {
		
		Double w = (double) acc * DatosEjercicio2.valorCand(v1.index());
		return new EdgeEjercicio2(v1, v2, acc, w);
	}
}