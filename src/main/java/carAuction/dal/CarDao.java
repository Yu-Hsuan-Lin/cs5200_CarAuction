package carAuction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import carAuction.model.*;

public class CarDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CarDao instance = null;
	protected CarDao() {
		connectionManager = new ConnectionManager();
	}
	public static CarDao getInstance() {
		if(instance == null) {
			instance = new CarDao();
		}
		return instance;
	}
	
	public Car create(Car car) throws SQLException {
		String insertCar =
				"INSERT INTO Car(CarID,UserID,Year,Maker,Model,Trim,Body,Transmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCar);
			insertStmt.setString(1, car.getCarID());
			insertStmt.setString(2, car.getUserID());
			insertStmt.setInt(3, car.getYear());
			insertStmt.setString(4, car.getMaker());
			insertStmt.setString(5, car.getModel());
			insertStmt.setString(6, car.getTrim());
			insertStmt.setString(7, car.getBody());
			insertStmt.setString(8, car.getTransmission());
			insertStmt.setString(9, car.getVIN());
			insertStmt.setString(10, car.getState());
			insertStmt.setFloat(11, car.getConditionScore());
			insertStmt.setInt(12, car.getOdoMeter());
			insertStmt.setString(13, car.getColor());
			insertStmt.setString(14, car.getInterior());
			insertStmt.setInt(15, car.getMMR());
			insertStmt.executeUpdate();
			
			return car;
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
	
	public Car updateUserID(Car car, String newUserID) throws SQLException {
		String updateCar = "UPDATE Car SET UserID=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setString(1, newUserID);
			updateStmt.setString(2, car.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			car.setUserID(newUserID);
			return car;
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
	
	public Car updateConditionScore(Car car, Float newConditionScore) throws SQLException {
		String updateCar = "UPDATE Car SET ConditionScore=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setFloat(1, newConditionScore);
			updateStmt.setString(2, car.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			car.setConditionScore(newConditionScore);
			return car;
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
	
	public Car updateOdoMeter(Car car, Integer newOdoMeter) throws SQLException {
		String updateCar = "UPDATE Car SET OdoMeter=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setInt(1, newOdoMeter);
			updateStmt.setString(2, car.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			car.setOdoMeter(newOdoMeter);
			return car;
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

	public Car updateMMR(Car car, Integer newMMR) throws SQLException {
		String updateCar = "UPDATE Car SET MMR=? WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCar);
			updateStmt.setInt(1, newMMR);
			updateStmt.setString(2, car.getCarID());
			updateStmt.executeUpdate();

			// Update the car parameter before returning to the caller.
			car.setMMR(newMMR);
			return car;
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
	
	public Car delete(Car car) throws SQLException {
		String deleteCar = "DELETE FROM Car WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCar);
			deleteStmt.setString(1, car.getCarID());
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
	
	public Car getCarById(String carID) throws SQLException {
		String selectCar =
			"SELECT CarID,UserID,Year,Maker,Model,Trim,Body,Trasmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
			"FROM Car " +
			"WHERE CarID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCar);
			selectStmt.setString(1, carID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultCarID = results.getString("CarID");
				String userID = results.getString("UserID");
				Integer year = results.getInt("Year");
				String maker = results.getString("Maker");
				String model = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String vin = results.getString("VIN");
				String state = results.getString("State");
				Float conditionScore = results.getFloat("ConditionScore");
				Integer odoMeter = results.getInt("OdoMeter");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				Integer mmr = results.getInt("MMR");
				
				Car car = new Car(resultCarID,userID,year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				return car;
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
	
	public List<Car> getCarForUser(Users users) throws SQLException {
		List<Car> cars = new ArrayList<Car>();
		String selectCars =
				"SELECT CarID,UserID,Year,Maker,Model,Trim,Body,Trasmission,VIN,State,ConditionScore,OdoMeter,Color,Interior,MMR " +
				"FROM Car " +
				"WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCars);
			selectStmt.setString(1, users.getUserID());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String resultCarID = results.getString("CarID");
				String userID = results.getString("UserID");
				Integer year = results.getInt("Year");
				String maker = results.getString("Maker");
				String model = results.getString("Model");
				String trim = results.getString("Trim");
				String body = results.getString("Body");
				String transmission = results.getString("Transmission");
				String vin = results.getString("VIN");
				String state = results.getString("State");
				Float conditionScore = results.getFloat("ConditionScore");
				Integer odoMeter = results.getInt("OdoMeter");
				String color = results.getString("Color");
				String interior = results.getString("Interior");
				Integer mmr = results.getInt("MMR");
				
				Car car = new Car(resultCarID,userID,year,maker,model,trim,body,transmission,vin,state,conditionScore,odoMeter,color,interior,mmr);
				cars.add(car);
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
		return cars;
	}
}
