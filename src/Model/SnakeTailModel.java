package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

import Model.Interface.Actor;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;
import View.GamePanelView;
public class SnakeTailModel extends Observable implements Actor, IPlayerBone{
	private IPlayerBone vorgaenger;
	private int lastMove;
	private Point2D.Double movement;
	private int speed = IConstants.SNAKE_SPEED;
	private Ellipse2D.Double bounding;
	public SnakeTailModel(GamePanelView gamePanelView, double x, double y, IPlayerBone vorgaenger, BufferedImage bufferedImage){
		this.bounding = new Ellipse2D.Double(x, y, bufferedImage.getWidth(), bufferedImage.getHeight());
		this.vorgaenger = vorgaenger;
	}

	public int getRotation() {
		return 0;
	}

	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	public double getX() {
		return bounding.x;
	}

	public double getY() {
		return bounding.y;
	}
	
	public void actuate(double delta) {
			double oldX = bounding.x;
			double oldY = bounding.y;
			bounding.x += vorgaenger.getMovement().x;
			bounding.y += vorgaenger.getMovement().y;
			movement= new Point2D.Double(bounding.x-oldX, bounding.y - oldY);
			setChanged();
			notifyObservers();
	}

	@Override
	public void checkCollision(Actor actor) {
		if(bounding.intersects(actor.getBounding())){
			System.out.println("Collision Tail");
		}
	}

	@Override
	public Double getMovement() {
		return movement;
	}
}
