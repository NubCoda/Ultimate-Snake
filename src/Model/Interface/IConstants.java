package Model.Interface;

/**
 * In dieser Klasse werden alle konstanten Werte gespeichert, die nicht
 * ver�ndert werden. Enthalten sind verschiedene Pfade f�r die Bilddateien, der
 * Datenbank und dem INI-File.
 */
public interface IConstants {
	/**
	 * Statische Variable f�r den Bild des Apfel
	 */
	public static final String APPLE_PATH = "./resources/pic/apple_sprite.png";

	/**
	 * Statische Variable f�r den Bild des Snake-Kopfes
	 */
	public static final String SNAKE_HEAD_PATH = "./resources/pic/head_sprite.png";

	/**
	 * Statische Variable f�r Bild des Snake-K�rperteils
	 */
	public static final String SNAKE_TAIL_PATH = "./resources/pic/tail_sprite.png";

	/**
	 * Statische Variable f�r den Bild des Gegners
	 */
	public static final String OPPONENT_PATH = "./resources/pic/ghost_sprite.png";

	/**
	 * Statische Variable f�r den Bild des Schusses
	 */
	public static final String BULLET_PATH = "./resources/pic/bullet_sprite.png";

	/**
	 * Statische Variable f�r den Bild der Barriere / des Baumstumpfes
	 */
	public static final String BARRIER_PATH = "./resources/pic/tree_sprite.png";

	/**
	 * Statische Variable f�r das Config-File
	 */
	public static final String CONFIG_PATH = "./resources/other/config.ini";

	/**
	 * Statische Variable f�r die Hintergrundmelodie.
	 */
	public static final String GAME_SOUND_PATH = "./resources/sound/background_game.mp3";

	/**
	 * Statische Variable f�r die Datenbank
	 */
	public static final String DATABASE_PATH = "jdbc:sqlite:./resources/other/database.db3";

	/**
	 * Statische Variable f�r die Datenbanktabelle
	 */
	public static final String TABLE_PLAYER = "player";
}