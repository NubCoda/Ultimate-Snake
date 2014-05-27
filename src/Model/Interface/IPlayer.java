package Model.Interface;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * 
 * 
 */
public interface IPlayer {
	/**
	 * 
	 * @return
	 */
	double getX();

	/**
	 * 
	 * @return
	 */
	double getY();
	
	/**
	 * 
	 * @return
	 */
	Direction getDirection();
	
	/**
	 * 
	 * @return
	 */
	Vector<Point2D.Double> getBonesPosition();
}