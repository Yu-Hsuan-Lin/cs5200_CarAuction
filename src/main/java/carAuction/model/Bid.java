package carAuction.model;

import java.util.Date;


public class Bid {
	protected String BidID;
	protected Auction Auction;
	protected Users User;
	protected Date BidTime;
	protected Float BidPrice;
	public Bid(String BidID, Auction Auction, Users User, Date BidTime, Float BidPrice) {
		this.BidID = BidID;
		this.Auction = Auction;
		this.User = User;
		this.BidTime = BidTime;
		this.BidPrice = BidPrice;
	}
	public String getBidID() {
		return BidID;
	}
	public void setBidID(String BidID) {
		this.BidID = BidID;
	}
	public Auction getAuction() {
		return Auction;
	}
	public void setAuction(Auction Auction) {
		this.Auction = Auction;
	}
	public Users getUser() {
		return User;
	}
	public void setUser(Users User) {
		this.User = User;
	}
	public Date getBidTime() {
		return BidTime;
	}
	public void setBidTime(Date bidTime) {
		BidTime = bidTime;
	}
	public Float getBidPrice() {
		return BidPrice;
	}
	public void setBidPrice(Float bidPrice) {
		BidPrice = bidPrice;
	}
	

}
