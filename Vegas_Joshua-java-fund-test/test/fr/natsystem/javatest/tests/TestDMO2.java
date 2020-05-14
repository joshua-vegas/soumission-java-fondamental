package fr.natsystem.javatest.tests;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;

public class TestDMO2 {

	public static void test() {
		
		PassengerSex sex = PassengerSex.MALE;
		PassengerClass pClass = PassengerClass.FIRST;
		
		Passenger passenger1 = new Passenger("Abbing, Mr Anthony", pClass, 50.0, sex, true);
		
		System.out.println(passenger1.toString());
	}
}
