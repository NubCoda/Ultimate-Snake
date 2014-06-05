package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IEnemy;
import Model.Interface.ISphere;
import View.GamePanelView;

/**
 * 
 * 
 */
public class OpponentModel extends Observable implements IEnemy {
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private boolean opponentAlive = true;
	private Rectangle2D.Double bounding;
	private int nextJumpY = 0;
	private int nextJumpX = 0;
	private int maxJumpLength = 100;
	private int minJumpLength = 50;
	private double positionX = 100;
	private double positionY = 100;
	private double padding = 50;

	/**
	 * 
	 * @param gamePanelView
	 * @param bufferedImage
	 * @param logic
	 */
	public OpponentModel(GamePanelView gamePanelView,
			BufferedImage bufferedImage) {
		this.gamePanelView = gamePanelView;
		setStartPosition();
		
		this.bounding = new Rectangle2D.Double(positionX, positionY,
				bufferedImage.getWidth(), bufferedImage.getHeight());

		try {
			this.bufferedImages = ImageIO.read(new File(
					IConstants.OPPONENT_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			x = (Math.random() * ((gamePanelView.getWidth() - padding) / 2) + padding);
			y = (Math.random() * ((gamePanelView.getHeight() - padding) / 2) + padding);
		} while (!MainController.getInstance().checkPosition(x, y));
		positionX = x;
		positionY = x;
	}

	/**
	 * 
	 */
	public void moveOpponent() {
		bounding.x += getNextMovement(true);
		bounding.y += getNextMovement(false);
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
		if (bounding.x <= bufferedImages.getWidth()) {
			bounding.x = bufferedImages.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.x >= gamePanelView.getWidth() - bufferedImages.getWidth()) {
			bounding.x = gamePanelView.getWidth() - bufferedImages.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.y <= bufferedImages.getHeight()) {
			bounding.y = bufferedImages.getHeight();
			nextJumpY = nextJumpY * -1;
		}
		if (bounding.y >= gamePanelView.getHeight()
				- bufferedImages.getHeight()) {
			bounding.y = gamePanelView.getHeight() - bufferedImages.getHeight();
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