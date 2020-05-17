package fr.natsystem.javatest.services.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;
import fr.natsystem.javatest.services.DataFileLoadingException;



public class PassengerCsvHandler {
	
	// Récupération de la liste des passagers à partir d'un fichier csv 
	public static List<Passenger> readPassengersFromCSVFile(File file) throws DataFileLoadingException {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(file.toPath());
		}catch (IOException e) {
			String detail = "aucun détail supplementaire";
			if (e instanceof FileNotFoundException) {
				detail = "le fichier n'est pas accessible";
			}
			if (e instanceof NoSuchFileException) {
				detail = "le fichier n'a pas été trouvé";
			}
			throw new DataFileLoadingException("Le fichier contenant les passagers n'a pas pu être chargé correctement "
					+ "(fichier en cause :" + file.getAbsolutePath() + ")"
					+ "\ndétail de l'erreur :" + detail , e);
		}
		
		// supprimme la ligne entête
		lines.remove(0);
		List<Passenger> passengers = new ArrayList<>();
		for (String line : lines) {
			Passenger passenger = PassengerCsvHandler.fromCSVLine(line);
			passengers.add(passenger);
		}
	
		return passengers;
	}
	

	// Méthode permettant de créer des objets passenger à partir des informations présentes dans les lignes d'un fichier. 
	public static Passenger fromCSVLine(String line) {
		String[] parts = line.split(";");
		Passenger passenger = new Passenger();
					
		passenger.setName(parts[0].strip());
		passenger.setPassengerClass(PassengerClass.resolveFromCode(parts[1].strip()));
		
		// Condition de vérifications si le champs age du fichier n'est pas vide.
		// Si c'est le cas mettre une valeur négative pour démontrer qu'il y a une erreur.
		// Sinon récupérer la valeur de la colonne.
		Double age =  !"".equals(parts[2].strip()) ? Double.valueOf(parts[2].strip()) : -1.0;
		passenger.setAge(age);
		
		passenger.setSex(PassengerSex.resolveFromCode(parts[3].strip()));
		passenger.setSurvived(Boolean.valueOf(parts[4].strip()));
		
		return passenger;
	}

}
