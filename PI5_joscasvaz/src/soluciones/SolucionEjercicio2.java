package soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio2;
import datos.DatosEjercicio2.Candidato;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio2 {
	
private List<Candidato> seleccionados = new ArrayList<>();
	
	public static SolucionEjercicio2 create(List<Integer> value) {
		return new SolucionEjercicio2(value);
	}
	
	public static SolucionEjercicio2 create(GurobiSolution gs) {
		return new SolucionEjercicio2(gs.objVal, gs.values);
	}
	
	private SolucionEjercicio2(Double vo, Map<String, Double> variables) {
		
		Candidato candidato = null;
		
		for (Map.Entry<String, Double> par: variables.entrySet()) {
			
			if (par.getValue()>0 && par.getKey().startsWith("x")) {
				
				Integer candidatoId = Integer.valueOf(par.getKey().substring(2)) + 1;
				
				String id = String.format("%02d",  candidatoId);
				
				candidato = DatosEjercicio2.candidatos.stream()
						.filter(c -> c.id()
								.replace("C", "")
								.trim()
								.equals(id))
						.findFirst()
						.get();
				
				seleccionados.add(candidato);
			}
		}
	}
	
	private SolucionEjercicio2(List<Integer> ls) {
		
		Candidato candidato = null;
		
		for (int i=0; i<ls.size(); i++) {
			
			if (ls.get(i)>0) {
				
				Integer e = i;
				candidato = DatosEjercicio2.candidatos.stream().filter(c -> Integer.valueOf(c.id().replace("C", "").trim()).equals(e + 1)).findFirst().get();
				
				seleccionados.add(candidato);
			}
		}
	}
	
	public String toString() {

		String finalToString = "Candidatos Seleccionados:\n";
		
		for (int i=0; i<seleccionados.size(); i++)
			finalToString = finalToString + seleccionados.get(i) + "\n";
		
		Double valoracionTotal = Double.valueOf(seleccionados.stream().map(c -> c.valoracion()).reduce((c1, c2) -> c1 + c2).get());
		finalToString += "\nValoración total: " + String.format("%.1f", valoracionTotal);
		Double gasto = seleccionados.stream().mapToDouble(c -> c.salario()).sum();
		finalToString += "; Gasto: " + String.format("%.1f", gasto);
		
		Double media = valoracionTotal/seleccionados.stream().count();
		
		finalToString += "; V. Media: " + String.format("%.1f", media);
		return finalToString;
	}

}
