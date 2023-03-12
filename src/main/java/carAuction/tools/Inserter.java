package carAuction.tools;

import carAuction.dal.*;
import carAuction.model.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Inserter {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		ForumDao forumDao = ForumDao.getInstance();
		ReplyDao replyDao = ReplyDao.getInstance();
		UserActivityDao userActivityDao = UserActivityDao.getInstance();
		
		
		// INSERT objects from our model.
		Date date = new Date();
		Users user1 = new Users("10000", "first1", "last1", "user1 Address1", 
				                    "user1 Address2", "user1 City", "WA",
				                    "00000", "US", "user1@northeastern.edu", "***123", "000-000-0000", new Date(125, 9, 3));
		user1 = usersDao.create(user1);
		
		Users user2 = new Users("20000", "first2", "last2", "user2 Address1", 
                					"user2 Address2", "user2 City2", "WA",
                					"00000", "US", "user2@northeastern.edu", "***456", "000-000-0000", new Date(105, 3, 20));
		user2 = usersDao.create(user2);
		
		Users user3 = new Users("30000", "first3", "last3", "user3 Address1", 
				"user3 Address2", "user3 City", "WA",
				"00000", "US", "user3@northeastern.edu", "***789", "000-000-0000", new Date(110, 0, 17));
		user3 = usersDao.create(user3);
		
		Forum forum1 = new Forum(auction, user1, date, "forum1 content ............");
		forum1 = forumDao.create(forum1);
		
		Forum forum2 = new Forum(auction, user1, date, "forum2 content ............");
		forum2 = forumDao.create(forum2);
		
		Forum forum3 = new Forum(auction, user2, date, "forum3 content ............");
		forum3 = forumDao.create(forum3);
		
		Forum forum4 = new Forum(auction, user2, date, "forum4 content ............");
		forum4 = forumDao.create(forum4);
		
		Reply reply1 = new Reply(forum1, user3, date, "reply1 content ............");
		reply1 = replyDao.create(reply1);
		
		Reply reply2 = new Reply(forum1, user2, date, "reply2 content ............");
		reply2 = replyDao.create(reply2);
		
		Reply reply3 = new Reply(forum2, user1, date, "reply3 content ............");
		reply3 = replyDao.create(reply3);
				
		Reply reply4 = new Reply(forum3, user3, date, "reply4 content ............");
		reply4 = replyDao.create(reply4);
		
		UserActivity userActivity1 = new UserActivity(user1, UserActivity.ActivityType.login, date);
		userActivity1 = userActivityDao.create(userActivity1);
		
		UserActivity userActivity2 = new UserActivity(user1, UserActivity.ActivityType.logout, date);
		userActivity2 = userActivityDao.create(userActivity2);
		
		UserActivity userActivity3 = new UserActivity(user2, UserActivity.ActivityType.login, date);
		userActivity3 = userActivityDao.create(userActivity3);
		
		UserActivity userActivity4 = new UserActivity(user2, UserActivity.ActivityType.logout, date);
		userActivity4 = userActivityDao.create(userActivity4);
		
		UserActivity userActivity5 = new UserActivity(user3, UserActivity.ActivityType.login, date);
		userActivity5 = userActivityDao.create(userActivity5);
		
		UserActivity userActivity6 = new UserActivity(user3, UserActivity.ActivityType.logout, date);
		userActivity6 = userActivityDao.create(userActivity6);
		
		
		// READ.
		UserActivity u1 = userActivityDao.getUserActivityByID("1");
		System.out.format("Reading User Activity:  ID:%s  Type:%s  UserID:%s  Time:%s  \n",
				u1.getActivityID(), u1.getActivityType(), u1.getUser().getUserID() , u1.getTimeStamp());
		
		List<UserActivity> uList = userActivityDao.getUserActivityForUser(user1);
		for(UserActivity u : uList) {
			System.out.format("Looping User Activities:  ID:%s  Type:%s  UserID:%s  Time:%s \n",
					u.getActivityID(), u.getActivityType(), u.getUser().getUserID() , u.getTimeStamp());
		}
		
		
		Forum f1 = forumDao.getForumById("3");
		System.out.format("Reading Forum:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		f1 = forumDao.updateForum(f1, "updated forum3 content...............");
		System.out.format("Reading Updated Forum:  ID:%s   AuctionID:%s  UserID:%s  Time:%s  Content%s  \n", 
				f1.getForumID(), f1.getAuction().getAuctionID(), f1.getUser().getUserID(), f1.getTimeStamp(), f1.getContent());
		
		List<Forum> fList = forumDao.getForumForUser(user1);
		for (Forum f : fList) {
			System.out.format("Looping Forum for User %s:  forumID:%s,  AuctionID:%s,  Time:%s,  Content:%s    \n ",
					f.getUser().getUserID(), f.getForumID(), f.getAuction().getAuctionID(), f.getTimeStamp(), f.getContent());
		}
		//need to test getForumForAuction after auction class created
		
		
		Reply r1 = replyDao.getReplyById("3");
		System.out.format("Reading Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		r1 = replyDao.updateReply(r1, "updated reply3 content..........");
		System.out.format("Reading Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
				r1.getReplyID(), r1.getForum().getForumID(), r1.getUser().getUserID(), r1.getTimeStamp(), r1.getContent());
		
		List<Reply> rList = replyDao.getReplyForForum(forum1);
		for(Reply r : rList) {
			System.out.format("Looping Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
		List<Reply> rList1 = replyDao.getReplyForUser(user3);
		for(Reply r : rList1) {
			System.out.format("Looping Reply:  ID:%s, ForumID:%s,  UserID:%s,  Time:%s,  Content:%s   \n ",
					r.getReplyID(), r.getForum().getForumID(), r.getUser().getUserID(), r.getTimeStamp(), r.getContent());
		}
		
	}
}
