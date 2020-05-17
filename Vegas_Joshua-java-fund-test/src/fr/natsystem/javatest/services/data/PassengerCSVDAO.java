package fr.natsystem.javatest.services.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;
import fr.natsystem.javatest.services.DataFileLoadingException;

public class PassengerCSVDAO {
	
	public static List<Passenger> readAll(File file) throws DataFileLoadingException {
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
			Passenger passenger = PassengerCSVDAO.fromCSVLine(line);
			passengers.add(passenger);
		}
		
		passengers.sort((i1,i2) -> i1.getAge().compareTo(i2.getAge()));
	
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
		
		// Modification de code pour r�p�rer valeur bool�ene true ou false � partir de string qui ont pour valeur 0 ou 1.
		// Exemple trouv�s sur le site : https://memorynotfound.com/java-convert-string-to-boolean/
		String isNumberBoolean = parts[4].strip();
		Boolean isSurvived = "1".equals(isNumberBoolean);
		passenger.setSurvived(isSurvived);
		
		return passenger;
	}
	
	public static void writeAll(List<Passenger> passengers) throws FileNotFoundException {
		
		// Collections.rotate(passengers.subList(2, 4), -1);
		
		// Cr�ation d'un fichers s'il n'existe pas sinon �criture dans le fichier existant.
		File file = new File("data_output.csv");

		// ouverture du fichier en �criture (remplacement)
		FileOutputStream outputStream = new FileOutputStream(file, false);
		PrintWriter writer = new PrintWriter(outputStream, true); // true veut dire "auto-flush"
		
		writer.println("Pclass;Name;Sex;Age;Survived");
		
		// Boucle sur toutes les lignes des passagers avec modifications des emplacement pour les colonnes class, name ainsi que age et sex.
		for (Passenger passenger : passengers) {
			writer.println(passenger.getPassengerClass() + ";" + passenger.getName() + ";" + passenger.getSex() + ";" + passenger.getAge() + ";" + passenger.getSurvived());
		}
		
		writer.close();
		
	}
}
