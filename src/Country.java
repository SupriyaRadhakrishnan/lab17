import java.util.Comparator;

public class Country {

	private String name;
	private int Population;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Country() {
		
	}
	public Country(String name, int population) {
		this.name = name;
		Population = population;
	}

	public int getPopulation() {
		return Population;
	}

	public void setPopulation(int population) {
		Population = population;
	}

	@Override
	public String toString() {
		return name +"(pop " + Population + ")";
	}


	


}
