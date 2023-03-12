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


public class BidDao {
	protected ConnectionManager connectionManager;

	private static BidDao instance = null;
	protected BidDao() {
		connectionManager = new ConnectionManager();
	}
	public static BidDao getInstance() {
		if(instance == null) {
			instance = new BidDao();
		}
		return instance;
	}

	public Bid create(Bid bid) throws SQLException {
		String insertBid =
			"INSERT INTO Bid(AuctionID,UserID,BidTime,BidPrice) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBid,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, bid.getAuction().getAuctionID());
			insertStmt.setInt(2, bid.getUser().getUserID());
			insertStmt.setTimestamp(3, new Timestamp(bid.getBidTime().getTime()));
			insertStmt.setFloat(4, bid.getBidPrice());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int bidID = -1;
			if(resultKey.next()) {
				bidID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			bid.setBidID(bidID);
			return bid;
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

	/**
	 * Update the BidPrice of the Bid instance.
	 * This runs a UPDATE statement.
	 */
	public Bid updateBidPrice(Bid bid, float BidPrice) throws SQLException {
		String updateeBidPrice = "UPDATE Bid SET BidPrice=? WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateeBidPrice);
			updateStmt.setFloat(1, BidPrice);
			updateStmt.setInt(2, bid.getBidID());
			updateStmt.executeUpdate();

			// Update the bid param before returning to the caller.
			bid.setBidPrice(BidPrice);
			return bid;
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

	/**
	 * Delete the Bid instance.
	 * This runs a DELETE statement.
	 */
	public Bid delete(Bid bid) throws SQLException {
		String deleteBid = "DELETE FROM Bid WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBid);
			deleteStmt.setInt(1, bid.getBidID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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

	/**
	 * Get the Bid record by fetching it from your MySQL instance.
	 */
	public Bid getBidById(int bidID) throws SQLException {
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bid " +
			"WHERE BidID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, bidID);
			results = selectStmt.executeQuery();
			AuctionDao auctionDao = AuctionDao.getInstance();
			UserDao userDao = UserDao.getInstance();
			if(results.next()) {
				int resultBidID = results.getInt("bidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				Auction auction = auctionDao.getAuctionById(results.getInt("AuctionID"));
				User user = userDao.getUserById(results.getInt("UserID"));
				Bid bid = new Bid(resultBidID, auction,
						user, bidTime, bidPrice);
				return bid;
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

	/**
	 * Get the all the Bid for a user.
	 */
	public List<Bid> getBidForUser(User user) throws SQLException {
		List<Bid> bids = new ArrayList<Bid>();
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bid " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, user.getUserById());
			results = selectStmt.executeQuery();
			AuctionDao auctionDao = AuctionDao.getInstance();
			while(results.next()) {
				int bidID = results.getInt("BidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				Auction auction = auctionDao.getAuctionById(results.getInt("AuctionID"));
				Bid bid = new Bid(bidID, auction, user,
						bidTime, bidPrice);
				bids.add(bid);
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
		return bids;
	}
	
	/**
	 * Get the all the Bid for an auction.
	 */
	public List<Bid> getBidForAuction(Auction auction) throws SQLException {
		List<Bid> bids = new ArrayList<Bid>();
		String selectBid =
			"SELECT BidID,AuctionID,UserID,BidTime,BidPrice " +
			"FROM Bid " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBid);
			selectStmt.setInt(1, auction.getAuctionById());
			results = selectStmt.executeQuery();
			UserDao userDao = UserDao.getInstance();
			while(results.next()) {
				int bidID = results.getInt("BidID");
				Date bidTime =  new Date(results.getTimestamp("BidTime").getTime());
				float bidPrice = results.getFloat("BidPrice");
				User user = userDao.getUserById(results.getInt("UserID"));
				Bid bid = new Bid(bidID, auction, user,
						bidTime, bidPrice);
				bids.add(bid);
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
		return bids;
	}
}
