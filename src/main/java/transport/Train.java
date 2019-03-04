package transport;

import model.City;

public class Train implements Transport {

    public Train(){

    }
    //polymorphic implementation of Transport travel method:
    public void travel(City cityA, City cityB) {
        System.out.println("From " + cityA.getName() + " to " + cityB.getName() + " you should travel by train.");

    }
}
