package fr.natsystem.javatest.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;
import fr.natsystem.javatest.services.PassengerDataService;
import fr.natsystem.javatest.services.data.PassengerCsvHandler;

public class TestBLI1 {

	public static void test() {
		
		//given : Un fichier contenant des lignes au format csv qui se trouve � la racine du projet
		File file = new File("Titanic.csv");
		
		//when : Lecture du fichier CSV Titanic avec tout les passagers.
		
		List<Passenger> passengers;
		try {
			passengers = PassengerCsvHandler.readPassengersFromCSVFile(file);
		} catch (Exception e) {
			System.out.println("une erreur est survenue");
			System.out.println(e.getMessage());
			return;
		}
		
		// Test compl�mentaire permettant de v�rifier si le fichier � bien �t� charg�.
		// 1313 �tant le nombre de lignes compris dans le fichier Titanic.csv moins l'ent�te.
		boolean success = passengers.size() == 1313;
		
		if (!success) {
			System.out.println("une erreur est survenue pendant le chargement");
		}
			
		//then : Affichage des passagers qui ont surv�cus 
		passengers = PassengerDataService.filsterSurvived(passengers, true);
		System.out.println("Survived: " + passengers.size());
		
		Double averageAge = PassengerDataService.averageAge(passengers);
		System.out.println("Average age for surviving population: " + averageAge);
		
		
	}

}
