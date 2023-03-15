package carAuction.model;

import java.util.Date;

public class Collection {
  protected String CollectionId;
  protected String UserID;
  protected String AuctionID;
  protected Boolean PriceChangeAlert;
  protected Boolean StatusChangeAlert;

  public Collection(String collectionId, String userID, String auctionID, Boolean priceChangeAlert,
      Boolean statusChangeAlert) {
    this.CollectionId = collectionId;
    this.UserID = userID;
    this.AuctionID = auctionID;
    this.PriceChangeAlert = priceChangeAlert;
    this.StatusChangeAlert = statusChangeAlert;
  }

  public Collection(String collectionId, String userID, String auctionID) {
    this.CollectionId = collectionId;
    this.UserID = userID;
    this.AuctionID = auctionID;
  }

  public String getCollectionId() {
    return this.CollectionId;
  }

  public void setCollectionId(String collectionId) {
    this.CollectionId = collectionId;
  }

  public String getUserID() {
    return this.UserID;
  }

  public void setUserID(String userID) {
    this.UserID = userID;
  }

  public String getAuctionID() {
    return this.AuctionID;
  }

  public void setAuctionID(String auctionID) {
    this.AuctionID = auctionID;
  }

  public Boolean getPriceChangeAlert() {
    return this.PriceChangeAlert;
  }

  public void setPriceChangeAlert(Boolean priceChangeAlert) {
    this.PriceChangeAlert = priceChangeAlert;
  }

  public Boolean getStatusChangeAlert() {
    return this.StatusChangeAlert;
  }

  public void setStatusChangeAlert(Boolean statusChangeAlert) {
    this.StatusChangeAlert = statusChangeAlert;
  }
}
