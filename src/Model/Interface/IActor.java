package Model.Interface;

import java.awt.geom.Rectangle2D;

/**
 * Dieses Interface gibt die Methoden, welche in allen Figuren des Spiels
 * vorhanden sein sollen.
 */
public interface IActor {
	/**
	 * Funktiosk�rper f�r alle Actor
	 * 
	 * @param delta
	 *            Die Systemzeit, die seitdem letzten Frame vergangen ist in
	 *            Sekunden.
	 */
	public void actuate(double delta);

	/**
	 * Funktionsk�rper zum Berechnen der Kollision zwischen den Figuren.
	 * 
	 * @param actor
	 *            Die Figur, zur welcher die Kollision gepr�ft wird.
	 */
	public void checkCollision(IActor actor);

	/**
	 * Der Methoden-K�rper, der das Bounding der Actor zur�ck gibt
	 * 
	 * @return Rectangle2d Das Rechteck, in welchem die Figur sich befindet
	 */
	public Rectangle2D getBounding();
}