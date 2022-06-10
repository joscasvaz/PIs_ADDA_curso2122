package geneticos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.DatosEjercicio2;
import datos.DatosEjercicio2.Candidato;
import soluciones.SolucionEjercicio2;
import us.lsi.ag.BinaryData;

public class GeneticoEjercicio2 implements BinaryData<SolucionEjercicio2>{
	
	List<Candidato> candidatos = DatosEjercicio2.candidatos;
	List<String> cualidadesDeseadas = DatosEjercicio2.cualidadesDeseadas;
	Integer presupuesto = DatosEjercicio2.getPresupuesto();
	
	private Double goal;
	private Double penalty;
	private Double fitness = null;

	@Override
	public Integer size() { return DatosEjercicio2.getNumCandidatos(); }
	
	private void calcula(List<Integer> ls) {
		
		goal = .0;
		penalty = .0;
		
		List<String> seleccionados = new ArrayList<>();
		
		Double salarioTotal = .0;
		Set<String> cualidadesCubiertas = new HashSet<>();
		Set<String> incompatibilidadesTotales = new HashSet<>();
		
		int i = 0;
		
		while(i < ls.size()) {
			
			if(ls.get(i).equals(1)) {
				
				Candidato candidato = candidatos.get(i);
				
				seleccionados.add(candidato.id());
				
				Integer valoracion = candidato.valoracion();
				goal += valoracion;
				
				Double salario = candidato.salario();
				salarioTotal += salario;
				
				List<String> cualidades = candidato.cualidades();
				cualidadesCubiertas.addAll(cualidades);
				
				List<String> incompatibilidades = candidato.incompatibilidades();
				incompatibilidadesTotales.addAll(incompatibilidades);
			}
			
			i++;
		}
		
		if(presupuesto < salarioTotal) { penalty += salarioTotal - presupuesto; }
		
		if(!cualidadesCubiertas.containsAll(cualidadesDeseadas)) {
			
			cualidadesDeseadas.removeAll(cualidadesDeseadas);
			penalty += cualidadesDeseadas.size();
		}
		
		for(String id:seleccionados) {
			if(incompatibilidadesTotales.contains(id)) { penalty++; } }
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		
		calcula(ls);
		Integer kP = candidatos.size();
		
		fitness = goal - kP * penalty;
		
		return fitness;
	}

	@Override
	public SolucionEjercicio2 solucion(List<Integer> value) { return SolucionEjercicio2.create(value); }
	
	public static GeneticoEjercicio2 of() { return new GeneticoEjercicio2(); }
	
}
