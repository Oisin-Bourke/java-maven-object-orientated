import model.AtlasEntry;
import model.City;
import model.Country;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class used to deserialize the json atlas data
 */

public class SingleAtlas {

    private static SingleAtlas ourInstance = new SingleAtlas();

    //a method to get ourInstance:
    public static SingleAtlas getInstance() {

        if(ourInstance == null) {
            ourInstance = new SingleAtlas();
        }
        return ourInstance;
    }

    private SingleAtlas() { }// a private constructor ensures only 'ourInstance' is made

    /**
     *  This method de-serializes the 'countries' data from the data.json file into an array list.
     *  Three loops:
     *  1) to get the countries data
     *      2) get the city data for that country
     *          3) get the points of interest data for that city
     *  The method catches any anomalies in the json data and allows the program to continue.
     */
    protected List<Country> initializeAtlasFromJSON() {

            List<Country> atlasList = new ArrayList<Country>();

            try{
            InputStream inputStream = ourInstance.getClass().getResourceAsStream("/data.json");
            JSONTokener jsonTokener = new JSONTokener(inputStream);

            JSONObject data = new JSONObject(jsonTokener);
            //System.out.println(data.has("countries"));//true

            if (data.has("countries")) {

                JSONArray countriesArray = data.getJSONArray("countries");

                //1) deserialise country data:
                for (int i = 0; i < countriesArray.length(); i++) {

                    int id = countriesArray.getJSONObject(i).getInt("id");
                    String name = countriesArray.getJSONObject(i).getString("name");
                    int capital = countriesArray.getJSONObject(i).getInt("capital");
                    JSONArray citiesArray = countriesArray.getJSONObject(i).getJSONArray("cities");

                    List<City> cityList = new ArrayList<City>();

                    //2) deserialise city data:
                    for (int j = 0; j < citiesArray.length(); j++) {
                        int id2 = citiesArray.getJSONObject(j).getInt("id");
                        String name2 = citiesArray.getJSONObject(j).getString("name");
                        int population = citiesArray.getJSONObject(j).getInt("population");

                        List<String> interestList = new ArrayList<String>();

                        JSONArray interestArray = citiesArray.getJSONObject(j).getJSONArray("interest");

                        //3) deserialise points of interest:
                        for (int k = 0; k < interestArray.length(); k++) {
                            String item = interestArray.getString(k);
                            interestList.add(item);
                        }
                        cityList.add(new City(id2, name2, population, interestList));//make each city and add to city list
                    }
                    atlasList.add(new Country(id, name, capital, cityList));//make each country and add to country list
                }
            }
        }catch (JSONException e){
            System.out.println("Error in json data @: "+e.getMessage());
        }

        return atlasList;//returning the full atlas array list
    }


}
