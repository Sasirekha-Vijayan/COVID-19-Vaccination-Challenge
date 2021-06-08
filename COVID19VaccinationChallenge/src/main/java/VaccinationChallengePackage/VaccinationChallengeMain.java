package VaccinationChallengePackage;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class VaccinationChallengeMain {
	        //Variable Declaration
			//Get constant values from VaccinationChallengeConstants class. We can directly access static variables without creating an instance of the class.
			static double Location1_Latitude = VaccinationChallengeConstants.Const_Location1_Latitude;
			static double Location2_Latitude = VaccinationChallengeConstants.Const_Location2_Latitude;
			static double Location3_Latitude = VaccinationChallengeConstants.Const_Location3_Latitude;
			static double Location1_Longitude = VaccinationChallengeConstants.Const_Location1_Longitude;				
			static double Location2_Longitude = VaccinationChallengeConstants.Const_Location2_Longitude;
			static double Location3_Longitude = VaccinationChallengeConstants.Const_Location3_Longitude;
			static double distanceFromLocation1;
			static double distanceFromLocation2;
			static double distanceFromLocation3;
			static int character;
			static int nearestLocation;
			static int intAge;
			static String importFileLocation = VaccinationChallengeConstants.Const_importFileLocation;
			static String strtxtVaccinationCenter1 = VaccinationChallengeConstants.Const_strtxtVaccinationCenter1;
			static String strtxtVaccinationCenter2 = VaccinationChallengeConstants.Const_strtxtVaccinationCenter2;
			static String strtxtVaccinationCenter3 = VaccinationChallengeConstants.Const_strtxtVaccinationCenter3;
			static String strtxtLattitude=VaccinationChallengeConstants.Const_strtxtLattitude;
			static String strtxtLongitude=VaccinationChallengeConstants.Const_strtxtLongitude;
			static String strtxtName=VaccinationChallengeConstants.Const_strtxtName;
			static String strtxtAge=VaccinationChallengeConstants.Const_strtxtAge;
			static String strtxtJSONHeader=VaccinationChallengeConstants.Const_strtxtJSONHeader;
			static String strName;
			static String JSON_DATA = "";

	//Main Method
	public static void main(String args[]) throws IOException {
		//Create instance for VaccinationDetailsProcess Class
		VaccinationDetailsProcess objProcess = new VaccinationDetailsProcess();
		FileReader reader = null;
		
		try {
			//read given people.txt file and get JSON data in a variable
			reader = new FileReader(importFileLocation);
			while ((character = reader.read()) != -1) {
				JSON_DATA = JSON_DATA + (char) character;
			}

			JSON_DATA.trim();

			final JSONObject obj = new JSONObject(JSON_DATA);
			final JSONArray geodata = obj.getJSONArray(strtxtJSONHeader);
			final int n = geodata.length();
			//loop through the JSON records and output the names grouped by vaccination center, and sorted by priority
			for (int i = 0; i < n; ++i) 
			{
				final JSONObject peopleJSONObj = geodata.getJSONObject(i);
				strName = peopleJSONObj.getString(strtxtName);
				intAge = peopleJSONObj.getInt(strtxtAge);

				distanceFromLocation1 = objProcess.calculateDistance(Location1_Latitude,
						peopleJSONObj.getDouble(strtxtLattitude), Location1_Longitude, peopleJSONObj.getDouble(strtxtLongitude));
				distanceFromLocation2 = objProcess.calculateDistance(Location2_Latitude,
						peopleJSONObj.getDouble(strtxtLattitude), Location2_Longitude, peopleJSONObj.getDouble(strtxtLongitude));
				distanceFromLocation3 = objProcess.calculateDistance(Location3_Latitude,
						peopleJSONObj.getDouble(strtxtLattitude), Location3_Longitude, peopleJSONObj.getDouble(strtxtLongitude));

				nearestLocation = objProcess.findNearestLocation(distanceFromLocation1, distanceFromLocation2,
						distanceFromLocation3);
				objProcess.insertPeopleDetailIntoGroup(strName, intAge, nearestLocation);
			}

			objProcess.mapPeopleDetailsToNearestVaccinationCenter(strtxtVaccinationCenter1, 1);
			objProcess.mapPeopleDetailsToNearestVaccinationCenter(strtxtVaccinationCenter2, 2);
			objProcess.mapPeopleDetailsToNearestVaccinationCenter(strtxtVaccinationCenter3, 3);
			objProcess.sortAndPrintPeopleDetails(strtxtVaccinationCenter1);
			objProcess.sortAndPrintPeopleDetails(strtxtVaccinationCenter2);
			objProcess.sortAndPrintPeopleDetails(strtxtVaccinationCenter3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}

	}

}
