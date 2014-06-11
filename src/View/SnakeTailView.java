package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IPlayerBone;

/**
 * Ausgabe des Bodyparts der Snake
 */
public class SnakeTailView extends SpriteView implements Observer {
	/**
	 * Positionierung des Bodyparts durch die übergebene Parameter.
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
	public SnakeTailView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		 * Über das Interface IPlayerBone werden der x- & der y-wert des
		 * Bodyparts ausgelesen (und in Spriteview gespeichert)
		 */
		IPlayerBone tail = (IPlayerBone) o;
		this.x = tail.getBounding().getX();
		this.y = tail.getBounding().getY();
	}
}
