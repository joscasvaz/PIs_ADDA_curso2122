package ejercicio3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;

import ejercicio3.DatosEjercicio3.Producto;

public class SolucionEjercicio3 {
	
	private Map<Producto, Integer> mem = new HashMap<>();
	
	public static SolucionEjercicio3 create(GraphPath<ProblemEjercicio3, EdgeEjercicio3> camino) {
		
		List<Integer> lista = camino.getEdgeList().stream()
				.map(EdgeEjercicio3::action)
				.toList();
		
		return new SolucionEjercicio3(lista);
	}
	
	private SolucionEjercicio3(List<Integer> lista) {
		
		Producto producto = null;
		
		for (int i=0; i<lista.size(); i++) {
			
			if (lista.get(i) > 0) {
				
				Integer e = i;
				producto = DatosEjercicio3.productos.stream().
						filter(p -> Integer.valueOf(p.id()
								.replace("P", "")
								.trim())
								.equals(e + 1))
						.findFirst()
						.get();
				
				mem.put(producto, lista.get(i));
			}
		}
	}
	
	public String toString() {
		
		Double beneficios = (double) mem.entrySet().stream()
				.mapToInt(p->p.getValue() * p.getKey().precio())
				.sum();
		
		List<String> productos = mem.entrySet().stream()
				.map(p->p.toString() + "uds")
				.toList();
		
		return "Productos seleccionados:\n" + productos +
				"\nBeneficios:\n" + beneficios;
	}
}
