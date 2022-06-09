package ejercicio1;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DatosEjercicio1 {
	
	public static record Archivo(String id, Integer tam) {
		
		private static Archivo create(String linea) {
			
			String[] trozos = linea.split(":");
			
			String id = trozos[0].trim();
			Integer tam = Integer.valueOf(trozos[1].trim());
			
			return new Archivo(id, tam);
		}
		
		public String toString() { return String.format("(%s: %d)",
				id(), tam()); }
	}
	
	public static record Memoria(String id, Integer cap, Integer tamMax) {
		
		private static Memoria create(String linea) {
			
			String[] trozos1 = linea.split(":");
			
			String id = trozos1[0].trim();
			
			String[] trozos2 = trozos1[1].split(";");
			
			Integer cap = Integer.parseInt(trozos2[0].replace("capacidad=", "").trim());
			Integer tamMax = Integer.parseInt(trozos2[1].replace("tam_max=", "").trim());
			
			return new Memoria(id, cap, tamMax);
		}
		
		public String toString() { return String.format("(%s: %d, %d)",
				id(), cap(), tamMax()); }
	}
	
	public static List<Archivo> archivos; 
	public static List<Memoria> memorias;
	
	public static Integer getNumArch() { return archivos.size(); }
	
	public static Integer getNumMem() { return memorias.size(); }
	
	public static Integer tamArch(Integer i) { return archivos.get(i).tam(); }
	
	public static Integer capMem(Integer j) { return memorias.get(j).cap(); }
	
	public static Integer tamMaxMem(Integer j) { return memorias.get(j).tamMax(); }
	
	public static void iniDatos(String path) {
		
		List<String> lineas = Files2.linesFromFile(path);
		
		Integer puntero1 = lineas.indexOf("// MEMORIAS");
		Integer puntero2 = lineas.indexOf("// FICHEROS");
		
		memorias = lineas.subList(puntero1 + 1, puntero2).stream()
				.map(Memoria::create)
				.toList();
		
		archivos = lineas.subList(puntero2 + 1, lineas.size()).stream()
				.map(Archivo::create)
				.toList();
	}
	
	public static void toConsole() {
		
		String separador = "========================";
		String2.toConsole("%s\nMemorias:\n%s\n%s\nArchivos:\n%s\n%s",
				separador, memorias, separador, archivos, separador);
	}
}