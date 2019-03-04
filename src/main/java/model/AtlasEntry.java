package model;

/**
 * An extendable Atlas Entry abstract class, which may allow for different types
 * of atlas entries to be added to the system (like regions). The class defines
 * a unique 'hidden' ID for any class that inherits from it.
 */

public abstract class AtlasEntry {

    private final String ID;
    public static int counter;//counts the number of countries and cities instantiated

    public AtlasEntry() {
        ID = Integer.toString(counter++);
        //System.out.println("atlas hidden ID: "+ID );
    }

    public String getID() {
        return ID;
    }

}
