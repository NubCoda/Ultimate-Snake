package View;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import ViewInterface.IDrawable;
import ViewInterface.IMoveable;

@SuppressWarnings("serial")
public class SpriteView extends Rectangle2D.Double implements IDrawable, IMoveable {

	/* (non-Javadoc)
	 * @see ViewInterface.IMoveable#move(java.awt.Point)
	 */
	@Override
	public void move(Point point) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ViewInterface.IDrawable#drawObjects(java.awt.Graphics)
	 */
	@Override
	public void drawObjects(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
	
	
}
