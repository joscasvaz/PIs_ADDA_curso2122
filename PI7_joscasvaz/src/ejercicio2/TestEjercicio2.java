package ejercicio2;

import us.lsi.common.Pair;
import us.lsi.common.String2;
import ejercicio1.TestEjercicio1;

public class TestEjercicio2 {

	public static void testEj2() {
		
		int i = 2;
		int j = 0;
		
		while(j < 2) {
			
			j++;
			
			Pair<String,String> path = TestEjercicio1.ruta(i,j);
			
			String2.toConsole("%s\n%s", path.second(), TestEjercicio1.algoritmoBT);
			
			DatosEjercicio2.iniDatos(path.first());
			DatosEjercicio2.toConsole();
		}
	}
	
	public static void main(String[] args) { testEj2(); }

}
