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
	
	@Override
	public ChromosomeType type() { return ChromosomeType.PermutationSubList; }
	
	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		Double objetivo = 0.;
		Double restricciones = 0.;
		
		Integer testCiudad = 0;
		Integer testCarretera = 0;
		
		if(value.size()>=3) {
			
			for (int i = 0; i<value.size() - 1; i++) {
				
				Ciudad ciudadInicial = DatosEjercicio5.graph.getVertex(value.get(i));
				Ciudad ciudadSiguiente = DatosEjercicio5.graph.getVertex(value.get(i+1));
				
				if (gf.containsEdge(ciudadInicial, ciudadSiguiente)) {
					
					Carretera carretera = gf.getEdge(ciudadInicial, ciudadSiguiente);
					testCarretera += predicadoCarretera.test(carretera) ? 0 : 1;
					objetivo += carretera.km();
					
				} else {
					
					restricciones += 1;
					
				} if (!ciudadInicial.equals(DatosEjercicio5.origen)) {
					
					testCiudad += predicadoCiudad.test(ciudadInicial) ? 0 : 1;
					
				}
			}
			
			Integer origen = DatosEjercicio5.origen.equals(graph.getVertex(value.get(0))) ? 0 : 1;
			Integer destino = DatosEjercicio5.destino.equals(graph.getVertex(value.get(value.size() - 1))) ? 0 : 1;
			
			restricciones += origen + destino + testCiudad + testCarretera;
		
		} else {
			
			restricciones += 1;
			
		}
		
		return restricciones < 1 ? - objetivo : - objetivo -145689.0 * restricciones;
	}
	
	@Override
	public SolucionEjercicio5 solucion(List<Integer> value) { return SolucionEjercicio5.create(value); }
	
	@Override
	public Integer itemsNumber() { return n; }
	
	public static void toConsole() {
		
		for (int i = 0; i<DatosEjercicio5.graph.vertexSet().size(); i++) {
			
			System.out.println(DatosEjercicio5.graph.getVertex(i));
		}
	}
	
	public static GeneticoEjercicio5 of() { return new GeneticoEjercicio5(); }
}
