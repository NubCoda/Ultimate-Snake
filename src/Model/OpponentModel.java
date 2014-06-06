package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.IPlayerBone;
import Model.Interface.ISphere;

/**
 * 
 * 
 */
public class OpponentModel extends Observable implements IEnemy {
	private boolean opponentAlive = true;
	private Rectangle2D.Double bounding;
	private int nextJumpY = 0;
	private int nextJumpX = 0;
	private int maxJumpLength = 100;
	private int minJumpLength = 50;
	private double padding = 50;
	private double maxX;
	private double maxY;

	/**
	 * 
	 * @param gamePanelView
	 * @param bufferedImage
	 * @param logic
	 */
	public OpponentModel(int maxX, int maxY, int width, int height) {
		this.bounding = new Rectangle2D.Double(0, 0, width, height);
		setStartPosition();

		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * 
	 * @param width
	 * @return
	 */
	private void setStartPosition() {
		double x = 0;
		double y = 0;
		do {
			x = (Math.random() * ((maxX - padding) / 2) + padding);
			y = (Math.random() * ((maxY - padding) / 2) + padding);
		} while (MainController.getInstance().getActorAt(x, y, bounding.getWidth(), bounding.getHeight()) != null);
		bounding.x = x;
		bounding.y = y;
	}

	/**
	 * 
	 */
	public void moveOpponent() {
		double x = 0;
		double y = 0;
		IActor actor;
		do {
			x = bounding.x + getNextMovement(true);
			y = bounding.y + getNextMovement(false);
			actor = MainController.getInstance().getActorAt(x, y, bounding.getWidth(), bounding.getHeight());
		} while (actor != null && (!actor.equals(this) || !(actor instanceof IPlayerBone)));
		bounding.x = x;
		bounding.y = y;
	}

	/**
	 * 
	 * @param movementOfX
	 * @return
	 */
	private int getNextMovement(boolean movementOfX) {
		int nextJump = 0;
		int nextDirectionValue = 0;
		if (movementOfX) {
			nextJump = nextJumpX;
		} else {
			nextJump = nextJumpY;
		}
		if (nextJump == 0) {
			Random random = new Random();
			Boolean direction = random.nextBoolean();
			nextJump = (int) (Math.random() * (maxJumpLength - minJumpLength) + minJumpLength);
			if (direction) {
				nextJump *= -1;
			}
		}
		if (nextJump < 0) {
			nextJump += 1;
			nextDirectionValue = 1;
		} else if (nextJump > 0) {
			nextJump -= 1;
			nextDirectionValue = -1;
		}
		if (movementOfX) {
			nextJumpX = nextJump;
		} else {
			nextJumpY = nextJump;
		}
		return nextDirectionValue;
	}

	/**
	 * 
	 */
	private void checkMovement() {
		if (bounding.x <= bounding.getWidth()) {
			bounding.x = bounding.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.x >= maxX - bounding.getWidth()) {
			bounding.x = maxX - bounding.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.y <= bounding.getHeight()) {
			bounding.y = bounding.getHeight();
			nextJumpY = nextJumpY * -1;
		}
		if (bounding.y >= maxY - bounding.getHeight()) {
			bounding.y = maxY - bounding.getHeight();
			nextJumpY = nextJumpY * -1;
		}
	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public void actuate(double delta) {
		if (opponentAlive) {
			checkMovement();
			moveOpponent();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof ISphere) {
			((ISphere) actor).setGone(true);
			setOpponentAlive(false);
			setChanged();
			notifyObservers();
			MainController.getInstance().removeActor(this);
		}
	}

	/**
	 * 
	 * @param alive
	 */
	public void setOpponentAlive(boolean alive) {
		this.opponentAlive = alive;
	}

	@Override
	public boolean isAlive() {
		return opponentAlive;
	}
}