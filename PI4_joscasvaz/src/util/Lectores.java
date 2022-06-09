package util;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Lectores {
	
	public static Graph<Investigador, Articulo> lectorEj1(String file) {
		
		return GraphsReader.newGraph(
				file,
				Investigador::ofFormat,
				Articulo::ofFormat,
				Graphs2::simpleWeightedGraph,
				Articulo::getPeso);
	}
	
	public static Graph<String, DefaultEdge> lectorEj2(String file) {
		
		Graph<String, DefaultEdge> res = new SimpleDirectedGraph<>(String::new, DefaultEdge::new, false);
		
		for(String[] s:Files2.streamFromFile(file)
				.dropWhile(s->!s.matches("L\\d{1,2},L\\d{1,2}"))
				.map(s->s.split(","))
				.toList()) {
			
			res.addVertex(s[0]); res.addVertex(s[1]);
			res.addEdge(s[0], s[1]);
		}
		
		return res;
	}
	
	public static List<List<String>> lectorEj2TestsApartado2(String file){
		
		return Files2.linesFromFile(file).stream()
				.map(s->List.of(s.substring(8).replace("]", "").split(",")))
				.toList();
	}
	
	public static Graph<Cruce, Calle> lectorEj3Tiempo(String file) {
		
		return GraphsReader.newGraph(
				file,
				Cruce::ofFormat,
				Calle::ofFormat,
				Graphs2::simpleWeightedGraph,
				Calle::getT);
	}
	
	public static Graph<Cruce, Calle> lectorEj3Esfuerzo(String file) {
		
		return GraphsReader.newGraph(
				file,
				Cruce::ofFormat,
				Calle::ofFormat,
				Graphs2::simpleWeightedGraph,
				Calle::getE);
	}
	
}
