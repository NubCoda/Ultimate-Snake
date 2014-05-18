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

	/**
	 * 
	 */
	public static final String APPLE_PAHT = "./resources/pic/apple_sprite.png";
	
	/**
	 * 
	 */
	public static final String SNAKE_HEAD_PAHT = "./resources/pic/head_sprite.png";
	
	/**
	 * 
	 */
	public static final String SNAKE_TAIL_PAHT = "./resources/pic/tail_sprite.png";

	/**
	 * 
	 */
	public static final String CONFIG_PATH = "./recources/other/config.ini";
	
	/**
	 * 
	 */
	public static final int SNAKE_SPEED = 150;

	/**
	 * 
	 */
	public static final String DATABASE_PATH = "jdbc:sqlite:./resources/other/database.db3";
	
	/**
	 * 
	 */
	public static final String TABLE_PLAYER = "player";
}