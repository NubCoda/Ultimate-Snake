package Model.Interface;

import java.awt.Dimension;
import java.awt.event.KeyEvent;


public interface IConstants {
	public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_UP = KeyEvent.VK_UP;
	public static final int KEY_DOWN = KeyEvent.VK_DOWN;

	public static final String APPLE_PAHT = "./resources/apple_sprite.png";
	public static final String SNAKE_TAIL_PAHT = "./resources/head_sprite.png";
	public static final String SNAKE_HEAD_PAHT = "./resources/tail_sprite.png";

	public static final Dimension DEFAULT_RESOLUTION = new Dimension(800, 600);
}
