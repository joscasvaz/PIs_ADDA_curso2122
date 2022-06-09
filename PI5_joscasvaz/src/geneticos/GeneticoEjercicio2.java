package geneticos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.DatosEjercicio2;
import datos.DatosEjercicio2.Candidato;
import soluciones.SolucionEjercicio2;
import us.lsi.ag.BinaryData;

public class GeneticoEjercicio2 implements BinaryData<SolucionEjercicio2>{

	@Override
	public Integer size() { return DatosEjercicio2.getNumCandidatos(); }

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		Double objetivo = 0.;
		Double restricciones = 0.;
		
		Integer esCompatible;
		Integer cualidadCubierta = null;
		
		Double presupuesto = (double) DatosEjercicio2.getPresupuesto();
		Set<String> cualidadesTotales = new HashSet<>();
		
		for (int i=0; i<value.size(); i++) {
			
			if (value.get(i)>0) {
				
				Candidato c = DatosEjercicio2.candidatos.get(i);
				presupuesto -= c.salario();
				
				for (int k=0; k<DatosEjercicio2.getNumCualidades(); k++) {
					
					cualidadCubierta = DatosEjercicio2.tieneCualidad(i, k);
					
					if (cualidadCubierta == 1) {
						
						cualidadesTotales.addAll(DatosEjercicio2.candidatos.get(i).cualidades());
						break;
					}
				}
				
				restricciones += -145689*(cualidadCubierta - 1);
				
				for (int j=1; j<value.size() && j!=i; j++) {
					
					if (value.get(j)>0) {
						
						esCompatible = DatosEjercicio2.esCompatible(i, j) ? 1 : 0;
						restricciones += -145689*esCompatible;
					}
				}
				
				objetivo += c.valoracion();
			}
		}
		
		cualidadCubierta = cualidadesTotales.stream().toList()
				.equals(DatosEjercicio2.cualidadesDeseadas) ? 1 : 0;
		
		restricciones += -145689*(cualidadCubierta - 1);
		
		restricciones += - Math.pow(Math.abs(presupuesto), 2);
		
		return objetivo + restricciones;
	}

	@Override
	public SolucionEjercicio2 solucion(List<Integer> value) { return SolucionEjercicio2.create(value); }
	
	public static GeneticoEjercicio2 of() { return new GeneticoEjercicio2(); }
	
}
