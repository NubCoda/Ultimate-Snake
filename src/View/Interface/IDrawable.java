package View.Interface;

import java.awt.Graphics;

/**
 * Interface zum Zeichnen von Grafiken
 */
public interface IDrawable {
	/**
	 * Zeichnet Grafikobjekte
	 * 
	 * @param graphics
	 *            Grafikobjekt, auf dem gezeichnet werden soll
	 */
	public void drawObjects(Graphics graphics);
}
