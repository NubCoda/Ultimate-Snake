package Model;

import java.util.Observable;
import java.util.Vector;

import Properties.GameSettings;
import Properties.PlayerHighscore;
import View.StatusLabelView;

public class StatusbarModel extends Observable {
	private Vector<StatusLabelView> statusLabelVector;
	private Vector<String> keyNameVector;
	private PlayerHighscore playerHighscore;
	private GameSettings gameSettings;

	public StatusbarModel(PlayerHighscore playerHighscore,
			GameSettings gameSettings) {
		this.playerHighscore = playerHighscore;
		this.gameSettings = gameSettings;
		statusLabelVector = new Vector<StatusLabelView>();
		keyNameVector = new Vector<String>();
	}

	public void addLabelToVector(StatusLabelView statusLabelView) {
		statusLabelVector.add(statusLabelView);
	}

	public void addKeyNameToVector(String keyName) {
		keyNameVector.add(keyName);
	}

	public void notifiyStatusbar() {
		setChanged();
		notifyObservers();
	}

	public void repaintElements() {
		statusLabelVector.elementAt(0).setText(
				"Spieler: " + playerHighscore.getPlayer().getPlayerName());
		statusLabelVector.elementAt(1).setText(
				"Aktueller Score: " + playerHighscore.getCurrentScore());
		statusLabelVector.elementAt(2).setText(
				"Highscore: " + playerHighscore.getHighscore());
		statusLabelVector.elementAt(3).setText(
				"Schwierigkeit: " + gameSettings.getDifficulty());
	}
}
