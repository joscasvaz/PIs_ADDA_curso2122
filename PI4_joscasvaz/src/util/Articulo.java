package util;

import java.util.Objects;

public class Articulo {
	
	public static Articulo ofFormat(String [] format) { return new Articulo(format); }
	
	public static Articulo ofWeight(Investigador i1, Investigador i2, Double peso) { return new Articulo(i1, i2, peso); }
	
	private static int num = 0;
	private Investigador i1;
	private Investigador i2;
	private Double peso;
	private int id;
	
	private Articulo(Investigador i1, Investigador i2, Double peso) {
		super();
		this.i1 = i1;
		this.i2 = i2;
		this.peso = peso;
		this.id = num;
		num++;
	}
	
	private Articulo(String[] format) {
		super();
		this.peso = Double.valueOf(format[2]);
		this.id = num;
		num++;
	}

	public Investigador getI1() {
		return this.i1;
	}

	public Investigador getI2() {
		return this.i2;
	}

	public Double getPeso() {
		return this.peso;
	}
	
	/*public void setPeso(Double nuevoPeso) {
		peso = nuevoPeso;
	}*/

	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i1, i2, id, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(i1, other.i1) && Objects.equals(i2, other.i2) && id == other.id
				&& Objects.equals(peso, other.peso);
	}

	@Override
	public String toString() {
		return "Articulo [i1=" + i1 + ", i2=" + i2 + ", peso=" + peso + ", id=" + id + "]";
	}
	
}
