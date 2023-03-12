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


public class ForumDao {
	protected ConnectionManager connectionManager;
		
	private static ForumDao instance = null;
	protected ForumDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ForumDao getInstance() {
		if(instance == null) {
			instance = new ForumDao();
		}
		return instance;
	}
	
	public Forum create(Forum forum) throws SQLException {
		String insertForum =
			"INSERT INTO Forum(AuctionID,UserID,TimeStamp,Content) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertForum,
				Statement.RETURN_GENERATED_KEYS);
			
			//!!!-----------no auction class yet-------------------!!!
			insertStmt.setString(1, forum.getAuction().getAuctionID()); 
			insertStmt.setString(2, forum.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(forum.getTimeStamp().getTime()));
			insertStmt.setString(4, forum.getContent());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			String forumID = "-1";
			if(resultKey.next()) {
				forumID = resultKey.getString(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			forum.setForumID(forumID);
			return forum;
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

	public Forum delete(Forum forum) throws SQLException {

		String deleteForum = "DELETE FROM Forum WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteForum);
			deleteStmt.setString(1, forum.getForumID());
			int affectedRows = deleteStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ForumID=" + forum.getForumID());
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
	
	public Forum updateForum(Forum forum, String newContent) throws SQLException {
		String updateForum = "UPDATE Forum SET Content=?,Created=? WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateForum);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setString(3, forum.getForumID());
			updateStmt.executeUpdate();

			forum.setContent(newContent);
			forum.setTimeStamp(newCreatedTimestamp);
			return forum;
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
	
	public Forum getForumById(String forumID) throws SQLException {
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forum " +
			"WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setString(1, forumID);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			
			//!!!-----------no auction class yet-------------------!!!
			AuctionDao auctionDao = AuctionDao.getInstance();
			
			if(results.next()) {
				String resultForumID = results.getString("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				String auctionID = results.getString("AuctionID");
				Auction auction = auctionDao.getAuctionById(auctionID);
				
				String userID = results.getString("UserID");
				Users user = usersDao.getUserFromuserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forum forum = new Forum(resultForumID, auction,
						user, timeStamp, content);
				
				return forum;
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
	
	public List<Forum> getForumForUser(Users user) throws SQLException {
		List<Forum> forums = new ArrayList<Forum>();
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forum " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setString(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			//!!!-----------no auction class yet-------------------!!!
			AuctionDao auctionDao = AuctionDao.getInstance();
			
			while(results.next()) {
				String resultForumID = results.getString("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				String auctionID = results.getString("AuctionID");
				Auction auction = auctionDao.getAuctionById(auctionID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forum forum = new Forum(resultForumID, auction,
						user, timeStamp, content);
				
				forums.add(forum);
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
		return forums;
	}

	//!!!-----------no auction class yet-------------------!!!
	public List<Forum> getForumForAuction(Auction auction) throws SQLException {
		List<Forum> forums = new ArrayList<Forum>();
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forum " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setString(1, auction.getAuctionID());
			results = selectStmt.executeQuery();
			
			UsersDao userDao = UsersDao.getInstance();
			
			while(results.next()) {
				String forumID = results.getString("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				String userID = results.getString("UserID");
				Users user = userDao.getUserFromuserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forum forum = new Forum(forumID, auction,
						user, timeStamp, content);
				
				forums.add(forum);
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
		return forums;
	}
	
}














