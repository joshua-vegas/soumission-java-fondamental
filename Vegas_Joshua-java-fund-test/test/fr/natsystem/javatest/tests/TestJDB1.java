package fr.natsystem.javatest.tests;

import fr.natsystem.javatest.services.Configurator;
import fr.natsystem.javatest.services.utils.SQLRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDB1 {
	public static void test() {
		createTablePassengers();
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		final  String connectionString = Configurator.getInstance().getProperty("db.host");
		System.out.println("connectionString : "+ connectionString);
		final  String userName = Configurator.getInstance().getProperty("db.userName");
		System.out.println("db.userName : "+ userName);
		final  String password = Configurator.getInstance().getProperty("db.password");
		System.out.println("db.password : "+ password);
		Class.forName("org.h2.Driver");
		
		final Connection connection = DriverManager.getConnection(connectionString, userName, password);
		
		return connection;
	}
	
	public static void createTablePassengers() {
		final String sql = SQLRequest.CREATETABLEPASSENGER.getRequest();
		Connection conn = null;
		try {
			conn = getConnection();
			final PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.execute();
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception thrown while creating new table passenger : " + e.getMessage());
		}finally {
			try {
			if(conn != null) {
				conn.close();
			}
			}catch(final SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}
