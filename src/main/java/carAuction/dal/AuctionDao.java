package carAuction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import carAuction.model.*;

public class AuctionDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AuctionDao instance = null;
	protected AuctionDao() {
		connectionManager = new ConnectionManager();
	}
	public static AuctionDao getInstance() {
		if(instance == null) {
			instance = new AuctionDao();
		}
		return instance;
	}
	
	public Auction create(Auction auction) throws SQLException {
		String insertCar =
				"INSERT INTO Auction(AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCar);
			insertStmt.setString(1, auction.getAuctionID());
			insertStmt.setString(2, auction.getTitle());
			insertStmt.setTimestamp(3, new Timestamp(auction.getStartTime().getTime()));
			insertStmt.setTimestamp(4, new Timestamp(auction.getEndTime().getTime()));
			insertStmt.setString(5, auction.getCarID());
			insertStmt.setString(6, auction.getUserID());
			insertStmt.setString(7, auction.getHighlights());
			insertStmt.setString(8, auction.getPictures());
			insertStmt.setFloat(9, auction.getMinimumPrice());
			insertStmt.setFloat(10, auction.getCurrentHighestPrice());
			insertStmt.setString(11, auction.getAuctionStatus().name());
			insertStmt.setString(12, auction.getCustomerServiceID());
			insertStmt.setBoolean(13, auction.getPriceChangeAlert());
			insertStmt.executeUpdate();
			
			return auction;
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
	
	public Auction updateTitle(Auction auction, String newTitle) throws SQLException {
		String updateCar = "UPDATE Auction SET Title=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newTitle);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setTitle(newTitle);
			return auction;
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
	
	public Auction updateEndTime(Auction auction, Date newEndTime) throws SQLException {
		String updateCar = "UPDATE Auction SET EndTime=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setTimestamp(1, new Timestamp(newEndTime.getTime()));
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setEndTime(newEndTime);
			return auction;
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
	
	public Auction updateHighlights(Auction auction, String newHighlights) throws SQLException {
		String updateCar = "UPDATE Auction SET Highlights=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newHighlights);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setHighlights(newHighlights);
			return auction;
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
	
	public Auction updatePictures(Auction auction, String newPictures) throws SQLException {
		String updateCar = "UPDATE Auction SET Pictures=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newPictures);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setPictures(newPictures);
			return auction;
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
	
	public Auction updateCurrentHighestPrice(Auction auction, Float newCurrentHighestPrice) throws SQLException {
		String updateCar = "UPDATE Auction SET CurrentHighestPrice=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setFloat(1, newCurrentHighestPrice);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setCurrentHighestPrice(newCurrentHighestPrice);
			return auction;
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
	
	public Auction updateAuctionStatus(Auction auction, Auction.AuctionStatusValue newAuctionStatus) throws SQLException {
		String updateCar = "UPDATE Auction SET AuctionStatus=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newAuctionStatus.name());
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setAuctionStatus(newAuctionStatus);
			return auction;
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
	
	public Auction updateCustomerServiceID(Auction auction, String newCustomerServiceID) throws SQLException {
		String updateCar = "UPDATE Auction SET CustomerServiceID=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newCustomerServiceID);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setCustomerServiceID(newCustomerServiceID);
			return auction;
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
	
	public Auction updatePriceChangeAlert(Auction auction, Boolean newPriceChangeAlert) throws SQLException {
		String updateCar = "UPDATE Auction SET PriceChangeAlert=? WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setBoolean(1, newPriceChangeAlert);
			updateStmt.setString(2, auction.getAuctionID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			auction.setPriceChangeAlert(newPriceChangeAlert);
			return auction;
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
	
	public Auction delete(Auction auction) throws SQLException {
		String deleteAuction = "DELETE FROM Auction WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAuction);
			deleteStmt.setString(1, auction.getAuctionID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Car instance.
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
	
	public Auction getAuctionById(String auctionID) throws SQLException {
		String selectAuction =
			"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
			"FROM Auction " +
			"WHERE AuctionID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuction);
			selectStmt.setString(1, auctionID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultAuctionID = results.getString("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				String carID = results.getString("CarID");
				String userID = results.getString("UserID");
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auction.AuctionStatusValue auctionStatus = Auction.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				String customerServiceID = results.getString("CustomerServiceID");
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auction auction = new Auction(resultAuctionID,title,startTime,endTime,carID,userID,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerServiceID,priceChangeAlert);
				return auction;
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
	
	public List<Auction> getAuctionForUser(Users users) throws SQLException {
		List<Auction> auctions = new ArrayList<Auction>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auction " +
				"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setString(1, users.getUserID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String auctionID = results.getString("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				String carID = results.getString("CarID");
				String userID = results.getString("UserID");
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auction.AuctionStatusValue auctionStatus = Auction.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				String customerServiceID = results.getString("CustomerServiceID");
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auction auction = new Auction(auctionID,title,startTime,endTime,carID,userID,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerServiceID,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
	
	public List<Auction> getAuctionForCar(Car car) throws SQLException {
		List<Auction> auctions = new ArrayList<Auction>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auction " +
				"WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setString(1, car.getCarID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String auctionID = results.getString("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				String carID = results.getString("CarID");
				String userID = results.getString("UserID");
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auction.AuctionStatusValue auctionStatus = Auction.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				String customerServiceID = results.getString("CustomerServiceID");
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auction auction = new Auction(auctionID,title,startTime,endTime,carID,userID,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerServiceID,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
	
	public List<Auction> getAuctionForCustomerService(CustomerService customerService) throws SQLException {
		List<Auction> auctions = new ArrayList<Auction>();
		String selectAuctions =
				"SELECT AuctionID,Title,StartTime,EndTime,CarID,UserID,Highlights,Pictures,MinimumPrice,CurrentHighestPrice,AuctionStatus,CustomerServiceID,PriceChangeAlert " +
				"FROM Auction " +
				"WHERE CustomerServiceID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuctions);
			selectStmt.setString(1, customerService.getCustomerServiceID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String auctionID = results.getString("AuctionID");
				String title = results.getString("Title");
				Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
				Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
				String carID = results.getString("CarID");
				String userID = results.getString("UserID");
				String highlights = results.getString("Highlights");
				String pictures = results.getString("Pictures");
				Float minimumPrice = results.getFloat("MinimumPrice");
				Float currentHighestPrice = results.getFloat("CurrentHighestPrice");
				Auction.AuctionStatusValue auctionStatus = Auction.AuctionStatusValue.valueOf(results.getString("AuctionStatus"));
				String customerServiceID = results.getString("CustomerServiceID");
				Boolean priceChangeAlert = results.getBoolean("PriceChangeAlert");
				
				Auction auction = new Auction(auctionID,title,startTime,endTime,carID,userID,highlights,pictures,minimumPrice,currentHighestPrice,auctionStatus,customerServiceID,priceChangeAlert);
				auctions.add(auction);
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
		return auctions;
	}
}
