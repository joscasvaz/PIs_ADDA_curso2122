package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import ejercicios.Ejercicio1;
import ejercicios.Ejercicio2;
import ejercicios.Ejercicio3;
import ejercicios.Ejercicio4;
import ejercicios.Ejercicio5;
import us.lsi.common.Pair;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import util.Lectores;
import util.Paridad;

public class Test<T> {
	
	static String ejN(int n) { return String.format("\n##########\nEJERCICIO %d\n##########\n", n); }
	static String ficheroEjN(int n) { return String.format("./ficheros/PI3E%d_DatosEntrada.txt", n); }
	
	static String solRec = "SOLUCION RECURSIVA";
	static String solIt = "SOLUCION ITERATIVA";
	
	static Function<String,Integer> fI = s->Integer.decode(s);
	
	public static void testEj1() {
		
		int ne = 1;
		
		String ej1 = ejN(ne);
		String ficheroEj1 = ficheroEjN(ne);
		
		List<Tree<Integer>> lecturaEj1 = Lectores.lectorArbolesNArios(ficheroEj1, fI);
		
		Predicate<Integer> par = i->Math2.esPar(i);
		Predicate<Integer> menor5 = i-> i < 5;
		
		System.out.println(ej1);
		
		Map<Tree<Integer>, List<List<Integer>>> mem = new HashMap<>();
		
		for(Tree<Integer> t:lecturaEj1) {
			
			List<Integer> solRecPar = Ejercicio1.versionRecursiva(t, par);
			List<Integer> solRecMenor5 = Ejercicio1.versionRecursiva(t, menor5);
			
			List<Integer> solItFuncPar = Ejercicio1.versionIterativaFunc(t, par);
			List<Integer> solItFuncMenor5 = Ejercicio1.versionIterativaFunc(t, menor5);
			
			mem.put(t, List.of(solRecPar, solRecMenor5, solItFuncPar, solItFuncMenor5));
		}
		
		System.out.println(solRec + "-PAR\n");
		
		for(Tree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(0)); }
		
		System.out.println("\n" + solRec + "-MENOR_QUE_CINCO\n");
		
		for(Tree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(1)); }
		
		System.out.println("\n" + solIt + "-PAR\n");
		
		for(Tree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(2)); }
		
		System.out.println("\n" + solIt + "-MENOR_QUE_CINCO\n");
		
		for(Tree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(3)); }
		
	}
	
	public static void testEj2() {
		
		int ne = 2;
		
		String ej2 = ejN(ne);
		String ficheroEj2 = ficheroEjN(ne);
		
		List<Pair<BinaryTree<Integer>,Integer>> lecturaEj2 = Lectores.lectorEj2(ficheroEj2);
		
		System.out.println(ej2);
		
		System.out.println(solRec + "\n");
		
		for(Pair<BinaryTree<Integer>,Integer> par: lecturaEj2) {
			
			BinaryTree<Integer> arbol = par.first();
			Integer n = par.second();
			
			System.out.println(par + ": " + Ejercicio2.versionRecursiva(arbol, n));
		}
	}
	
	public static void testEj3() {
		
		int ne = 3;
		
		String ej3 = ejN(ne);
		String ficheroEj3 = ficheroEjN(ne);
		
		List<BinaryTree<Integer>> lecturaEj3 = Lectores.lectorArbolesBinarios(ficheroEj3, fI);
		
		System.out.println(ej3);
		
		System.out.println(solRec + "\n");
		
		for(BinaryTree<Integer> arbol:lecturaEj3) { System.out.println(arbol + ": " + Ejercicio3.versionRecursiva(arbol)); }
	}
	
	public static void testEj4() {
		
		int ne = 4;
		
		String ej4 = ejN(ne);
		String ficheroEj4 = ficheroEjN(ne);
		
		List<Tree<Character>> lecturaEj4 = Lectores.lectorArbolesNArios(ficheroEj4, s->s.charAt(0));
		
		System.out.println(ej4);
		
		System.out.println(solRec + "\n");
		
		for(Tree<Character> arbol:lecturaEj4) { System.out.println(arbol + ": " + Ejercicio4.versionRecursiva(arbol)); }
	}
	
	public static void testEj5() {
		
		int ne = 5;
		
		String ej5 = ejN(ne);
		String ficheroEj5 = ficheroEjN(ne);
		
		List<BinaryTree<Integer>> lecturaEj5 = Lectores.lectorArbolesBinarios(ficheroEj5, fI);
		
		System.out.println(ej5);
		
		Map<BinaryTree<Integer>, List<Map<Paridad,List<Integer>>>> mem = new HashMap<>();
		
		for(BinaryTree<Integer> t:lecturaEj5) {
			
			Map<Paridad,List<Integer>> solRec = Ejercicio5.versionRecursiva(t);
			Map<Paridad,List<Integer>> solItFunc = Ejercicio5.versionIterativaFunc(t);
			
			mem.put(t, List.of(solRec, solItFunc));
		}
		
		System.out.println(solRec + "\n");
		
		for(BinaryTree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(0)); }
		
		System.out.println("\n" + solIt + "\n");
		
		for(BinaryTree<Integer> k:mem.keySet()) { System.out.println(k + ": " + mem.get(k).get(1)); }
	}

	public static void main(String[] args) {
		
		testEj1();
		testEj2();
		testEj3();
		testEj4();
		testEj5();

	}

}
