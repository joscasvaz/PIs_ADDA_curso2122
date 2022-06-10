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
	
	private Integer tprod = DatosEjercicio3.getMaxTProducc();
	private Integer tmanual = DatosEjercicio3.getMaxTManual();
	
	private Double goal;
	private Double penalty;
	private Double fitness = null;
	
	@Override
	public Integer size() { return DatosEjercicio3.getNumProd(); }

	@Override
	public ChromosomeType type() { return ChromosomeType.Range; }
	
	private void calcula(List<Integer> ls) {
		
		goal = .0;
		penalty = .0;
		
		Integer tprodTotal = 0;
		Integer tmanualTotal = 0;
		
		int i = 0;
		while(i < ls.size()) {
			
			Integer udsProducto = ls.get(i);
			Integer maxUds = DatosEjercicio3.getMaxUds(i);
			
			Integer ingresos = DatosEjercicio3.getIngresos(i);
			
			goal += ingresos * udsProducto;
			
			if(maxUds < udsProducto) { penalty += udsProducto - maxUds; }
			
			int j = 0;
			while(j < componentes.size()) {
				
				tprodTotal += DatosEjercicio3.getTCompProdProducc(i, j);
				tmanualTotal += DatosEjercicio3.getTCompProdElab(i, j);
				
				j++;
			}
			
			i++;
		}
		
		if(tprod < tprodTotal) { penalty += tprodTotal - tprod; }
		if(tmanual < tmanualTotal) { penalty += tmanualTotal - tmanual; }
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		
		calcula(ls);
		Integer kP = componentes.size() * productos.size();
		
		fitness = goal - kP * penalty;
		
		return fitness;
	}

	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) { return SolucionEjercicio3.create(value); }

	@Override
	public Integer max(Integer i) { return productos.get(i).maxUds(); }

	@Override
	public Integer min(Integer i) { return 0; }
	
	public static GeneticoEjercicio3 of() { return new GeneticoEjercicio3(); }
}
