package uk.ac.aber.cs221.group16.authenticaion;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	
	public User(int theId, String theFirstName, String theLastName, String theUserName, String thePassword){
		id = theId;
		firstName = theFirstName;
		lastName = theLastName;
		userName = theUserName;
		password = thePassword;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + ", password="
				+ password + "]";
	}
}
