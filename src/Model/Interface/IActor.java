package Model.Interface;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * 
 * 
 */
public interface IActor {
	/**
	 * 
	 * @param delta
	 */
	public void actuate(double delta);

	/**
	 * 
	 * @param actor
	 */
	public void checkCollision(IActor actor);

	/**
	 * 
	 * @return
	 */
	public Rectangle2D getBounding();
}