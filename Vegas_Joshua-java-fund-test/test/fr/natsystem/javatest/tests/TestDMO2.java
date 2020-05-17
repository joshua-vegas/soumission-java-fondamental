package fr.natsystem.javatest.tests;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;

public class TestDMO2 {

	public static void test() {
		
		Passenger passenger = new Passenger();
		
		PassengerSex sex = PassengerSex.MALE;
		PassengerClass pClass = PassengerClass.FIRST;
				
		passenger.setName("Abbing, Mr Anthony");
		passenger.setPassengerClass(pClass);
		passenger.setAge(50.0);
		passenger.setSex(sex);
		passenger.setSurvived(true);

		System.out.println(passenger.toString());
	}
}
