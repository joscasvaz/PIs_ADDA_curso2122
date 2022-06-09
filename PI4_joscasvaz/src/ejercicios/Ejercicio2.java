package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

public class Ejercicio2 {

	//TODO
	
	public static Set<String> apartado1(Graph<String,DefaultEdge> grafo){
		
		return grafo.vertexSet().stream()
				.filter(l->grafo.inDegreeOf(l) == 0)
				.collect(Collectors.toSet());
	}
	
	public static Boolean apartado2(Graph<String,DefaultEdge> grafo, List<String> libros) {
		
		Boolean res = false;
		
		DijkstraShortestPath<String,DefaultEdge> dijkstra = new DijkstraShortestPath<>(grafo);
		GraphPath<String,DefaultEdge> camino = null;
		
		for(int i = 0; i < libros.size() - 1; i++) {
			
			camino = dijkstra.getPath(libros.get(i), libros.get(i + 1));
			res = camino != null;
		}
		
		return res;
	}
	
	public static List<String> apartado3(Graph<String,DefaultEdge> grafo, String libro){
		
		List<String> res = new ArrayList<>();
		
		DijkstraShortestPath<String,DefaultEdge> dijkstra = new DijkstraShortestPath<>(grafo);
		TopologicalOrderIterator<String,DefaultEdge> itOrdenTopologico = new TopologicalOrderIterator<>(grafo);
		
		Set<String> aux = grafo.vertexSet().stream()
				.filter(l-> !l.equals(libro) &&
						dijkstra.getPath(l, libro) != null)
				.collect(Collectors.toSet());
		
		itOrdenTopologico.forEachRemaining(l->res.add(l));
		
		grafo.vertexSet().stream()
		.filter(l-> !aux.contains(l))
		.forEach(l-> res.remove(l));
		
		return res;
	}
	
}
