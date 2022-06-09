package ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ejercicio1.DatosEjercicio1;
import ejercicio2.DatosEjercicio2.Candidato;

public class BTEjercicio2 {
	
	public static class StateEjercicio2 {
	
		private ProblemEjercicio2 vertex;
		private static List<Candidato> seleccionados;
		private List<Integer> acciones;
		private List<ProblemEjercicio2> vertices;
		
		private StateEjercicio2(ProblemEjercicio2 vertex, List<Integer> acciones,
				List<Candidato> seleccionados, List<ProblemEjercicio2> vertices) {
			
			super();
			this.vertex = vertex;
			//this.seleccionados = seleccionados;
			this.acciones = acciones;
			this.vertices = vertices;
		}
		
		public static StateEjercicio2 of(ProblemEjercicio2 vertex, List<Candidato> seleccionados,
				 List<Integer> acciones, List<ProblemEjercicio2> vertices) {
			 return new StateEjercicio2(vertex, acciones, seleccionados, vertices);
		 }
		
		public static StateEjercicio2 of(ProblemEjercicio2 vertex) {
			
			
			List<ProblemEjercicio2> vertices = new ArrayList<>();
			vertices.add(vertex);
			
			return new StateEjercicio2(vertex, vertex.actions(), seleccionados, vertices);
		}
		
		public ProblemEjercicio2 vertex() { return vertex; }
		public List<Candidato> seleccionados() { return SolucionEjercicio2.seleccionados; }
		public Double valor() {
			return SolucionEjercicio2.valor;
		}
		
		SolucionEjercicio2 solucion() {
			return SolucionEjercicio2.create(BTEjercicio2.start, this.acciones); }
		
		void forward(Integer a) {
			
			this.acciones.add(a);
			ProblemEjercicio2 p = this.vertex().neighbor(a);
			this.vertices.add(p);
			//this.seleccionados = this.seleccionados();
			this.vertex = p;
		}
		
		void back(Integer a) {
			
			this.acciones.remove(this.acciones.size() - 1);
			this.vertices.remove(this.vertices.size() - 1);
			this.vertex = this.vertices.get(this.vertices.size() - 1);
			//this.seleccionados = this.seleccionados();
		}
	}
	
	public static ProblemEjercicio2 start;
	public static StateEjercicio2 estado;
	public static Integer maxValue;
	public static Set<SolucionEjercicio2> soluciones;
	
	public static void btm(Integer guardadosInicial) {
		
		BTEjercicio2.start = ProblemEjercicio2.of(0, new ArrayList<>(), 0);
		BTEjercicio2.estado = StateEjercicio2.of(start);
		BTEjercicio2.maxValue = Integer.MIN_VALUE;
		BTEjercicio2.soluciones = new HashSet<>();
		btm();
	}
	
	public static void btm(Integer guardadosInicial, Integer maxValue, SolucionEjercicio2 s) {
		
		BTEjercicio2.start = ProblemEjercicio2.of(0, new ArrayList<>(), 0);
		BTEjercicio2.estado = StateEjercicio2.of(start);
		BTEjercicio2.maxValue = maxValue;
		BTEjercicio2.soluciones = new HashSet<>();
		BTEjercicio2.soluciones.add(s);
		btm();
	}
	
	public static void btm() {
		
		if(BTEjercicio2.estado.vertex().index().equals(DatosEjercicio1.getNumArch())) {
			
			Integer valor = estado.valor().intValue();
			if(BTEjercicio2.maxValue < valor) {
				
				BTEjercicio2.maxValue = valor;
				BTEjercicio2.soluciones.add(BTEjercicio2.estado.solucion());
			}
			
		} else {
			
			List<Integer> alternativas = BTEjercicio2.estado.vertex().actions();
			for(Integer i:alternativas) {
				
				Double cota = BTEjercicio2.estado.valor() +
						HeuristicEjercicio2.cota(BTEjercicio2.estado.vertex(), i);
				
				if(BTEjercicio2.maxValue < cota) continue;
				
				BTEjercicio2.estado.forward(i);
				btm();
				BTEjercicio2.estado.back(i);
			}
		}
	}
	
}
