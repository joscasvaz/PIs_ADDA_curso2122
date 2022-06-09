package ejercicio1;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record ProblemEjercicio1(Integer index, List<Integer> capRestante) {
	
	public static ProblemEjercicio1 of(Integer index, List<Integer> capRestante) {
		return new ProblemEjercicio1(index, capRestante);
	}
	
	public List<Integer> actions() {
		
		List<Integer> alternativas = List2.empty();
		
		if (DatosEjercicio1.getNumArch() <= index) { return alternativas; }
		
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
	
	public ProblemEjercicio1 neighbor(Integer a) {
		
		List<Integer> copia = List2.copy(capRestante);
		
		if (a < DatosEjercicio1.getNumMem()) 
			copia.set(a, copia.get(a) - DatosEjercicio1.tamArch(index));
		
		return of(index + 1, copia);
	}
}
