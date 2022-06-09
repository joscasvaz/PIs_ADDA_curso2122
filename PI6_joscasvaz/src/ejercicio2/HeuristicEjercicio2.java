package ejercicio2;

import java.util.function.Predicate;

import ejercicio2.DatosEjercicio2.Candidato;

public class HeuristicEjercicio2 {
	
	public static Double heuristic(VertexEjercicio2 v1, Predicate<VertexEjercicio2> goal,
			VertexEjercicio2 v2) {
		
		if (v1.getCualidadesACubrir().isEmpty()) { return 0.; }
		else {
			return (double) DatosEjercicio2.candidatos.stream()
					.mapToInt(Candidato::valor)
					.sum();
		}
	}
}
