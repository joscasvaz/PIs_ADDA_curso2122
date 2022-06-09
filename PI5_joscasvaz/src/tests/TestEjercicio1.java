package tests;

import java.io.IOException;
import java.util.List;

import datos.DatosEjercicio1;
import geneticos.GeneticoEjercicio1;
import soluciones.SolucionEjercicio1;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEjercicio1 {
	
	public static void testPLE() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i < 4; i++) {
			
			String fichero = "ficheros/PI5Ej1DatosEntrada" + i + ".txt";
			DatosEjercicio1.iniDatos(fichero);
			
			try {
			
				AuxGrammar.generate(DatosEjercicio1.class, 
						"lsi_models/ejercicio1.lsi", 
						"gurobi_models/sol_ejercicio1_" + i + ".lp");
			
			} catch (IOException e) { e.printStackTrace(); }
			
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/sol_ejercicio1_" + i + ".lp");
			
			System.out.println("**********************************************");
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
			System.out.println(SolucionEjercicio1.create(gs));
			System.out.println(gs.toString((s,d) -> d>0.));
			System.out.println("**********************************************");
		}
	}
	
	public static void testAG() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i<4; i++) {
			
			String fichero = "ficheros/PI5Ej1DatosEntrada" + i + ".txt";
			DatosEjercicio1.iniDatos(fichero);
			
			AlgoritmoAG.POPULATION_SIZE = 2;
			StoppingConditionFactory.NUM_GENERATIONS = 160;
			StoppingConditionFactory.FITNESS_MIN = -9.0;
			StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
			StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
			
			var alg = AlgoritmoAG.of(GeneticoEjercicio1.of());
			alg.ejecuta();
			
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
			System.out.println(alg.bestSolution());
			System.out.println(alg.getBestChromosome().fitness() + "\n");
		}
	}

}
