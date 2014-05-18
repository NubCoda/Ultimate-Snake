package Controller;

import java.util.Vector;

import Model.DatabaseConnection;
import Properties.Player;

/**
 * Diese Klasse nur wenn notwendig lassen sonst diese Funktionen irgendwo anders unterbringen 
 */
public class DatabaseController {
	private static DatabaseController databaseController;
	private DatabaseConnection databaseAccessObject;
	
	/**
	 * 
	 */
	private DatabaseController() {
		databaseAccessObject = new DatabaseConnection();
	}
	
	/**
	 * 
	 * @return
	 */
	public static DatabaseController getInstance() {
		if(databaseController == null) {
			databaseController = new DatabaseController();
		}
		return databaseController;
	}
	
	/**
	 * 
	 * @param playerName
	 */
	public void createPlayer(String playerName) {
		databaseAccessObject.createPlayer(playerName);
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<Player> getPlayers() {
		Vector<Player> playerVector = databaseAccessObject.getPlayers();
		return playerVector;
	}
}