package ejercicio2;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosEjercicio2 {
	
	public static record Candidato(String id,
			List<String> cualidades,
			Double sueldoMin,
			Integer valor,
			List<String> incomp) {
		
		public static Candidato create(String s) {
			
			String[] trozos1 = s.split(":");
			String id = trozos1[0].trim();
			
			String[] trozos2 = trozos1[1].trim().split(";");
			
			List<String> cualidades = List2.parse(trozos2[0].trim().split(","),
					String::new);
			Double sueldoMin = Double.valueOf(trozos2[1].trim());
			Integer valoracion = Integer.valueOf(trozos2[2].trim());
			List<String> incompatibilidades = List2.parse(trozos2[3].trim().split(","),
					String::new); 
			
			return new Candidato(id, cualidades, sueldoMin, valoracion, incompatibilidades);
		}
		
		public String toString() {
			
			return String.format("%s: %s; %s, %d; %s",
					id(), cualidades(), sueldoMin(), valor(), incomp());
		}
	}
	
	public static List<String> cualidadesDeseadas;
	private static Integer presupuesto;
	public static List<Candidato> candidatos;
	
	public static Integer getNumCand() { return candidatos.size(); }
	
	public static Integer getNumCualDes() { return cualidadesDeseadas.size(); }
	
	public static Integer getPresup() { return presupuesto; }
	
	public static Integer valorCand(Integer i) { return candidatos.get(i).valor(); }
	
	public static Double sueldoMin(Integer i) { return candidatos.get(i).sueldoMin(); }
	
	public static Boolean esComp(Integer i, Integer k) {
		return candidatos.get(i).incomp().contains(candidatos.get(k).id());
	}
	
	public static Integer tieneCualidad(Integer i, Integer j) {
		return candidatos.get(i).cualidades().contains(cualidadesDeseadas.get(j)) ? 1 : 0;
	}
	
	public static void iniDatos(String path) {
		
		List<String> lineas = Files2.linesFromFile(path);
		
		cualidadesDeseadas = List2.parse(lineas.get(0).replace("Cualidades Deseadas:", "").trim()
				.split(","), String::new);
		
		presupuesto = Integer.valueOf(lineas.get(1).replace("Presupuesto Máximo:", "").trim());
		
		candidatos = lineas.subList(lineas.indexOf("Presupuesto Máximo: " + presupuesto) + 1,
				lineas.size()).stream()
				.map(Candidato::create)
				.toList();
	}
	
	public static void toConsole() {
		
		String separador = "========================";
		String2.toConsole("%s\nCandidatos:\n%s\n%s", separador, candidatos, separador);
	}
}
