package tests;

import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio1.*;
import ejercicio2.*;
import ejercicio3.*;
import ejercicio4.*;
import us.lsi.common.String2;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;

public class Tests {
	
	public static void main(String[] args) {
		
		Tests.execute(1, 1);
		
	}
	
	private final static String SEPARADOR = "\n=======================\n";
	
	private static String path(int ejercicio, int archivo) { return String.format(
			"./ficheros/PI6Ej%dDatosEntrada%d.txt", ejercicio, archivo); }
	
	public static <V,E> void execute(int ejercicio, int archivo) {
		
		String ruta = path(ejercicio, archivo);
		
		EGraph<V,E> graph = null;
		
		if(ejercicio == 1) {
			
			VertexEjercicio1.iniDatos(ruta);
			
			graph = (EGraph<V,E>) SimpleVirtualGraph.sum(VertexEjercicio1.initialVertex(),
					VertexEjercicio1.goal(),
					EdgeEjercicio1::weight);
			
		} else if(ejercicio == 2) {
			
			DatosEjercicio2.iniDatos(ruta);
			VertexEjercicio2.iniDatos(ruta);
			
			graph = (EGraph<V,E>) SimpleVirtualGraph.sum(VertexEjercicio2.initialVertex(),
					VertexEjercicio2.goal(),
					EdgeEjercicio2::weight);
			
		} else if(ejercicio == 3) {
			
			VertexEjercicio3.iniDatos(ruta);
			
			graph = (EGraph<V,E>) SimpleVirtualGraph.sum(VertexEjercicio3.initialVertex(),
					VertexEjercicio3.goal(),
					EdgeEjercicio3::weight);
			
		} /*else if(ejercicio == 4) {
			
			VertexEjercicio4.iniDatos(ruta);
			
			graph = (EGraph<V,E>) SimpleVirtualGraph.sum(VertexEjercicio4.initialVertex(),
					VertexEjercicio4.goal(),
					EdgeEjercicio4::weight);
			
		}*/ else { String2.toConsole("ERROR: numero de ejercicio no valido."); }
		
		String2.toConsole("%s%s%s",
			SEPARADOR, ruta.replace("./ficheros/", ""), SEPARADOR);
		
		TestAStar.test(graph, ejercicio);
		TestBT.test(graph, ejercicio);
		TestDPR.test(graph, ejercicio);
	}
	
	public static <T> void tester(Optional<T> op, Boolean create, int ejercicio) {
		
		T x;
		
		if (op.isPresent()) {
			
			x = op.get();
			
			if(create) {
				
				if(ejercicio == 1) {
					
					String2.toConsole(SolucionEjercicio1.create(
							(GraphPath<VertexEjercicio1, EdgeEjercicio1>) x).toString());
					
				} else if(ejercicio == 2) {
					String2.toConsole(SolucionEjercicio2.create(
							(GraphPath<VertexEjercicio2, EdgeEjercicio2>) x).toString());
					
				} else if(ejercicio == 3) {
					String2.toConsole(SolucionEjercicio3.create(
							(GraphPath<VertexEjercicio3, EdgeEjercicio3>) x).toString());
					
				} else if(ejercicio == 4) {
					String2.toConsole(SolucionEjercicio4.create(
							(GraphPath<VertexEjercicio4, EdgeEjercicio4>) x).toString());
					
				} else { String2.toConsole("ERROR: numero de ejercicio no valido."); }
				
			} else { String2.toConsole("%s", x); }
			
		} else {
			
			String2.toConsole("****************");
			
			if(ejercicio == 1) { DatosEjercicio1.toConsole(); }
			else if(ejercicio == 2) { DatosEjercicio2.toConsole(); }
			else if(ejercicio == 3) { DatosEjercicio3.toConsole(); }
			else if(ejercicio == 4) { DatosEjercicio4.toConsole(); }
			else { String2.toConsole("ERROR: numero de ejercicio no valido."); }
			
			String2.toConsole("No hay solucion.\n");
		}
	}
	
}
