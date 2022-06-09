package ejercicio1;

import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.common.String2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BackTracking;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.AStar.AStarType;
import us.lsi.graphs.alg.BackTracking.BTType;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class TestEjercicio1 {
	
	public static void main(String[] args) {

		for (int i = 1; i <= 2; i++) {
			
			final String SEPARADOR = "\n=======================\n";
			String path = "./ficheros/PI6Ej1DatosEntrada" + i + ".txt";
			
			String2.toConsole("%s%s%s", SEPARADOR, path.replace("./ficheros/", ""), SEPARADOR);
			VertexEjercicio1.iniDatos(path);
			
			testAStar();
			testBT();
			testDPR();
		}
	}
	
	private static <T> void tester(Optional<T> op, Boolean create) {
		
		T x;
		
		if (op.isPresent()) {
			
			x = op.get();
			
			if(create) {
				String2.toConsole("%s", SolucionEjercicio1.create(
						(GraphPath<VertexEjercicio1, EdgeEjercicio1>) x)); }
			else { String2.toConsole("%s", x); }
			
		} else {
			
			String2.toConsole("****************");
			DatosEjercicio1.toConsole();
			String2.toConsole("No hay solucion.\n");
		}
	}

	private static void testAStar() {
		
		EGraph<VertexEjercicio1, EdgeEjercicio1> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio1.initialVertex(),VertexEjercicio1.goal(),
						EdgeEjercicio1::weight);
		
		AStar<VertexEjercicio1, EdgeEjercicio1> astar = AStar.of(grafo, HeuristicEjercicio1::heuristic,
				AStarType.Max);
		
		Optional<GraphPath<VertexEjercicio1, EdgeEjercicio1>> sol = astar.search();
		
		String2.toConsole("==== Algoritmo A* ====\n\n");
		
		tester(sol, true);
	}
	
	private static void testBT() {
		
		EGraph<VertexEjercicio1, EdgeEjercicio1> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio1.initialVertex(), VertexEjercicio1.goal(),
						EdgeEjercicio1::weight);
		
		BackTracking<VertexEjercicio1, EdgeEjercicio1, SolucionEjercicio1> bt =
				BackTracking.of(grafo, HeuristicEjercicio1::heuristic, SolucionEjercicio1::create,
						BTType.Max);
		
		bt.search();
		Optional<SolucionEjercicio1> sol = bt.getSolution();
		
		String2.toConsole("\n==== Algoritmo BT ====\n\n");
		
		tester(sol, false);
	}
	
	private static void testDPR() {
			
		EGraph<VertexEjercicio1, EdgeEjercicio1> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio1.initialVertex(), VertexEjercicio1.goal(),
						EdgeEjercicio1::weight);
		
		DynamicProgrammingReduction<VertexEjercicio1, EdgeEjercicio1> dpr =
				DynamicProgrammingReduction.of(grafo, HeuristicEjercicio1::heuristic, PDType.Max);
		
		Optional<GraphPath<VertexEjercicio1, EdgeEjercicio1>> sol = dpr.search();
		
		String2.toConsole("\n==== Algoritmo DPR ====\n\n");
		
		tester(sol, true);
	}
	
}