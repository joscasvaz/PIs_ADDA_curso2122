package ejercicio2;

import java.util.List; 
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ejercicio2.DatosEjercicio2.Candidato;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio2(Integer index, List<Candidato> elegidos, Integer presupRestante)
implements VirtualVertex<VertexEjercicio2, EdgeEjercicio2, Integer> {
	
	private static Set<String> cualidadesACubrir;
	
	public static void iniDatos(String path) {
		
		VertexEjercicio2.cualidadesACubrir = DatosEjercicio2.cualidadesDeseadas.stream()
				.collect(Collectors.toSet());
	}
	
	private static Integer n = DatosEjercicio2.getNumCand();
	
	public Set<String> getCualidadesACubrir(){ return cualidadesACubrir; }
	
	public static VertexEjercicio2 of(Integer index, List<Candidato> elegidos, Integer presupRestante) {
		return new VertexEjercicio2(index, elegidos, presupRestante);
	}
	
	public static VertexEjercicio2 initialVertex() { return of(0, List2.empty(),
			DatosEjercicio2.getPresup()); }
	
	public static Predicate<VertexEjercicio2> goal(){ return v -> v.index() == n; }
	
	@Override
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

	@Override
	public VertexEjercicio2 neighbor(Integer a) {
		
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

	@Override
	public EdgeEjercicio2 edge(Integer a) {
		
		VertexEjercicio2 v = this.neighbor(a);
		return EdgeEjercicio2.of(this,v,a);
	}
}