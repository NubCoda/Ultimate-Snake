package View;

import java.util.Observable;
import java.util.Observer;

import Controller.MainController;
import Model.Interface.IEnemy;

/**
 * Zeichnet den Gegner mithilfe der Parentklasse Spriteview
 */
public class OpponentView extends SpriteView implements Observer {
	/**
	 * Zeichnet den Opponent an den Stellen x & y, welche aus dem Contoller
	 * übergeben werden
	 * 
	 * @param path
	 *            Pfad des Bildes
	 * @param x
	 *            x-Position, an welcher gezeichnet wird
	 * @param y
	 *            y-Position, an welcher gezeichnet wird
	 * @param gamePanelView
	 *            Das Panel auf welchem das Bild gezeichnet wird
	 */
	public OpponentView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		/*
		 * Aktualisiert die Position des Opponents und entfernt diesen, wenn der
		 * Wert "isAlive" aus dem Opponentmodel = false ist
		 */
		IEnemy opponent = ((IEnemy) observable);
		this.x = opponent.getBounding().getX();
		this.y = opponent.getBounding().getY();
		if (!opponent.isAlive()) {
			MainController.getInstance().removeSpriteView(this);
			observable.deleteObserver(this);
		}
	}

}
