package model;

import java.io.Serializable;

public class UserModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String phoneNumber;
	private String password;
	private String image;
	private String userRole;
	
	
	public UserModel() {
	    // Default constructor
	}

	
	public UserModel(String username, String firstName, String lastName, String gender, String email,
			String phoneNumber, String password, String image) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.image = image;
	}
	
	
	
	public UserModel(String username, String firstName, String lastName, String gender, String email,
			String phoneNumber, String password, String image, String userRole) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.image = image;
		this.userRole = userRole;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}

