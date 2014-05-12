package Controller;

import DataAccessObject.DatabaseAccessObject;
import Properties.Player;

public class DatabaseController {
	private static DatabaseController databaseController;
	private DatabaseAccessObject databaseAccessObject;
	private Player player;
	
	private DatabaseController() {
		databaseAccessObject = new DatabaseAccessObject();
	}
	
	public static DatabaseController getInstance() {
		if(databaseController == null) {
			databaseController = new DatabaseController();
		}
		return databaseController;
	}
	
}
