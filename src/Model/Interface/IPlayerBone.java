package Model.Interface;

import java.awt.geom.Point2D;

/**
 * Dieses Interface dient für die Schlangenteile
 */
public interface IPlayerBone extends IActor {
	/**
	 * Dieser Methoden-Körper gibt das Objekt vom Typ "Direction" zurück
	 * 
	 * @return Die Richtung in welche das Schlangenteil sich bewegt
	 */
	Direction getDirection();

	/**
	 * Methoden-Körper, der die Position vor dem letzten Movement zurückgibt
	 * 
	 * @return Die Position vor der letzten Bewegung des Schlangenteils
	 */
	Point2D.Double getMovement();
}