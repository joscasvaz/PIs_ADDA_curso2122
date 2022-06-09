package ejercicio2;

import java.util.List;
import java.util.Set;

import ejercicio2.DatosEjercicio2.Candidato;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public record ProblemEjercicio2(Integer index, List<Candidato> elegidos, Integer presupRestante){
	
	private static Set<String> cualidadesACubrir;
	
	private static Integer n = DatosEjercicio2.getNumCand();
	
	public Set<String> getCualidadesACubrir(){ return cualidadesACubrir; }
	
	public static ProblemEjercicio2 of(Integer index, List<Candidato> elegidos, Integer presupRestante) {
		return new ProblemEjercicio2(index, elegidos, presupRestante);
	}
	
	public List<Integer> actions() {
		
		if (index == n) { return List2.empty(); }
		
		if (DatosEjercicio2.sueldoMin(index) <= presupRestante) {
			
			Boolean comp = elegidos.stream()
					.allMatch(c->DatosEjercicio2.esComp(index,
							DatosEjercicio2.candidatos.indexOf(c)));
			
			if (comp) { return List2.of(0, 1); }
			else { return List2.of(0); }
			
		} else { return List2.of(0); }
	}

	public ProblemEjercicio2 neighbor(Integer a) {
		
		Integer auxPresupuesto = presupRestante;
		List<Candidato> auxElegidos = List2.copy(elegidos);
		Set<String> auxCualidades = Set2.copy(cualidadesACubrir);
		
		if (a == 1) {
			
			Candidato elegido = DatosEjercicio2.candidatos.get(index);
			
			auxElegidos.add(elegido);
			auxCualidades.removeAll(elegido.cualidades());
			auxPresupuesto = (int) (auxPresupuesto - elegido.sueldoMin());
		}
		
		return of(index + 1, auxElegidos, auxPresupuesto);
	}

}