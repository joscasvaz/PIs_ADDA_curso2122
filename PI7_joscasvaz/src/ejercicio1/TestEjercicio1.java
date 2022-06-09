package ejercicio1;

import java.util.List;
import java.util.stream.Collectors;

import ejercicio1.DatosEjercicio1.Memoria;

import us.lsi.common.Pair;
import us.lsi.common.String2;

public class TestEjercicio1 {
	
	public static String algoritmoBT = "#### Algoritmo BT ####";
	
	public static Pair<String,String> ruta(int i, int j) {
		
		String s1 = String.format("./ficheros/PI7Ej%sDatosEntrada%s.txt", i,j);
		String s2 = String.format("Para el fichero %s", s1.replace("./ficheros/", ""));
		
		return Pair.of(s1, s2);
	}
	
	public static void testEj1() {
		
		int i = 1;
		int j = 0;
		
		while(j < 2) {
			
			j++;
			
			Pair<String,String> path = ruta(i,j);
			
			String2.toConsole("%s\n%s", path.second(), algoritmoBT);
			
			DatosEjercicio1.iniDatos(path.first());
			DatosEjercicio1.toConsole();
			
			List<Integer> capRestante = DatosEjercicio1.memorias.stream()
					.map(Memoria::cap)
					.collect(Collectors.toList());
			
			BTEjercicio1.btm(capRestante);
			
			String2.toConsole("%s\n", BTEjercicio1.solucionOptima);
		}
	}
	
	public static void main(String[] args) { testEj1(); }
}
