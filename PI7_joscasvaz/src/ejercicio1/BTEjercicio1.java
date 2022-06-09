package ejercicio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BTEjercicio1 {
	
	public static class StateEjercicio1 {
		
		private ProblemEjercicio1 vertex;
		private Integer numGuardados;
		private List<Integer> acciones;
		private List<ProblemEjercicio1> vertices;
		
		private StateEjercicio1(ProblemEjercicio1 vertex, Integer numGuardados, List<Integer> acciones,
				List<ProblemEjercicio1> vertices) {
			
			super();
			this.vertex = vertex;
			this.numGuardados = numGuardados;
			this.acciones = acciones;
			this.vertices = vertices;
		}
		
		public static StateEjercicio1 of(ProblemEjercicio1 vertex, Integer numGuardados,
				 List<Integer> acciones, List<ProblemEjercicio1> vertices) {
			 return new StateEjercicio1(vertex, numGuardados, acciones, vertices);
		 }
		
		public static StateEjercicio1 of(ProblemEjercicio1 vertex) {
			
			List<ProblemEjercicio1> lista = new ArrayList<>();
			lista.add(vertex);
			
			return new StateEjercicio1(vertex, 0, vertex.actions(), lista);
		}
		
		public ProblemEjercicio1 vertex() { return vertex; }
		public Integer numGuardados() { return SolucionEjercicio1.create(vertex, vertex.actions()).tam; }
		
		SolucionEjercicio1 solucion() {
			return SolucionEjercicio1.create(BTEjercicio1.start, this.acciones); }
		
		void forward(Integer a) {
			
			this.acciones.add(a);
			ProblemEjercicio1 p = this.vertex().neighbor(a);
			this.vertices.add(p);
			if(a < DatosEjercicio1.getNumMem()) this.numGuardados++;
			this.vertex = p;
		}
		
		void back(Integer a) {
			
			this.acciones.remove(this.acciones.size() - 1);
			this.vertices.remove(this.vertices.size() - 1);
			this.vertex = this.vertices.get(this.vertices.size() - 1);
			if(a < DatosEjercicio1.getNumMem()) this.numGuardados--;
		}
	}
	
	public static ProblemEjercicio1 start;
	public static StateEjercicio1 estado;
	public static Integer maxValue;
	public static Set<SolucionEjercicio1> soluciones;
	public static SolucionEjercicio1 solucionOptima;
	
	public static void btm(List<Integer> capRestante) {
		
		BTEjercicio1.start = ProblemEjercicio1.of(0, capRestante);
		BTEjercicio1.estado = StateEjercicio1.of(start);
		BTEjercicio1.maxValue = Integer.MIN_VALUE;
		BTEjercicio1.soluciones = new HashSet<>();
		
		btm();
	}
	
	public static void btm(List<Integer> capRestante, Integer maxValue, SolucionEjercicio1 s) {
		
		BTEjercicio1.start = ProblemEjercicio1.of(0, capRestante);
		BTEjercicio1.estado = StateEjercicio1.of(start);
		BTEjercicio1.maxValue = maxValue;
		BTEjercicio1.soluciones = new HashSet<>();
		BTEjercicio1.soluciones.add(s);
		
		btm();
	}
	
	public static void btm() {
		
		if(BTEjercicio1.estado.vertex().index().equals(DatosEjercicio1.getNumArch())) {
			
			Integer valor = estado.numGuardados();
			
			if(BTEjercicio1.maxValue < valor) {
				
				BTEjercicio1.maxValue = valor;
				BTEjercicio1.soluciones.add(BTEjercicio1.estado.solucion());
				BTEjercicio1.solucionOptima = BTEjercicio1.estado.solucion();
			}
			
		} else {
			
			List<Integer> alternativas = BTEjercicio1.estado.vertex().actions();
			
			for(Integer i:alternativas) {
				
				Double cota = BTEjercicio1.estado.numGuardados() +
						HeuristicEjercicio1.cota(BTEjercicio1.estado.vertex(), i);
				
				if(BTEjercicio1.maxValue < cota) {
					
					BTEjercicio1.estado.forward(i);
					btm();
					BTEjercicio1.estado.back(i);
				}
			}
		}
	}
}
