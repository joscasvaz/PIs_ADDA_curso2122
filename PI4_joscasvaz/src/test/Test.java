package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Pair;
import us.lsi.common.Trio;
import us.lsi.graphs.views.SubGraphView;
import util.Articulo;
import util.Calle;
import util.Cruce;
import util.Investigador;
import util.Lectores;

public class Test {
	
	private static String ejN(int n) { return String.format("\n##########\nEJERCICIO %d\n##########\n", n); }
	
	private static String rutaEjInumJ(int i, int j){
		
		String s = "./ficheros/PI4E" + i + "_DatosEntrada";
		
		if(0 < j) { s += j; }
		s += ".txt";
		
		return s;
	}
	
	private static String rutaResultadoEjIApartadoJ(int i, int j){
		
		return String.format("./ficheros/resultados/PI4E%d_ResultadoApartado%d.gv", i, j);
	}
	
	public static void testEj1() {
		
		String rutaEj1num0 = rutaEjInumJ(1, 0);
		Graph<Investigador,Articulo> grafoEj1 = Lectores.lectorEj1(rutaEj1num0);
		
		System.out.println(ejN(1));
		System.out.println(rutaEj1num0);
		
		System.out.println("\nLECTURA:\n" + grafoEj1);
		
		System.out.println("\nAPARTADO A");
		
		SubGraphView<Investigador,Articulo,Graph<Investigador,Articulo>> solApartado1 = Ejercicio1.apartado1(grafoEj1);
		
		GraphColors.toDot(grafoEj1, rutaResultadoEjIApartadoJ(1, 1),
				i-> String.format("INV-%d %d", i.getId(), i.getBirth()),
				a-> Integer.valueOf(a.getPeso().intValue()).toString(),
				i->GraphColors.colorIf(Color.blue, solApartado1.vertexSet().contains(i)),
				a->GraphColors.colorIf(Color.blue, solApartado1.edgeSet().contains(a)));
		
		System.out.println("GRAFO SOLUCION APARTADO A EN: " + rutaResultadoEjIApartadoJ(1, 1));
		
		System.out.println("\nAPARTADO B");
		
		Set<Investigador> solApartado2 = Ejercicio1.apartado2(grafoEj1);
		
		GraphColors.toDot(grafoEj1, rutaResultadoEjIApartadoJ(1, 2),
				i-> String.format("INV-%d", i.getId()),
				a-> Integer.valueOf(a.getPeso().intValue()).toString(),
				i->GraphColors.colorIf(Color.blue, Color.green, solApartado2.contains(i)),
				a->GraphColors.color(Color.green));
		
		List<String> l = solApartado2.stream()
				.map(i-> "INV-" + i.getId())
				.toList();
		
		System.out.println("Los 5 investigadores con mayor numero de colaboradores son:\n" + l);
		System.out.println("GRAFO SOLUCION APARTADO B EN: " + rutaResultadoEjIApartadoJ(1, 2));
		
		System.out.println("\nAPARTADO C");
		
		Map<Investigador,List<Investigador>> solApartado3 = Ejercicio1.apartado3(grafoEj1);
		
		System.out.println("Las listas de colaboradores ordenados por articulos conjuntos para cada investigador son:");
		
		Set<Articulo> maxs = new HashSet<>();
		
		for(Entry<Investigador,List<Investigador>> m:solApartado3.entrySet()) {
			
			String k = "INV-" + m.getKey().getId();
			
			List<String> v = new ArrayList<>();
			
			for(Investigador i:m.getValue()) {
				
				if(m.getValue().indexOf(i) == 0) { maxs.add(grafoEj1.getEdge(m.getKey(), i)); }
				
				v.add("INV-" + i.getId());
			}
			
			System.out.println(k + " -> " + v);
		}
		
		GraphColors.toDot(grafoEj1, rutaResultadoEjIApartadoJ(1, 3),
				i-> String.format("INV-%d", i.getId()),
				a-> Integer.valueOf(a.getPeso().intValue()).toString(),
				i->GraphColors.color(Color.black),
				a->GraphColors.colorIf(Color.green, maxs.contains(a)));
		
		System.out.println("GRAFO SOLUCION APARTADO C EN: " + rutaResultadoEjIApartadoJ(1, 3));
		
		System.out.println("\nAPARTADO D");
		
		Trio<Investigador,Investigador,GraphPath<Investigador,Articulo>> solApartado4 = Ejercicio1.apartado4(grafoEj1);
		
		System.out.println("El par de investigadores mas lejanos es: " + Pair.of("INV-" + solApartado4.first().getId(),
				"INV-" + solApartado4.second().getId()));
		
		
		GraphColors.toDot(grafoEj1, rutaResultadoEjIApartadoJ(1, 4),
				i-> String.format("INV-%d", i.getId()),
				a-> Integer.valueOf(a.getPeso().intValue()).toString(),
				i->GraphColors.colorIf(Color.blue, Color.black, solApartado4.third().getVertexList().contains(i)),
				a->GraphColors.colorIf(Color.blue, Color.black, solApartado4.third().getEdgeList().contains(a)));
		
		System.out.println("GRAFO SOLUCION APARTADO D EN: " + rutaResultadoEjIApartadoJ(1, 4));
		
		System.out.println("\nAPARTADO E");
		
		List<Set<Investigador>> solApartado5 = Ejercicio1.apartado5(grafoEj1);
		System.out.println("Las reuniones serian:\n");
		
		for(Set<Investigador> r:solApartado5) {
			
			String s = "[";
			
			Iterator<Investigador> it = r.iterator();
			
			for(Investigador i:r) {
				
				it.next();
				
				if(it.hasNext()) { s += String.format("INV-%d, ", i.getId()); }
				else { s += String.format("INV-%d]", i.getId());; }
			}
			
			System.out.println(s);
		}
		
		//TODO: implementar grafo apartado e
	}
	
