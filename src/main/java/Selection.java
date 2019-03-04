import model.City;
import model.Country;
import java.util.List;
import java.util.Scanner;

/**
 * A Selection class to help the main method with processing user inputs
 * 1) methods to search the atlas for a country or a city
 * 2) methods to ensure valid user input.
 */
public class Selection {

    //a method to retrieve the country from the atlas
    public Country selectCountry(String input, List<Country> theAtlas){

        Country selectedCountry = null;

        for (Country country : theAtlas){

            if(country.getName().equals(input)){
                selectedCountry = country;//if user input matches country
            }
        }

        return selectedCountry;
    }

    //a method to retrieve the city from the country list
    public City selectCity(String input, Country country){

        City selectedCity = null;

        for (City city : country.getCityList()){

            if (city.getName().equals(input)){
                selectedCity = city;//if user input matched city
            }
        }

        return selectedCity;
    }

    /**
     * A method to ensure that the scanned user country input finds a match
     * in the atlas. The loops breaks only when a valid input is found and
     * 'start' = false.  The method uses this class's selectCountry method.
     */
    public Country ensureValidCountry(List <Country> theAtlas){

        Scanner scanner = new Scanner(System.in);
        Country country = null;

        boolean start = true;
        while(start){
            String a = scanner.nextLine();
            country = selectCountry(a,theAtlas);
            if (country!= null)
                start = false;//break loop
            else
                System.out.print("ENTER a valid country: ");
        }

        return country;
    }

    /**
     * A method to ensure that the scanned user city input finds a match
     * in the country list. The loops breaks only when a valid input is found and
     * 'start' = false.  The method uses this class's selectCity method.
     */
    public City ensureValidCity(Country country){

        Scanner scanner = new Scanner(System.in);
        City city = null;

        boolean start = true;
        while(start){
            String a = scanner.nextLine();
            city = selectCity(a,country);
            if (city!= null)
                start = false;//break loop
            else
                System.out.print("ENTER a valid city: ");
        }

        return city;
    }

}
