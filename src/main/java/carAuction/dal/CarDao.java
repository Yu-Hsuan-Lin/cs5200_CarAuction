package carAuction.dal;

public class CarDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PersonsDao instance = null;
	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PersonsDao getInstance() {
		if(instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}
}