	public static void testEj2() {
		
		String rutaEj2num1 = rutaEjInumJ(2, 1);
		String rutaEj2num2 = rutaEjInumJ(2, 2);
		
		Graph<String,DefaultEdge> grafoEj2 = Lectores.lectorEj2(rutaEj2num1);
		
		System.out.println(ejN(2));
		System.out.println(rutaEj2num1);
		
		System.out.println("\nLECTURA:\n" + grafoEj2);
		
		System.out.println("\nAPARTADO A");
		
		Set<String> solApartado1 = Ejercicio2.apartado1(grafoEj2);
		
		System.out.println("Los libros mas necesarios para continuar con los demas son:\n" + solApartado1);
		
		GraphColors.toDot(grafoEj2, rutaResultadoEjIApartadoJ(2, 1),
				l->l,
				e->"",
				l->GraphColors.colorIf(Color.blue, Color.black, solApartado1.contains(l)),
				e->GraphColors.color(Color.black));
		
		System.out.println("GRAFO SOLUCION APARTADO A EN: " + rutaResultadoEjIApartadoJ(2, 1));
		
		System.out.println("\nAPARTADO B");
		
		List<List<String>> lecturaArchivoTest = Lectores.lectorEj2TestsApartado2(rutaEj2num2);
		
		int i = 0;
		
		while(i < lecturaArchivoTest.size()) {
			
			Boolean solApartado2 = Ejercicio2.apartado2(grafoEj2, lecturaArchivoTest.get(i));
			
			String s = "Para el Test" + (i+1) + " " + lecturaArchivoTest.get(i);
			
			if(!solApartado2) { s += " no"; }
			
			System.out.println(s + " hay solucion.");
			
			i++;
		}
		
		System.out.println("\nAPARTADO C");
		
		List<String> libros = List.of("L3","L9","L7");
		
		for(String libro:libros) {
			
			List<String> solApartado3 = Ejercicio2.apartado3(grafoEj2, libro);
			
			System.out.println("Para " + libro + " previamente hay que leer " + solApartado3);
		}
		
	}
	
	public static void testEj3() {
		
		String rutaEj3num0 = rutaEjInumJ(3, 0);
		Graph<Cruce, Calle> grafoEj3Tiempo = Lectores.lectorEj3Tiempo(rutaEj3num0);
		Graph<Cruce, Calle> grafoEj3Esfuerzo = Lectores.lectorEj3Esfuerzo(rutaEj3num0);
		
		System.out.println(ejN(3));
		System.out.println(rutaEj3num0);
		
		System.out.println("\nLECTURA:\nTIEMPO: " + grafoEj3Tiempo + "\nESFUERZO: " + grafoEj3Esfuerzo);
		
		System.out.println("\nAPARTADO A");
		
		List<Pair<String,String>> testEj3ApartadoA = List.of(Pair.of("m7", "m2"),Pair.of("m4", "m9"));
		
		int i = 1;
		
		for(Pair<String,String> monumentos:testEj3ApartadoA) {
			
			String m1 = monumentos.first();
			String m2 = monumentos.second();
			
			GraphPath<Cruce,Calle> solEj3Apartado1 = Ejercicio3.apartado1(grafoEj3Tiempo, m1, m2);
			
			String rutaResultadoEj3Apartado1TestI = rutaResultadoEjIApartadoJ(3, 1).replace(".gv", String.format("Test%d.gv", i));
			
			GraphColors.toDot(grafoEj3Tiempo, rutaResultadoEj3Apartado1TestI,
					x->"INT-" + x.getId(),
					c->Integer.valueOf(c.getT().intValue()).toString(),
					x->GraphColors.colorIf(Color.blue, Color.black, solEj3Apartado1.getVertexList().contains(x)),
					c->GraphColors.colorIf(Color.blue, Color.black, solEj3Apartado1.getEdgeList().contains(c)));
			
			System.out.println("Camino de menor duracion de " + m1 + " a " + m2 + " en " + rutaResultadoEj3Apartado1TestI);
			
			i++;
		}
		
		System.out.println("\nAPARTADO B");
		
		GraphPath<Cruce,Calle> solEj3Apartado2 = Ejercicio3.apartado2(grafoEj3Esfuerzo);
		
		GraphColors.toDot(grafoEj3Tiempo, rutaResultadoEjIApartadoJ(3, 2),
				x->"INT-" + x.getId(),
				c->Integer.valueOf(c.getT().intValue()).toString(),
				x->GraphColors.colorIf(Color.blue, Color.black, solEj3Apartado2.getVertexList().contains(x)),
				c->GraphColors.colorIf(Color.blue, Color.black, solEj3Apartado2.getEdgeList().contains(c)));
		
		System.out.println("GRAFO SOLUCION APARTADO B EN: " + rutaResultadoEjIApartadoJ(3, 2));
	}
	
	public static void testEj4() {
		//TODO
	}

	public static void main(String[] args) {
		
		testEj1();
		testEj2();
		testEj3();
		testEj4();
		
	}
}
