package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException, ParseException {
		// DAO instances.
		AuctionDao auctionDao = AuctionDao.getInstance();
		BidDao bidDao = BidDao.getInstance();
		CarDao carDao = CarDao.getInstance();
		CustomerServiceDao customerServiceDao = CustomerServiceDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();
		
		// INSERT objects from our model.
		Car car = new Car("0", "37", 2015, "kia", "so", "LX", "SUV", "automatic", "5xh", "ca", (float) 5.0, 1663, "white", "black", 20500);
		
		String inputString = "2015-02-17";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = formatter.parse(inputString);
		String inputString2 = "2015-03-17";
		Date inputDate2 = formatter.parse(inputString2);
		String bidtimeSrting = "2015-02-24";
		Date bidtime = formatter.parse(bidtimeSrting);
		Auction auction = new Auction("1", "kia-a", inputDate, inputDate2, "54", "45", "kiakia", "https://www.pc", (float) 4000, (float) 4000, Auction.AuctionStatusValue.Failed, "5", true);
		Users user1 = new Users("10000", "first1", "last1", "user1 Address1", 
                "user1 Address2", "user1 City", "WA",
                "00000", "US", "user1@northeastern.edu", "***123", "000-000-0000", new Date(125, 9, 3));
		
		Bid bid = new Bid("1", auction, user1, bidtime, (float)4500);
		bid = bidDao.create(bid);
		CustomerService cs1 = new CustomerService("1", "Bill", "K");
		cs1 = customerServiceDao.create(cs1);
		
		// READ
		// Bid
		Bid bidRead = bidDao.getBidById("1");
		System.out.format("Reading bidById: bidID:%s bidPrice:%s \n",
				bidRead.getBidID(), bidRead.getBidPrice());
		
		List<Bid> bidRead2 = bidDao.getBidForUser(user1);
		for (Bid bidR : bidRead2) {
			System.out.format("Reading bidForUser: bidID:%s bidPrice:%s \n",
					bidR.getBidID(), bidR.getBidPrice());
		}
		List<Bid> bidRead3 = bidDao.getBidForAuction(auction);
		for (Bid bidR : bidRead3) {
			System.out.format("Reading bidForAuction: bidID:%s bidPrice:%s \n",
					bidR.getBidID(), bidR.getBidPrice());
		}
		
		// CustomerService
		CustomerService csrRead = customerServiceDao.getCustomerServiceById("1");
		System.out.format("Reading CSById: CSID:%s firstname:%s \n",
				csrRead.getCustomerServiceID(), csrRead.getFirstName());
		
		// UPDATE
		Bid bidUpdate = bidDao.updateBidPrice(bid, (float) 5400);
		System.out.format("Reading bidUpdate: bidID:%s bidPrice:%s \n",
				bidUpdate.getBidID(), bidUpdate.getBidPrice());
		
		// DELETE
		bidDao.delete(bid);
		customerServiceDao.delete(cs1);


	}
		

}