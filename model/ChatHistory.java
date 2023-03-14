package carAuction.model;

import carAuction.model.ChatHistory.ServiceType;
import java.util.Date;

public class ChatHistory {
  protected String ChatID;
  protected String CustomerServiceID;
  protected String UserID;
  protected Date TimeStamp;
  protected ServiceType ServiceType;
  public enum ServiceType {
    Account, Payment, Auction, Bidding, Legal,Security,Privacy, Other
  };

  public ChatHistory(String chatID, String customerServiceID, String userID, Date timeStamp, ServiceType serviceType) {
    this.ChatID = chatID;
    this.CustomerServiceID = customerServiceID;
    this.UserID = userID;
    this.TimeStamp = timeStamp;
    this.ServiceType = serviceType;
  }

  public ChatHistory(String chatID, String customerServiceID, String userID) {
    this.ChatID = chatID;
    this.CustomerServiceID = customerServiceID;
    this.UserID = userID;
  }

  public ChatHistory.ServiceType getServiceType() {
    return this.ServiceType;
  }

  public void setServiceType(ChatHistory.ServiceType serviceType) {
    this.ServiceType = serviceType;
  }

  public String getChatID() {
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

  public void setChatID(String chatID) {
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
