package Model.Interface;

import java.awt.geom.Rectangle2D;

public interface IActor{
	public void actuate(double delta);
	public void checkCollision(IActor actor);
	public Rectangle2D getBounding();
}
