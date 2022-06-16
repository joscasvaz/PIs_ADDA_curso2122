package tests;

import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio1.EdgeEjercicio1;
import ejercicio1.HeuristicEjercicio1;
import ejercicio1.VertexEjercicio1;
import ejercicio2.EdgeEjercicio2;
import ejercicio2.HeuristicEjercicio2;
import ejercicio2.VertexEjercicio2;
import ejercicio3.EdgeEjercicio3;
import ejercicio3.HeuristicEjercicio3;
import ejercicio3.VertexEjercicio3;
import ejercicio4.EdgeEjercicio4;
import ejercicio4.HeuristicEjercicio4;
import ejercicio4.VertexEjercicio4;
import us.lsi.common.String2;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class TestDPR {
	
	public static <V,E> void test(EGraph<V,E> graph, int ejercicio) {
		
		
		DynamicProgrammingReduction<V,E> dpr = null;
		Object sol = null;
		
		if(ejercicio == 1) {
			
			dpr = (DynamicProgrammingReduction<V, E>) DynamicProgrammingReduction.of(
					(EGraph<VertexEjercicio1,EdgeEjercicio1>) graph,
					HeuristicEjercicio1::heuristic,
					PDType.Max);
			
		} else if(ejercicio == 2) {
			
			dpr = (DynamicProgrammingReduction<V, E>) DynamicProgrammingReduction.of(
					(EGraph<VertexEjercicio2,EdgeEjercicio2>) graph,
					HeuristicEjercicio2::heuristic,
					PDType.Max);
			
		} else if(ejercicio == 3) {
			
			dpr = (DynamicProgrammingReduction<V, E>) DynamicProgrammingReduction.of(
					(EGraph<VertexEjercicio3,EdgeEjercicio3>) graph,
					HeuristicEjercicio3::heuristic,
					PDType.Max);
			
		} else if(ejercicio == 4) {
			
			dpr = (DynamicProgrammingReduction<V, E>) DynamicProgrammingReduction.of(
					(EGraph<VertexEjercicio4,EdgeEjercicio4>) graph,
					HeuristicEjercicio4::heuristic,
					PDType.Max);
			
		} else { String2.toConsole("ERROR: i no valido."); }
		
		sol = dpr.search();
		
		String2.toConsole("\n==== Algoritmo DPR ====\n");
		
		Tests.tester((Optional<GraphPath<V,E>>) sol, true, ejercicio);
	}
}
