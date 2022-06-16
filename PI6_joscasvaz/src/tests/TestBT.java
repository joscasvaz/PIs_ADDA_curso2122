package tests;

import java.util.Optional;

import ejercicio1.*;
import ejercicio2.*;
import ejercicio3.*;
import ejercicio4.*;
import us.lsi.common.String2;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.virtual.EGraph;

public class TestBT {
	
	public static <V,E,S> void test(EGraph<V,E> graph, int ejercicio) {
		
		BackTracking<V,E,S> bt = null;
		Object sol = null;
		
		if(ejercicio == 1) {
			
			bt = (BackTracking<V,E,S>) BackTracking.of(
					(EGraph<VertexEjercicio1,EdgeEjercicio1>) graph,
					HeuristicEjercicio1::heuristic,
					SolucionEjercicio1::create,
					BTType.Max);
			
		} else if(ejercicio == 2) {
			
			bt = (BackTracking<V,E,S>) BackTracking.of(
					(EGraph<VertexEjercicio2,EdgeEjercicio2>)graph,
					HeuristicEjercicio2::heuristic,
					SolucionEjercicio2::create,
					BTType.Max);
			
		} else if(ejercicio == 3) {
			
			bt = (BackTracking<V,E,S>) BackTracking.of(
					(EGraph<VertexEjercicio3,EdgeEjercicio3>)graph,
					HeuristicEjercicio3::heuristic,
					SolucionEjercicio3::create,
					BTType.Max);
			
		} else if(ejercicio == 4) {
			
			bt = (BackTracking<V,E,S>) BackTracking.of(
					(EGraph<VertexEjercicio4,EdgeEjercicio4>)graph,
					HeuristicEjercicio4::heuristic,
					SolucionEjercicio4::create,
					BTType.Max);
			
		} else { String2.toConsole("ERROR: i no valido."); }
		
		bt.search();
		sol = bt.getSolution();
		
		String2.toConsole("\n==== Algoritmo BT ====\n");
		
		Tests.tester((Optional<S>) sol, false, 1);
	}
}
