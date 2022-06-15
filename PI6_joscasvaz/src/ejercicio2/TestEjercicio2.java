package ejercicio2;

import java.util.Optional;

import org.jgrapht.GraphPath;

import tests.Tests;
import us.lsi.common.String2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEjercicio2 {
	
	public static void main(String[] args) {

		for (int i = 1; i <= 2; i++) {
			
			final String SEPARADOR = "\n=======================\n";
			String path = "./ficheros/PI6Ej2DatosEntrada" + i + ".txt";
			
			String2.toConsole("%s%s%s", SEPARADOR, path.replace("./ficheros/", ""),
					SEPARADOR);
			DatosEjercicio2.iniDatos(path);
			VertexEjercicio2.iniDatos(path);
			
			testAStar();
			testBT();
			testDPR();
		}
	}

	private static void testAStar() {

		EGraph<VertexEjercicio2, EdgeEjercicio2> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio2.initialVertex(),
						VertexEjercicio2.goal(),
						EdgeEjercicio2::weight);
		
		AStar<VertexEjercicio2, EdgeEjercicio2> astar = AStar.of(grafo,
				HeuristicEjercicio2::heuristic,
				AStarType.Max);
		
		Optional<GraphPath<VertexEjercicio2, EdgeEjercicio2>> sol = astar.search();

		String2.toConsole("==== Algoritmo A* ====\n\n");
		
		Tests.tester(sol, true, 2);
	}
	
	private static void testBT() {
		
		EGraph<VertexEjercicio2, EdgeEjercicio2> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio2.initialVertex(),
						VertexEjercicio2.goal(),
						EdgeEjercicio2::weight);
		
		BackTracking<VertexEjercicio2, EdgeEjercicio2, SolucionEjercicio2> bt =
				BackTracking.of(grafo, HeuristicEjercicio2::heuristic,
						SolucionEjercicio2::create,
						BTType.Max);
		
		bt.search();
		Optional<SolucionEjercicio2> sol = bt.getSolution();
		
		String2.toConsole("\n==== Algoritmo BT ====\n\n");
		
		Tests.tester(sol, false, 2);
	}
	
	private static void testDPR() {
			
		EGraph<VertexEjercicio2, EdgeEjercicio2> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio2.initialVertex(),
						VertexEjercicio2.goal(),
						EdgeEjercicio2::weight);
		
		DynamicProgrammingReduction<VertexEjercicio2, EdgeEjercicio2> dpr =
				DynamicProgrammingReduction.of(grafo, HeuristicEjercicio2::heuristic,
						PDType.Max);
		
		Optional<GraphPath<VertexEjercicio2, EdgeEjercicio2>> sol = dpr.search();
		
		String2.toConsole("\n==== Algoritmo DPR ====\n\n");
		
		Tests.tester(sol, true, 2);
	}
	
}
