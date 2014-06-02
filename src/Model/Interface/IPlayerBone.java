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
	Point2D.Double getMovement();
}