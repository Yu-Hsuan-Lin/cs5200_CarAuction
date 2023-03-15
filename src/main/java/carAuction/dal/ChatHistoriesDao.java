package carAuction.dal;

import carAuction.model.ChatHistories.ServiceType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import carAuction.model.*;

public class ChatHistoriesDao {
  protected ConnectionManager connectionManager;

  private static ChatHistoriesDao instance = null;
  protected ChatHistoriesDao() {
    connectionManager = new ConnectionManager();
  }
  public static ChatHistoriesDao getInstance() {
    if(instance == null) {
      instance = new ChatHistoriesDao();
    }
    return instance;
  }

  public ChatHistories create(ChatHistories chatHistories) throws SQLException {
    String insertChatHistories =
        "INSERT INTO ChatHistories(CustomerServiceID, UserID, TimeStamp, ServiceType) " +
            "VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BlogPosts has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertChatHistories,
          Statement.RETURN_GENERATED_KEYS);
      // Note: for the sake of simplicity, just set Picture to null for now.
      insertStmt.setString(1, chatHistories.getCustomerServiceID());
      insertStmt.setString(2, chatHistories.getUserID());
      insertStmt.setTimestamp(3, new Timestamp(chatHistories.getTimeStamp().getTime()));
      insertStmt.setString(4, chatHistories.getServiceType().name());
      insertStmt.executeUpdate();

      return chatHistories;
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

  public ChatHistories updateServiceType(ChatHistories chatHistories, ServiceType newServiceType) throws SQLException {
    String updateChatHistories = "UPDATE ChatHistories SET ServiceType=? WHERE ChatID=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateChatHistories);
      updateStmt.setString(1, newServiceType.name());
      updateStmt.setInt(2, chatHistories.getChatID());
      updateStmt.executeUpdate();
      return chatHistories;
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

  public ChatHistories delete(ChatHistories chatHistories) throws SQLException {

    String deleteChatHistories = "DELETE FROM ChatHistories WHERE ChatID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteChatHistories);
      deleteStmt.setInt(1, chatHistories.getChatID());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the BlogPosts instance.
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


  public ChatHistories getChatHistoriesByUserID(String UserID) throws SQLException {
    String selectChatHistories =
        "SELECT (ChatID, CustomerServiceID, UserID, TimeStamp, ServiceType " +
            "FROM ChatHistories " +
            "WHERE UserID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectChatHistories);
      selectStmt.setString(1, UserID);
      results = selectStmt.executeQuery();
      ChatHistoriesDao chatHistoriesDao = ChatHistoriesDao.getInstance();
      if(results.next()) {
        int resultChatID = results.getInt("ChatID");
        String CustomerServiceID = results.getString("CustomerServiceID");
        UserID = results.getString("UserID");
        Date TimeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
        ChatHistories.ServiceType serviceType =
            ChatHistories.ServiceType.valueOf(results.getString("ServiceType"));

        ChatHistories chatHistories = new ChatHistories(resultChatID, CustomerServiceID, UserID,
            TimeStamp, serviceType);
        return chatHistories;
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
