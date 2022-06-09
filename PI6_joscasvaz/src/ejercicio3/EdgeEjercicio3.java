package ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EdgeEjercicio3 (VertexEjercicio3 source, VertexEjercicio3 target, Integer action,
		Double weight) implements SimpleEdgeAction<VertexEjercicio3, Integer>{
	
	public static EdgeEjercicio3 of(VertexEjercicio3 v1, VertexEjercicio3 v2, Integer acc) {
		
		Double w = (double) acc * DatosEjercicio3.productos.get(v1.index()).precio();
		return new EdgeEjercicio3(v1, v2, acc, w);
	}
}
