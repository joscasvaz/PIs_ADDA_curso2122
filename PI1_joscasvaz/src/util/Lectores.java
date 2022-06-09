package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class Lectores {
	
	public static List<List<String>> lectorEj1y2(Integer salto,String path){
		
		List<String> lineas = Files2.linesFromFile(path); //lectura de lineas del archivo en formato string
		lineas = lineas.subList(salto, lineas.size()); //salta las n lineas que le pasamos como salto
		
		List<List<String>> res = new ArrayList<>(); //inicializamos lista resultado
		for(String s:lineas) { //recorremos cada cadena en lineas
			List<String> aux = new ArrayList<>(); //inicializamos una lista auxiliar para guardar trozos
			String[] trozos = s.split(","); //rompemos cada linea por la coma
			for(String trozo:trozos) { //recorremos los trozos resultantes de partir la linea
				aux.add(trozo); //usamos la lista auxiliar para recoger los trozos
			}
			res.add(aux); //guardamos la lista auxiliar en la lista resultado
		}
		
		return res; //retorno resultado
	}
	
	public static <T> List<Pair<T,T>> lectorEj3y4(String path, Function<String,T> f){
		
		List<List<String>> listas = lectorEj1y2(0,path); //hago lectura reutilizando codigo del primer lector
		
		List<Pair<T,T>> res = new ArrayList<>(); //inicializamos lista resultado
		for(List<String> lista:listas) { //recorro la lectura
			T i = f.apply(lista.get(0)); //aplicamos la funcion f al primer subelemento de la lectura
			T j = f.apply(lista.get(1)); //aplicamos la funcion f al segundo subelemento de la lectura
			res.add(Pair.of(i, j)); //guardo el par de subelementos en la lista resultado
		}
		
		return res; //retorno resultado
	}
	
}
