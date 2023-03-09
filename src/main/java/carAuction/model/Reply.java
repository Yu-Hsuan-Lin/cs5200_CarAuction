package carAuction.model;

import java.util.Date;

public class Reply {
	protected String replyID;
	protected Forum forum;
	protected Users user;
	protected Date timeStamp;
	protected String content;
	
	public Reply(String replyID, Forum forum, Users user, Date timeStamp, String content) {
		this.replyID = replyID;
		this.forum = forum;
		this.user = user;
		this.timeStamp = timeStamp;
		this.content = content;
	}

	public String getReplyID() {
		return replyID;
	}

	public void setReplyID(String replyID) {
		this.replyID = replyID;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
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
