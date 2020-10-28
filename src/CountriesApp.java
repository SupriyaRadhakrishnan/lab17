
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.Comparator;

public class CountriesApp{

	private static  Path filename = Paths.get("Countries.txt");
	private static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       
		int userChoice =0;
		Country country = new Country();
		do {
			System.out.println("Welcome to Countries Maintenance Application!");
			System.out.println("1 See all the countries\n2 Add a country \n3 Delete a country\n4 Modify a country \n5 Exit");
			userChoice = Validator.getInt(scnr, "Enter menu Number");
			if(userChoice<1 || userChoice>5)
				System.out.println("Invalid Selction.Try Again!");
			else if(userChoice ==1)
			{
				try {
					List<Country> listOfCountries = listCountries();
					
										
					System.out.println(listOfCountries.toString());
					
				} catch (IOException e) {
					System.out.println("Unable to read file.");
				}
				
			}
			else if(userChoice ==2)
			{
			String name = Validator.getString(scnr, "Enter Country: ");
			int population = Validator.getInt(scnr, "Enter Population(in millions): ");
	
			try {
				addToFile(new Country(name,population));
			} catch (IOException e) {
				
				System.out.println("Unable to write to file.");
			}
			}
			else if(userChoice ==3)
			{
			String name = Validator.getString(scnr, "Enter Country: ");
			try {
				deleteFromFile(name);
			} catch (IOException e) {
				
				System.out.println("Unable to write to file.");
			}
			
			}
			else if(userChoice ==4)
			{
			String name = Validator.getString(scnr, "Enter Country: ");
			int population = Validator.getInt(scnr, "Enter Population(in millions): ");
	
			try {
				modify(new Country(name,population));
			} catch (IOException e) {
				
				System.out.println("Unable to write to file.");
			}
			}
		}while(!(userChoice==5));
		System.out.println("GoodBye!");
	}
	
	public static List<Country> listCountries() throws IOException
	{
		List<String> allLines = Files.readAllLines(filename);
		allLines.remove(null);
		List<Country> listOfCountries = new ArrayList<Country>();
		List<String> names = new ArrayList<String>();
		List<Integer> pops = new ArrayList<>();
		System.out.println("1 List Countries Aplhabetically \n2 List Countries by Population");
	int userInput =  Validator.getInt(scnr, "Enter Option: ");
//		
		for(String line:allLines)
		{
			String[] values = line.split(":");
			names.add(values[0]);
			pops.add(new Integer(values[1]));
			listOfCountries.add(new Country(values[0],Integer.parseInt(values[1])));
		}
		if(userInput ==2)
		{
				Comparator<Country> com = new PopulationComparator();
				Collections.sort(listOfCountries,com);
		}
		else if(userInput ==1)
		{	
				Collections.sort(listOfCountries,new Comparator<Country>() {

					@Override
					public int compare(Country o1, Country o2) {
						
						return o1.getName().compareTo(o2.getName());
					}
					
				});
		}
		return listOfCountries;
	}
	
	public static void addToFile(Country country) throws IOException
	{
		String line = country.getName() +":" + country.getPopulation();
		
		
		List<String> lines = Collections.singletonList(line);
		Files.write(filename, lines, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
	public static void deleteFromFile(String name) throws IOException
	{
		List<String> allLines = Files.readAllLines(filename);
		Files.newBufferedWriter(filename , StandardOpenOption.TRUNCATE_EXISTING);
		String line ="";
		for(String eachLine:allLines)
		{
			String[] values = eachLine.split(":");
			if(values[0].equals(name));
			else
			{		
				addToFile(new Country(values[0],Integer.parseInt(values[1])));
			}
		}
			
	}
	public static void modify(Country country) throws IOException
	{
		//String line = country.getName() +":" + country.getPopulation();
		List<String> allLines = Files.readAllLines(filename);
		Files.newBufferedWriter(filename , StandardOpenOption.TRUNCATE_EXISTING);
		for(String eachLine:allLines)
		{
			String[] values = eachLine.split(":");
			if(values[0].equals(country.getName()))
			{
				addToFile(new Country(country.getName(),country.getPopulation()));
			}
			else
			{		
				addToFile(new Country(values[0],Integer.parseInt(values[1])));
			}
		}
			
	}

	
}
