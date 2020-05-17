package fr.natsystem.javatest.services.utils;

public enum SQLRequest {
	CREATETABLEPASSENGER("CREATE TABLE passengers ("
			+ "name VARCHAR(50) PRIMARY KEY NOT NULL,"
			+ "Pclass VARCHAR(3),"
			+ "age INT(3),"
			+ "sexe VARCHAR(6),"
			+ "survived BOOLEAN"
			+ ")")
	;
	
	private String request;

	public String getRequest() {
		return request;
	}

	private SQLRequest(String request) {
		this.request = request;
	}
}
