package test;

import util.Lectores;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.common.IntPair;
import us.lsi.common.IntTrio;
import us.lsi.common.IntegerSet;
import us.lsi.common.Matrix;
import us.lsi.common.Pair;

public class Test {
	
	static String ficheroEj1 = "./ficheros/PI2Ej1DatosEntrada.txt";
	
	static String fichero1Ej2 = "./ficheros/PI2Ej2DatosEntrada1.txt";
	static String fichero2Ej2 = "./ficheros/PI2Ej2DatosEntrada2.txt";
	
	static String ficheroEj3 = "./ficheros/PI2Ej3DatosEntrada.txt";
	static String ficheroEj4 = "./ficheros/PI2Ej4DatosEntrada.txt";
	static String ficheroEj5 = "./ficheros/PI2Ej5DatosEntrada.txt";
	
	static String solRec = "Sol. Rec. ";
	static String noFinal = solRec + "No Final:		";
	static String siFinal = solRec + "Final:		";
	static String conMem = solRec + "con memoria:	";
	static String sinMem = solRec + "sin memoria:	";
	static String solIterativa = "Sol. Iterativa:		";
	static String solFuncional = "Sol. Funcional:			";
	
	static String separador1 = "________________________________________________________________";
	static String separador2 = "#################################################################";
	static String ejercicioN(Integer n, String f) { return separador2 + "\nEJERCICIO " + n + "\n" + f + "\n" + separador2; }
	
	public static void testEj1() {
		
		System.out.println(ejercicioN(1, ficheroEj1));
		
		List<IntTrio> lectura = Lectores.lectorEj1yEj5(ficheroEj1);
		Iterator<IntTrio> it = lectura.iterator();
		
		while(it.hasNext()) {
			
			IntTrio t = it.next();
			
			String solucionRecursivaNoFinal = Ejercicio1.versionRecursivaNoFinal(t);
			String solucionRecursivaFinal = Ejercicio1.versionRecursivaFinal(t);
			String solucionIterativa = Ejercicio1.versionIterativa(t);
			String solucionFuncional = Ejercicio1.versionFuncional(t);
			
			System.out.println("(a, b, c) = " + t);
			System.out.println(noFinal + solucionRecursivaNoFinal);
			System.out.println(solIterativa + "	" + solucionIterativa);
			System.out.println(siFinal + solucionRecursivaFinal);
			System.out.println(solFuncional + solucionFuncional);
			
			if(it.hasNext()) { System.out.println(separador1); }
		}
	}
	
	public static void testEj2() {
		
		System.out.println(ejercicioN(2, fichero1Ej2));
		
		Matrix<String> lectura1 = Lectores.lectorEj2(fichero1Ej2);
		Matrix<String> lectura2 = Lectores.lectorEj2(fichero2Ej2);
		
		List<String> solucion1 = Ejercicio2.versionRecursiva(lectura1);
		List<String> solucion2 = Ejercicio2.versionRecursiva(lectura2);
		
		System.out.println("Lectura Fichero 1: " + lectura1);
		System.out.println("\nLista de cadenas obtenida:");
		
		int i = 0;
		while(i < solucion1.size()) { System.out.println((i + 1) + ") " + solucion1.get(i)); i++; }
		
		System.out.println(ejercicioN(2, fichero2Ej2));
		
		System.out.println("Lectura Fichero 2: " + lectura2);
		System.out.println("\nLista de cadenas obtenida:");
		
		i = 0;
		while(i < solucion2.size()) { System.out.println((i + 1) + ") " + solucion2.get(i)); i++; }
	}
	
	public static void testEj3() {
		
		System.out.println(ejercicioN(3, ficheroEj3));
		
		List<Pair<List<Integer>,IntPair>> lectura = Lectores.lectorEj3(ficheroEj3);
		Iterator<Pair<List<Integer>,IntPair>> it = lectura.iterator();
		
		while(it.hasNext()) {
			
			Pair<List<Integer>,IntPair> par = it.next();
			
			List<Integer> lista = par.first();
			IntPair rango = par.second();
			
			IntegerSet solucion = Ejercicio3.versionRecursiva(lista, rango);
			
			System.out.println("Lista: "+ lista + "\nRango: [" + rango.first() + ", " + rango.second() + ")");
			System.out.println("Conjunto obtenido: " + solucion);
			
			if(it.hasNext()) { System.out.println(separador1); }
		}
	}
	
	public static void testEj4() {
		
		System.out.println(ejercicioN(4, ficheroEj4));
		
		List<Integer> lectura = Lectores.lectorEj4(ficheroEj4);
		Iterator<Integer> it = lectura.iterator();
		
		while(it.hasNext()) {
			
			Integer i = it.next();
			
			BigInteger solucionRecursivaConMemoria = Ejercicio4.versionRecursivaConMemoria(i);
			BigInteger solucionRecursivaSinMemoria = Ejercicio4.versionRecursivaSinMemoria(i);
			BigInteger solucionIterativa = Ejercicio4.versionIterativa(i);
			
			System.out.println("Entero de entrada:	" + i);
			System.out.println(conMem + solucionRecursivaConMemoria);
			System.out.println(sinMem + solucionRecursivaSinMemoria);
			System.out.println(solIterativa + solucionIterativa);
			
			if(it.hasNext()) { System.out.println(separador1); }
		}
	}
	
	public static void testEj5() {
		
		System.out.println(ejercicioN(5, ficheroEj5));
		
		List<IntTrio> lectura = Lectores.lectorEj1yEj5(ficheroEj5);
		Iterator<IntTrio> it = lectura.iterator();
		
		while(it.hasNext()) {
			
			IntTrio t = it.next();
			
			Integer solucionRecursivaConMemoria = Ejercicio5.versionRecursivaConMemoria(t);
			Integer solucionRecursivaSinMemoria = Ejercicio5.versionRecursivaSinMemoria(t);
			Integer solucionIterativa = Ejercicio5.versionIterativa(t);
			
			System.out.println("Lectura: " + t);
			System.out.println(conMem + solucionRecursivaConMemoria);
			System.out.println(sinMem + solucionRecursivaSinMemoria);
			System.out.println(solIterativa + solucionIterativa);
			
			if(it.hasNext()) { System.out.println(separador1); }
		}
	}
	
	public static void main(String[] args) {
		
		testEj1();
		testEj2();
		testEj3();
		testEj4();
		testEj5();
		
	}
	
}
