package rest.model;;

/**
 * Users is a simple, plain old java objects (POJO).
 */
public class Users {
    protected String  userName;
    protected String  password;
    protected String  firstName;
    protected String  lastName;
    protected String  email;
    protected String  phoneNumber;
	
	public Users(String userName, String password, String firstName,
                 String lastName, String email, String phoneNumber) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
        this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Users(String userName) {
		this.userName = userName;
	}

	/** Getters and setters. */

    public String getusername() {
		return userName;
	}

	public void setusername(String userName) {
		this.userName = userName;
	}

    public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

    public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

    public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

    public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
    public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
