package rest.model;

import java.util.Date;

public class CreditCards {
    protected Long cardNumber;
    protected Date expirationDate;
    protected Users user;

    // This constructor can be used for reading records from MySQL, where we have all fields,
	// including the cardNumber.
	public CreditCards(Long cardNumber, Date expirationDate, Users user) {
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.user = user;
	}
	
		public CreditCards(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	
    /** Getters and setters. */
    
	public Long getcardNumber() {
		return cardNumber;
	}

	public void setcardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getexpirationDate() {
		return expirationDate;
	}

	public void setexpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Users getuser() {
		return user;
	}

	public void setuser(Users user) {
		this.user = user;
	}
}