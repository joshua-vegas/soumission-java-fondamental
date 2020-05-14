package fr.natsystem.javatest.tests;

import java.io.File;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.services.data.PassengerCsvHandler;



public class TestSER1 {

	public static void test() {
		//given : Un fichier contenant des lignes au format csv qui se trouve à la racine du projet eclipse
		File file = new File("Titanic.csv");
		
		//when
		
		List<Passenger> passengers;
		try {
			passengers = PassengerCsvHandler.readPassengersFromCSVFile(file);
		} catch (Exception e) {
			System.out.println("une erreur est survenue");
			System.out.println(e.getMessage());
			return;
		}
			
		//then
		System.out.println(passengers);
		
		boolean success = passengers.size() == 1314;
		
		
		if (!success) {
			System.out.println("une erreur est survenue pendant le chargement");
		}
	}

}
