package tests;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import util.Lectores;

public class Test {
	
	static String separador = "##################################################";
	static String fEj1 = "./ficheros/PI1E1_DatosEntrada.txt";
	static String f1Ej2 = "./ficheros/PI1E2_DatosEntrada1.txt";
	static String f2Ej2 = "./ficheros/PI1E2_DatosEntrada2.txt";
	static String fEj3 = "./ficheros/PI1E3_DatosEntrada.txt";
	static String fEj4 = "./ficheros/PI1E4_DatosEntrada.txt";
	
	public static void testEj1() {
		
		List<List<String>> lecturaEj1 = Lectores.lectorEj1y2(3,fEj1);
		Predicate<String> pS = x->x.contains("a") || x.contains("e") || x.contains("o");
		Predicate<Integer> pI = x->x%2==0;
		Function<String,Integer> f = x->x.length();
		Iterator<List<String>> it = lecturaEj1.iterator();
		
		System.out.println(separador);
		System.out.println("#		Ejercicio 1			 #");
		System.out.println("#	"+ fEj1 +"	 #");
		System.out.println(separador + "\n");
		
		while(it.hasNext()) {
			List<String> l = it.next();
			System.out.println("Entrada: " + l);
			
			boolean bIt = Ejercicio1.versionIterativa(l, pS, pI, f);
			boolean bRf = Ejercicio1.versionRecursivaFinal(l, pS, pI, f);
			
			System.out.println("Solución iterativa: " + bIt);
			System.out.println("Solución recursiva final: " + bRf + "\n");
		}
		
	}
	
	public static void testEj2() {
		
		List<List<String>> lecturaEj2f1 = Lectores.lectorEj1y2(0,f1Ej2);
		List<List<String>> lecturaEj2f2 = Lectores.lectorEj1y2(0,f2Ej2);
		
		System.out.println(separador);
		System.out.println("#		Ejercicio 2			 #");
		System.out.println("#	"+ f1Ej2 +"	 #");
		System.out.println(separador + "\n");
		
		System.out.println("Entrada: " + lecturaEj2f1);
		System.out.println("Solución iterativa: " + Ejercicio2.versionIterativa(lecturaEj2f1));
		System.out.println("Solución recursiva final: " + Ejercicio2.versionRecursivaFinal(lecturaEj2f1) + "\n");
		
		System.out.println(separador);
		System.out.println("#		Ejercicio 2			 #");
		System.out.println("#	"+ f2Ej2 +"	 #");
		System.out.println(separador + "\n");
		
		System.out.println("Entrada: " + lecturaEj2f2);
		System.out.println("Solución iterativa: " + Ejercicio2.versionIterativa(lecturaEj2f2));
		System.out.println("Solución recursiva final: " + Ejercicio2.versionRecursivaFinal(lecturaEj2f2) + "\n");
		
	}
	
	public static void testEj3() {
		
		List<Pair<Integer,Integer>> lectura = Lectores.lectorEj3y4(fEj3, x->Integer.parseInt(x));
		Iterator<Pair<Integer,Integer>> it = lectura.iterator();
		
		System.out.println(separador);
		System.out.println("#		Ejercicio 3			 #");
		System.out.println("#	"+ fEj3 +"	 #");
		System.out.println(separador + "\n");
		
		while(it.hasNext()) {
			
			Pair<Integer,Integer> par = it.next();
			
			System.out.println("Entrada: " + par);
			System.out.println("Solución iterativa: " + Ejercicio3.versionIterativa(par.first(), par.second()));
			System.out.println("Solución recursiva final: " + Ejercicio3.versionRecursivaFinal(par.first(), par.second()) + "\n");
		}
		
	}
	
	public static void testEj4() {
		List<Pair<Double,Double>> lectura = Lectores.lectorEj3y4(fEj4, x->Double.parseDouble(x));
		Iterator<Pair<Double,Double>> it = lectura.iterator();
		
		System.out.println(separador);
		System.out.println("#		Ejercicio 4			 #");
		System.out.println("#	"+ fEj4 +"	 #");
		System.out.println(separador + "\n");
		
		while(it.hasNext()) {
			
			Pair<Double,Double> par = it.next();
			System.out.println("Entrada: " + par);
			System.out.println("Solución iterativa: " + Ejercicio4.versionIterativa(par.first(), par.second()));
			System.out.println("Solución recursiva: " + Ejercicio4.versionRecursiva(par.first(), par.second()));
			System.out.println("Solución funcional: " + Ejercicio4.versionFuncional(par.first(), par.second()) + "\n");
		}
		
	}

	public static void main(String[] args) {
		
		testEj1();
		testEj2();
		testEj3();
		testEj4();
	}
}
