package fr.natsystem.javatest.services.utils;

public enum SQLRequest {
	CREATETABLEPASSENGER("CREATE TABLE passengers ("
			+ "name VARCHAR(50) PRIMARY KEY NOT NULL,"
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
