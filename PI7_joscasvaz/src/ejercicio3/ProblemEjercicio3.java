package ejercicio3;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record ProblemEjercicio3(Integer index, Integer tProdRestante, Integer tElabRestante) {

	public static Integer tProdIni;
	public static Integer tElabIni;
	
	public static ProblemEjercicio3 of(Integer index, Integer tProdRestante, Integer tElabRestante) {
		return new ProblemEjercicio3(index, tProdRestante, tElabRestante);
	}
	
	public Integer getRatioUds(Integer i) {
		
		Integer tProdTotal = 0;
		Integer tElabTotal = 0;
		
		for(int j = 0; j < DatosEjercicio3.getNumComp(); j++) {
			
			if(DatosEjercicio3.tieneComponente(i, j)){
				tProdTotal += DatosEjercicio3.getTiempoProd(i);
				tElabTotal += DatosEjercicio3.getTiempoElab(i);
			}
		}
		
		return Math.min(DatosEjercicio3.getMaxUds(i),
				Math.min(tProdRestante/tProdTotal, tElabRestante/tElabTotal));		
	}
	
	public List<Integer> actions() {
		
		if (index == DatosEjercicio3.getNumProd()) { return List2.empty(); }
		else {
			
			return IntStream.rangeClosed(0, getRatioUds(index))
					.boxed()
					.toList();
		}
	}

	public ProblemEjercicio3 neighbor(Integer a) {
		
		Integer auxTProd = tProdRestante;
		Integer auxTElab = tElabRestante;
		
		for (int j = 0; j < DatosEjercicio3.getNumComp(); j++) {
			
			if (DatosEjercicio3.tieneComponente(index, j)) {
				auxTProd -= a * DatosEjercicio3.getTiempoProdTotalProducto(index, j);
				auxTElab -= a * DatosEjercicio3.getTiempoElabTotalProducto(index, j);
			}
		}
		
		return of(index + 1, auxTProd, auxTElab);
	}

}
