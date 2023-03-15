package carAuction.dal;

import carAuction.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Data access object (DAO) class to interact with the underlying Users table in your MySQL
 * instance. This is used to store {@link Users} into your MySQL instance and retrieve 
 * {@link Users} from MySQL instance.
 */
public class UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	/**
	 * Save the Users instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(FirstName, LastName, Address1, Address2, City, State,  Zipcode,  Country, Phone, Email, Password, SignUp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, user.getFirstName());
			insertStmt.setString(2, user.getLastName());
			insertStmt.setString(3, user.getAddress1());
			insertStmt.setString(4, user.getAddress2());
			insertStmt.setString(5, user.getCity());
			insertStmt.setString(6, user.getState());
			insertStmt.setString(7, user.getZipcode());
			insertStmt.setString(8, user.getCountry());
			insertStmt.setString(9, user.getPhone());
			insertStmt.setString(10, user.getEmail());
			insertStmt.setString(11, user.getpassword());
			insertStmt.setTimestamp(12, new Timestamp(user.getSignUp().getTime()));
			insertStmt.executeUpdate();
			
			
			resultKey = insertStmt.getGeneratedKeys();
			int userID = -1;
			if(resultKey.next()) {
				userID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserID(userID);
			return user;
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

	/**
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Users getUserFromUserID(int userID) throws SQLException {
		String selectUser = "SELECT UserID, FirstName, LastName, Address1, Address2, City, State,  Zipcode,  Country, Phone, Email, Password, SignUp from Users WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userID);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultuserID = results.getInt("userID");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String address1 = results.getString("address1");
				String address2 = results.getString("address2");
				String city = results.getString("city");
                String state = results.getString("state");
                String zipcode = results.getString("zipcode");
                String country = results.getString("country");
                String phone = results.getString("phone");
                String email = results.getString("email");
                String password = results.getString("password");
                Date signUp = results.getDate("signUp");
			
				Users User = new Users(resultuserID, firstName, lastName, address1, 
						               address2, city, state,  zipcode,  
						               country, phone, email, password, signUp );
				return User;
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
	 * Update the password of the Persons instance.
	 * This runs a UPDATE statement.
	 */
	
	public Users updatepassword(Users user, String newpassword) throws SQLException {
		String updateUser = "UPDATE Users SET password=? WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newpassword);
			updateStmt.setInt(2, user.getUserID());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setLastName(newpassword);
			return user;
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
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Users instance.
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
}
