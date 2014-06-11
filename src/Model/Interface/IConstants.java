package Model.Interface;

/**
 * In dieser Klasse werden alle konstanten Werte gespeichert, die nicht
 * verändert werden. Enthalten sind verschiedene Pfade für die Bilddateien, der
 * Datenbank und dem INI-File.
 */
public interface IConstants {
	/**
	 * Statische Variable für den Bild des Apfel
	 */
	public static final String APPLE_PATH = "./resources/pic/apple_sprite.png";

	/**
	 * Statische Variable für den Bild des Snake-Kopfes
	 */
	public static final String SNAKE_HEAD_PATH = "./resources/pic/head_sprite.png";

	/**
	 * Statische Variable für Bild des Snake-Körperteils
	 */
	public static final String SNAKE_TAIL_PATH = "./resources/pic/tail_sprite.png";

	/**
	 * Statische Variable für den Bild des Gegners
	 */
	public static final String OPPONENT_PATH = "./resources/pic/ghost_sprite.png";

	/**
	 * Statische Variable für den Bild des Schusses
	 */
	public static final String BULLET_PATH = "./resources/pic/bullet_sprite.png";

	/**
	 * Statische Variable für den Bild der Barriere / des Baumstumpfes
	 */
	public static final String BARRIER_PATH = "./resources/pic/tree_sprite.png";

	/**
	 * Statische Variable für das Config-File
	 */
	public static final String CONFIG_PATH = "./resources/other/config.ini";

	/**
	 * Statische Variable für die Hintergrundmelodie.
	 */
	public static final String GAME_SOUND_PATH = "./resources/sound/background_game.mp3";

	/**
	 * Statische Variable für die Datenbank
	 */
	public static final String DATABASE_PATH = "jdbc:sqlite:./resources/other/database.db3";

	/**
	 * Statische Variable für die Datenbanktabelle
	 */
	public static final String TABLE_PLAYER = "player";
}