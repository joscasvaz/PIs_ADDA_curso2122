package soluciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio3;
import datos.DatosEjercicio3.Producto;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio3 {
	
private Map<Producto, Integer> map = new HashMap<>();
	
	public static SolucionEjercicio3 create(List<Integer> value) {
		return new SolucionEjercicio3(value);
	}
	
	public static SolucionEjercicio3 create(GurobiSolution gs) {
		return new SolucionEjercicio3(gs.objVal, gs.values);
	}
	
	private SolucionEjercicio3(Double vo, Map<String, Double> variables) {
		
		Producto producto = null;
		
		for (Map.Entry<String, Double> par: variables.entrySet()) {
			
			if (par.getValue()>0 && par.getKey().startsWith("x")) {
				
				Integer productoId = Integer.valueOf(par.getKey().substring(2)) + 1;
				String id = String.format("%02d", productoId);
				
				producto = DatosEjercicio3.productos.stream()
						.filter(p -> p.id().replace("P", "").trim().equals(id))
						.findFirst()
						.get();
				
				map.put(producto, par.getValue().intValue());
			}
		}
	}
	
	private SolucionEjercicio3(List<Integer> ls) {
		
		Producto producto = null;
		
		for (int i=0; i<ls.size(); i++) {
			
			if (ls.get(i)>0) {
				
				Integer e = i;
				producto = DatosEjercicio3.productos.stream()
						.filter(p -> Integer.valueOf(p.id().replace("P", "").trim())
								.equals(e + 1))
						.findFirst()
						.get();
				
				map.put(producto, ls.get(i));
			}
		}
	}
	
	public String toString() {

		String finalToString = "Productos Seleccionados:\n";
		Double beneficio = 0.;
		
		for (Map.Entry<Producto, Integer> par: map.entrySet()) {
			
			finalToString += par.getKey() + ": " + par.getValue() + " unidades" + "\n";
			Integer uds = par.getValue();
			beneficio += uds * par.getKey().precio();
		}
		
		finalToString += "\nBeneficio: " + String.format("%.1f", beneficio);
		
		return  finalToString;
	}

}
