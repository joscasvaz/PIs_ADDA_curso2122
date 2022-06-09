package tests;

import java.util.List;

import datos.DatosEjercicio5;
import geneticos.GeneticoEjercicio5;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio5 {

	public static void testAG() {
		
		List<String> ls = List.of("a", "b", "c");
		
		for (int i = 1; i<4; i++) {
			
			String fichero = "ficheros/PI5Ej5DatosEntrada" + i + ".txt";
			
			DatosEjercicio5.iniDatos(fichero);
			DatosEjercicio5.predicadosOrigenDestino("ficheros/PI5Ej5DatosEntrada.txt", i);
			
			AlgoritmoAG.ELITISM_RATE  = 0.30;
			AlgoritmoAG.CROSSOVER_RATE = 0.8;
			AlgoritmoAG.MUTATION_RATE = 0.7;
			AlgoritmoAG.POPULATION_SIZE = 50;
			
			StoppingConditionFactory.NUM_GENERATIONS = 40000;
			StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
			StoppingConditionFactory.FITNESS_MIN = 317.7;
			StoppingConditionFactory.stoppingConditionType = 
					StoppingConditionFactory.StoppingConditionType.GenerationCount;
			
			var alg = AlgoritmoAG.of(GeneticoEjercicio5.of());
			alg.ejecuta();
			
			System.out.println(ls.get(i - 1) + ") " + fichero.replace("ficheros/", "") + ":\n");
			
			if (i==1) {
				
				System.out.println("Predicados: Ciudad con más de " 
				+ DatosEjercicio5.hab + " hab. y Carretera con más de " 
						+ DatosEjercicio5.kms.intValue() + " kms:\n");
			
			} else if (i==2) {
				
				System.out.println("Predicados: Ciudad con máximo " 
				+ DatosEjercicio5.hab + " hab. y Carretera con al menos " 
						+  DatosEjercicio5.kms.intValue() + " kms:\n");
			
			} else {
				
				System.out.println("Predicados: Ciudad con más de " 
				+ DatosEjercicio5.hab + " hab. y Carretera con menos de " 
						+  DatosEjercicio5.kms.intValue() + " kms:\n");
			
			}
			
			System.out.println(alg.bestSolution());
			System.out.println(alg.getBestChromosome().fitness() + "\n");
		}
	}
	
}
