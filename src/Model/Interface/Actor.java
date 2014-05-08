package Model.Interface;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

public abstract class Actor extends Observable{
	protected Rectangle2D.Double bounding;
	
	public Actor(double x, double y, BufferedImage img){
		this.bounding = new Rectangle2D.Double(x, y, img.getWidth(), img.getHeight());
	}
	
	public abstract void actuate(double delta);
	public abstract void checkCollision(Actor actor);
	public abstract Rectangle2D.Double getBounding();
}
