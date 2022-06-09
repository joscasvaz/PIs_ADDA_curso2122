package geneticos;

import java.util.List;
import java.util.stream.Collectors;

import datos.DatosEjercicio1;
import datos.DatosEjercicio1.Archivo;
import datos.DatosEjercicio1.Memoria;
import soluciones.SolucionEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;

public class GeneticoEjercicio1 implements ValuesInRangeData<Integer, SolucionEjercicio1> {
	
	private List<Archivo> ficheros = DatosEjercicio1.archivos;
	private static List<Memoria> memorias = DatosEjercicio1.memorias;
	
	private Double goal;
	private Double penalty;
	private Double fitness = null;
	
	private static List<Integer> capacidades = memorias.stream()
			.map(Memoria::capacidad)
			.collect(Collectors.toList());
	
	@Override
	public Integer size() { return ficheros.size(); }
	
	@Override
	public ChromosomeType type() { return ChromosomeType.Range; }
	
	private void calculate(List<Integer> ls) {
		
		this.goal = .0;
		this.penalty = .0;
		
		List<Integer> auxCapacidades = List2.copy(capacidades);
		
		int i = 0;
		
		while(i < ls.size()) {
			
			Integer tamFichero = ficheros.get(i).tam();
			
			Integer idMemAsignada = ls.get(i);
			
			if(idMemAsignada < memorias.size()) {
				
				Integer capMemAsignada = auxCapacidades.get(idMemAsignada);
				
				if(tamFichero <= (capMemAsignada - tamFichero)) {
					
					auxCapacidades.set(idMemAsignada, (capMemAsignada - tamFichero));
					goal++;
				}
				
				Integer tamMaxMemAsignada = memorias.get(idMemAsignada).tamMax();
				
				if(tamMaxMemAsignada < tamFichero) { penalty += (tamFichero - tamMaxMemAsignada); }
				
			} else { penalty++; }
			
			i++;
		}
	}
	
	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		calculate(value);
		Integer kP = ficheros.size();
		
		fitness = goal - kP * penalty;
		
		return fitness;
	}

	@Override
	public SolucionEjercicio1 solucion(List<Integer> value) { return SolucionEjercicio1.create(value); }

	@Override
	public Integer max(Integer i) { return DatosEjercicio1.getNumMemorias() + 1; }
	
	@Override
	public Integer min(Integer i) { return 0; }
	
	public static GeneticoEjercicio1 of() { return new GeneticoEjercicio1(); }
	
}
