package Model.Interface;

import java.awt.geom.Point2D;

/**
 * Dieses Interface dient f�r die Schlangenteile
 */
public interface IPlayerBone extends IActor {
	/**
	 * Dieser Methoden-K�rper gibt das Objekt vom Typ "Direction" zur�ck
	 * 
	 * @return Die Richtung in welche das Schlangenteil sich bewegt
	 */
	Direction getDirection();

	/**
	 * Methoden-K�rper, der die Position vor dem letzten Movement zur�ckgibt
	 * 
	 * @return Die Position vor der letzten Bewegung des Schlangenteils
	 */
	Point2D.Double getMovement();
}