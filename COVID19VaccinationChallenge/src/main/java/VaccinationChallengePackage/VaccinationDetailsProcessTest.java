package VaccinationChallengePackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class VaccinationDetailsProcessTest {

	VaccinationDetailsProcess objProcess = new VaccinationDetailsProcess();
	
	//test case to Insert person details into group
	@Test
	public void testinsertPeopleDetailIntoGroup() {		

		assertEquals(true,objProcess.peopleDetailsGroup1.isEmpty());
		objProcess.insertPeopleDetailIntoGroup("Sasirekha", 30, 1);
		assertEquals(false,objProcess.peopleDetailsGroup1.isEmpty());		
	}
	
	//test case to map people details to Nearest Vaccination Center
	@Test
	public void testmapPeopleDetailsToNearestVaccinationCenter()
	{
		assertEquals(true,objProcess.peopleDetailsGroupedByVaccinationCenter.isEmpty());
		objProcess.mapPeopleDetailsToNearestVaccinationCenter("Galway Racecourse",1);
		assertEquals(false,objProcess.peopleDetailsGroupedByVaccinationCenter.isEmpty());
	}
	
	//test case to check sortAndPrintPeopleDetails
	@Test
	public void testsortAndPrintPeopleDetails()
	{
	
		assertEquals(true,objProcess.printPeopleDetailsResult.isEmpty());
		objProcess.insertPeopleDetailIntoGroup("Sasirekha", 30, 1);
		objProcess.mapPeopleDetailsToNearestVaccinationCenter("Galway Racecourse",1);
		objProcess.sortAndPrintPeopleDetails("Galway Racecourse");
		assertEquals(false,objProcess.printPeopleDetailsResult.isEmpty());
		
	}
	
//test case to find Distance between 2 location
	@Test
	public void testCalculateDistance() {
	
		double distanceResult;

		distanceResult = objProcess.calculateDistance(53.298810877564875, 53.09402405506328, -8.997003657335881,
				-8.020019531250002);
		assertEquals(69.0, Math.round(distanceResult), 0);

		distanceResult = objProcess.calculateDistance(51.89742637092438, 53.09402405506328, -8.465763459121026,
				-8.020019531250002);
		assertEquals(136.0, Math.round(distanceResult), 0);

		distanceResult = objProcess.calculateDistance(53.28603418885669, 53.09402405506328, -6.4444477725802285,
				-8.020019531250002);
		assertEquals(107.0, Math.round(distanceResult), 0);

	}

	//test case to find Nearest Location
	@Test
	public void testCalculateMin() {
		assertEquals(1, objProcess.findNearestLocation(52.7, 89.8, 100));
		assertEquals(2, objProcess.findNearestLocation(52.7, 29.5, 100));
		assertEquals(3, objProcess.findNearestLocation(52.7, 89.8, 30.9));

	}

}
