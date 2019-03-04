package model;

import java.util.List;

/**
 * The City model
 */

public class City extends AtlasEntry {

    private int id;
    private String name;
    private int population;
    private List <String> pointsOfInterest;//each city has its own points of interest array

    public City(int id, String name,int population,List<String>pointsOfInterest) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.pointsOfInterest = pointsOfInterest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public List<String> getPointsOfInterest() {
        return pointsOfInterest;
    }

    /**
     * using a string builder to output the city attributes
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(name + " ~ Population: " + population + " | ");
        sb.append("Points of Interest: ");
        for(int i = 0; i < getPointsOfInterest().size();i++){
            sb.append(getPointsOfInterest().get(i));
            if(i == getPointsOfInterest().size()-1)
                sb.append(".");//adds full stop after the last point of interest
            else
                sb.append(", ");
        }

        return sb.toString();
    }
}
