package geneticos;

import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import datos.DatosEjercicio5;
import soluciones.SolucionEjercicio5;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.graphs.views.IntegerVertexGraphView;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class GeneticoEjercicio5 implements SeqNormalData<SolucionEjercicio5>{
	
	Integer n = DatosEjercicio5.n;
	SimpleWeightedGraph<Ciudad, Carretera> gf = DatosEjercicio5.gf;
	
	private IntegerVertexGraphView<Ciudad, Carretera> graph = DatosEjercicio5.graph;
	private Predicate<Ciudad> predicadoCiudad = DatosEjercicio5.predCiudad;
	private Predicate<Carretera> predicadoCarretera = DatosEjercicio5.predCarretera;
	
	private Double goal;
	private Double penalty;
	
	private Double fitness = null;
	
	@Override
	public ChromosomeType type() { return ChromosomeType.PermutationSubList; }
	
	private void calculate(List<Integer> ls) {
		
		goal = .0;
		penalty = .0;
		
		int i = 0;
		while(i < ls.size()) {
			
			Integer indexCiudad = ls.get(i);
			
			if(indexCiudad < DatosEjercicio5.n) {
				
				Ciudad ciudadSeleccionada = graph.getVertex(indexCiudad);
				Boolean cumplePredCiudad = predicadoCiudad.test(ciudadSeleccionada);
				
				if(!cumplePredCiudad) { penalty++; }
				
				if(i < ls.size() - 1) {
					
					Integer indexVecina = ls.get(i + 1);
					
					Ciudad ciudadVecina = graph.getVertex(indexVecina);
					Boolean cumplePredVecina = predicadoCiudad.test(ciudadVecina);
					
					if(!cumplePredVecina) { penalty++; }
					
					if(graph.containsEdge(indexCiudad, indexVecina)) {
						
						Carretera carreteraSeleccionada = Carretera.of(
								graph.getEdge(indexCiudad, indexVecina).weight());
						Boolean cumplePredCarretera = predicadoCarretera
								.test(carreteraSeleccionada);
						
						if(!cumplePredCarretera) { penalty++; }
						
						goal -= carreteraSeleccionada.km();
					}
				}
			}
			
			i++;
		}
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		
		calculate(ls);
		Integer kP = graph.n;
		
		fitness = goal - kP * penalty;
		
		return fitness;
	}
	
	@Override
	public SolucionEjercicio5 solucion(List<Integer> ls) {
		return SolucionEjercicio5.create(ls); }
	
	@Override
	public Integer itemsNumber() { return n; }
	
	public static void toConsole() {
		
		for (int i = 0; i<DatosEjercicio5.graph.vertexSet().size(); i++) {
			
			System.out.println(DatosEjercicio5.graph.getVertex(i));
		}
	}
	
	public static GeneticoEjercicio5 of() { return new GeneticoEjercicio5(); }
}
