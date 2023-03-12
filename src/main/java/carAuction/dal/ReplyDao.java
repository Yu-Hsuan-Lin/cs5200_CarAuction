package carAuction.dal;

import carAuction.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReplyDao {
	protected ConnectionManager connectionManager;
	
	private static ReplyDao instance = null;
	protected ReplyDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReplyDao getInstance() {
		if(instance == null) {
			instance = new ReplyDao();
		}
		return instance;
	}
	
    
	public Reply create(Reply reply) throws SQLException {
		String insertReply =
			"INSERT INTO Reply(ForumID,UserID,TimeStamp,Content) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReply, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, reply.getForum().getForumID());
			insertStmt.setString(2, reply.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(reply.getTimeStamp().getTime()));
			insertStmt.setString(4, reply.getContent());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			String replyID = "-1";
			if(resultKey.next()) {
				replyID = resultKey.getString(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reply.setReplyID(replyID);
			return reply;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Reply delete(Reply reply) throws SQLException {

		String deleteReply = "DELETE FROM Reply WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReply);
			deleteStmt.setString(1, reply.getReplyID());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ReplyID=" + reply.getReplyID());
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Reply updateReply(Reply reply, String newContent) throws SQLException {
		String updateReply = "UPDATE Forum SET Content=?,Created=? WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReply);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setString(3, reply.getReplyID());
			updateStmt.executeUpdate();

			reply.setContent(newContent);
			reply.setTimeStamp(newCreatedTimestamp);
			return reply;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Reply getReplyById(String replyID) throws SQLException {
		String selectReply =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Reply " +
			"WHERE ReplyID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReply);
			selectStmt.setString(1, replyID);
			results = selectStmt.executeQuery();
			ForumDao forumDao = ForumDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			
			if(results.next()) {
				String resultReplyID = results.getString("ReplyID");
				
				String forumID = results.getString("ForumID");
				Forum forum = forumDao.getForumById(forumID);
				
				String userID = results.getString("UserID");
				Users user = usersDao.getUserFromuserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Reply reply = new Reply(resultReplyID, forum,
						user, timeStamp, content);
				
				return reply;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Reply> getReplyForForum(Forum forum) throws SQLException {
		List<Reply> replies = new ArrayList<Reply>();
		String selectReply =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Reply " +
			"WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReply);
			selectStmt.setString(1, forum.getForumID());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				String ReplyID = results.getString("ReplyID");
				String userID = results.getString("UserID");
				Users user = usersDao.getUserFromuserID(userID);
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Reply reply = new Reply(ReplyID, forum, user, timeStamp, content);
				
				replies.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return replies;
	}

	public List<Reply> getReplyForUser(Users user) throws SQLException {
		List<Reply> replies = new ArrayList<Reply>();
		String selectReply =
			"SELECT ReplyID,ForumID,UserID,TimeStamp,Content " +
			"FROM Reply " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReply);
			selectStmt.setString(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			ForumDao forumDao = ForumDao.getInstance();
			
			while(results.next()) {
				String replyID = results.getString("ReplyID");
				
				String forumID = results.getString("ForumID");
				Forum forum = forumDao.getForumById(forumID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Reply reply = new Reply(replyID, forum, user, timeStamp, content);
				
				replies.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return replies;
	}

}
