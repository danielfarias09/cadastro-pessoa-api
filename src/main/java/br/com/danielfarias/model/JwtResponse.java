package br.com.danielfarias.model;

import java.io.Serializable;

public class JwtResponse implements Serializable{

	private static final long serialVersionUID = 6064334298102325431L;
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
