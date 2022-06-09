package util;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Pair;
import us.lsi.streams.Stream2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Lectores {
	
	public static <T> List<Tree<T>> lectorArbolesNArios(String path, Function<String,T> parser){
		
		return Stream2.file(path)
				.map(t->Tree.parse(t, parser))
				.toList();
	}
	
	public static <T> List<BinaryTree<T>> lectorArbolesBinarios(String path, Function<String,T> parser){
		
		return Stream2.file(path)
				.map(t->BinaryTree.parse(t, parser))
				.toList();
	}
	
	public static List<Pair<BinaryTree<Integer>,Integer>> lectorEj2(String path){
		
		return Stream2.file(path)
				.map(l->l.split("#"))
				.map(p->Pair.of(BinaryTree.parse(p[0], s->Integer.decode(s)), Integer.decode(p[1])))
				.toList();
	}
	
}
