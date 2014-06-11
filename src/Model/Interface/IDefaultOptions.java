package Model.Interface;

import java.awt.event.KeyEvent;

/**
 * Dieser Interfaces beinhaltet Konstanten f�r die Default-Optionen
 */
public interface IDefaultOptions {
	/**
	 * Der Standardwert f�r die Taste "rechts"
	 */
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;

	/**
	 * Der Standardwert f�r die Taste "links"
	 */
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;

	/**
	 * Der Standardwert f�r die Taste "oben"
	 */
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;

	/**
	 * Der Standardwert f�r die Taste "unten"
	 */
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;

	/**
	 * Der Standardwert f�r die Taste "Leertaste / Schuss"
	 */
	public static final int DEFAULT_KEY_SHOOT = KeyEvent.VK_SPACE;

	/**
	 * Der Standardwert f�r den Schwierigkeitsgrad
	 */
	public static final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;

	/**
	 * Der Standardwert f�r den Spieler
	 */
	public static final String DEFAULT_PLAYER = "Default";
}