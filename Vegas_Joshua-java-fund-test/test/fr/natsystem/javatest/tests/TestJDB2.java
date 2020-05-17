package fr.natsystem.javatest.tests;

import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.datamodel.PassengerClass;
import fr.natsystem.javatest.datamodel.PassengerSex;
import fr.natsystem.javatest.services.PassengerJDBCDAO;

public class TestJDB2 {
	
	public static void test() {
		PassengerJDBCDAO dao = new PassengerJDBCDAO();

		
		Passenger passenger = new Passenger();
		
		PassengerSex sex = PassengerSex.MALE;
		PassengerClass pClass = PassengerClass.FIRST;
		
		passenger.setName("Joshua");
		passenger.setAge(30.0);
		passenger.setPassengerClass(pClass);
		passenger.setSex(sex);
		passenger.setSurvived(true);
		
		System.out.println(passenger.toString());
	
		dao.create(passenger);
	}
	
}
