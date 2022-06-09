package ejercicio4;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class DatosEjercicio4 {
public static record Elemento(String id, Integer tam, List<String> tipos) {
		
		private static Elemento create(String s) {
			
			String[] trozos1 = s.split(":");
			String id = trozos1[0].trim();
			
			String[] trozos2 = trozos1[1].trim().split(";");
			Integer tam = Integer.valueOf(trozos2[0].trim());
			List<String> tipos = List2.parse(trozos2[1].trim().split(","), String::new);  

			return new Elemento(id, tam, tipos);
		}
		
		public String toString() { return id(); }
	}
		
	public static record Contenedor(String id, Integer capacidad, String tipo) {
			
		private static Contenedor create(String s) {
				
			String[] v = s.split(":");
			String id = v[0].trim();
			
			String[] info = v[1].trim().split(";");
			Integer capacidad = Integer.valueOf(info[0].replace("capacidad=", "").trim());
			String tipo = info[1].replace("tipo=", "").trim();
			return new Contenedor(id, capacidad, tipo);
			
		}
		
		public String toString() { return id(); }
	}
	
	public static List<Elemento> elementos;
	public static List<Contenedor> contenedores;
	
	public static Integer getNumEl() { return elementos.size(); }
	
	public static Integer getNumCont() { return contenedores.size(); }
	
	public static Integer tamEl(Integer i) { return elementos.get(i).tam(); }
	
	public static Integer capCont(Integer j) { return contenedores.get(j).capacidad(); }
	
	public static Boolean esComp(Integer i, Integer j) {
		return elementos.get(i).tipos().contains(contenedores.get(j).tipo());
	}
	
	public static Integer tipoCompatible2(Integer i, Integer j) {
		return elementos.get(i).tipos().contains(contenedores.get(j).tipo()) ? 1 : 0;
	}
	
	public static void iniDatos(String path) {
		
		List<String> lineas = Files2.linesFromFile(path);
		
		Integer puntero1 = lineas.indexOf("// CONTENEDORES");
		Integer puntero2 = lineas.indexOf("// ELEMENTOS");
		
		contenedores = lineas.subList(puntero1 + 1, puntero2).stream()
				.map(Contenedor::create)
				.toList();
		
		elementos = lineas.subList(puntero2 + 1, lineas.size()).stream()
				.map(Elemento::create)
				.toList();
	}
		
	public static void toConsole() {
		
		String separador = "========================";
		String2.toConsole("%s\nContenedores:\n%s\n%s\nElementos:\n%s\n%s",
				separador, contenedores, separador, elementos, separador);
	}
	
}
