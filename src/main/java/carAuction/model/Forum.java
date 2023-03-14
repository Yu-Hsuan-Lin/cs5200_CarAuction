package carAuction.model;

import java.util.Date;

public class Forum {
	protected String forumID;
	protected Auction auction;
	protected Users user;
	protected Date timeStamp;
	protected String content;
	
	public Forum(String forumID, Auction auction, Users user, Date timeStamp, String content) {
		this.forumID = forumID;
		this.auction = auction;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}
	
	public Forum(String forumID) {
		this.forumID = forumID;
	}
	
	public Forum(Auction auction, Users user, Date timeStamp, String content) {
		this.auction = auction;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}

	public String getForumID() {
		return forumID;
	}

	public void setForumID(String forumID) {
		this.forumID = forumID;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
