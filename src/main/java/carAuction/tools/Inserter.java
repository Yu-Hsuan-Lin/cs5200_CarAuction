package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
		
		// INSERT objects from our model.
		Users user1 = new Users("10000", "first1", "last1", "user1 Address1", 
				                    "user1 Address2", "user1 City", "WA",
				                    "00000", "US", "user1@northeastern.edu", "***123", "000-000-0000", new Date(125, 9, 3));
		user1 = usersDao.create(user1);
		
		Users user2 = new Users("20000", "first2", "last2", "user2 Address1", 
                					"user2 Address2", "user2 City2", "WA",
                					"00000", "US", "user2@northeastern.edu", "***456", "000-000-0000", new Date(105, 3, 20));
		user2 = usersDao.create(user2);
		
		Users user3 = new Users("30000", "first3", "last3", "user3 Address1", 
									"user3 Address2", "user3 City", "WA",
									"00000", "US", "user3@northeastern.edu", "***789", "000-000-0000", new Date(110, 0, 17));
		user3 = usersDao.create(user3);
        
		CreditCards creditCard1 = new CreditCards("403003344455", user1, 3, 2026, "user1", "00000");
		creditCard1 = creditCardsDao.create(creditCard1);
		CreditCards creditCard2 = new CreditCards("439394030302", user2, 6, 2042, "user2", "00000");
		creditCard2 = creditCardsDao.create(creditCard2);
		CreditCards creditCard3 = new CreditCards("494904233344", user3, 3, 2026, "user3", "00000");
		creditCard3 = creditCardsDao.create(creditCard3);
		
		
		// READ.
		Users u1 = usersDao.getUserFromuserID("10000");
		System.out.format("Reading user: u:%s p:%s f:%s l:%s e:%s p:%s \n",
			u1.getUserID(), u1.getFirstName(), u1.getLastName(),
			u1.getAddress1(), u1.getAddress2(), u1.getCity(),
			u1.getState(), u1.getZipcode(), u1.getCountry(),
			u1.getPhone(), u1.getEmail(), u1.getpassword(), u1.getSignUp());
	
		CreditCards cd1 = creditCardsDao.getCreditCardByCardNumber("403003344455");
		List<CreditCards> cdList1 = creditCardsDao.getCreditCardsByUserID("10000");
		System.out.format("Reading creditCard: c:%s e:%s u:%s \n",
			cd1.getCardNumber(), cd1.getUser(), cd1.getExpirationMonth(),
			cd1.getExpirationYear(), cd1.getNameOnCard(), cd1.getZipCode());
		for(CreditCards cd : cdList1) {
			System.out.format("Looping creditCards: c:%s u:%s e:%s e:%s n:%s z:%s \n",
			cd.getCardNumber(), cd.getUser(), cd.getExpirationMonth(),
			cd.getExpirationYear(), cd.getNameOnCard(), cd.getZipCode());
		}
		
		// UPDATE
		
		usersDao.updatepassword(user1, "123***");
		creditCardsDao.updateNameOnCard(creditCard1, "Bella");
		
		
		//DELETE
		creditCardsDao.delete(creditCard1);
		usersDao.delete(user1);
		
	}
}
