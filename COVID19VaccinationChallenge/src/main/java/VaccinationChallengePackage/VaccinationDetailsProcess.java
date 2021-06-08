package VaccinationChallengePackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VaccinationDetailsProcess {

	// HashMap to hold people Details Name and Age Grouped by Center name as Key
	Map<String, ArrayList<Person>> peopleDetailsGroupedByVaccinationCenter = new HashMap<>();
	ArrayList<Person> peopleDetailsGroup1 = new ArrayList<Person>();
	ArrayList<Person> peopleDetailsGroup2 = new ArrayList<Person>();
	ArrayList<Person> peopleDetailsGroup3 = new ArrayList<Person>();
	ArrayList<Person> printPeopleDetailsResult = new ArrayList<Person>();

	// insert people details to their nearest center - Grouping
	public void insertPeopleDetailIntoGroup(String Name, int Age, int vaccinationCenter) {
		switch (vaccinationCenter) {
		case 1:

			peopleDetailsGroup1.add(new Person(Name, Age));
			break;
		case 2:

			peopleDetailsGroup2.add(new Person(Name, Age));
			break;
		case 3:

			peopleDetailsGroup3.add(new Person(Name, Age));
			break;
		}

	}

	// Map People Details to Nearest center with key
	public void mapPeopleDetailsToNearestVaccinationCenter(String key, int vaccinationCenter) {
		switch (vaccinationCenter) {
		case 1:

			peopleDetailsGroupedByVaccinationCenter.put(key, peopleDetailsGroup1);
			break;
		case 2:

			peopleDetailsGroupedByVaccinationCenter.put(key, peopleDetailsGroup2);
			break;
		case 3:

			peopleDetailsGroupedByVaccinationCenter.put(key, peopleDetailsGroup3);
			break;
		}
	}

	// Sorting people details with Age as priority using Comparator and print the
	// output to Console
	public void sortAndPrintPeopleDetails(String key) {

		System.out.println(
				"-----------------------------------------------------------------------------------------------------------");
		System.out
				.println("List of people for \"" + key + "\" Vaccination Center Sorted by Priority(Person Name - Age)");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------");
		// Get people details from hash map using key
		printPeopleDetailsResult = peopleDetailsGroupedByVaccinationCenter.get(key);
		// Sort using Comparator
		Collections.sort(printPeopleDetailsResult, Person.perAgeComparator);
		// Print Output to Console
		for (Person personresult : printPeopleDetailsResult) {
			System.out.println(personresult.getPersonName() + "-" + personresult.getPersonAge());
		}
	}
//Calculate the distance between 2 location using their latitude and longitude values
	public double calculateDistance(double vaccinationCenterLatitude, double personLatitude,
			double vaccinationCenterLongitude, double personLongitude) {		
		
		// The math module contains a function named toRadians which converts from degrees to radians.
        double radiusLatitude1 = Math.toRadians(vaccinationCenterLatitude);
        double radiusLatitude2 = Math.toRadians(personLatitude);
        double radiusLongitude1 = Math.toRadians(vaccinationCenterLongitude);
        double radiusLongitude2 = Math.toRadians(personLongitude);
 
       //Calculate Latitude and Longitude Difference
        double latitudeDifference = radiusLatitude2 - radiusLatitude1;
        double longitudeDifference = radiusLongitude2 - radiusLongitude1;
        
        // Haversine formula
        double a = Math.pow(Math.sin(latitudeDifference / 2), 2)
                 + Math.cos(radiusLatitude1) * Math.cos(radiusLatitude2)
                 * Math.pow(Math.sin(longitudeDifference / 2),2);
             
        double c = 2 * Math.asin(Math.sqrt(a));
 
        // Radius of earth
        double r = 6371;
 
        // calculate the result
        return(r * c);

	}

	//compare and returns the minimum value out of 3 input distance.
	public int findNearestLocation(double a, double b, double c) {
		double min = Math.min(Math.min(a, b), c);
		if (min == a) {
			return 1;
		} else if (min == b) {
			return 2;
		} else {
			return 3;
		}
	}

}
