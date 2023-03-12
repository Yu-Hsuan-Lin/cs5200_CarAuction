package carAuction.model;

import java.util.Date;

public class Auction {
	protected String AuctionID;
	protected String Title;
	protected Date StartTime;
	protected Date EndTime;
	protected String CarID;
	protected String UserID;
	protected String Highlights;
	protected String Pictures;
	protected Float MinimumPrice;
	protected Float CurrentHighestPrice;
	protected AuctionStatusValue AuctionStatus;
	protected String CustomerServiceID;
	protected Boolean PriceChangeAlert;
	
	public enum AuctionStatusValue {
		Active, Failed, Succeed
	}

	public Auction(String auctionID, String title, Date startTime, Date endTime, String carID, String userID,
			String highlights, String pictures, Float minimumPrice, Float currentHighestPrice,
			AuctionStatusValue auctionStatus, String customerServiceID, Boolean priceChangeAlert) {
		this.AuctionID = auctionID;
		this.Title = title;
		this.StartTime = startTime;
		this.EndTime = endTime;
		this.CarID = carID;
		this.UserID = userID;
		this.Highlights = highlights;
		this.Pictures = pictures;
		this.MinimumPrice = minimumPrice;
		this.CurrentHighestPrice = currentHighestPrice;
		this.AuctionStatus = auctionStatus;
		this.CustomerServiceID = customerServiceID;
		this.PriceChangeAlert = priceChangeAlert;
	}

	public Auction(String auctionID, String carID, String userID, String customerServiceID) {
		this.AuctionID = auctionID;
		this.CarID = carID;
		this.UserID = userID;
		this.CustomerServiceID = customerServiceID;
	}

	public String getAuctionID() {
		return this.AuctionID;
	}

	public void setAuctionID(String auctionID) {
		this.AuctionID = auctionID;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public Date getStartTime() {
		return this.StartTime;
	}

	public void setStartTime(Date startTime) {
		this.StartTime = startTime;
	}

	public Date getEndTime() {
		return this.EndTime;
	}

	public void setEndTime(Date endTime) {
		this.EndTime = endTime;
	}

	public String getCarID() {
		return this.CarID;
	}

	public void setCarID(String carID) {
		this.CarID = carID;
	}

	public String getUserID() {
		return this.UserID;
	}

	public void setUserID(String userID) {
		this.UserID = userID;
	}

	public String getHighlights() {
		return this.Highlights;
	}

	public void setHighlights(String highlights) {
		this.Highlights = highlights;
	}

	public String getPictures() {
		return this.Pictures;
	}

	public void setPictures(String pictures) {
		this.Pictures = pictures;
	}

	public Float getMinimumPrice() {
		return this.MinimumPrice;
	}

	public void setMinimumPrice(Float minimumPrice) {
		this.MinimumPrice = minimumPrice;
	}

	public Float getCurrentHighestPrice() {
		return this.CurrentHighestPrice;
	}

	public void setCurrentHighestPrice(Float currentHighestPrice) {
		this.CurrentHighestPrice = currentHighestPrice;
	}

	public AuctionStatusValue getAuctionStatus() {
		return this.AuctionStatus;
	}

	public void setAuctionStatus(AuctionStatusValue auctionStatus) {
		this.AuctionStatus = auctionStatus;
	}

	public String getCustomerServiceID() {
		return this.CustomerServiceID;
	}

	public void setCustomerServiceID(String customerServiceID) {
		this.CustomerServiceID = customerServiceID;
	}

	public Boolean getPriceChangeAlert() {
		return this.PriceChangeAlert;
	}

	public void setPriceChangeAlert(Boolean priceChangeAlert) {
		this.PriceChangeAlert = priceChangeAlert;
	}
}
