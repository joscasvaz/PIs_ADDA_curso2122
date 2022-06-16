package tests;

import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio1.*;
import ejercicio2.*;
import ejercicio3.*;
import ejercicio4.*;
import us.lsi.common.String2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.virtual.EGraph;

public class TestAStar {
	
	public static <V,E> void test(EGraph<V,E> graph, int ejercicio) {
		
		AStar<V,E> astar = null;
		Object sol = null;
		
		if(ejercicio == 1) {
			
			astar = (AStar<V, E>) AStar.of(
					(EGraph<VertexEjercicio1,EdgeEjercicio1>) graph,
					HeuristicEjercicio1::heuristic,
					AStarType.Max);
			
		} else if(ejercicio == 2) {
			
			astar = (AStar<V, E>) AStar.of(
					(EGraph<VertexEjercicio2,EdgeEjercicio2>) graph,
					HeuristicEjercicio2::heuristic,
					AStarType.Max);
			
		} else if(ejercicio == 3) {
			
			astar = (AStar<V, E>) AStar.of(
					(EGraph<VertexEjercicio3,EdgeEjercicio3>) graph,
					HeuristicEjercicio3::heuristic,
					AStarType.Max);
			
		} else if(ejercicio == 4) {
			
			astar = (AStar<V, E>) AStar.of(
					(EGraph<VertexEjercicio4,EdgeEjercicio4>) graph,
					HeuristicEjercicio4::heuristic,
					AStarType.Max);
			
		} else { String2.toConsole("ERROR: i no valido."); }
		
		sol = astar.search();
		
		String2.toConsole("==== Algoritmo A* ====\n");
		
		Tests.tester((Optional<GraphPath<V,E>>) sol, true, ejercicio);
	}
}
