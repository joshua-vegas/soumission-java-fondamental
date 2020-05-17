package fr.natsystem.javatest.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


import fr.natsystem.javatest.datamodel.Passenger;
import fr.natsystem.javatest.services.utils.SQLRequest;

public class PassengerJDBCDAO {
	
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
	
	public void create(Passenger p) {
		final String sql = SQLRequest.INSERTPASSENGER.getRequest();
		Connection conn = null;
		try {
			conn = getConnection();
			final PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, p.getName());
			pstm.setString(2, p.getPassengerClass().getCode());
			pstm.setDouble(3, p.getAge());
			pstm.setString(4, p.getSex().getCode());
			pstm.setBoolean(5, p.getSurvived());
			pstm.execute();
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception thrown while creating new Student! : " + e.getMessage());
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
