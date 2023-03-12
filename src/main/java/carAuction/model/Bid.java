package carAuction.model;

import java.util.Date;


public class Bid {
	protected int BidID;
	protected Auction Auction;
	protected User User;
	protected Date BidTime;
	protected Float BidPrice;
	public Bid(int bidID, Auction Auction, User User, Date BidTime, Float BidPrice) {
		this.BidID = bidID;
		this.Auction = Auction;
		this.User = User;
		this.BidTime = BidTime;
		this.BidPrice = BidPrice;
	}
	public int getBidID() {
		return BidID;
	}
	public void setBidID(int BidID) {
		this.BidID = BidID;
	}
	public int getAuction() {
		return Auction;
	}
	public void setAuction(Auction Auction) {
		this.Auction = Auction;
	}
	public User getUser() {
		return User;
	}
	public void setUser(User User) {
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
