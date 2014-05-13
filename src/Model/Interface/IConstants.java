package Model.Interface;

import java.awt.Dimension;
import java.awt.event.KeyEvent;


public interface IConstants {
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;

	public static final Dimension DEFAULT_RESOLUTION = new Dimension(1600, 900);
	public static final Dimension[] RESOLUTIONS = new Dimension[] {
		new Dimension(800, 600),
		new Dimension(1024,768),
		new Dimension(1280,720),
		new Dimension(1280,1024),
		new Dimension(1440,1080),
		new Dimension(1600,900)
	};
	
	public static final String APPLE_PAHT = "./resources/pic/apple_sprite.png";
	public static final String SNAKE_HEAD_PAHT = "./resources/pic/head_sprite.png";
	public static final String SNAKE_TAIL_PAHT = "./resources/pic/tail_sprite.png";

	public static final String CONFIG_PATH = "./recources/other/config.ini";
	public static final int SNAKE_SPEED = 150;
	
	
}