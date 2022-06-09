package ejercicios;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import us.lsi.common.IntTrio;
import us.lsi.math.Math2;

public class Ejercicio1 {
	
	static Predicate<IntTrio> caso1 = t->t.first() < 3 && t.second() < 3 && t.third() < 3;
	static Predicate<IntTrio> caso2 = t->t.first() < 5 || t.second() < 5 || t.third() < 5;
	static Predicate<IntTrio> caso3 = t->Math2.esPar(t.first()) && Math2.esPar(t.second()) && Math2.esPar(t.third());
	static Predicate<IntTrio> base = t->caso1.test(t) || caso2.test(t); //caso base
	
	static <T> Predicate<IntTrio> stop(Map<IntTrio,T> m) { return t->m.containsKey(t); };
	
	public static String versionRecursivaNoFinal(IntTrio t) { return auxVersionRecursivaNoFinal(t, ""); } //llamada a funcion aux recursiva
	
	public static String auxVersionRecursivaNoFinal(IntTrio t, String s) { //funcion auxiliar para hacer recursion final
		
		Integer a = t.first();
		Integer b = t.second();
		Integer c = t.third();
		
		Boolean c1 = caso1.test(t);
		Boolean c3 = caso3.test(t);
		
		if(!base.test(t)) { //si no es caso base
			
			if(c3) {
				
				t = IntTrio.of(a / 2, b - 2, c / 2);
				s += Integer.toString(a * b * c);
				
			} else { //entraria en el caso de que no se den c1 c2 ni c3
				
				t = IntTrio.of(a / 3, b - 3, c / 3);
				s += Integer.toString(a + b + c);
			}
			
			return auxVersionRecursivaNoFinal(t, s); //llamada recursiva
			
		} else { //cuando sea caso base
			
			if(c1) { s += String.format("(%d)", a * b * c); } //modificacion del resultado de
			else {s += String.format("(%d)", a + b + c);}     //la llamada recursiva: no es recursion final
			
			return s;
		}
	}
	
	public static String versionRecursivaFinal(IntTrio t) {
		
		String s = ""; //acumulador
		
		Integer a = t.first();
		Integer b = t.second();
		Integer c = t.third();
		
		Boolean c1 = caso1.test(t);
		Boolean c2 = caso2.test(t);
		Boolean c3 = caso3.test(t);
		
		if(c1 || c2 || c3) { //si se dan los tres casos propuestos
			
			if(c1) { s = String.format("(%d)", a * b * c); } //caso base 1
			
			if(c2) { s = String.format("(%d)", a + b + c); } //caso base 2
			
			if(c3) {
				
				t = IntTrio.of(a / 2, b - 2, c / 2);
				s = Integer.toString(a * b * c) + versionRecursivaNoFinal(t); //llamada recursiva
			}
			
		} else { //si no se da ninguno
			
			t = IntTrio.of(a / 3, b - 3, c / 3);
			s = Integer.toString(a + b + c) + versionRecursivaNoFinal(t); //llamada recursiva
		}
		
		return s; //retornamos la cadena resultante sin modificarla: recursion final
	}
	
	public static String versionIterativa(IntTrio t) {
		
		String s = ""; //acumulador
		
		Integer a = 0;
		Integer b = 0;
		Integer c = 0;
		
		Boolean c1 = caso1.test(t);
		Boolean c3 = caso3.test(t);
		
		while(!base.test(t)) { //mientras no sea caso base
			
			a = t.first();
			b = t.second();
			c = t.third();
			
			if(c3) {
				
				t = IntTrio.of(a / 2, b - 2, c / 2);
				s += Integer.toString(a * b * c);
				
			} else {
				
				t = IntTrio.of(a / 3, b - 3, c / 3);
				s += Integer.toString(a + b + c);
			}
			
			c1 = caso1.test(t);
			c3 = caso3.test(t);
		}
		
		a = t.first(); //hay que volver
		b = t.second();//a actualizar
		c = t.third(); //los valores
		
		if(c1) { s += String.format("(%d)", a * b * c); }
		else { s += String.format("(%d)", a + b + c); }
		
		return s;
	}
	
	private static record Memoria(Integer a, Integer b, Integer c, String s) { //record muy util para version funcional
		
		private static Memoria of(Integer a,Integer b, Integer c, String s) { return new Memoria(a,b,c,s); }
		
		private Memoria refresh() { //vamos a usarlo como funcion de next
			
			Memoria m;
			
			if(mcaso3.test(Memoria.of(a, b, c, s))) { m = Memoria.of(a / 2, b - 2, c / 2, s + Integer.toString(a * b * c)); }
			else { m = Memoria.of(a / 3, b - 3, c / 3, s + Integer.toString(a + b + c)); }
			
			return m;
		}
		
	}
	
	static Predicate<Memoria> mcaso1 = m -> m.a() < 3 && m.b() < 3 && m.c() < 3; //actualizo predicados para record nuevo, este es c1
	static Predicate<Memoria> mcaso2 = m -> m.a() < 5 || m.b() < 5 || m.c() < 5; //actualizacion c2
	static Predicate<Memoria> mcaso3 = m -> Math2.esPar(m.a()) && Math2.esPar(m.b()) && Math2.esPar(m.c()); //actualizacion c3
	static Predicate<Memoria> mbase = m-> mcaso1.test(m) || mcaso2.test(m); //actualizacion casos base
	
	public static String versionFuncional(IntTrio t) {
		
		Memoria mem = Memoria.of(t.first(), t.second(), t.third(), ""); //inicializamos record
		
		Function<Memoria,String> solucion = m->{ //funcion que nos va a aplicar la solucion final con los casos base
			
			String sol = "";
			
			if(mcaso1.test(m)) { sol = String.format("(%d)", m.a() * m.b() * m.c()); } //caso base 1
			else if(mcaso2.test(m)) { sol = String.format("(%d)", m.a() + m.b() + m.c()); } //caso base 2
			
			return m.s() + sol; //actualizamos la propiedad cadena del record
		};
		
		return Stream
		.iterate( //iteramos
				mem,
				m->m.refresh()) //usamos la propiedad del record como funcion de next para actualizar su cadena
		.filter(mbase) //cuando lleguemos en la secuencia a un caso base pasa el filtro
		.findFirst()
		.map(m->solucion.apply(m)) //mapeamos el caso base para obtener la solucion
		.get(); //obtenemos finalmente la cadena resultado
	}
	
}
