package ejercicio3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio3(Integer index, Integer tProdRestante, Integer tElabRestante)
implements VirtualVertex<VertexEjercicio3, EdgeEjercicio3, Integer>{

	public static Integer tProdIni;
	public static Integer tElabIni;
	
	public static void iniDatos(String path) {
		
		DatosEjercicio3.iniDatos(path);
		VertexEjercicio3.tProdIni = DatosEjercicio3.getTotalProd();
		VertexEjercicio3.tElabIni = DatosEjercicio3.getTotalManual();
	}
	
	public static VertexEjercicio3 of(Integer index, Integer tProdRestante, Integer tElabRestante) {
		return new VertexEjercicio3(index, tProdRestante, tElabRestante);
	}
	public static VertexEjercicio3 initialVertex() {
		return of(0, VertexEjercicio3.tProdIni, VertexEjercicio3.tElabIni);
	}
	public static Predicate<VertexEjercicio3> goal(){
		return v -> v.index == DatosEjercicio3.productos.size();
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
	
	@Override
	public List<Integer> actions() {
		
		if (index == DatosEjercicio3.getNumProd()) { return List2.empty(); }
		else {
			
			return IntStream.rangeClosed(0, getRatioUds(index))
					.boxed()
					.toList();
		}
	}

	@Override
	public VertexEjercicio3 neighbor(Integer a) {
		
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

	@Override
	public EdgeEjercicio3 edge(Integer a) {
		
		VertexEjercicio3 v = this.neighbor(a);
		return EdgeEjercicio3.of(this,v,a);
	}
}
