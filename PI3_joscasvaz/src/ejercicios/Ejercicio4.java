package ejercicios;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio4 {
	
	static Predicate<String> esPalindroma = s->{
		
		Boolean b = true;
		int i = 0;
		
		while((i < s.length()/2) && b) { b = s.charAt(i) == s.charAt(s.length() - (i + 1)); i++; }
		
		return b;
	};
	
	public static Set<String> versionRecursiva(Tree<Character> arbol){
		
		Set<String> res = new HashSet<>();
		
		switch(arbol.getType()) {
		
		case Empty:
			return null;
			
		case Leaf:
			res.add(arbol.getLabel().toString());
			
		case Nary:
			res = auxVersionRecursiva(arbol, "", res);
			
		default:
			break;
		}
		
		return res;
	}
	
	private static Set<String> auxVersionRecursiva(Tree<Character> arbol, String s, Set<String> res){
		
		if(!arbol.isEmpty()) { s += arbol.getLabel().toString(); }
		
		if(arbol.isLeaf()) { if(esPalindroma.test(s)) { res.add(s); } }
		
		if(arbol.isNary()) { for(Tree<Character> t:arbol.getChildren()) { auxVersionRecursiva(t, s, res); } }
		
		return res;
	}
	
}
