package carAuction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import carAuction.model.*;
import java.sql.Statement;


public class CollectionDao {
  protected ConnectionManager connectionManager;
  private static CollectionDao instance = null;
  protected CollectionDao() {
    connectionManager = new ConnectionManager();
  }
  public static CollectionDao getInstance() {
    if(instance == null) {
      instance = new CollectionDao();
    }
    return instance;
  }

  public Collection create(Collection collection) throws SQLException {
    String insertCollection =
        "INSERT INTO Collection(collectionId,userID,auctionID,priceChangeAlert,"
            + "statusChangeAlert) " +
            "VALUES(?,?,?,?,?);";

    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCollection,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, collection.getCollectionId());
      insertStmt.setString(2, collection.getUserID());
      insertStmt.setString(3, collection.getAuctionID());
      insertStmt.setBoolean(4, collection.getPriceChangeAlert());
      insertStmt.setBoolean(5, collection.getStatusChangeAlert());
      insertStmt.executeUpdate();

      return collection;

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }


  public Collection updatePriceChangeAlert(Collection collection, Boolean newPriceChangeAlert)
      throws SQLException {
    String updateCollection = "UPDATE Collection SET PriceChangeAlert =? WHERE CollectionId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCollection);
      updateStmt.setBoolean(1, newPriceChangeAlert);
      updateStmt.setString(2, collection.getCollectionId());
      updateStmt.executeUpdate();

      // Update the blogComment param before returning to the caller.
      collection.setPriceChangeAlert(newPriceChangeAlert);
      return collection;
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

  public Collection delete(Collection collection) throws SQLException {
    String deleteCollection = "DELETE FROM Collection WHERE CollectionId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCollection);
      deleteStmt.setString(1, collection.getCollectionId());
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

  public Collection getCollectionById(String CollectionId) throws SQLException {
    String selectCollection =
        "SELECT CollectionId,UserID,AuctionID,PriceChangeAlert,StatusChangeAlert " +
            "FROM Collection " +
            "WHERE CollectionId = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollection);
      selectStmt.setString(1, CollectionId);
      results = selectStmt.executeQuery();

      if(results.next()) {
        String resultCollectionId = results.getString("CollectionId");
        String UserID = results.getString("UserID");
        String AuctionID = results.getString("AuctionID");
        Boolean PriceChangeAlert = results.getBoolean("PriceChangeAlert");
        Boolean StatusChangeAlert = results.getBoolean("StatusChangeAlert");

        Collection collection = new Collection(resultCollectionId, UserID,
            AuctionID, PriceChangeAlert, StatusChangeAlert);
        return collection;
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
}



