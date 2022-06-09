package ejercicio2;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;

import ejercicio2.DatosEjercicio2.Candidato;

public class SolucionEjercicio2 {
	
	public static List<Candidato> seleccionados = new ArrayList<>();
	public static Double valor;
	public static Double salarioTotal;
	
	public static SolucionEjercicio2 create(GraphPath<ProblemEjercicio2, EdgeEjercicio2> camino) {
		
		List<Integer> lista = camino.getEdgeList().stream()
				.map(EdgeEjercicio2::action)
				.toList();
		
		return new SolucionEjercicio2(lista);
	}
	
	public static SolucionEjercicio2 create(ProblemEjercicio2 start, List<Integer> acc) {
		return new SolucionEjercicio2(acc); //TODO review
	}
	
	private SolucionEjercicio2(List<Integer> lista) {
		
		Candidato candidato = null;
		
		for (int i = 0; i < lista.size(); i++) {
			
			if (lista.get(i) > 0) {
				
				Integer idCandidato = i + 1;
				
				candidato = DatosEjercicio2.candidatos.stream()
						.filter(c -> Integer.valueOf(c.id()
								.replace("C", "")
								.trim())
								.equals(idCandidato))
						.findFirst()
						.get();
				
				seleccionados.add(candidato);
			}
		}
	}
	
	public String toString() {

		String res = "Seleccion:\n";
		
		for (int i = 0; i < seleccionados.size(); i++) { res += seleccionados.get(i) + "\n"; }
		
		salarioTotal = seleccionados.stream()
				.mapToDouble(Candidato::sueldoMin)
				.sum();
		
		valor = (double) seleccionados.stream()
				.mapToInt(Candidato::valor)
				.sum();
		
		return res +
				"\nValor total: " + valor +
				"\nSalario total: " + salarioTotal;
	}
}
