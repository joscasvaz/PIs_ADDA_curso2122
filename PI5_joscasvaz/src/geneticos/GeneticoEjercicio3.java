package geneticos;

import java.util.List;

import datos.DatosEjercicio3.Componente;
import datos.DatosEjercicio3.Producto;
import soluciones.SolucionEjercicio3;
import datos.DatosEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticoEjercicio3 implements ValuesInRangeData<Integer, SolucionEjercicio3>{

	private List<Componente> componentes = DatosEjercicio3.componentes;
	private List<Producto> productos = DatosEjercicio3.productos;
	
	@Override
	public Integer size() { return DatosEjercicio3.getNumProd(); }

	@Override
	public ChromosomeType type() { return ChromosomeType.Range; }

	@Override
	public Double fitnessFunction(List<Integer> value) {

		Double objetivo = 0.;
		Integer restricciones = 0;
		
		Integer tiempoTotalProd = DatosEjercicio3.getMaxTProducc();
		Integer tiempoTotalElab = DatosEjercicio3.getMaxTManual();
		Integer tiempoProd = 0;
		Integer tiempoElab = 0; 
		
		for (int i=0; i<value.size(); i++) {
			
			if (value.get(i) > 0) {
			
				objetivo += value.get(i)*productos.get(i).precio();
			
				for (int j=0; j<componentes.size(); j++) {
					
					if (DatosEjercicio3.tieneComponente(i, j)) {
						
						tiempoProd += DatosEjercicio3.getTCompProdProducc(i, j) * value.get(i);
						tiempoElab += DatosEjercicio3.getTCompProdElab(i, j) * value.get(i); 
						
					}
				}
				
				restricciones += tiempoTotalProd < tiempoProd ? 1 : 0;
				restricciones += tiempoTotalElab < tiempoElab ? 1 : 0;
			}
		}
		
		return restricciones < 1 ? objetivo : objetivo - 145689.0*(restricciones);
	}

	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) { return SolucionEjercicio3.create(value); }

	@Override
	public Integer max(Integer i) { return productos.get(i).maxUds(); }

	@Override
	public Integer min(Integer i) { return 0; }
	
	public static GeneticoEjercicio3 of() { return new GeneticoEjercicio3(); }
}
