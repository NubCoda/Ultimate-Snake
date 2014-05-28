package Model.Interface;

import java.util.Vector;

import Properties.Player;
import Properties.PlayerHighscore;

/**
 * 
 * 
 */
public interface IDataBaseConnection {
	/**
	 * 
	 */
	public void createConnection();

	/**
	 * 
	 * @param playerName
	 */
	public void createPlayer(String playerName);

	/**
	 * 
	 * @return
	 */
	public Vector<PlayerHighscore> getPlayers();
}