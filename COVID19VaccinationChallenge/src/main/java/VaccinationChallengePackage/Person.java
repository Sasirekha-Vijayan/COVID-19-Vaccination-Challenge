package VaccinationChallengePackage;
import java.util.Comparator;
public class Person {
	private String personName;
    private int personAge;

	public Person(String personName, int personAge) {
        this.setPersonName(personName);
        this.setPersonAge(personAge);
    }

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}


	   /*Sort as per Government guidelines, by age priority*/
	public static Comparator<Person> perAgeComparator = new Comparator<Person>() {

		public int compare(Person s1, Person s2) {

		   int Age1 = s1.getPersonAge();
		   int Age2 = s2.getPersonAge();

		   /*Descending order - Elder person gets first priority*/
		   return Age2-Age1;
	   }
	};
}
