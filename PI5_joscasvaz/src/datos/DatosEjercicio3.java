package datos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;

public class DatosEjercicio3 {
	
	public static record Componente(String id, Integer tProd, Integer tElab) {
		
		private static Componente create(String s) {
			
			String[] v = s.split(":");
			String id = v[0].trim();
			
			String[] v2 = v[1].split(";");
			
			Integer tProd = Integer.parseInt(v2[0].replace("prod=", "").trim());
			Integer tElab = Integer.parseInt(v2[1].replace("elab=", "").trim());
			
			return new Componente(id, tProd, tElab);
		}
		
		public String toString() { return id(); }
	}
	
	public static record Producto(String id, Integer precio, Map<Componente, Integer> numComp, Integer maxUds) {
		
		private static Producto create(String s) {
			
			String[] v = s.split("->");
			String id = v[0].trim();
			
			String[] v1 = v[1].split(";");
			Integer precio = Integer.parseInt(v1[0].replace("precio=", "").trim());
			
			Map<Componente, Integer> numComp = new HashMap<>();
			String[] parseaMap = v1[1].replace("comp=", "").replace("(", "").replace(")", "").split(",");
			
			for (String comp: parseaMap) {
				
				String idComp = comp.substring(0, comp.indexOf(":"));
				
				Componente c = componentes.stream()
						.filter(componente->componente.id().equals(idComp.trim()))
						.findFirst()
						.get();
				
				Integer num = Integer.valueOf(comp.substring(comp.indexOf(":") + 1,
						comp.length()).trim());
				
				numComp.put(c, num);
			}
			
			Integer maxUds = Integer.valueOf(v1[2].replace("max_u=", "").trim());
			
			return new Producto(id, precio, numComp, maxUds);
		}
		
		public String toString() { return id(); }
	}
	
	public static List<Componente> componentes;
	public static List<Producto> productos;
	private static int TMaxProd;
	private static int TMaxManual;
	
	public static Integer getNumProd() { return productos.size(); }
	
	public static Integer getNumComp() { return componentes.size(); }
	
	public static Integer getMaxTProducc() { return TMaxProd; }
	
	public static Integer getMaxTManual() { return TMaxManual; }
	
	public static Integer getIngresos(Integer i) { return productos.get(i).precio(); }
	
	public static Integer getMaxUds(Integer i) { return productos.get(i).maxUds(); }
	
	public static Integer getUdsCompProd(Integer i, Integer j) {
		return productos.get(i).numComp().get(componentes.get(j));
	}
	
	/*Integer getNumProd()
Integer getNumComp()

Integer getMaxTProducc()
Integer getMaxTManual()

Integer getTCompProdProducc(Integer i, Integer j)
Integer getTCompProdElab(Integer i, Integer j)

Integer getIngresos(Integer i)
Integer getMaxUds(Integer i)

Integer n = getNumProd()
Integer m = getNumComp()*/
	
	public static Boolean tieneComponente(Integer i, Integer j) {
		return productos.get(i).numComp().keySet().contains(componentes.get(j));
	}
	
	public static Integer getTiempoProd(Integer j) { return componentes.get(j).tProd(); }
	
	public static Integer getTiempoElab(Integer j) { return componentes.get(j).tElab(); }
	
	public static Integer getTCompProdProducc(Integer i, Integer j) {
		return getTiempoProd(j)*getUdsCompProd(i, j);
	}
	
	public static Integer getTCompProdElab(Integer i, Integer j) {
		return getTiempoElab(j)*getUdsCompProd(i, j);
	}
	
	public static void datos(String fichero) {
		
		List<String> lineas = Files2.linesFromFile(fichero);
		
		TMaxProd = Integer.valueOf(lineas.get(0).replace("T_prod = ", "").trim());
		TMaxManual = Integer.valueOf(lineas.get(1).replace("T_manual = ", "").trim());
		
		Integer puntero1 = lineas.indexOf("// COMPONENTES");
		Integer puntero2 = lineas.indexOf("// PRODUCTOS");
		
		componentes = lineas.subList(puntero1 + 1, puntero2).stream()
				.map(Componente::create)
				.toList();
		
		productos = lineas.subList(puntero2 + 1, lineas.size()).stream()
				.map(Producto::create)
				.toList();
		
	}
	
	public static void toConsole() {
		
		System.out.println("Lista de componentes:\n");
		for (Componente c: componentes) {
			System.out.println("- " + c);
		}
		System.out.println("*********************");
		System.out.println("Lista de productos:\n");
		for (Producto p: productos) {
			System.out.println("- " + p);
		}
	}
}
