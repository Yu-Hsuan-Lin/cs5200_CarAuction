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


public class ForumsDao {
	protected ConnectionManager connectionManager;
		
	private static ForumsDao instance = null;
	protected ForumsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ForumsDao getInstance() {
		if(instance == null) {
			instance = new ForumsDao();
		}
		return instance;
	}
	
	public Forums create(Forums forums) throws SQLException {
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
			insertStmt.setInt(1, forums.getAuction().getAuctionID()); 
			insertStmt.setInt(2, forums.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(forums.getTimeStamp().getTime()));
			insertStmt.setString(4, forums.getContent());
			insertStmt.executeUpdate();
			
			return forums;
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

	public Forums delete(Forums forum) throws SQLException {

		String deleteForum = "DELETE FROM Forums WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteForum);
			deleteStmt.setInt(1, forum.getForumID());
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
	
	public Forums updateForum(Forums forum, String newContent) throws SQLException {
		String updateForum = "UPDATE Forum SET Content=?,Created=? WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateForum);
			updateStmt.setString(1, newContent);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, forum.getForumID());
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
	
	public Forums getForumById(int forumID) throws SQLException {
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE ForumID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setInt(1, forumID);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			
			//!!!-----------no auction class yet-------------------!!!
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			
			if(results.next()) {
				int resultForumID = results.getInt("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				int auctionID = results.getInt("AuctionID");
				Auctions auction = auctionsDao.getAuctionById(auctionID);
				
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(resultForumID, auction,
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
	
	public List<Forums> getForumForUser(Users user) throws SQLException {
		List<Forums> forums = new ArrayList<Forums>();
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setInt(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			//!!!-----------no auction class yet-------------------!!!
			AuctionsDao auctionsDao = AuctionsDao.getInstance();
			
			while(results.next()) {
				int resultForumID = results.getInt("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				int auctionID = results.getInt("AuctionID");
				Auctions auction = auctionsDao.getAuctionById(auctionID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(resultForumID, auction,
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
	public List<Forums> getForumForAuction(Auctions auction) throws SQLException {
		List<Forums> forums = new ArrayList<Forums>();
		String selectForum =
			"SELECT ForumID,AuctionID,UserID,TimeStamp,Content " +
			"FROM Forums " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectForum);
			selectStmt.setInt(1, auction.getAuctionID());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int forumID = results.getInt("ForumID");
				
				//!!!-----------no auction class yet-------------------!!!
				int userID = results.getInt("UserID");
				Users user = usersDao.getUserFromUserID(userID);
				
				Date timeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
				String content = results.getString("Content");
				
				Forums forum = new Forums(forumID, auction,
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














