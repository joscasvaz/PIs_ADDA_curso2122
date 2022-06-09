package ejercicios;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.tour.HeldKarpTSP;

import util.Calle;
import util.Cruce;

public class Ejercicio3 {

	public static GraphPath<Cruce, Calle> apartado1(Graph<Cruce, Calle> grafo, String m1, String m2){
		
		FloydWarshallShortestPaths<Cruce, Calle> floydWarshall = new FloydWarshallShortestPaths<>(grafo);
		
		Cruce i1 = null;
		Cruce i2 = null;
		
		Iterator<Cruce> it = grafo.vertexSet().iterator();
		
		while(it.hasNext() && (i1 == null || i2 == null)) {
			
			Cruce i = it.next();
			
			if(i.getM().equals(m1)) { i1 = i; }
			if(i.getM().equals(m2)) { i2 = i; }
		}
		
		return floydWarshall.getPath(i1, i2);
	}
	
	public static GraphPath<Cruce, Calle> apartado2(Graph<Cruce, Calle> grafo){
		
		//TODO: solucionar coloreado edges
		
		HeldKarpTSP<Cruce, Calle> heldKarp = new HeldKarpTSP<>();
		
		return heldKarp.getTour(grafo);
	}
	
	public static GraphPath<Cruce, Calle> apartado3(Graph<Cruce, Calle> grafo, Set<Calle> cortadas){
		
		//TODO
		
		return null;
	}
	
}
