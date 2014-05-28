package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;

import Model.Interface.IConstants;
import Properties.GameSettings;
import Properties.Player;
import Properties.PlayerHighscore;

public class FileModel extends Observable {
	private File file;

	public FileModel() {
		file = new File(IConstants.CONFIG_PATH);
	}

	public void writeToIniFile(PlayerHighscore playerHighscore, String difficulty) {
		Properties properties = new Properties();
		properties.setProperty("player_name",
				String.valueOf(playerHighscore.getPlayer().getPlayerName()));
		properties.setProperty("player_id",
				String.valueOf(playerHighscore.getPlayer().getPlayerId()));
		properties.setProperty("highscore",
				String.valueOf(playerHighscore.getHighscore()));
		properties.setProperty("highscore_id",
				String.valueOf(playerHighscore.getHighscore_id()));
		properties.setProperty("difficulty", difficulty);
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
			playerHighscore = new PlayerHighscore(new Player(
					Integer.valueOf(properties.getProperty("player_id")),
					properties.getProperty("player_name")),
					Integer.valueOf(properties.getProperty("highscore")),
					Integer.valueOf(properties.getProperty("highscore_id")));
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
			gameSettings = new GameSettings(properties.getProperty("snake_speed", "Normal"));
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
