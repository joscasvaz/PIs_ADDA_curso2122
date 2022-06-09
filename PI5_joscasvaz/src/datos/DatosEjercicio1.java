package datos;

import java.util.List;

import us.lsi.common.Files2;

public class DatosEjercicio1 {
	
	public static record Archivo(String id, Integer tam) {
		
		private static Archivo create(String linea) {
			
			String[] s = linea.split(":");
			String id = s[0].trim();
			Integer tam = Integer.valueOf(s[1].trim());
			
			return new Archivo(id, tam);
			
		}
		
		public String toString() { return id(); }
	}
	
	public static record Memoria(String id, Integer capacidad, Integer tamMax) {
		
		private static Memoria create(String linea) {
			
			String[] s = linea.split(":");
			String mem = s[0].trim();
			String[] capacidadTam = s[1].split(";");
			
			Integer capacidad = Integer.parseInt(capacidadTam[0].replace("capacidad=", "").trim());
			Integer tam_max = Integer.parseInt(capacidadTam[1].replace("tam_max=", "").trim());
			
			return new Memoria(mem, capacidad, tam_max);
		}
		
		public String toString() { return id(); }
	}
	
	public static List<Archivo> archivos; 
	public static List<Memoria> memorias;
	
	public static Integer getNumArchivos() { return archivos.size(); }
	
	public static Integer getNumMemorias() { return memorias.size(); }
	
	public static Integer getTamArch(Integer i) { return archivos.get(i).tam(); }
	
	public static Integer getCapMem(Integer j) { return memorias.get(j).capacidad(); }
	
	public static Integer getTamMaxMem(Integer j) { return memorias.get(j).tamMax(); }
	
	public static void iniDatos(String fichero) {
		
		List<String> lineas = Files2.linesFromFile(fichero);
		Integer puntero1 = lineas.indexOf("// MEMORIAS");
		Integer puntero2;
		
		if (lineas.contains("// FICHEROS")) { puntero2 = lineas.indexOf("// FICHEROS"); }
		else { puntero2 = lineas.indexOf("// ARCHIVOS"); }
		
		memorias = lineas.subList(puntero1 + 1, puntero2).stream()
				.map(Memoria::create)
				.toList();
		
		archivos = lineas.subList(puntero2 + 1, lineas.size()).stream()
				.map(Archivo::create)
				.toList();
		
	}
	
	public static void toConsole() {
		
		System.out.println("Memorias y archivos iniciales:\n");
		System.out.println("- " + memorias + "\n");
		System.out.println("- " + archivos);
	}
}
