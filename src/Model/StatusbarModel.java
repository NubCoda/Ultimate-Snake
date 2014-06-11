package Model;

import java.util.Observable;

import Controller.OptionsController;
import Model.Interface.Difficulty;
import Properties.Player;

/**
 * Dies ist die Klasse für das Model der Statusbar.
 */
public class StatusbarModel extends Observable {
	/*
	 * Player Variable
	 */
	private Player player;

	/**
	 * Der Konstruktor des Statusbarmodels
	 * 
	 * @param player
	 *            Das Spielerobjekt mit den Informationen über den aktuellen
	 *            Spieler
	 */
	public StatusbarModel(Player player) {
		this.player = player;
	}

	/**
	 * Gibt den Namen des aktuellen Spielers zurück
	 * 
	 * @return Der Name des aktuellen Spielers
	 */
	public String getPlayerName() {
		return player.getPlayerName();
	}

	/**
	 * Gibt die Score des aktuellen Spielers zurück
	 * 
	 * @return Die Score des aktuellen Spielers
	 */
	public int getScore() {
		return player.getScore();
	}

	/**
	 * Gibt die Highscore des Spielers zurück
	 * 
	 * @return Die Highscore des aktuellen Spielers
	 */
	public int getHighscore() {
		return player.getHighscore();
	}

	/**
	 * Gibt die Anzahl der verfügbaren Schüsse zurück
	 * 
	 * @return Die Anzahl der verfügbaren Schüsse
	 */
	public int getBulletCount() {
		return player.getBulletCount();
	}

	/**
	 * Gibt den Schwierigkeitsgrad zurück
	 * 
	 * @return Der Schwierigkeitsgrad des Spiels
	 */
	public Difficulty getDifficulty() {
		return Difficulty.fromString(OptionsController.getInstance().getOption(
				"difficulty"));
	}

	/**
	 * Benachrichtigt den Observer und zeichnet neu.
	 */
	public void updateStatus() {
		setChanged();
		notifyObservers();
	}
}