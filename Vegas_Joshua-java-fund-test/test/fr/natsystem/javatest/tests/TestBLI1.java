package fr.natsystem.javatest.tests;

import java.util.ArrayList;
import java.util.List;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;
import fr.natsystem.javatest.services.PassengerDataService;

public class TestBLI1 {

	public static void test() {
		
		// Création d'un liste de passagers
		PassengerSex sexMale = PassengerSex.MALE;
		PassengerSex sexFemale = PassengerSex.FEMALE;
		PassengerClass pClassFirst = PassengerClass.FIRST;
		PassengerClass pClassSecound = PassengerClass.FIRST;
		PassengerClass pClassThird = PassengerClass.FIRST;
		
		Passenger passenger1 = new Passenger("Abbing, Mr Anthony", pClassFirst, 50.0, sexMale, true);
		Passenger passenger2 = new Passenger("Aurelie, MMe Vegas", pClassSecound, 39.0, sexFemale, false);
		Passenger passenger3 = new Passenger("Patrick, Mr Velasquez", pClassThird, 40.0, sexMale, true);
		Passenger passenger4 = new Passenger("Laura, MMe Thomas", pClassFirst, 25.0, sexFemale, true);
		Passenger passenger5 = new Passenger("Elliott, Mr Brunet", pClassThird, 32.0, sexMale, false);
		
		List<Passenger> passengers = new ArrayList<Passenger>();
		
		passengers.add(passenger1);
		passengers.add(passenger2);
		passengers.add(passenger3);
		passengers.add(passenger4);
		passengers.add(passenger5);
		
		// Affichage des passagers qui ont survécus
		PassengerDataService.filsterSurvived(passengers, true);
		
		System.out.println("Survived :" + passengers);
		
		
		
	}

}
