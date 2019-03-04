package transport;

/**
 * The Transport strategy interface defines the simple travel 'contracts',
 * which must be implemented by the different types of travel objects:
 * Bus,Train or Fly. At runtime, the travel objects implement
 * their own versions of travelling between City A and City B (polymorphism).
 */

import model.City;

public interface Transport {

    void travel(City cityA, City cityB);

}
