package Model.Interface;

import java.awt.geom.Point2D;

/**
 * 
 * 
 */
public interface IPlayerBone extends IActor {
	/**
	 * 
	 * @return
	 */
	Direction getDirection();

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
	Point2D.Double getMovement();
}