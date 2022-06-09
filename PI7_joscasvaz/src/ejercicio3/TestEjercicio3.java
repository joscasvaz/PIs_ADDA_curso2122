package ejercicio3;

import ejercicio1.TestEjercicio1;
import us.lsi.common.Pair;
import us.lsi.common.String2;

public class TestEjercicio3 {
	
	public static String algoritmoPD = "#### Algoritmo PD ####";
	
	public static void testEj3() {
		
		int i = 3;
		int j = 0;
		
		while(j < 2) {
			
			j++;
			
			Pair<String,String> path = TestEjercicio1.ruta(i,j);
			
			String2.toConsole("%s\n%s", path.second(), algoritmoPD);
			
			DatosEjercicio3.iniDatos(path.first());
			DatosEjercicio3.toConsole();
		}
	}
	
	public static void main(String[] args) { testEj3(); }

}
