package Model.Interface;

import java.awt.event.KeyEvent;

/**
 * 
 * 
 */
public interface IConstants {
	/**
	 * 
	 */
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;

	/**
	 * 
	 */
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;

	/**
	 * 
	 */
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;

	/**
	 * 
	 */
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;

	public static final int DEFAULT_KEY_SPACE = KeyEvent.VK_SPACE;

	/**
	 * 
	 */
	public static final String APPLE_PATH = "./resources/pic/apple_sprite.png";

	/**
	 * 
	 */
	public static final String SNAKE_HEAD_PATH = "./resources/pic/head_sprite.png";

	/**
	 * 
	 */
	public static final String SNAKE_TAIL_PATH = "./resources/pic/tail_sprite.png";

	public static final String OPPONENT_PATH = "./resources/pic/ghost_sprite.png";
	
	public static final String BARRIER_PATH = "./resources/pic/tree_sprite.png";
	
	public static final String BULLET_PATH = "./resources/pic/bullet_sprite.png";
	/**
	 * 
	 */
	public static final String CONFIG_PATH = "./resources/other/config.ini";

	/**
	 * 
	 */
	public static final int SNAKE_SPEED = 50;

	/**
	 * 
	 */
	public static final String DATABASE_PATH = "jdbc:sqlite:./resources/other/database.db3";

	/**
	 * 
	 */
	public static final String TABLE_PLAYER = "player";
	/**
	 * 
	 */
	public static final String TABLE_HIGHSCORE = "player_highscore";
}