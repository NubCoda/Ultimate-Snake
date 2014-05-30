package Model;

import java.util.Observable;
import java.util.Vector;

import javax.swing.JLabel;

import Properties.GameSettings;
import Properties.PlayerHighscore;

public class StatusbarModel extends Observable {
	private Vector<JLabel> statusLabelVector;
	private Vector<String> keyNameVector;
	private PlayerHighscore playerHighscore;
	private GameSettings gameSettings;

	public StatusbarModel(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		statusLabelVector = new Vector<JLabel>();
		keyNameVector = new Vector<String>();
	}

	public void addLabelToVector(JLabel jLabel) {
		statusLabelVector.add(jLabel);
	}

	public void addKeyNameToVector(String keyName) {
		keyNameVector.add(keyName);
	}

	public void notifiyStatusbar() {
		setChanged();
		notifyObservers();
	}

	public void setValuesOfPlayer(PlayerHighscore playerHighscore) {
		statusLabelVector.elementAt(0).setText(
				"Spieler: " + playerHighscore.getPlayer().getPlayerName());
		statusLabelVector.elementAt(1).setText(
				"Aktueller Score: " + playerHighscore.getCurrentScore());
		statusLabelVector.elementAt(2).setText(
				"Highscore: " + playerHighscore.getHighscore());
		statusLabelVector.elementAt(3).setText(
				"Schwierigkeit: " + gameSettings.getDifficulty());
		setChanged();
		notifyObservers();
	}
}
