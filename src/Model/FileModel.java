package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;

import Controller.OptionsController;
import Model.Interface.IConstants;
import Properties.GameSettings;
import Properties.PlayerHighscore;

public class FileModel extends Observable {
	private File file;

	public FileModel() {
		file = new File(IConstants.CONFIG_PATH);
	}

	public void writeToIniFile(PlayerHighscore playerHighscore, int difficulty) {
		Properties properties = new Properties();
		properties.setProperty("player_name",
				String.valueOf(playerHighscore.getPlayer().getPlayerName()));
		properties.setProperty("difficulty", String.valueOf(difficulty));
		try {
			properties.store(new FileOutputStream(getFile()), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PlayerHighscore getLastPlayerFromFile() {
		PlayerHighscore playerHighscore = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getFile()));
			String playerName = properties.getProperty("player_name");
			playerHighscore = OptionsController.getInstance().getSinglePlayer(
					playerName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerHighscore;
	}

	public GameSettings getGameSettingsFromFile() {
		GameSettings gameSettings = null;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getFile()));
			gameSettings = new GameSettings(Integer.valueOf(properties
					.getProperty("difficulty", "2")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameSettings;
	}

	public File getFile() {
		return file;
	}
}
