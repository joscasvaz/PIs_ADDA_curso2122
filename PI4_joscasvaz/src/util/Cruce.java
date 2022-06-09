package util;

import java.util.Objects;

public class Cruce {
	
	public static Cruce ofId(Integer id) { return new Cruce(id); }
	
	public static Cruce ofFormat(String [] format) { return new Cruce(format); }
	
	private Integer id;
	private Boolean hmi;
	private String m;
	private Integer i;
	
	private Cruce(Integer id) { this.id = id; }
	
	private Cruce(String[] format) {
		this.id = Integer.decode(format[0]);
		this.hmi = Boolean.valueOf(format[1]);
		
		if(this.hmi) {
			
			this.m = format[2];
			this.i = Integer.decode(String.valueOf(format[3].charAt(0)));
			
		} else {
			
			this.m = "";
			this.i = 0;
		}
	}

	public Integer getId() {
		return id;
	}

	public Boolean getHmi() {
		return hmi;
	}

	public String getM() {
		return m;
	}

	public Integer getI() {
		return i;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hmi, i, id, m);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cruce other = (Cruce) obj;
		return Objects.equals(hmi, other.hmi) && Objects.equals(i, other.i) && Objects.equals(id, other.id)
				&& Objects.equals(m, other.m);
	}

	@Override
	public String toString() {
		return "Cruce [id=" + id + ", hmi=" + hmi + ", m=" + m + ", i=" + i + "]";
	}
	
}
