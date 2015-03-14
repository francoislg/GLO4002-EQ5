package ca.ulaval.glo4002.GRAISSE.user;

public class User {
	private String email;
	
	public User(String email){
		this.email = email;
	}
	
	public boolean hasEmail(String email){
		return this.email.equals(email);
	}
	
	public String getEmail(){
		return email;
	}
}
