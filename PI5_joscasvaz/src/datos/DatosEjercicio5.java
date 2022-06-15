package datos;

import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class DatosEjercicio5 {
	
	public static SimpleWeightedGraph<Ciudad, Carretera> gf;
	
	public static Integer n;
	public static Integer hab;
	public static Double kms;
	
	public static IntegerVertexGraphView<Ciudad, Carretera> graph;
	
	public static Predicate<Ciudad> predCiudad;
	public static Predicate<Carretera> predCarretera;
	
	public static Ciudad origen;
	public static Ciudad destino;
	
	public static void iniDatos(String fichero) {
		
		gf = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				Graphs2::simpleWeightedGraph,
				Carretera::km);
		
		graph = IntegerVertexGraphView.of(gf);
		n = graph.vertexSet().size();
	}
	
	public static void predicadosOrigenDestino(String fichero, Integer i) {
		
		List<String> lineas = Files2.linesFromFile(fichero);
		
		lineas = lineas.stream()
				.map(String::trim)
				.toList();
		
		Integer puntero = lineas.indexOf("PI5Ej5DatosEntrada" + i + ".txt:");
		
		if (puntero == 0) {
			
			hab = Integer.parseInt(lineas.get(puntero + 1)
					.replace("- Al menos una ciudad intermedia con mas de", "")
					.replace("habitantes", "")
					.trim());
			
			predCiudad = v -> v.habitantes() > hab;
			
			kms = Double.parseDouble(lineas.get(puntero + 2)
					.replace("- Al menos una carretera con mas de", "")
					.replace("kms", "")
					.trim());
			
			predCarretera = e -> e.km() > kms;
			
		} else if (puntero == 5) {
			
			hab = Integer.parseInt(lineas.get(puntero + 1)
					.replace("- Al menos una ciudad intermedia con máximo", "")
					.replace("habitantes", "").trim());
			predCiudad = v -> v.habitantes() <= hab;
			
			kms = Double.parseDouble(lineas.get(puntero + 2)
					.replace("- Al menos una carretera con al menos", "")
					.replace("kms", "").trim());
			predCarretera = e -> e.km() >= kms;
			
		} else {
			
			hab = Integer.parseInt(lineas.get(puntero + 1)
					.replace("- Al menos una ciudad intermedia con mas de", "")
					.replace("habitantes", "").trim());
			predCiudad = v -> v.habitantes() > hab;
			
			kms = Double.parseDouble(lineas.get(puntero + 2)
					.replace("- Al menos una carretera con menos de", "")
					.replace("kms", "").trim());
			predCarretera = e -> e.km() < kms;
		}
		
		String[] origenDestino = lineas.get(puntero + 3).split(";");
		
		origen = gf.vertexSet().stream()
				.filter(c -> c.nombre()
						.equals(origenDestino[0]
								.replace("- Origen:", "")
								.trim()))
				.findFirst()
				.get();
		
		destino = gf.vertexSet().stream()
				.filter(c -> c.nombre()
						.equals(origenDestino[1]
								.replace("Destino:", "")
								.trim()))
				.findFirst()
				.get();
	}
	
}
