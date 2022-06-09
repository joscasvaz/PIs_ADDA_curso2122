package soluciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio4;
import datos.DatosEjercicio4.Contenedor;
import datos.DatosEjercicio4.Elemento;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio4 {

	private Map<Contenedor, List<Elemento>> map = new HashMap<Contenedor, List<Elemento>>();
	private Integer tamaño = 0;
	
	public static SolucionEjercicio4 create(List<Integer> value) {
		return new SolucionEjercicio4(value);
	}
	
	public static SolucionEjercicio4 create(GurobiSolution gs) {
		return new SolucionEjercicio4(gs.objVal, gs.values);
	}
	
	private SolucionEjercicio4(Double vo, Map<String, Double> variables) {
		
		Elemento elemento = null;
		Contenedor contenedor = null;
		
		for (Map.Entry<String, Double> par: variables.entrySet()) {
			
			if (par.getValue()>0 && par.getKey().startsWith("x")) {
				
				// Formateamos el String x_i_j para que la i la convierta en una string de tipo id que sea igual al id de los elementos
				// Nota: el metodo indexOf("_", 2) lo usamos para que el id que formatee vaya desde el indice de i hasta que acabe el numero
				// en caso de que i sea un número de dos dígitos (si no lo ponemos nos formateará el 10 como 01)
				
				// Para que la id se corresponda con el elemento correcto
				
				Integer elementoId = Integer.valueOf(par.getKey().substring(2, par.getKey().indexOf("_", 2))) + 1;
				
				String id = String.format("%02d",  elementoId);
				
				if (par.getKey().contains("99")) {
					String id2 = String.format("%03d", Integer.valueOf(par.getKey().substring(2, par.getKey().indexOf("_", 2)))); //para que el 100 no de problemas
					elemento = DatosEjercicio4.elementos.stream().filter(f -> f.id().replace("E", "").trim().equals(id2 + 1)).findFirst().get();
				} else {
					elemento = DatosEjercicio4.elementos.stream().filter(f -> f.id().replace("E", "").trim().equals(id)).findFirst().get();
				}
				
				// Procesamos el valor del contenedor para que se corresponda con el que aparece en el .txt
				
				Integer cont = Integer.parseInt(par.getKey().substring(4).replace("_", "").trim()); 
				contenedor = DatosEjercicio4.contenedores.stream().filter(c -> Integer.valueOf(c.id().replace("CONT", "").trim()).equals(cont + 1)).findFirst().get();
				
				if (map.containsKey(contenedor)) {
					map.get(contenedor).add(elemento);
					tamaño++;
				} else {
					List<Elemento> ls = new ArrayList<>();
					ls.add(elemento);
					map.put(contenedor, ls);
					tamaño++;
				}
			}
		}
	}
	
	private SolucionEjercicio4(List<Integer> ls) {
		
		Contenedor contenedor = null;
		Elemento elemento = null;
		
		for (int i=0; i<ls.size(); i++) {
			
			if (ls.get(i) < DatosEjercicio4.contenedores.size()) {
				
				Integer idCont = ls.get(i);
				
				contenedor = DatosEjercicio4.contenedores.stream().filter(c -> Integer.valueOf(c.id().replace("CONT", "").trim()).equals(idCont + 1)).findFirst().get();
				elemento = DatosEjercicio4.elementos.get(i);
				
				if (map.containsKey(contenedor)) {
					map.get(contenedor).add(elemento);
					tamaño++;
				} else {
					List<Elemento> lsAux = new ArrayList<>();
					lsAux.add(elemento);
					map.put(contenedor, lsAux);
					tamaño++;
				}
			}
		}
	}
	
	public String toString() {

		List<String> ls = new ArrayList<>();
		
		for (Map.Entry<Contenedor, List<Elemento>> par: map.entrySet())
			ls.add(par.getKey() + ": " + par.getValue());
		
		String finalToString = "Reparto obtenido:\n";
		
		for (int i=0; i<ls.size(); i++)
			finalToString = finalToString + ls.get(i) + "\n";
		
		return  finalToString;
	}
}
