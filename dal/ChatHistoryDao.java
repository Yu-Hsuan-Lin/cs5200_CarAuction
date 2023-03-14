package carAuction.dal;

import carAuction.model.ChatHistory.ServiceType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import carAuction.model.*;

public class ChatHistoryDao {
  protected ConnectionManager connectionManager;

  private static ChatHistoryDao instance = null;
  protected ChatHistoryDao() {
    connectionManager = new ConnectionManager();
  }
  public static ChatHistoryDao getInstance() {
    if(instance == null) {
      instance = new ChatHistoryDao();
    }
    return instance;
  }

  public ChatHistory create(ChatHistory chatHistory) throws SQLException {
    String insertChatHistory =
        "INSERT INTO ChatHistory(ChatID, CustomerServiceID, UserID, TimeStamp, ServiceType) " +
            "VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      // BlogPosts has an auto-generated key. So we want to retrieve that key.
      insertStmt = connection.prepareStatement(insertChatHistory,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, chatHistory.getChatID());
      // Note: for the sake of simplicity, just set Picture to null for now.
      insertStmt.setString(2, chatHistory.getCustomerServiceID());
      insertStmt.setString(3, chatHistory.getUserID());
      insertStmt.setTimestamp(4, new Timestamp(chatHistory.getTimeStamp().getTime()));
      insertStmt.setString(5, chatHistory.getServiceType().name());
      insertStmt.executeUpdate();

      return chatHistory;
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

  public ChatHistory updateServiceType(ChatHistory chatHistory, ServiceType newServiceType) throws SQLException {
    String updateChatHistory = "UPDATE ChatHistory SET ServiceType=? WHERE ChatID=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateChatHistory);
      updateStmt.setString(1, newServiceType.name());
      updateStmt.setString(2, chatHistory.getChatID());
      updateStmt.executeUpdate();
      return chatHistory;
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

  public ChatHistory delete(ChatHistory chatHistory) throws SQLException {

    String deleteChatHistory = "DELETE FROM ChatHistory WHERE ChatID=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteChatHistory);
      deleteStmt.setString(1, chatHistory.getChatID());
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


  public ChatHistory getChatHistoryByUserID(String UserID) throws SQLException {
    String selectChatHistory =
        "SELECT (ChatID, CustomerServiceID, UserID, TimeStamp, ServiceType " +
            "FROM ChatHistory " +
            "WHERE UserID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectChatHistory);
      selectStmt.setString(1, UserID);
      results = selectStmt.executeQuery();
      ChatHistoryDao chatHistoryDao = ChatHistoryDao.getInstance();
      if(results.next()) {
        String resultChatID = results.getString("ChatID");
        String CustomerServiceID = results.getString("CustomerServiceID");
        UserID = results.getString("UserID");
        Date TimeStamp = new Date(results.getTimestamp("TimeStamp").getTime());
        ChatHistory.ServiceType serviceType =
            ChatHistory.ServiceType.valueOf(results.getString("ServiceType"));

        ChatHistory chatHistory = new ChatHistory(resultChatID, CustomerServiceID, UserID,
            TimeStamp, serviceType);
        return chatHistory;
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
