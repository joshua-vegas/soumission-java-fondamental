package fr.natsystem.javatest.tests;

import java.io.File;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.services.data.PassengerCSVDAO;

public class TestSER2 {
	
	public static void test() {
		//given : Un fichier contenant des lignes au format csv qui se trouve à la racine du projet
		File file = new File("Titanic.csv");
		
		//when : Lecture du fichier CSV Titanic avec tout les passagers.
		
		List<Passenger> passengers;
		try {
			passengers = PassengerCSVDAO.readAll(file);
		} catch (Exception e) {
			System.out.println("une erreur est survenue");
			System.out.println(e.getMessage());
			return;
		}
		
		// Test complémentaire permettant de vérifier si le fichier à bien été chargé.
		// 1313 étant le nombre de lignes compris dans le fichier Titanic.csv moins l'entête.
		boolean success = passengers.size() == 1313;
		
		if (!success) {
			System.out.println("une erreur est survenue pendant le chargement");
		}
			
		//then : Affichage des cinq premiers passagers.		
		for (int i = 0; i < 5; i++) {
			System.out.println(passengers.get(i));
		}
		
	}
	
}
