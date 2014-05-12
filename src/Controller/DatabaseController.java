package Controller;

import DataAccessObject.DatabaseAccessObject;

public class DatabaseController {
	private static DatabaseController databaseController;
	private DatabaseAccessObject databaseAccessObject;
	
	private DatabaseController() {
		databaseAccessObject = new DatabaseAccessObject();
	}
	
	public static DatabaseController getInstance() {
		if(databaseController == null) {
			databaseController = new DatabaseController();
		}
		return databaseController;
	}
	
	public void createPlayer(String playerName) {
		databaseAccessObject.createPlayer(playerName);
	}
	
}
