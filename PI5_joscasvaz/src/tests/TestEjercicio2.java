package tests;

import java.io.IOException;
import java.util.List;

import datos.DatosEjercicio2;
import geneticos.GeneticoEjercicio2;
import soluciones.SolucionEjercicio2;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEjercicio2 {
	
	public static void testPLE() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i < 4; i++) {
			
			String fichero = "ficheros/PI5Ej2DatosEntrada" + i + ".txt";
			DatosEjercicio2.iniDatos(fichero);
			
			try {
				
				AuxGrammar.generate(DatosEjercicio2.class, 
						"lsi_models/ejercicio2.lsi", 
						"gurobi_models/sol_ejercicio2_" + i + ".lp");
			
			} catch (IOException e) { e.printStackTrace(); }
			
			GurobiSolution gs = GurobiLp.gurobi("gurobi_models/sol_ejercicio2_" + i + ".lp");
			
			System.out.println("**********************************************");
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
			System.out.println(SolucionEjercicio2.create(gs));
			System.out.println(gs.toString((s,d) -> d>0.));
			System.out.println("**********************************************");
		}
	}
	
	public static void testAG() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i<4; i++) {
			String fichero = "ficheros/PI5Ej2DatosEntrada" + i + ".txt";
			DatosEjercicio2.iniDatos(fichero);
			AlgoritmoAG.POPULATION_SIZE = 500;
			StoppingConditionFactory.NUM_GENERATIONS = 250;
			StoppingConditionFactory.FITNESS_MIN = 16.0;
			
			var alg = AlgoritmoAG.of(GeneticoEjercicio2.of());
			alg.ejecuta();
			
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":" + "\n");
			
			System.out.println(alg.bestSolution());
			System.out.println(alg.getBestChromosome().fitness() + "\n");
		}
	}

}
