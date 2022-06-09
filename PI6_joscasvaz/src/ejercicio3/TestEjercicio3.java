package ejercicio3;

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

public class TestEjercicio3 {
	
	public static void main(String[] args) {

		for (int i = 1; i <= 2; i++) {
			
			final String SEPARADOR = "\n=======================\n";
			String path = "./ficheros/PI6Ej3DatosEntrada" + i + ".txt";
			
			String2.toConsole("%s%s%s", SEPARADOR, path.replace("./ficheros/", ""), SEPARADOR);
			VertexEjercicio3.iniDatos(path);
			
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
				String2.toConsole("%s", SolucionEjercicio3.create(
						(GraphPath<VertexEjercicio3, EdgeEjercicio3>) x)); }
			else { String2.toConsole("%s", x); }
			
		} else {
			
			String2.toConsole("****************");
			DatosEjercicio3.toConsole();
			String2.toConsole("No hay solucion.\n");
		}
	}

	private static void testAStar() {

		EGraph<VertexEjercicio3, EdgeEjercicio3> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio3.initialVertex(),VertexEjercicio3.goal(),
						EdgeEjercicio3::weight);
		
		AStar<VertexEjercicio3, EdgeEjercicio3> astar = AStar.of(grafo, HeuristicEjercicio3::heuristic,
				AStarType.Max);
		
		Optional<GraphPath<VertexEjercicio3, EdgeEjercicio3>> sol = astar.search();

		String2.toConsole("==== Algoritmo A* ====\n\n%s");
		
		tester(sol, true);
	}
	
	private static void testBT() {
		
		EGraph<VertexEjercicio3, EdgeEjercicio3> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio3.initialVertex(), VertexEjercicio3.goal(),
						EdgeEjercicio3::weight);
		
		BackTracking<VertexEjercicio3, EdgeEjercicio3, SolucionEjercicio3> bt =
				BackTracking.of(grafo, HeuristicEjercicio3::heuristic, SolucionEjercicio3::create,
						BTType.Max);
		
		bt.search();
		Optional<SolucionEjercicio3> sol = bt.getSolution();
		
		String2.toConsole("\n==== Algoritmo BT ====\n\n%s");
		
		tester(sol, false);
	}
	
	private static void testDPR() {
			
		EGraph<VertexEjercicio3, EdgeEjercicio3> grafo =
				SimpleVirtualGraph.sum(VertexEjercicio3.initialVertex(), VertexEjercicio3.goal(),
						EdgeEjercicio3::weight);
		
		DynamicProgrammingReduction<VertexEjercicio3, EdgeEjercicio3> dpr =
				DynamicProgrammingReduction.of(grafo, HeuristicEjercicio3::heuristic, PDType.Max);
		
		Optional<GraphPath<VertexEjercicio3, EdgeEjercicio3>> sol = dpr.search();
		
		String2.toConsole("\n==== Algoritmo DPR ====\n\n%s");
		
		tester(sol, true);
	}
	
}
