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
	
	private Double goal;
	private Double penalty;
	
	private Double fitness = null;
	
	private void calculate(List<Integer> ls) {
		
		goal = .0;
		penalty = .0;
		
		List<Integer> capacidadesRestantes = contenedores.stream()
				.map(Contenedor::capacidad)
				.collect(Collectors.toList());
		
		int i = 0;
		while(i < ls.size()) {
			
			Integer contenedorAsignado = ls.get(i);
			
			if(contenedorAsignado < contenedores.size()) {
			
				Integer capacidadContenedor = capacidadesRestantes.get(contenedorAsignado);
				
				Integer tamElemento = elementos.get(i).tam();
				
				List<String> tiposElemento = elementos.get(i).tipos();
				
				String tipoContenedor = contenedores.get(contenedorAsignado).tipo();
				
				Integer nuevaCapacidad = capacidadContenedor - tamElemento;
				
				if(!tiposElemento.contains(tipoContenedor)) { penalty++; }
				
				if(0 <= nuevaCapacidad) {
					
					capacidadesRestantes.set(contenedorAsignado, nuevaCapacidad);
					
					if(nuevaCapacidad.equals(0)) { goal++; }
					
				} else { penalty += Math.abs(nuevaCapacidad); }
			}
			
			i++;
		}
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {

		calculate(ls);
		Integer kP = contenedores.size() * elementos.size();
		
		fitness = goal - kP * penalty;
		
		return fitness;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) {
		return SolucionEjercicio4.create(value); }

	@Override
	public Integer max(Integer i) { return DatosEjercicio4.getNumCont() + 1; }

	@Override
	public Integer min(Integer i) { return 0; }
	
	public static GeneticoEjercicio4 of() { return new GeneticoEjercicio4(); }
}
