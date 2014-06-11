package View;

import java.util.Observable;
import java.util.Observer;

import Controller.MainController;
import Model.Interface.IEnemy;

/**
 * Zeichnet das Hindernis auf dem Gamepanelview
 */
public class BarrierView extends SpriteView implements Observer {
	/**
	 * Kontruktor des Hindernisses
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
	public BarrierView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		/*
		 * Auslesen der neuen Position und wenn das Hinderniss zerstört ist,
		 * dieses aus dem Spiel entfernen
		 */
		IEnemy barrier = ((IEnemy) observable);
		this.x = barrier.getBounding().getX();
		this.y = barrier.getBounding().getY();
		if (!barrier.isAlive()) {
			MainController.getInstance().removeSpriteView(this);
			observable.deleteObserver(this);
		}
	}
}
