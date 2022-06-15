package tests;

import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio1.*;
import ejercicio2.*;
import ejercicio3.*;
import ejercicio4.*;
import us.lsi.common.String2;

public class Tests {
	
	public static void main(String[] args) {
		
	}
	
	private final String SEPARADOR = "\n=======================\n";
	
	private static String path(int i, int j) { return String.format(
			"./ficheros/PI6Ej%dDatosEntrada%d.txt", i, j); }
	
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
					
				} else { String2.toConsole("ERROR: i no valido."); }
				
			} else { String2.toConsole("%s", x); }
			
		} else {
			
			String2.toConsole("****************");
			
			if(ejercicio == 1) { DatosEjercicio1.toConsole(); }
			else if(ejercicio == 2) { DatosEjercicio2.toConsole(); }
			else if(ejercicio == 3) { DatosEjercicio3.toConsole(); }
			else if(ejercicio == 4) { DatosEjercicio4.toConsole(); }
			else { String2.toConsole("ERROR: i no valido."); }
			
			String2.toConsole("No hay solucion.\n");
		}
	}
	
}
