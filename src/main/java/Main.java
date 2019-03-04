import java.util.List;
import model.AccessBordering;
import model.City;
import model.Country;
import transport.Bus;
import transport.Fly;
import transport.Train;
import transport.TravelContext;

/**
 * The Main class simply contains the main method, where the atlas
 * is instantiated from data.json by the singleton instance,
 * and where the program receives user input and generates
 * the appropriate output.
 */
public class Main {

    public static void main(String []args){

        final List <Country> theAtlas;//a final local variable to hold the atlas

        //using the singleton SingleAtlas class to initialise the atlas
        SingleAtlas atlas = SingleAtlas.getInstance();
        theAtlas = atlas.initializeAtlasFromJSON();

        //System.out.println(AtlasEntry.counter);number of cities and countries instantiated

        System.out.println("*** Welcome to the Atlas Journey Planner ***\n");

        //printing the entire atlas to the console:
        for(Country country : theAtlas){
            System.out.println(country);
        }

        Selection selection = new Selection();//an instance of Selection to help process user input

        //ensuring valid place name inputs:

        System.out.print("ENTER your country of origin: ");
        Country countryA = selection.ensureValidCountry(theAtlas);

        System.out.print("ENTER your city of origin: ");
        City cityA = selection.ensureValidCity(countryA);

        System.out.print("ENTER the destination country: ");
        Country countryB = selection.ensureValidCountry(theAtlas);

        System.out.print("ENTER the destination city: ");
        City cityB = selection.ensureValidCity(countryB);

        //what path to take:
        System.out.println();

        /**
         * Calling the compareTo method with countryA against countryB
         * to get the travelCode (0 or 1 or -1).
         */
        int travelCode = countryA.compareTo(countryB);
        //System.out.println("The travel code is: "+travelCode);

        /**
         * The switch statement takes the travel code from the compareTo method
         * and gives the appropriate travel instructions by way of the strategy
         * interface using the TravelContext conduit.
         *          0 = Bus
         *          1 = Train
         *          -1 = Fly
         * If the two cities are in countries A and B that do not border (-1),
         * but have a mutual border with a third Country C, the user will be
         * advised to travel by train via the capital of Country C
         * (ie France and Netherlands are not bordering but both border Belgium)
         */

        TravelContext travelContext;//an instance of TravelContext to assess the strategy interface

            switch (travelCode){
                case 0:
                    travelContext = new TravelContext(new Bus());
                    travelContext.executeTransportStrategy(cityA,cityB);
                    break;
                case 1:
                    travelContext = new TravelContext(new Train());
                    travelContext.executeTransportStrategy(cityA,cityB);
                    break;
                case -1:
                    travelContext = new TravelContext(new Fly());
                    travelContext.executeTransportStrategy(cityA,cityB);
                    new Fly().flightItinerary(cityA,countryA,cityB,countryB);

                    /** alternative to flying calls the checkMutualBordering method.
                     * if the method finds a match (both counties have a shared border with a 3rd country),
                     * and does not return null, we use viaCountryC getCapitalCity as the 'viaCityC'.
                     * We then use a train object to travel from: cityA ==> viaCityC ==> cityB
                     * **/
                    String countryC = AccessBordering.checkMutualBordering(countryA,countryB);
                    if(countryC!=null){
                        Country viaCountryC = selection.selectCountry(countryC,theAtlas);
                        City viaCityC = viaCountryC.getCapitalCity();

                        System.out.println("If you prefer not to fly, alternatively you can go by train via "+countryC+".");
                        travelContext = new TravelContext(new Train());
                        travelContext.executeTransportStrategy(cityA,viaCityC);// A ==> C
                        travelContext.executeTransportStrategy(viaCityC,cityB);// C ==> B
                    } else {
                        System.out.println("No viable alternative to flying found.");
                    }
                    break;

                default:break;
            }
    }
}
