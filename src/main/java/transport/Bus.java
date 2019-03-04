package transport;

import model.City;

public class Bus implements Transport {

    public Bus() {

    }
    //polymorphic implementation of Transport travel method:
    public void travel(City cityA, City cityB) {
        System.out.println("From "+cityA.getName()+" to "+cityB.getName()+" you should travel by bus.");
    }
}
