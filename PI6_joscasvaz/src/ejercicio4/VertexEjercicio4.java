package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio4.DatosEjercicio4.Contenedor;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;


public record VertexEjercicio4(Integer index, List<Integer> elRestantes, List<Integer> capRestante)
	implements VirtualVertex<VertexEjercicio4, EdgeEjercicio4, Integer> {
	
	static List<Integer> capIniciales;
	static List<Integer> elIniciales;
	
	public static void iniDatos(String path) {
		
		VertexEjercicio4.capIniciales = DatosEjercicio4.contenedores.stream()
				.map(Contenedor::capacidad)
				.collect(Collectors.toList());
		
		VertexEjercicio4.elIniciales = IntStream.range(0, DatosEjercicio4.elementos.size())
				.boxed()
				.toList();
		
	}
	
	public static VertexEjercicio4 of(Integer index, List<Integer> elRestantes, List<Integer> capRestante) {
		return new VertexEjercicio4(index, elRestantes, capRestante);
	}
	
	public static VertexEjercicio4 copy(VertexEjercicio4 v) {
		return new VertexEjercicio4(v.index(), v.elRestantes(), v.capRestante());
	}
	
	public static VertexEjercicio4 initialVertex() {
		return of(0, elIniciales, capIniciales);
	}
	
	public static Predicate<VertexEjercicio4> goal(){
		return v-> v.index() == DatosEjercicio4.getNumEl();
	}
	
	@Override
	public List<Integer> actions() {
		
		List<Integer> alternativas = new ArrayList<>();
		
		if(this.index() < DatosEjercicio4.elementos.size()) {
			
			alternativas = IntStream.range(0, this.elRestantes().size())
					.boxed()
					.toList();
		}
		
		return alternativas;
	}
	
	@Override
	public VertexEjercicio4 neighbor(Integer a) {
		
		List<Integer> nuevosElRestantes = new ArrayList<>(elRestantes);
		List<Integer> nuevasCapRestantes = List2.copy(capRestante);
		
		Integer cap = 100000;
		Integer tam = 0;
		
		int ind = 0;
		
		if(DatosEjercicio4.elementos.size() < a && 0 <= a) {
			
			tam = DatosEjercicio4.elementos.get(this.elRestantes().get(a)).tam();
			
			int i = 0;
			while(i < this.capRestante().size()) {
				
				Integer capSel = this.capRestante().get(i);
				
				if(0 <= (capSel - tam)){
					cap = Math.min(cap, capSel - tam);
					ind = i;
				}
				
				i++;
			}
			
			nuevosElRestantes.remove(a);
			nuevasCapRestantes.set(ind, cap);
		}
		
		return of(index + 1, nuevosElRestantes, nuevasCapRestantes);
	}

	@Override
	public EdgeEjercicio4 edge(Integer a) {
		VertexEjercicio4 neighbor = this.neighbor(a);
		return EdgeEjercicio4.of(this, neighbor, a);
	}
}
