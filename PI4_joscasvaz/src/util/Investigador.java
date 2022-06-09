package util;

import java.util.Objects;

public class Investigador {
	
	public static Investigador ofId(Integer id) { return new Investigador(id); }
	
	public static Investigador ofFormat(String [] format) { return new Investigador(format); }
	
	private Integer id;
	private Integer birth;
	private String city;

	Investigador(Integer id) { this.id = id; }
	
	private Investigador(String[] format) {
		super();
		this.id = Integer.valueOf(format[0]);
		this.birth = Integer.valueOf(format[1]);
		this.city = format[2];
	}
	
	public Integer getId() {
		return this.id;
	}

	public Integer getBirth() {
		return this.birth;
	}

	public String getCity() {
		return city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birth, city, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investigador other = (Investigador) obj;
		return Objects.equals(birth, other.birth) && Objects.equals(city, other.city) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Investigador [id=" + id + ", birth=" + birth + ", city=" + city + "]";
	}
}