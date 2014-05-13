package Model.Interface;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

public interface IActor{
	public boolean checkPosition(Point point);
	public void actuate(double delta);
	public void checkCollision(IActor actor);
	public Rectangle2D getBounding();
}
