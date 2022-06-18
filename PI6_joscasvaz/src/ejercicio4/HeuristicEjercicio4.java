package ejercicio4;

import java.util.function.Predicate;

public class HeuristicEjercicio4 {
	
	public static Double heuristic(VertexEjercicio4 v1, Predicate<VertexEjercicio4> goal,
			VertexEjercicio4 v2) {
		
		Double res = .0;
		
		if(!goal.test(v1)) {
			
			res = 1.0 * v1.capRestante().stream()
					.filter(c-> c == 0)
					.count();
		}
		
		return res;
	}
	
}
