package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IActor;

/**
 * Zeichnet den Apfel
 */
public class AppleView extends SpriteView implements Observer {
	/**
	 * Konstruktor zum Zeichnen des Apfels
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
	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IActor apple = ((IActor) observable);
		this.x = apple.getBounding().getX();
		this.y = apple.getBounding().getY();
	}
}
