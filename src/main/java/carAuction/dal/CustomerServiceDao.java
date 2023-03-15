package carAuction.dal;

import carAuction.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerServiceDao {
	protected ConnectionManager connectionManager;

	private static CustomerServiceDao instance = null;
	protected CustomerServiceDao() {
		connectionManager = new ConnectionManager();
	}
	public static CustomerServiceDao getInstance() {
		if(instance == null) {
			instance = new CustomerServiceDao();
		}
		return instance;
	}

	public CustomerService create(CustomerService customerService) throws SQLException {
		String insertCustomerService =
			"INSERT INTO CustomerService(CustomerServiceID,FirstName,LastName) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCustomerService);
			insertStmt.setString(1, customerService.getCustomerServiceID());
			insertStmt.setString(2, customerService.getFirstName());
			insertStmt.setString(3, customerService.getLastName());
			insertStmt.executeUpdate();
			return customerService;
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
	 * Delete the CustomerService instance.
	 * This runs a DELETE statement.
	 */
	public CustomerService delete(CustomerService customerService) throws SQLException {
		String deleteCustomerService = "DELETE FROM CustomerService WHERE customerServiceID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCustomerService);
			deleteStmt.setString(1, customerService.getCustomerServiceID());
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
	 * Get the CustomerService record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single CustomerService instance.
	 */
	public CustomerService getCustomerServiceById(String CustomerServiceID) throws SQLException {
		String selectCustomerService =
			"SELECT CustomerServiceID,FirstName,LastName " +
			"FROM CustomerService " +
			"WHERE CustomerServiceID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomerService);
			selectStmt.setString(1, CustomerServiceID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultCustomerServiceID = results.getString("CustomerServiceID");
				String FirstName = results.getString("FirstName");
				String LastName = results.getString("LastName");
				CustomerService customerService = new CustomerService(resultCustomerServiceID, FirstName,
						LastName);
				return customerService;
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