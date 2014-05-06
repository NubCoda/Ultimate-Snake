package Model.Interface;

import java.awt.Dimension;
import java.awt.event.KeyEvent;


public interface IConstants {
	/** Die Standard-Optionen für das Spiel */
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;

	public static final Dimension DEFAULT_RESOLUTION = new Dimension(800, 600);

	/** Pfade für die Sprites des Spiels */
	public static final String APPLE_PAHT = "./resources/apple_sprite.png";
	public static final String SNAKE_HEAD_PAHT = "./resources/head_sprite.png";
	public static final String SNAKE_TAIL_PAHT = "./resources/tail_sprite.png";

	/** Pfad für die Konfigurationsdatei des Spiels */
	public static final String CONFIG_PATH = "./resources/config.ini";
	
	public static final String DATABASE_NAME = "database.db3";
	public static final String DATABASE_PATH = "jdbc:sqlite:./resources/database.db3";
	
	public static final String TABLE_PLAYER_NAME = "player";

}