package fr.natsystem.javatest.services;

import fr.natsystem.javatest.datamodel.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassengerDataService {
	// Méthode qui filtre une liste des passagers en fonction de s'ils sont vivant ou non
	public static List<Passenger> filsterSurvived(List<Passenger> passengers, Boolean survived) {
		List<Passenger> passengerToRemove = new ArrayList<Passenger>();
		
		for (Passenger passenger : passengers) {
			if (passenger.getSurvived().equals(!survived)) {
				passengerToRemove.add(passenger);
			}
		}
		passengers.removeAll(passengerToRemove);
		return passengers;
	}
	
	// Méthode qui affiche l'âge moyen d'une liste passagers
	public static Double averageAge(List<Passenger> passengers) {
		List<Passenger> passengerWithAge= new ArrayList<Passenger>();
				
		for (Passenger passenger : passengers) {
			if (passenger.getAge() != null) {
				passengerWithAge.add(passenger);
			}
		}
		Double answer = passengerWithAge.collect(Collectors.averagingDouble(num -> num));
		return answer;
	}
	
	// Méhode de calcul de la distribution des passagers par âge
	public static Map<Integer, Double> calculateAgeDistribution(List<Passenger> passengers) {
		
		
		return map;
	}
}
