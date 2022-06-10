package tests;

import java.io.IOException;
import java.util.List;

import datos.DatosEjercicio3;
import geneticos.GeneticoEjercicio3;
import soluciones.SolucionEjercicio3;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEjercicio3 {
	
	public static void testPLE() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i < 4; i++) {
			
			String fichero = "ficheros/PI5Ej3DatosEntrada" + i + ".txt";
			DatosEjercicio3.datos(fichero);
			
			try {
				
				AuxGrammar.generate(DatosEjercicio3.class, 
						"lsi_models/ejercicio3.lsi", 
						"gurobi_models/sol_ejercicio3_" + i + ".lp");
			
			} catch (IOException e) { e.printStackTrace(); }
			
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/sol_ejercicio3_" + i + ".lp");
			
			System.out.println("**********************************************");
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
			System.out.println(SolucionEjercicio3.create(gs));
			System.out.println(gs.toString((s,d) -> d>0.));
			System.out.println("**********************************************");
		}
	}

	public static void testAG() {
	
	List<String> ls = List.of("a", "b", "c");
	
	for (int i = 1; i<4; i++) {
		String fichero = "ficheros/PI5Ej3DatosEntrada" + i + ".txt";
		DatosEjercicio3.datos(fichero);
		AlgoritmoAG.POPULATION_SIZE = 50;
		StoppingConditionFactory.NUM_GENERATIONS = 40000;
		StoppingConditionFactory.FITNESS_MIN = 1810.0;
		
		var alg = AlgoritmoAG.of(GeneticoEjercicio3.of());
		alg.ejecuta();
		
		System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
		
		System.out.println(alg.bestSolution());
		System.out.println(alg.getBestChromosome().fitness() + "\n");
		}
	}
}
