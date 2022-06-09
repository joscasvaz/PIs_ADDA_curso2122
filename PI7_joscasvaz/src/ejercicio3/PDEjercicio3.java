package ejercicio3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PDEjercicio3 {
	
	public static record SolParcialEj3(Integer index, Integer beneficio)
	implements Comparable<SolParcialEj3>{
		
		public static SolParcialEj3 of(Integer index, Integer beneficio) {
			return new SolParcialEj3(index, beneficio); }
		
		@Override
		public int compareTo(SolParcialEj3 sp) { return this.beneficio.compareTo(sp.beneficio()); }
	}
	
	public static Map<ProblemEjercicio3,SolParcialEj3> mem;
	public static ProblemEjercicio3 start;
	public static Integer maxValue = Integer.MIN_VALUE;
	
	/*
	 public static SolucionMochila pd(Integer initialCapacity) {
		MochilaPD.maxValue = Integer.MIN_VALUE;
		MochilaPD.start = MochilaProblem.of(0,initialCapacity);
		MochilaPD.memory = new HashMap<>();
		pd(start,0,memory);
		return MochilaPD.solucion();
	}
	
	public static SolucionMochila pd(Integer initialCapacity, Integer maxValue, SolucionMochila s) {
		MochilaPD.maxValue = maxValue;
		MochilaPD.start = MochilaProblem.of(0,initialCapacity);
		MochilaPD.memory = new HashMap<>();
		pd(start,0,memory);
		if(MochilaPD.memory.get(start) == null) return s;
		else return MochilaPD.solucion();
	}
	 */
	
	public static SolParcialEj3 pd(ProblemEjercicio3 v, Integer accValue,
			Map<ProblemEjercicio3,SolParcialEj3> mem) {
		
		SolParcialEj3 r;
		List<SolParcialEj3> soluciones = new ArrayList<>();
		
		if(mem.containsKey(v)) r = mem.get(v);
		else if(v.index().equals(DatosEjercicio3.getNumProd())) { //TODO comprobar n
			
			r = SolParcialEj3.of(0, null);
			mem.put(v, r);
			if(PDEjercicio3.maxValue < accValue) PDEjercicio3.maxValue = accValue;
			
		} else {
			
			for(Integer i:v.actions()) {
				
				Double cota = accValue + HeuristicEjercicio3.cota(v, i);
				
				if(cota < PDEjercicio3.maxValue) continue;
				SolParcialEj3 s = pd(v.neighbor(i),
						accValue + i * DatosEjercicio3.getPrecioVenta(v.index()), mem);
				
				if(s != null) {
					
					SolParcialEj3 sp = SolParcialEj3.of(i, s.beneficio()); //accion pendiente?
					soluciones.add(sp);
				}
			}
		}
		
		r = soluciones.stream()
				.max(Comparator.naturalOrder())
				.orElse(null);
		
		if(r != null) mem.put(v, r);
		return r;
	}
	
	/*public static SolucionMochila solucion(){
		List<Integer> acciones = new ArrayList<>();
		MochilaProblem v = MochilaPD.start;
		Spm s = MochilaPD.memory.get(v);
		while(s.a() != null) {
			acciones.add(s.a());
			v = v.vecino(s.a());	
			s = MochilaPD.memory.get(v);
		}
		return SolucionMochila.of(MochilaPD.start,acciones);
	}*/
}
