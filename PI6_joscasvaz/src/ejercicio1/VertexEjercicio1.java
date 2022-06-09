package ejercicio1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import ejercicio1.DatosEjercicio1.Memoria;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record VertexEjercicio1(Integer index, List<Integer> capRestante)
implements VirtualVertex<VertexEjercicio1, EdgeEjercicio1, Integer>{

	public static void iniDatos(String path) {
			
		DatosEjercicio1.iniDatos(path);
		VertexEjercicio1.capInicial = DatosEjercicio1.memorias.stream()
				.map(Memoria::cap)
				.toList();
	}
		
	public static VertexEjercicio1 of(Integer index, List<Integer> capRestante) {
		return new VertexEjercicio1(index, capRestante);
	}
	
	public static VertexEjercicio1 initialVertex() { return of(0, VertexEjercicio1.capInicial); }
	
	public static Predicate<VertexEjercicio1> goal() { return v ->
	v.index() == DatosEjercicio1.getNumArch(); }
		
	public static List<Integer> capInicial;
	
	@Override
	public List<Integer> actions() {
		
		List<Integer> alternativas = List2.empty();
		
		if (index >= DatosEjercicio1.getNumArch()) { return alternativas; }
		
		List<Integer> memorias = IntStream.rangeClosed(0, DatosEjercicio1.getNumMem())
				.boxed()
				.toList();
		
		for (int i:memorias) {
			
			if (i < DatosEjercicio1.getNumMem()
				&& DatosEjercicio1.tamArch(index) <= DatosEjercicio1.tamMaxMem(i)
				&& DatosEjercicio1.tamArch(index) <= capRestante.get(i)) {
				
				alternativas.add(i);
				
			} else if (i == DatosEjercicio1.getNumMem()) { alternativas.add(i); }
		}
		
		return alternativas;
	}
	
	@Override
	public VertexEjercicio1 neighbor(Integer a) {
		
		List<Integer> copia = List2.copy(capRestante);
		
		if (a < DatosEjercicio1.getNumMem() &&
				0 <= a) { copia.set(a, copia.get(a) - DatosEjercicio1.tamArch(index)); }
		
		return of(index + 1, copia);
	}
	
	@Override
	public EdgeEjercicio1 edge(Integer a) {
		
		VertexEjercicio1 v = this.neighbor(a);
		return EdgeEjercicio1.of(this,v,a);
	}
}
