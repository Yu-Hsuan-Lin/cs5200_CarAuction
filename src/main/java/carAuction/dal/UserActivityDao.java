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

public class UserActivityDao {
	protected ConnectionManager connectionManager;
	
	private static UserActivityDao instance = null;
	protected UserActivityDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UserActivityDao getInstance() {
		if(instance == null) {
			instance = new UserActivityDao();
		}
		return instance;
	}
	
	public UserActivity create(UserActivity userActivity) throws SQLException {
		String insertUserActivity = "INSERT INTO UserActivity(ActivityID,UserID,ActivityType,TimeStamp) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUserActivity, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, userActivity.getActivityID());
			insertStmt.setString(2, userActivity.getUser().getUserID());
			insertStmt.setString(3, userActivity.getActivityType().name());
			insertStmt.setTimestamp(4, new Timestamp(userActivity.getTimeStamp().getTime()));
			insertStmt.executeUpdate();
			
//			resultKey = insertStmt.getGeneratedKeys();
//			String ActivityID = "-1";
//			if(resultKey.next()) {
//				ActivityID = resultKey.getString(1);
//			} else {
//				throw new SQLException("Unable to retrieve auto-generated key.");
//			}
//			
//			userActivity.setActivityID(ActivityID);
			
			return userActivity;
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
		}
	}
	
	public UserActivity delete(UserActivity userActivity) throws SQLException {
		String deleteUserActivity = "DELETE FROM UserActivity WHERE ActivityID =?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUserActivity);
			deleteStmt.setString(1, userActivity.getActivityID());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for ActivityID=" + userActivity.getActivityID());
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
	
	public UserActivity getUserActivityByID(String ActivityID) throws SQLException {
		String selectUserActivity = "SELECT ActivityID,UserID,ActivityType,TimeStamp FROM UserActivity WHERE ActivityID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserActivity);
			selectStmt.setString(1, ActivityID);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			
			if(results.next()) {
				String resultActivityID = results.getString("ActivityID");
				Users user = usersDao.getUserFromUserID(results.getString("UserID"));
				UserActivity.ActivityType activityType = UserActivity.ActivityType.valueOf(
						results.getString("ActivityType"));
				Date TimeStamp =  new Date(results.getTimestamp("TimeStamp").getTime());
				UserActivity userActivity = new UserActivity(resultActivityID, user, activityType,TimeStamp);
				
				return userActivity;
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
	
	
	// return all userActivities for specific user
	public List<UserActivity> getUserActivityForUser(Users user) throws SQLException {
		List<UserActivity> userActivities = new ArrayList<UserActivity>();
		String selectUserActivity =
			"SELECT ActivityID,UserID,ActivityType,TimeStamp " +
			"FROM UserActivity " +
			"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUserActivity);
			selectStmt.setString(1, user.getUserID());
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				
				String resultActivityID = results.getString("ActivityID");
				UserActivity.ActivityType activityType = UserActivity.ActivityType.valueOf(
						results.getString("ActivityType"));
				Date TimeStamp =  new Date(results.getTimestamp("TimeStamp").getTime());
				UserActivity userActivity = new UserActivity(resultActivityID, user, activityType,TimeStamp);
				
				userActivities.add(userActivity);
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
		return userActivities;
	}

}
