package util;

import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.IntPair;
import us.lsi.common.IntTrio;
import us.lsi.common.Matrix;
import us.lsi.common.Pair;
import us.lsi.streams.Stream2;

public class Lectores {
	
	public static List<IntTrio> lectorEj1yEj5(String path){
		
		return Stream2.file(path)
				.map(s->List.of(s.split(",")).stream() //parto por la coma
						.map(Integer::parseInt) //convierto a entero
						.toList()) //acumulo en lista
				.map(l->IntTrio.of(l.get(0), l.get(1), l.get(2))) //guardo elementos deserializados
				.toList(); //acumulado en lista
	}
	
	public static Matrix<String> lectorEj2(String path){
		
		List<List<String>> lectura = Files2.streamFromFile(path)
				.map(s->List.of(s.split(" "))) //parto por los espacios
				.toList(); //acumulo en lista
		
		String[][] arreglo = new String[lectura.size()][];
		
		for(int i=0; i < lectura.size(); i++) {
		    arreglo[i] = lectura.get(i).toArray(new String[0]); //lo paso a un array
		}
		
		return Matrix.of(arreglo); //monto matriz a partir del arreglo
	}
	
	public static List<Pair<List<Integer>,IntPair>> lectorEj3(String path){
		
		return Stream2.file(path)
				.map(s->List.of(s.split("#"))) //parto por #
				.map(l->Pair.of(
						List.of(l.get(0).split(",")).stream()
						.map(i->Integer.parseInt(i))
						.toList(), //primer elemento del par: lista de enteros partiendo por la ,
						IntPair.of(Integer.parseInt(l.get(1).split(",")[0]),
								Integer.parseInt(l.get(1).split(",")[1])))) //segundo elemento del par, otro par partiendo la ,
				.toList(); //acumulo en lista
	}
	
	public static List<Integer> lectorEj4(String path){
		
		return Stream2.file(path)
				.map(s->Integer.parseInt(s.replace("n=", ""))) //me cargo el n= y paso a entero
				.toList(); //acumulo a lista
		
	}
	
}
