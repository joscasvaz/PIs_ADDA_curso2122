package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;

import ejercicio1.DatosEjercicio1.Archivo;
import ejercicio1.DatosEjercicio1.Memoria;
import us.lsi.common.List2;

public class SolucionEjercicio1 {
	
	private Map<Memoria, List<Archivo>> map = new HashMap<>();
	private Integer tam = 0;
	
	public static SolucionEjercicio1 create(GraphPath<VertexEjercicio1, EdgeEjercicio1> camino) {
		
		List<Integer> lista = camino.getEdgeList().stream()
				.map(EdgeEjercicio1::action)
				.toList();
		
		return new SolucionEjercicio1(lista);
	}
	
	private SolucionEjercicio1(List<Integer> lista) {
		
		Memoria mem = null;
		Archivo arch = null;
		
		for (int i = 0; i < lista.size(); i++) {
			
			if (lista.get(i) != DatosEjercicio1.getNumMem()) {
				
				Integer idMemoria = lista.get(i) + 1;
				
				mem = DatosEjercicio1.memorias.stream()
						.filter(m ->Integer.valueOf(m.id()
								.replace("MEM", "")
								.trim())
								.equals(idMemoria))
						.findFirst()
						.get();
				
				arch = DatosEjercicio1.archivos.get(i);
				
				if (map.containsKey(mem)) {
					
					map.get(mem).add(arch);
					tam++;
					
				} else {
					
					map.put(mem, List2.of(arch));
					tam++;
				}
			}
		}
	}
	
	public String toString() {

		List<String> reparto = map.entrySet().stream()
				.map(p->String.format("%s : cap=%s, tamMax=%s : %s",
					p.getKey(), p.getKey().cap(), p.getKey().tamMax(), p.getValue()))
				.toList();
		
		String res = "Distribucion optima:\n";
		
		for (int i = 0; i < reparto.size(); i++) { res += reparto.get(i) + "\n"; }
		
		return  res + "\nSe guardaron " + tam + " archivos";
	}
}
