package Model.Interface;

import java.awt.event.KeyEvent;

/**
 * Dieser Interfaces beinhaltet Konstanten für die Default-Optionen
 */
public interface IDefaultOptions {
	/**
	 * Der Standardwert für die Taste "rechts"
	 */
	public static final int DEFAULT_KEY_RIGHT = KeyEvent.VK_RIGHT;

	/**
	 * Der Standardwert für die Taste "links"
	 */
	public static final int DEFAULT_KEY_LEFT = KeyEvent.VK_LEFT;

	/**
	 * Der Standardwert für die Taste "oben"
	 */
	public static final int DEFAULT_KEY_UP = KeyEvent.VK_UP;

	/**
	 * Der Standardwert für die Taste "unten"
	 */
	public static final int DEFAULT_KEY_DOWN = KeyEvent.VK_DOWN;

	/**
	 * Der Standardwert für die Taste "Leertaste / Schuss"
	 */
	public static final int DEFAULT_KEY_SHOOT = KeyEvent.VK_SPACE;

	/**
	 * Der Standardwert für den Schwierigkeitsgrad
	 */
	public static final Difficulty DEFAULT_DIFFICULTY = Difficulty.MEDIUM;

	/**
	 * Der Standardwert für den Spieler
	 */
	public static final String DEFAULT_PLAYER = "Default";
}