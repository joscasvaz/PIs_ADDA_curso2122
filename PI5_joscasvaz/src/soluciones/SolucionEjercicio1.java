package soluciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio1;
import datos.DatosEjercicio1.Archivo;
import datos.DatosEjercicio1.Memoria;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio1 {
	
	private Map<Memoria, List<Archivo>> map = new HashMap<Memoria, List<Archivo>>();
	private Integer tam = 0;
	
	public static SolucionEjercicio1 create(List<Integer> value) { return new SolucionEjercicio1(value); }
	
	public static SolucionEjercicio1 create(GurobiSolution gs) {
		return new SolucionEjercicio1(gs.objVal, gs.values);
	}
	
	private SolucionEjercicio1(Double vo, Map<String, Double> variables) {
		
		Archivo fichero = null;
		Memoria memoria = null;
		
		for (Map.Entry<String, Double> par: variables.entrySet()) {
			
			if (par.getValue()>0 && par.getKey().startsWith("x")) {
				
				Integer ficheroId = Integer.valueOf(par.getKey().substring(2, par.getKey()
								.indexOf("_", 2))) + 1;
				
				String id = String.format("%02d",  ficheroId);
				
				if (par.getKey().contains("99")) {
					
					String id2 = String.format("%03d",
							Integer.valueOf(par.getKey().substring(2, par.getKey()
									.indexOf("_", 2))));
					
					fichero = DatosEjercicio1.archivos.stream()
							.filter(f -> f.id()
									.replace("F", "")
									.trim()
									.equals(id2 + 1))
							.findFirst()
							.get();
				
				} else {
					
					fichero = DatosEjercicio1.archivos.stream()
							.filter(f -> f.id()
									.replace("F", "")
									.trim()
									.equals(id))
							.findFirst()
							.get();
				}
				
				Integer mem = Integer.parseInt(par.getKey().substring(4).replace("_", "").trim());
				
				memoria = DatosEjercicio1.memorias.stream()
						.filter(m -> Integer.valueOf(m.id()
								.replace("MEM", "")
								.trim())
								.equals(mem + 1))
						.findFirst()
						.get();
				
				if (map.containsKey(memoria)) {
					
					map.get(memoria).add(fichero);
					tam++;
					
				} else {
					
					List<Archivo> ls = new ArrayList<>();
					ls.add(fichero);
					map.put(memoria, ls);
					tam++;
				}
			}
		}
	}
	
	private SolucionEjercicio1(List<Integer> ls) {
		
		Memoria memoria = null;
		Archivo archivo = null;
		
		for (int i=0; i<ls.size(); i++) {
			
			if (ls.get(i)>0) {
				
				Integer idMemoria = ls.get(i);
				
				memoria = DatosEjercicio1.memorias.stream()
						.filter(m -> Integer.valueOf(m.id()
								.replace("MEM", "")
								.trim())
								.equals(idMemoria))
						.findFirst()
						.get();
				
				archivo = DatosEjercicio1.archivos.get(i);
				
				if (map.containsKey(memoria)) {
					
					map.get(memoria).add(archivo);
					tam++;
					
				} else {
					
					List<Archivo> lsAux = new ArrayList<>();
					lsAux.add(archivo);
					map.put(memoria, lsAux);
					tam++;
				}
			}
		}
	}
	
	public String toString() {

		List<String> ls = new ArrayList<>();
		
		for (Map.Entry<Memoria, List<Archivo>> par: map.entrySet()) {
			ls.add(par.getKey() + ": " + par.getValue());
		}
		
		String finalToString = "Reparto obtenido:\n";
		
		for (int i=0; i<ls.size(); i++) { finalToString = finalToString + ls.get(i) + "\n"; }
		
		finalToString += "\nNº de archivos: " + tam.toString();
		
		return  finalToString;
	}

}
