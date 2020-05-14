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



public class PassengerCsvHandler {
	
	// Récupération de la liste des passagers à partir d'un fichier csv 
	public static List<Passenger> readPassengersFromCSVFile(File file) throws Exception {
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
			throw new Exception("Le fichier contenant les passagers n'a pas pu être chargé correctement "
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
	

	public static Passenger fromCSVLine(String line) {
		String[] parts = line.split(",");
		String[] parts1 = line.split(";");
					
		String name = parts[0].strip() + parts[1].strip();
		PassengerClass passengerClass = PassengerClass.resolveFromCode(parts1[1].strip());
		
		System.out.println("La classe du passager :" + passengerClass);
			
		Double age = Double.valueOf(parts1[1].strip());
		
		PassengerSex sex = PassengerSex.resolveFromCode(parts1[3].strip());
		Boolean survived = Boolean.valueOf(parts1[4].strip());

		Passenger passenger = new Passenger(name, passengerClass, age, sex, survived);

		return passenger;
	}

}
