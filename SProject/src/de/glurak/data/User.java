package de.glurak.data;

// Diese Klasse implementiert den User.
// Author: Christopher Distelkämper
// Date: 25.01.2014

public class User {
	
	// Attribute
	private String id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String emailadr;
	
	// Constructor
	/**
	 * Constructor
	 * @param id
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param emailadr
	 */
	public User(String id, String username, String password, String firstname, String lastname, String emailadr){
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailadr = emailadr;
		
	}
	
	// Getter- und Setter- Methoden
	/**
	 * @return
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * @param username
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * @return
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * @return
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * @param firstname
	 */
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	
	/**
	 * @return
	 */
	public String getFirstname(){
		return this.firstname;
	}
	
	/**
	 * @param lastname
	 */
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	
	/**
	 * @return
	 */
	public String getLastname(){
		return this.lastname;
	}
	
	/**
	 * @param emailadr
	 */
	public void setEmailadr(String emailadr){
		this.emailadr = emailadr;
	}
	
	/**
	 * @return
	 */
	public String getEmailadr(){
		return this.emailadr;
	}

}
