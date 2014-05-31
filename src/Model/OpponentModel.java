package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IEnemy;
import View.GamePanelView;

/**
 * 
 * 
 */
public class OpponentModel extends Observable implements IActor, IEnemy {
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

		this.positionX = setStartPosition(true);
		this.positionY = setStartPosition(false);

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
	private double setStartPosition(boolean width) {
		double newPosition;
		if (width) {
			newPosition = (Math.random()
					* ((gamePanelView.getWidth() - padding) / 2) + padding);
		} else {
			newPosition = (Math.random()
					* ((gamePanelView.getHeight() - padding) / 2) + padding);
		}
		return newPosition;
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

	/**
	 * 
	 */
	public Rectangle2D getBounding() {
		return bounding;
	}

	/**
	 * 
	 */
	public void actuate(double delta) {
		if (opponentAlive) {
			checkMovement();
			moveOpponent();
			setChanged();
			notifyObservers();
		}
	}

	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof BulletModel) {
			setOpponentAlive(false);
		}
	}

	/**
	 * 
	 * @param alive
	 */
	public void setOpponentAlive(boolean alive) {
		this.opponentAlive = alive;
	}
}