package ejercicio2;

import java.util.function.Predicate;

import ejercicio2.DatosEjercicio2.Candidato;

public class HeuristicEjercicio2 {
	
	public static Double heuristic(ProblemEjercicio2 v1, Predicate<ProblemEjercicio2> goal,
			ProblemEjercicio2 v2) {
		
		if (v1.getCualidadesACubrir().isEmpty()) { return 0.; }
		else {
			return (double) DatosEjercicio2.candidatos.stream()
					.mapToInt(Candidato::valor)
					.sum();
		}
	}
	
	public static Double cota(ProblemEjercicio2 v, Integer a) {
		//TODO revisar
		ProblemEjercicio2 auxV = v.neighbor(a);
		return a * DatosEjercicio2.valorCand(v.index()).doubleValue() + heuristic(auxV,null,null);
	}
}