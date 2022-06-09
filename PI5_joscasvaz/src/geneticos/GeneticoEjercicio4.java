package geneticos;

import java.util.List;
import java.util.stream.Collectors;

import datos.DatosEjercicio4;
import datos.DatosEjercicio4.Contenedor;
import datos.DatosEjercicio4.Elemento;
import soluciones.SolucionEjercicio4;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticoEjercicio4 implements ValuesInRangeData<Integer, SolucionEjercicio4>{

	@Override
	public Integer size() { return DatosEjercicio4.getNumEl(); }

	@Override
	public ChromosomeType type() { return ChromosomeType.Range; }

	private List<Contenedor> contenedores = DatosEjercicio4.contenedores;
	private List<Elemento> elementos = DatosEjercicio4.elementos;
	
	@Override
	public Double fitnessFunction(List<Integer> value) {

		Double objetivo = 0.;
		Double restricciones = 0.;
		
		Integer m = DatosEjercicio4.contenedores.size();
		
		List<Integer> capacidadContenedores = contenedores.stream()
				.map(Contenedor::capacidad)
				.collect(Collectors.toList());
		
		Integer esCompatible = 0;
		
		for (int i=0; i<value.size(); i++) {
			
			if (value.get(i)<m) {
			
					esCompatible = DatosEjercicio4.esComp(i, value.get(i)) ? 1 : 0;
					restricciones += -145689*(1 - esCompatible);
					
				if (capacidadContenedores.get(value.get(i)) > 0) {
						
					Integer cap = capacidadContenedores.get(value.get(i));
					cap -= elementos.get(i).tamaño();
								
					if (cap == 0) { objetivo++; }
					
					capacidadContenedores.set(value.get(i), cap);
				} 
			}
		}
		
		Integer numContLlenos = (int) capacidadContenedores.stream().filter(x -> x==0).count();
		
		if (numContLlenos < 1) { restricciones += -145689.0; }
		
		return objetivo + restricciones;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) { return SolucionEjercicio4.create(value); }

	@Override
	public Integer max(Integer i) { return DatosEjercicio4.getNumCont() + 1; }

	@Override
	public Integer min(Integer i) { return 0; }
	
	public static GeneticoEjercicio4 of() { return new GeneticoEjercicio4(); }
}
