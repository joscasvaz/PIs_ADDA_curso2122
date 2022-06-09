package ejercicios;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import us.lsi.common.Trio;
import us.lsi.graphs.views.SubGraphView;
import util.Articulo;
import util.Investigador;

public class Ejercicio1 {
	
	public static SubGraphView<Investigador,Articulo,Graph<Investigador,Articulo>> apartado1(Graph<Investigador,Articulo> grafo){
		
		return SubGraphView.of(grafo,
				i -> i.getBirth() < 1982 || grafo.edgesOf(i).stream().anyMatch(a -> 5 < a.getPeso()),
				a -> 5 < a.getPeso());
	}
	
	public static Set<Investigador> apartado2(Graph<Investigador,Articulo> grafo){
		
		Function<Investigador,Integer> f = i -> grafo.degreeOf(i);
		Comparator<Investigador> cmp = Comparator.comparing(f).reversed();
		
		return grafo.vertexSet().stream()
				.sorted(cmp)
				.limit(5)
				.collect(Collectors.toSet());
	}
	
	public static Map<Investigador, List<Investigador>> apartado3(Graph<Investigador,Articulo> grafo){
		
		//TODO: solucionar fallo
		
		Map<Investigador, List<Investigador>> mem = new HashMap<>();
		
		grafo.vertexSet().stream()
		.forEach(i->mem.put(i, grafo.edgesOf(i).stream()
				.sorted(Comparator.comparing(Articulo::getPeso).reversed())
				.map(a->grafo.getEdgeTarget(a)).toList()));
		
		return mem;
	}
	
	public static Trio<Investigador, Investigador, GraphPath<Investigador, Articulo>> apartado4(Graph<Investigador,Articulo> grafo){
		
		//TODO: cambiar peso = 1
		/*Graph<Investigador,Articulo> grafoSinPeso = grafo;
		
		for(Articulo a:grafo.edgeSet()) {
			a.setPeso(1.0);
			grafoSinPeso.setEdgeWeight(grafo.getEdgeSource(a), grafo.getEdgeTarget(a), 1.0);
		}
		System.out.println(grafoSinPeso);*/
		
		Trio<Investigador, Investigador, GraphPath<Investigador, Articulo>> res = Trio.of(null, null, null);
		
		DijkstraShortestPath<Investigador, Articulo> dijkstra = new DijkstraShortestPath<>(grafo);
		GraphPath<Investigador, Articulo> max_camino = null;
		int max_len = 0;
		
		for(Investigador i:grafo.vertexSet()) {
			
			for(Investigador j:grafo.vertexSet()) {
				
				GraphPath<Investigador, Articulo> camino = dijkstra.getPath(i, j);
				int len = camino.getLength();
				
				if(max_len < len) {
					
					max_len = len;
					max_camino = camino;
					
					res = Trio.of(i, j, max_camino);
				}
			}
		}
		
		return res;
	}
	
	public static List<Set<Investigador>> apartado5(Graph<Investigador,Articulo> grafo){
		
		//TODO: solucionar fallo
		
		/*Graph<Investigador,Articulo> grafoAux = new SimpleWeightedGraph<Investigador,Articulo>(null, null);
		Graphs.addGraph(grafo, grafoAux);
		
		for(Investigador i:grafoAux.vertexSet()) {
			
			for(Investigador j:grafoAux.vertexSet()) {
				
				if(!i.equals(j) &&
						grafoAux.getEdge(i, j) == null &&
						!i.getCity().equals(j.getCity())) {
					
					grafoAux.addEdge(i, j, Articulo.ofWeight(i, j, 1.0));
				}
			}
		}*/
		
		GreedyColoring<Investigador,Articulo> greedyColoring = new GreedyColoring<>(grafo);
		
		return greedyColoring.getColoring().getColorClasses().stream()
				.filter(s->1 < s.size())
				.toList();
	}
	
}
