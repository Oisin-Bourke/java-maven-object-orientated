package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

/**
 * This class accesses the json 'bordering' data:
 *  1) To check if the two countries are bordering
 *  2) To check if two countries that are not bordering have a mutual bordering country
 *  I do not de-serialise the bordering data.
 */

public class AccessBordering {

    private static AccessBordering bordering = new AccessBordering();

    /**
     * Access json bordering data at the property key of selectedCountryA, and check
     * whether selectedCountryB's id is in the associated array, returning 'true' if
     * matching ids are found, or 'false' if they are not bordering.
     */
    static boolean checkBordering(Country selectedCountryA,Country selectedCountryB){

            InputStream inputStream = bordering.getClass().getResourceAsStream("/data.json");
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            JSONObject jsonObject = new JSONObject(jsonTokener);

            if(jsonObject.has("bordering")) {

                JSONObject jsonBordering = jsonObject.getJSONObject("bordering");
                JSONArray list = jsonBordering.getJSONArray(String.valueOf(selectedCountryA.getId()));//the property 'key'

                //check if list contains destination country id
                for (int i = 0; i < list.length(); i++) {

                    int x = list.getInt(i);

                    if (x == selectedCountryB.getId()) return true;//if bordering

                }
            }
            return false;//if not bordering
    }

    /**
     * This method is used to check whether two countries that do not border, share a mutual
     * border with another country 'C', by comparing both arrays for
     * matching ids. If there is a match, we use a json data sweep to access the name of that
     * country, returning the simple string (rather than rebuilding Country C).
     */
    public static String checkMutualBordering(Country selectedCountryA, Country selectedCountryB){

        InputStream inputStream = bordering.getClass().getResourceAsStream("/data.json");
        JSONTokener jsonTokener = new JSONTokener(inputStream);
        JSONObject jsonObject = new JSONObject(jsonTokener);

        if(jsonObject.has("bordering")) {

            JSONObject jsonBordering = jsonObject.getJSONObject("bordering");

            //get the array associated with country A and Country B
            JSONArray listA = jsonBordering.getJSONArray(String.valueOf(selectedCountryA.getId()));//the property 'key'
            JSONArray listB = jsonBordering.getJSONArray(String.valueOf(selectedCountryB.getId()));//the property 'key'

            //comparing the two arrays for matching ids:
            for (int i = 0; i < listA.length(); i++) {

                for (int j = 0; j < listB.length(); j++) {

                    if (listA.getInt(i) == listB.getInt(j)) {

                        int matchingId = listA.getInt(i);//found a match

                        JSONArray countriesArray = jsonObject.getJSONArray("countries");

                        //use the matching id to get the country string at the index position:
                        return countriesArray.getJSONObject(matchingId - 1).getString("name");
                    }
                }
            }
        }
        return null;//no mutual bordering country found
    }

}
