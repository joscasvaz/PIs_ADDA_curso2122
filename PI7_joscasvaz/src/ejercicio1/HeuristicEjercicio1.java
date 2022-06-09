package ejercicio1;

import java.util.function.Predicate;

public class HeuristicEjercicio1 {
	
	public static Double heuristic(ProblemEjercicio1 v1, Predicate<ProblemEjercicio1> goal,
			ProblemEjercicio1 v2) { return (double) (DatosEjercicio1.getNumArch() - v1.index()); }
	
	public static Double cota(ProblemEjercicio1 v, Integer a) {
		//TODO revisar
		ProblemEjercicio1 auxV = v.neighbor(a);
		return a * DatosEjercicio1.tamArch(v.index()).doubleValue() + heuristic(auxV, null, null);
	}
}