### Project Title:  World Atlas with Journey Planner

##### Author: 
Oisin Bourke

#### Getting Started: 
The application runs from the main( ) method located at: `src/main/java/Main.java`. `Main.java` sole function is the main( ) method.

#### Prerequisites:
The application uses Maven dependency management to manage the JSON dependency. The `pom.xml` is located in the root directory.

The JSON data file (`data.json`) used to serialize the atlas and inform bordering status is located in the `src/main/resources` folder.

#### Instructions:
The user is presented with the full contents of the atlas in the following format:

`1) Country: Ireland,	Population: 2800000,	Capital: Dublin.`  
`Ireland's Cities: `  
`Dublin ~ Population: 1000000 | Points of Interest: Parliament Building, National Museum, Cathedral. `  
`Galway ~ Population: 100000 | Points of Interest: Tourist Information, Pub.`   
`Cork ~ Population: 200000 | Points of Interest: Tourist Information, Pub.`   
`Limerick ~ Population: 100000 | Points of Interest: Tourist Information, Pub.`

The user is then asked to ENTER travel information, such as:  

* `ENTER your country of origin:` _Ireland_

* `ENTER your city of origin:` _Galway_

* `ENTER the destination country:` _France_

* `ENTER the destination city:` **_Bordox_**

  * `ENTER a valid city:` Bordeaux

User input _must_ be entered as presented in the atlas (case sensitive). 
However, if the user enters a country or city incorrectly, the system will ask the user to re-enter a valid place name.  

Finally, the user will be provided with a travel itinerary, such as:

`From Galway to Bordeaux you should travel by plane. `  
`Travel itinerary:   `  
`From Galway to Dublin you should travel by bus.`  
`From Dublin to Paris you should travel by plane.`  
`From Paris to Bordeaux you should travel by bus. `  
`If you prefer not to fly, alternatively you can go by train via United Kingdom. `  
`From Galway to London you should travel by train. `  
`From London to Bordeaux you should travel by train. ` 

Internal journeys are made by bus, bordering journeys are made by train, and non-bordering journeys are made by plane via 
capital cities. Alternative to flying will only be provided when both countries share a mutual border with a 3rd country (as above). 

#### Built With
Maven - Dependency Management

#### Tested 

1. The project was built in IntelliJ IDEA and tested incrementally. 
2. The version includes an Eclipse export `.classpath` and `.project` so that the project can be imported into Eclipse (import 'Existing Maven Projects')
3. The project has been tested in Eclipse.
4. The project has been tested using the Mac terminal with `mvn install` and builds successfully.



