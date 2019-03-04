package transport;

import model.City;

/**
 * The TravelContext class provides a single conduit access
 * to the Transport strategy interface
 */

public class TravelContext {

    private Transport transport;

    public TravelContext(Transport transport){
        this.transport = transport;
    }

    //provides for polymorphic implementation:
    public void executeTransportStrategy(City cityA, City cityB){
        transport.travel(cityA,cityB);
    }

}
