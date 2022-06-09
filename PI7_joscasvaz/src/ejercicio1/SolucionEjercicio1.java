package ejercicio1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicio1.DatosEjercicio1.Archivo;
import ejercicio1.DatosEjercicio1.Memoria;

public class SolucionEjercicio1 {
	
	private Map<Memoria, List<Archivo>> map = new HashMap<>();
	public Integer tam = 0;
	
	public static SolucionEjercicio1 create(ProblemEjercicio1 start, List<Integer> acc) {
		return new SolucionEjercicio1(start, acc);
	}
	
	private SolucionEjercicio1(ProblemEjercicio1 start, List<Integer> acc) {
		
		//ProblemEjercicio1 p = start;
		
		Memoria mem = null;
		Archivo arch = null;
		
		for (int i = 0; i < acc.size(); i++) {
			
			Integer accion = acc.get(i);
			
			if (accion < DatosEjercicio1.getNumMem()) {
				
				Integer idMemoria = acc.get(i) + 1;
				
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
					
					List<Archivo> aux = new ArrayList<>();
					aux.add(arch);
					map.put(mem, aux);
					tam++;
				}
			}
			
			//p = p.neighbor(accion);
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
