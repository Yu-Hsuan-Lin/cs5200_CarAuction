package carAuction.model;


public class CustomerService {
	protected String CustomerServiceID;
	protected String FirstName;
	protected String LastName;
	
	public CustomerService(String customerServiceID, String firstName, String lastName) {
		this.CustomerServiceID = customerServiceID;
		this.FirstName = firstName;
		this.LastName = lastName;
	}
	public String getCustomerServiceID() {
		return CustomerServiceID;
	}
	public void setCustomerServiceID(String customerServiceID) {
		CustomerServiceID = customerServiceID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
}