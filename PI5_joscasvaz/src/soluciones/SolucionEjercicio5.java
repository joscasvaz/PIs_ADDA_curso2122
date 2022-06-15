package soluciones;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicio5;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class SolucionEjercicio5 {
	
	private List<Ciudad> ciudades = new ArrayList<>();
	private Double kms = 0.;
	
	public static SolucionEjercicio5 create(List<Integer> ls) {
		return new SolucionEjercicio5(ls); }
	
	private SolucionEjercicio5(List<Integer> ls) {
		
		ciudades = ls.stream()
				.map(i -> DatosEjercicio5.graph.getVertex(i))
				.toList();
		
		Ciudad c1 = null;
		Ciudad c2 = null;
		
		for (int i = 0; i < ciudades.size()-1; i++) {
			
			c1 = ciudades.get(i);
			c2 = ciudades.get(i + 1);
			
			if (DatosEjercicio5.gf.containsEdge(c1, c2)) {
				
				Carretera carretera = DatosEjercicio5.gf.getEdge(c1, c2);
				kms += carretera.km();
			}
		}
	}
	
	public String toString() { return String.format("%s; Kms: %.1f", ciudades, kms); }
}
