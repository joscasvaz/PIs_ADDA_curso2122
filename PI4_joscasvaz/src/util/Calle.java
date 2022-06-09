package util;

import java.util.Objects;

public class Calle {

	public static Calle ofFormat(String [] format) { return new Calle(format); }
	
	public static Calle ofWeight(Cruce c1, Cruce c2, Double t, Double e) { return new Calle(c1, c2, t, e); }
	
	private static int num = 0;
	private Cruce c1;
	private Cruce c2;
	private Double t;
	private Double e;
	private int id;
	
	private Calle(Cruce c1, Cruce c2, Double t, Double e) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.t = t;
		this.e = e;
		this.id = num;
		num++;
	}
	
	private Calle(String[] format) {
		super();
		this.t = Double.valueOf(String.valueOf(format[2].charAt(0)));
		this.e = Double.valueOf(String.valueOf(format[3].charAt(0)));
		this.id = num;
		num++;
	}

	public static int getNum() {
		return num;
	}

	public Cruce getC1() {
		return c1;
	}

	public Cruce getC2() {
		return c2;
	}

	public Double getT() {
		return t;
	}

	public Double getE() {
		return e;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(c1, c2, e, id, t);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calle other = (Calle) obj;
		return Objects.equals(c1, other.c1) && Objects.equals(c2, other.c2) && Objects.equals(e, other.e)
				&& id == other.id && Objects.equals(t, other.t);
	}

	@Override
	public String toString() {
		return "Calle [c1=" + c1 + ", c2=" + c2 + ", t=" + t + ", e=" + e + ", id=" + id + "]";
	}
	
}
