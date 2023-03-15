package carAuction.model;

import carAuction.model.ChatHistories.ServiceType;
import java.util.Date;

public class ChatHistories {
  protected int ChatID;
  protected String CustomerServiceID;
  protected String UserID;
  protected Date TimeStamp;
  protected ServiceType ServiceType;
  public enum ServiceType {
    Account, Payment, Auction, Bidding, Legal,Security,Privacy, Other
  };

  public ChatHistories(int chatID, String customerServiceID, String userID, Date timeStamp, ServiceType serviceType) {
    this.ChatID = chatID;
    this.CustomerServiceID = customerServiceID;
    this.UserID = userID;
    this.TimeStamp = timeStamp;
    this.ServiceType = serviceType;
  }

  public ChatHistories(int chatID, String customerServiceID, String userID) {
    this.ChatID = chatID;
    this.CustomerServiceID = customerServiceID;
    this.UserID = userID;
  }

  public ChatHistories.ServiceType getServiceType() {
    return this.ServiceType;
  }

  public void setServiceType(ChatHistories.ServiceType serviceType) {
    this.ServiceType = serviceType;
  }

  public int getChatID() {
    return this.ChatID;
  }

  public String getCustomerServiceID() {
    return this.CustomerServiceID;
  }

  public String getUserID() {
    return this.UserID;
  }

  public Date getTimeStamp() {
    return this.TimeStamp;
  }

  public void setChatID(int chatID) {
    this.ChatID = chatID;
  }

  public void setCustomerServiceID(String customerServiceID) {
    this.CustomerServiceID = customerServiceID;
  }

  public void setUserID(String userID) {
    this.UserID = userID;
  }

  public void setTimeStamp(Date timeStamp) {
    this.TimeStamp = timeStamp;
  }
}
