package fr.natsystem.javatest.services.utils;

public enum SQLRequest {
	CREATETABLEPASSENGER("CREATE TABLE passengers ("
			+ "name VARCHAR(50) PRIMARY KEY NOT NULL,"
			+ "pclass VARCHAR(3),"
			+ "age FLOAT(3),"
			+ "sex VARCHAR(6),"
			+ "survived BOOLEAN"
			+ ")"),
	INSERTPASSENGER("INSERT INTO PASSENGERS(NAME, PCLASS, AGE, SEX, SURVIVED) values (?,?,?,?,?)")
	;
	
	private String request;

	public String getRequest() {
		return request;
	}

	private SQLRequest(String request) {
		this.request = request;
	}
}
