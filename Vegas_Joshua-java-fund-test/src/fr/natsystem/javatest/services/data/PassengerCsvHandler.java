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
	
	// R�cup�ration de la liste des passagers � partir d'un fichier csv 
	public static List<Passenger> readPassengersFromCSVFile(File file) throws DataFileLoadingException {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(file.toPath());
		}catch (IOException e) {
			String detail = "aucun d�tail supplementaire";
			if (e instanceof FileNotFoundException) {
				detail = "le fichier n'est pas accessible";
			}
			if (e instanceof NoSuchFileException) {
				detail = "le fichier n'a pas �t� trouv�";
			}
			throw new DataFileLoadingException("Le fichier contenant les passagers n'a pas pu �tre charg� correctement "
					+ "(fichier en cause :" + file.getAbsolutePath() + ")"
					+ "\nd�tail de l'erreur :" + detail , e);
		}
		
		// supprimme la ligne ent�te
		lines.remove(0);
		List<Passenger> passengers = new ArrayList<>();
		for (String line : lines) {
			Passenger passenger = PassengerCsvHandler.fromCSVLine(line);
			passengers.add(passenger);
		}
	
		return passengers;
	}
	

	// M�thode permettant de cr�er des objets passenger � partir des informations pr�sentes dans les lignes d'un fichier. 
	public static Passenger fromCSVLine(String line) {
		String[] parts = line.split(";");
		Passenger passenger = new Passenger();
					
		passenger.setName(parts[0].strip());
		passenger.setPassengerClass(PassengerClass.resolveFromCode(parts[1].strip()));
		
		// Condition de v�rifications si le champs age du fichier n'est pas vide.
		// Si c'est le cas mettre une valeur n�gative pour d�montrer qu'il y a une erreur.
		// Sinon r�cup�rer la valeur de la colonne.
		Double age =  !"".equals(parts[2].strip()) ? Double.valueOf(parts[2].strip()) : -1.0;
		passenger.setAge(age);
		
		passenger.setSex(PassengerSex.resolveFromCode(parts[3].strip()));
		passenger.setSurvived(Boolean.valueOf(parts[4].strip()));
		
		return passenger;
	}

}
