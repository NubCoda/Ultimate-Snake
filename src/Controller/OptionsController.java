package Controller;

import java.awt.Dimension;
import java.util.Vector;

import Model.DatabaseConnectionModel;
import Model.OptionsModel;
import Properties.Player;
import View.MainView;

/**
 * 
 * 
 */
public class OptionsController {
	private static OptionsController optionsController;
	private OptionsModel optionsModel;
	private DatabaseConnectionModel databaseConnectionModel;
	

	/**
	 * 
	 */
	private OptionsController() {
		optionsModel = new OptionsModel();
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
}