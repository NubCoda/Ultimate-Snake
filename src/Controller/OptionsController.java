package Controller;

import java.awt.Dimension;
import java.io.File;
import java.util.Properties;
import java.util.Vector;

import Model.DatabaseConnectionModel;
import Model.FileModel;
import Model.OptionsModel;
import Model.Interface.IConstants;
import Properties.Player;
import Properties.PlayerHighscore;
import View.MainView;

/**
 * 
 * 
 */
public class OptionsController {
	private static OptionsController optionsController;
	private OptionsModel optionsModel;
	private DatabaseConnectionModel databaseConnectionModel;
	private FileModel fileModel;

	/**
	 * 
	 */
	private OptionsController() {
		optionsModel = new OptionsModel();
		databaseConnectionModel = new DatabaseConnectionModel();
		fileModel = new FileModel();
	}

	/**
	 * 
	 * @return
	 */
	public static OptionsController getInstance() {
		if (optionsController == null) {
			optionsController = new OptionsController();
		}
		return optionsController;
	}

	/**
	 * 
	 * @param mainView
	 * @param dimension
	 */
	public void setResolution(MainView mainView, Dimension dimension) {
		optionsModel.setDimension(dimension);
		optionsModel.addObserver(mainView);
		optionsModel.setChanges();
	}

	public void createPlayer(String playerName) {
		databaseConnectionModel.createPlayer(playerName);
	}

	public Vector<Player> getPlayers() {
		Vector<Player> playerVector = databaseConnectionModel.getPlayers();
		return playerVector;
	}
	
	public PlayerHighscore setLastPlayerFromFile(){
		PlayerHighscore playerHighscore = null;
		playerHighscore = fileModel.getLastPlayerFromFile();
		return playerHighscore;
	}

	public void savePlayerToFile(PlayerHighscore playerHighscore) {
		fileModel.writeToIniFile(playerHighscore);
	}
	
	public PlayerHighscore getSinglePlayer(String playerName) {
		PlayerHighscore playerHighscore = null;
		playerHighscore = databaseConnectionModel.getSinglePlayer(playerName);
		return playerHighscore;
	}
}