package carAuction.model;

import java.util.Date;

public class Auction {
	protected String AuctionID;
	protected String Title;
	protected Date StartTime;
	protected Date EndTime;
	protected Car car;
	protected Users user;
	protected String Highlights;
	protected String Pictures;
	protected Float MinimumPrice;
	protected Float CurrentHighestPrice;
	protected AuctionStatusValue AuctionStatus;
	protected CustomerService customerService;
	protected Boolean PriceChangeAlert;
	
	public enum AuctionStatusValue {
		Active, Failed, Succeed
	}

	public Auction(String auctionID, String title, Date startTime, Date endTime, Car car, Users user,
			String highlights, String pictures, Float minimumPrice, Float currentHighestPrice,
			AuctionStatusValue auctionStatus, CustomerService customerService, Boolean priceChangeAlert) {
		this.AuctionID = auctionID;
		this.Title = title;
		this.StartTime = startTime;
		this.EndTime = endTime;
		this.car = car;
		this.user = user;
		this.Highlights = highlights;
		this.Pictures = pictures;
		this.MinimumPrice = minimumPrice;
		this.CurrentHighestPrice = currentHighestPrice;
		this.AuctionStatus = auctionStatus;
		this.customerService = customerService;
		this.PriceChangeAlert = priceChangeAlert;
	}

	public Auction(String auctionID, Car car, Users user, CustomerService customerService) {
		this.AuctionID = auctionID;
		this.car = car;
		this.user = user;
		this.customerService = customerService;
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

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public CustomerService getCustomerService() {
		return this.customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Boolean getPriceChangeAlert() {
		return this.PriceChangeAlert;
	}

	public void setPriceChangeAlert(Boolean priceChangeAlert) {
		this.PriceChangeAlert = priceChangeAlert;
	}
}
