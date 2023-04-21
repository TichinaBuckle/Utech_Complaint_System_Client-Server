package domain;

public class Account {
	
	// local variable of student class
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private String password;
	private String role;
	
	// constructor
	
	// dafualt
	public Account() {
		id = "";
		firstName = "";
		lastName = "";
		email = "";
		contactNumber = "";
		password = "";
		role = "";
		}	
	
	// primary
	public Account(String id, String firstName, String lastName, String email, String contactNumber, String password,
			String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.role = role;
	}
	
	// copy
	public Account(Account account) {
		id = account.id;
		firstName = account.firstName;
		lastName = account.lastName;
		email = account.email;
		contactNumber = account.contactNumber;
		password = account.password;
		role = account.role;
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// toString
	@Override
	public String toString() {
		return "Account [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", password=" + password + ", role=" + role + "]";
	}
	
}
