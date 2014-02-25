package de.glurak.data;

public class User {
	
	// Attribute
	private String id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String emailadr;
	
	// Constructor
	public User(String id, String username, String password, String firstname, String lastname, String emailadr){
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailadr = emailadr;
		
	}
	
	// Getter- und Setter- Methoden
	public String getId(){
		return this.id;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	
	public String getFirstname(){
		return this.firstname;
	}
	
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	
	public String getLastname(){
		return this.lastname;
	}
	
	public void setEmailadr(String emailadr){
		this.emailadr = emailadr;
	}
	
	public String getEmailadr(){
		return this.emailadr;
	}

}
