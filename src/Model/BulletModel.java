package Model;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;
import Model.Interface.IEnemy;
import View.GamePanelView;

public class BulletModel extends Observable implements IActor, IElement {
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private Rectangle2D.Double bounding;
	private double speed = 0.5;
	private double timeAlive = 0;
	private final double TIMETOLIVEINSECONDS = 10;
//	private boolean bulletIsAlive = true;
//	private List<OpponentModel> opponents1;
	private Direction direction;


	public BulletModel(double midOfSnakeHeadGraphicX,
			double midOfSnakeHeadGraphicY, BufferedImage bufferedImage, GamePanelView gamePanelView, Direction direction) {
		this.gamePanelView = gamePanelView;
		this.bufferedImages=bufferedImage;
		this.direction = direction;
		this.bounding = new Rectangle2D.Double(midOfSnakeHeadGraphicX, midOfSnakeHeadGraphicY, bufferedImage.getWidth(),
				bufferedImage.getHeight());
		
	}

	public Rectangle2D getBounding() {
		return bounding;
	}

	public void actuate(double delta) {
		timeAlive += delta;
		if (timeAlive > TIMETOLIVEINSECONDS) {
			//remove.this
		}
			if(this.direction == Direction.DOWN){
				bounding.y += speed*delta;
		}else if(this.direction == Direction.RIGHT){
			bounding.x += speed*delta;
		}else if(this.direction == Direction.LEFT) {
			bounding.x -= speed*delta;
		}else if(this.direction == Direction.UP){
			bounding.y-=speed*delta;
		}
		setChanged();
		notifyObservers();
	}

	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof OpponentModel) {
			((OpponentModel) actor).setOpponentAlive(false);
		}

	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImages;
	}

	@Override
	public boolean checkPosition(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return this.direction;
	}

}
