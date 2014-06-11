package View;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasse f�r die Texte im Spiel
 */
public class MenuView extends SpriteView {
	private String text;

	/**
	 * Zeichnet das Men�, durch die �bergabe des "null"-Wertes wei� die
	 * Parentklasse Spriteview, dass das Men� kein Buffered Image benutzt.
	 * 
	 * @param x
	 *            x-Position, an welcher gezeichnet wird
	 * @param y
	 *            y-Position, an welcher gezeichnet wird
	 * @param gamePanelView
	 *            Das Panel auf welchem das Bild gezeichnet wird
	 * @param text
	 *            Der Text, welcher auf dem GamePanel dargestellt werden soll
	 */
	public MenuView(double x, double y, GamePanelView gamePanelView, String text) {
		super(null, x, y, gamePanelView);
		this.text = text;
	}

	@Override
	public void drawObjects(Graphics graphics) {
		/*
		 * Zeichnet das Men� Die L�nge und breite sind hierbei jeweils ein
		 * Prozentualer Antel am Gesamten Fenster
		 */
		graphics.setColor(Color.RED);
		graphics.drawString(text,
				(int) ((x * gamePanelView.getWidth() / 100) - (graphics
						.getFontMetrics().getStringBounds(text, graphics)
						.getWidth() / 2)),
				(int) (y * gamePanelView.getHeight() / 100));
	}
}