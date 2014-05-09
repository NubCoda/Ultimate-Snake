package Model.Interface;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

public interface Actor{
	public void actuate(double delta);
	public void checkCollision(Actor actor);
	public Rectangle2D getBounding();
}
