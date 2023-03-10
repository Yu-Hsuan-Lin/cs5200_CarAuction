package rest.model;

public class Companies {
    
	protected String companyName;
	protected String description;
	
	public Companies(String companyName, String description) {
		this.companyName = companyName;
		this.description = description;
	}
	
	public Companies(String companyName) {
		this.companyName = companyName;
	}

	public String getcompanyname() {
		return companyName;
	}

	public void setcompanyname(String companyName) {
		this.companyName = companyName;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

}
