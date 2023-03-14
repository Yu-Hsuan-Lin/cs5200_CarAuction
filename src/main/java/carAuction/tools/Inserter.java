package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Inserter {
	public static void main(String[] args) throws SQLException, ParseException {
		
		// Users and CreditCards inserter
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
		Users u1 = usersDao.getUserFromUserID("10000");
		System.out.format("Reading user: u:%s p:%s f:%s l:%s e:%s p:%s \n",
			u1.getUserID(), u1.getFirstName(), u1.getLastName(),
			u1.getAddress1(), u1.getAddress2(), u1.getCity(),
			u1.getState(), u1.getZipcode(), u1.getCountry(),
			u1.getPhone(), u1.getEmail(), u1.getpassword(), u1.getSignUp());
	
		CreditCards cd1 = creditCardsDao.getCreditCardByCardNumber("403003344455");
		List<CreditCards> cdList1 = creditCardsDao.getCreditCardsByUserID("10000");
		System.out.format("Reading c:%s u:%s e:%s e:%s n:%s z:%s \n",
			cd1.getCardNumber(), cd1.getUser().getUserID(), cd1.getExpirationMonth(),
			cd1.getExpirationYear(), cd1.getNameOnCard(), cd1.getZipCode());
		for(CreditCards cd : cdList1) {
			System.out.format("Looping creditCards: c:%s u:%s e:%s e:%s n:%s z:%s \n",
			cd.getCardNumber(), cd.getUser().getUserID(), cd.getExpirationMonth(),
			cd.getExpirationYear(), cd.getNameOnCard(), cd.getZipCode());
		}
		
		// UPDATE
		usersDao.updatepassword(user1, "123***");
		System.out.format("Reading user after updating password: u:%s p:%s f:%s l:%s e:%s p:%s \n",
				user1.getUserID(), user1.getFirstName(), user1.getLastName(),
				user1.getAddress1(), user1.getAddress2(), user1.getCity(),
				user1.getState(), user1.getZipcode(), user1.getCountry(),
				user1.getPhone(), user1.getEmail(), user1.getpassword(), user1.getSignUp());
		creditCardsDao.updateNameOnCard(creditCard1, "Bella");
		System.out.format("Reading creditCard after updating name of card: c:%s u:%s e:%s e:%s n:%s z:%s \n",
				creditCard1.getCardNumber(), creditCard1.getUser().getUserID(), creditCard1.getExpirationMonth(),
				creditCard1.getExpirationYear(), creditCard1.getNameOnCard(), creditCard1.getZipCode());
		
		//DELETE
//		creditCardsDao.delete(creditCard1);
//		usersDao.delete(user1);
		
		System.out.println();
		
		// UserActivity inserter
		Date date = new Date();
		UserActivityDao userActivityDao = UserActivityDao.getInstance();
		
		UserActivity userActivity1 = new UserActivity("1", user1, UserActivity.ActivityType.login, date);
		userActivity1 = userActivityDao.create(userActivity1);
		
		UserActivity userActivity2 = new UserActivity("2", user1, UserActivity.ActivityType.logout, date);
		userActivity2 = userActivityDao.create(userActivity2);
		
		UserActivity userActivity3 = new UserActivity("3", user2, UserActivity.ActivityType.login, date);
		userActivity3 = userActivityDao.create(userActivity3);
		
		UserActivity userActivity4 = new UserActivity("4", user2, UserActivity.ActivityType.logout, date);
		userActivity4 = userActivityDao.create(userActivity4);
		
		UserActivity userActivity5 = new UserActivity("5", user3, UserActivity.ActivityType.login, date);
		userActivity5 = userActivityDao.create(userActivity5);
		
		UserActivity userActivity6 = new UserActivity("6", user3, UserActivity.ActivityType.logout, date);
		userActivity6 = userActivityDao.create(userActivity6);
		
		UserActivity uA1 = userActivityDao.getUserActivityByID("1");
		System.out.format("Reading User Activity:  ID:%s  Type:%s  UserID:%s  Time:%s  \n",
				uA1.getActivityID(), uA1.getActivityType(), uA1.getUser().getUserID() , uA1.getTimeStamp());
		
		List<UserActivity> uAList = userActivityDao.getUserActivityForUser(user1);
		for(UserActivity u : uAList) {
			System.out.format("Looping User Activities:  ID:%s  Type:%s  UserID:%s  Time:%s \n",
					u.getActivityID(), u.getActivityType(), u.getUser().getUserID() , u.getTimeStamp());
		}
		
		System.out.println();
		
		// Car inserter
		CarDao carDao = CarDao.getInstance();
		
		Car car1 = new Car("1",user2, 2015, "Kia", "Sorento","LX","SUV","automatic","5xyktca69fg566472","ca",5.0F,16639,"white","black",20500);
		car1 = carDao.create(car1);
		
		Car car2 = new Car("2",user1,2015,"Kia","Sorento","LX","SUV","automatic","5xyktca69fg561319","ca",5.0F,9393,"white","beige",20800);
		car2 = carDao.create(car2);
		
		Car car3 = new Car("3",user2,2014,"BMW","3 Series","328i SULEV","Sedan","automatic","wba3c1c51ek116351","ca",4.5f,1331,"gray","black",31900);
		car3 = carDao.create(car3);
		
		Car car4 = new Car("4",user2,2015,"Volvo","S60","T5","Sedan","automatic","yv1612tb4f1310987","ca",4.1f,14282,"white","black",27500);
		car4 = carDao.create(car4);
		
		Car car5 = new Car("5",user3,2014,"BMW","6 Series Gran Coupe","650i","Sedan","automatic","wba6b2c57ed129731","ca",4.3f,2641,"gray","black",66000);
		car5 = carDao.create(car5);
		
		Car car6 = new Car("6",user3,2015,"Nissan","Altima","2.5 S","Sedan","automatic","1n4al3ap1fn326013","ca",1.0F,5554,"gray","black",15350);
		car6 = carDao.create(car6);

		System.out.format("Reading Car: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				car1.getCarID(), car1.getUser().getUserID(), car1.getYear(), car1.getMaker(), car1.getModel(), car1.getTrim(), car1.getBody(), car1.getTransmission(), 
				car1.getVIN(), car1.getState(), car1.getConditionScore(), car1.getOdoMeter(), car1.getColor(), car1.getInterior(), car1.getMMR());
		carDao.updateUser(car1,user1);
		carDao.updateConditionScore(car1,5.0F);
		carDao.updateOdoMeter(car1,16000);
		carDao.updateMMR(car1,20000);
		System.out.format("Reading Car after updateing: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				car1.getCarID(), car1.getUser().getUserID(), car1.getYear(), car1.getMaker(), car1.getModel(), car1.getTrim(), car1.getBody(), car1.getTransmission(), 
				car1.getVIN(), car1.getState(), car1.getConditionScore(), car1.getOdoMeter(), car1.getColor(), car1.getInterior(), car1.getMMR());
		
		Car carSearchByID1 = carDao.getCarById("5");
		System.out.format("Reading Car5: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
				carSearchByID1.getCarID(), carSearchByID1.getUser().getUserID(), carSearchByID1.getYear(), carSearchByID1.getMaker(), carSearchByID1.getModel(), carSearchByID1.getTrim(), carSearchByID1.getBody(), carSearchByID1.getTransmission(), 
				carSearchByID1.getVIN(), carSearchByID1.getState(), carSearchByID1.getConditionScore(), carSearchByID1.getOdoMeter(), carSearchByID1.getColor(), carSearchByID1.getInterior(), carSearchByID1.getMMR());
		
		
		List<Car> carSearchByUser1 = carDao.getCarForUser(user1);
		for(Car c : carSearchByUser1) {
			System.out.format("Reading User1's Cars: CarID:%s UserID:%s Year:%s Maker:%s Model:%s Trim:%s Body:%s Transmission:%s VIN:%s State:%s ConditionScore:%s OdoMeter:%s Color:%s Interior:%s MMR:%s \n",
					c.getCarID(), c.getUser().getUserID(), c.getYear(), c.getMaker(), c.getModel(), c.getTrim(), c.getBody(), c.getTransmission(), 
					c.getVIN(), c.getState(), c.getConditionScore(), c.getOdoMeter(), c.getColor(), c.getInterior(), c.getMMR());
		}
		
//		carDao.delete(car1);
		
		System.out.println();
		
		// CustomerService inserter
		CustomerServiceDao customerServiceDao = CustomerServiceDao.getInstance();
		
		CustomerService cs1 = new CustomerService("1", "Bill", "K");
		cs1 = customerServiceDao.create(cs1);
		
		CustomerService cs2 = new CustomerService("2", "B", "K");
		cs2 = customerServiceDao.create(cs2);
		
		CustomerService csrRead = customerServiceDao.getCustomerServiceById("1");
		System.out.format("Reading CSById: CSID:%s first_name:%s last_name:%s\n",
				csrRead.getCustomerServiceID(), csrRead.getFirstName(), csrRead.getLastName());
		
//		customerServiceDao.delete(cs1);
		
		System.out.println();
		
		// Auction inserter
		AuctionDao auctionDao = AuctionDao.getInstance();
		
		Auction auction1 = new Auction("1", "auction1", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car1, user1, "h1", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/kia_k900.jpg", 40000F, 40000F, Auction.AuctionStatusValue.Active, cs1, true);
		auction1 = auctionDao.create(auction1);
		
		Auction auction2 = new Auction("2", "auction2", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car2, user1, "h2", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/kia_k900.jpg", 40000F, 40000F, Auction.AuctionStatusValue.Active, cs1, true);
		auction2 = auctionDao.create(auction2);
		
		Auction auction3 = new Auction("3", "auction3", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car3, user2, "h3", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/nissan_altima.jpg", 40000F, 40000F, Auction.AuctionStatusValue.Failed, cs1, true);
		auction3 = auctionDao.create(auction3);
		
		Auction auction4 = new Auction("4", "auction4", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car4, user2, "h4", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/chevrolet_cruze.jpg", 40000F, 40000F, Auction.AuctionStatusValue.Active, cs2, true);
		auction4 = auctionDao.create(auction4);
		
		Auction auction5 = new Auction("5", "auction5", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car5, user3, "h5", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/hyundai_elantra.jpg,", 40000F, 40000F, Auction.AuctionStatusValue.Succeed, cs2, true);
		auction5 = auctionDao.create(auction5);
		
		Auction auction6 = new Auction("6", "auction6", new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-02-17 04:30:00"), new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-03-17 04:30:00"), car6, user3, "h6", "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/acura_mdx.jpg", 40000F, 40000F, Auction.AuctionStatusValue.Active, cs2, true);
		auction6 = auctionDao.create(auction6);
		
		System.out.format("Reading Auction1: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auction1.getAuctionID(), auction1.getTitle(), auction1.getStartTime(), auction1.getEndTime(), auction1.getCar().getCarID(), auction1.getUser().getUserID(), auction1.getHighlights(), auction1.getPictures(),
				auction1.getMinimumPrice(), auction1.getCurrentHighestPrice(), auction1.getAuctionStatus(), auction1.getCustomerService().getCustomerServiceID(), auction1.getPriceChangeAlert());
		auctionDao.updateTitle(auction1, "title1");
		auctionDao.updateEndTime(auction1, new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2015-04-17 04:30:00"));
		auctionDao.updateHighlights(auction1, "highlght1");
		auctionDao.updatePictures(auction1, "https://neu-cs5200-team-project.s3.us-east-2.amazonaws.com/all_car_picture/nissan_altima.jpg");
		auctionDao.updateCurrentHighestPrice(auction1, 50000F);
		auctionDao.updateAuctionStatus(auction1, Auction.AuctionStatusValue.Failed);
		auctionDao.updateCustomerService(auction1, cs2);
		auctionDao.updatePriceChangeAlert(auction1, false);
		System.out.format("Reading Auction1 after updating: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auction1.getAuctionID(), auction1.getTitle(), auction1.getStartTime(), auction1.getEndTime(), auction1.getCar().getCarID(), auction1.getUser().getUserID(), auction1.getHighlights(), auction1.getPictures(),
				auction1.getMinimumPrice(), auction1.getCurrentHighestPrice(), auction1.getAuctionStatus(), auction1.getCustomerService().getCustomerServiceID(), auction1.getPriceChangeAlert());
		
		Auction auctionSeachByID = auctionDao.getAuctionById("2");
		System.out.format("Auction seached by ID: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
				auctionSeachByID.getAuctionID(), auctionSeachByID.getTitle(), auctionSeachByID.getStartTime(), auctionSeachByID.getEndTime(), auctionSeachByID.getCar().getCarID(), auctionSeachByID.getUser().getUserID(), auctionSeachByID.getHighlights(), auctionSeachByID.getPictures(),
				auctionSeachByID.getMinimumPrice(), auctionSeachByID.getCurrentHighestPrice(), auctionSeachByID.getAuctionStatus(), auctionSeachByID.getCustomerService().getCustomerServiceID(), auctionSeachByID.getPriceChangeAlert());
		
		List<Auction> auctionsSeachForUser = auctionDao.getAuctionForUser(user1);
		for(Auction au : auctionsSeachForUser) {
			System.out.format("Auction seached for User: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
		List<Auction> auctionsSeachForCar = auctionDao.getAuctionForCar(car5);
		for(Auction au : auctionsSeachForCar) {
			System.out.format("Auction seached for Car: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
		List<Auction> auctionsSeachForCustomerService = auctionDao.getAuctionForCustomerService(cs2);
		for(Auction au : auctionsSeachForCustomerService) {
			System.out.format("Auction seached for Customer Service: AuctionID:%s Title:%s StartTime:%s EndTime:%s CarID:%s UserID:%s Highlights:%s Pictures:%s MinimumPrice:%s CurrentHighestPrice:%s AuctionStatus:%s CustomerServiceID:%s PriceChangeAlert:%s \n",
					au.getAuctionID(), au.getTitle(), au.getStartTime(), au.getEndTime(), au.getCar().getCarID(), au.getUser().getUserID(), au.getHighlights(), au.getPictures(),
					au.getMinimumPrice(), au.getCurrentHighestPrice(), au.getAuctionStatus(), au.getCustomerService().getCustomerServiceID(), au.getPriceChangeAlert());
		}
		
//		auctionDao.delete(auction1);
		
		System.out.println();
		
		
		
		// Bid inserter
		
		// ChatHistory inserter
		
		// Collection inserter
		
		
		
		
		
		
		// Forum inserter
		ForumDao forumDao = ForumDao.getInstance();
		
		Forum forum1 = new Forum("1", auction1, user1, date, "forum1 content ............");
		forum1 = forumDao.create(forum1);
		
		Forum forum2 = new Forum("2", auction2, user1, date, "forum2 content ............");
		forum2 = forumDao.create(forum2);
		
		Forum forum3 = new Forum("3", auction3, user2, date, "forum3 content ............");
		forum3 = forumDao.create(forum3);
		
		Forum forum4 = new Forum("4", auction4, user2, date, "forum4 content ............");
		forum4 = forumDao.create(forum4);
		
		Forum forum5 = new Forum("5", auction4, user3, date, "forum5 content ............");
		forum5 = forumDao.create(forum5);

		
		Forum f1 = forumDao.getForumById("3");
		System.out.format("Reading Forum:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content:%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		f1 = forumDao.updateForum(f1, "updated forum3 content...............");
		System.out.format("Reading Updated Forum:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content:%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		List<Forum> fList = forumDao.getForumForUser(user1);
		for (Forum f : fList) {
			System.out.format("Looping Forum for User %s:  forumID:%s,  AuctionID:%s,  Time:%s,  Content:%s    \n ",
					f.getUser().getUserID(), f.getForumID(), f.getAuction().getAuctionID(), f.getTimeStamp(), f.getContent());
		}
		
		List<Forum> fList1 = forumDao.getForumForAuction(auction4);
		for (Forum f : fList1) {
			System.out.format("Looping Forum for User %s:  forumID:%s,  AuctionID:%s,  Time:%s,  Content:%s    \n ",
					f.getUser().getUserID(), f.getForumID(), f.getAuction().getAuctionID(), f.getTimeStamp(), f.getContent());
		}
		
		System.out.println();
		
		// Reply inserter
		
		ReplyDao replyDao = ReplyDao.getInstance();
		
		Reply reply1 = new Reply("1", forum1, user3, date, "reply1 content ............");
		reply1 = replyDao.create(reply1);
		
		Reply reply2 = new Reply("2", forum1, user2, date, "reply2 content ............");
		reply2 = replyDao.create(reply2);
		
		Reply reply3 = new Reply("3", forum2, user1, date, "reply3 content ............");
		reply3 = replyDao.create(reply3);
				
		Reply reply4 = new Reply("4", forum3, user3, date, "reply4 content ............");
		reply4 = replyDao.create(reply4);
		
		
		Reply r1 = replyDao.getReplyById("3");
		System.out.format("Reading Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		r1 = replyDao.updateReply(r1, "updated reply3 content..........");
		System.out.format("Reading Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		List<Reply> rList = replyDao.getReplyForForum(forum1);
		for(Reply r : rList) {
			System.out.format("Looping Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
		List<Reply> rList1 = replyDao.getReplyForUser(user3);
		for(Reply r : rList1) {
			System.out.format("Looping Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
		
		System.out.println();
		
		
		
	}
}
