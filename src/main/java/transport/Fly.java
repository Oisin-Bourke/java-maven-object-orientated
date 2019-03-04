package transport;

import model.City;
import model.Country;

public class Fly implements Transport {

    public Fly(){

    }

    //polymorphic implementation of Transport travel method:
    public void travel(City cityA, City cityB) {
        System.out.println("From "+cityA.getName()+" to "+cityB.getName()+" you should travel by plane.");
    }

    /**
     * The flight itinerary method is specific to the Fly class, and determines
     * how to travel between City A and City B, dependent upon the different combinations
     * of capital and non-capital cities.
     * The method uses both the Fly travel method and the Bus travel method.
     */
    public void flightItinerary(City cityA, Country countryA, City cityB, Country countryB){

        System.out.print("Travel itinerary: "+"\n");

        //if flying between two capitals:
        if(cityA.getId()==countryA.getCapitalId() && cityB.getId()==countryB.getCapitalId()){
            travel(cityA,cityB);
        }
        //if flying between capital and non capital
        if(cityA.getId()==countryA.getCapitalId() && cityB.getId()!=countryB.getCapitalId()){
            travel(cityA,countryB.getCapitalCity());
            new Bus().travel(countryB.getCapitalCity(),cityB);
        }
        //if flying between non capital and capital
        if(cityA.getId()!=countryA.getCapitalId() && cityB.getId()==countryB.getCapitalId()){
            new Bus().travel(cityA,countryA.getCapitalCity());
            travel(countryA.getCapitalCity(),cityB);
        }
        //if flying between two non capitals
        if(cityA.getId()!=countryA.getCapitalId() && cityB.getId()!=countryB.getCapitalId()){
            new Bus().travel(cityA,countryA.getCapitalCity());
            travel(countryA.getCapitalCity(),countryB.getCapitalCity());
            new Bus().travel(countryB.getCapitalCity(),cityB);
        }
    }

}
