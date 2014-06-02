package Controller;

import java.awt.Dimension;
import java.util.Vector;

import Model.DatabaseConnectionModel;
import Model.FileModel;
import Model.OptionsModel;
import Properties.GameSettings;
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

	public void updateHighScore(PlayerHighscore playerHighscore) {
		playerHighscore.setHighscore(playerHighscore.getCurrentScore());
		databaseConnectionModel.updatePlayerScore(playerHighscore);
		saveToFile(playerHighscore, MainController.getInstance()
				.getCurrentGameSettings().getDifficulty());
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

	public boolean createPlayer(String playerName) {
		boolean created = databaseConnectionModel.createPlayer(playerName);
		return created;
	}

	public Vector<PlayerHighscore> getPlayers() {
		Vector<PlayerHighscore> playerVector = databaseConnectionModel
				.getPlayers();
		return playerVector;
	}

	public Vector<PlayerHighscore> getTopPlayers() {
		Vector<PlayerHighscore> vectorTopPlayer = databaseConnectionModel
				.getTopTenPlayers();
		return vectorTopPlayer;
	}

	public PlayerHighscore getAndSetLastPlayerFromFile() {
		PlayerHighscore playerHighscore = null;
		playerHighscore = fileModel.getLastPlayerFromFile();
		return playerHighscore;
	}

	public GameSettings getGameSettings() {
		GameSettings gameSettings = fileModel.getGameSettingsFromFile();
		return gameSettings;
	}

	public void saveToFile(PlayerHighscore playerHighscore, int difficulty) {
		fileModel.writeToIniFile(playerHighscore, difficulty);
	}

	public PlayerHighscore getSinglePlayer(String playerName) {
		PlayerHighscore playerHighscore = null;
		playerHighscore = databaseConnectionModel.getSinglePlayer(playerName);
		// MainController.getInstance().notifiyStatusbar();
		return playerHighscore;
	}
}