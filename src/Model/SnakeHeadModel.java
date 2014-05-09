package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

import Model.Interface.Actor;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;

public class SnakeHeadModel extends Observable implements Actor, IPlayerBone {
	private Point2D.Double movement;
	
	private int speed = IConstants.SNAKE_SPEED;
	private int rotation = 0;
	private Ellipse2D.Double bounding;
	public SnakeHeadModel(double x, double y, BufferedImage bufferedImage) {
		this.bounding = new Ellipse2D.Double(x, y, bufferedImage.getWidth(), bufferedImage.getHeight());
	}

	public void actuate(double delta) {
		move(delta);
		setChanged();
		notifyObservers();
	}

	private void move(double delta) {
		if(rotation == 270){
			bounding.y -= speed * delta;
		} else if(rotation > 270 && rotation < 360){
			bounding.y -= speed * delta;
			bounding.x += speed * delta;
		} else if(rotation == 0){
			bounding.x += speed * delta;
		} else if(rotation > 0 && rotation < 90){
			bounding.y += speed * delta;
			bounding.x += speed * delta;
		} else if(rotation == 90){
			bounding.y += speed * delta;
		} else if(rotation > 90 && rotation < 180){
			bounding.y += speed * delta;
			bounding.x -= speed * delta;
		} else if(rotation == 180){
			bounding.x -= speed * delta;
		} else {
			bounding.y -= speed * delta;
			bounding.x -= speed * delta;
		}
		System.out.println(bounding.getCenterX());
		System.out.println(bounding.getCenterY());
		movement = new Point2D.Double(((bounding.getWidth()/2)*Math.cos(Math.toRadians(rotation)))+bounding.getCenterX(), ((bounding.getWidth()/2)*Math.sin(Math.toRadians(rotation)))+bounding.getCenterY());
		System.out.println(movement);
	}

	public void checkCollision(Actor actor) {
		if(bounding.intersects(actor.getBounding())){
//			System.out.println("Collision HEad");
		}
	}

	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}
	
	@Override
	public int getRotation() {
		return this.rotation;
	}
	
	public Point2D.Double getMovement(){
		return movement;
	}

	public void rotateSnake(int rotation) {
//		if ((this.direction == Direction.RIGHT && (direction == Direction.UP || direction == Direction.DOWN))
//				|| (this.direction == Direction.LEFT && (direction == Direction.UP || direction == Direction.DOWN))
//				|| (this.direction == Direction.UP && (direction == Direction.RIGHT || direction == Direction.LEFT))
//				|| (this.direction == Direction.DOWN && (direction == Direction.LEFT || direction == Direction.RIGHT))) {
//			newDirection = direction;
//		}
		this.rotation = this.rotation + rotation;
		if(this.rotation< 0){
			this.rotation += 360;
		} else if (this.rotation > 360) {
			this.rotation -= 360;
		}
	}

	@Override
	public double getX() {
		return bounding.x;
	}

	@Override
	public double getY() {
		return bounding.y;
	}
}
