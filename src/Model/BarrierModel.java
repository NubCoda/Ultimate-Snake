package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Random;

import Model.Interface.IActor;
import View.GamePanelView;

public class BarrierModel extends Observable implements IActor {

	private Rectangle2D.Double bounding;
	private GamePanelView gamePanelView;
	private boolean barrierAlive = false;

	public BarrierModel(GamePanelView gamePanelView, BufferedImage bufferedImage) {
		this.bounding = new Rectangle2D.Double(0, 0, bufferedImage.getWidth(),
				bufferedImage.getHeight());
		this.gamePanelView = gamePanelView;
	}

	public void moveBarrier() {
		Random random = new Random();
		int x = random.nextInt((int) (gamePanelView.getWidth() - bounding
				.getWidth()));
		int y = random.nextInt((int) (gamePanelView.getHeight() - bounding
				.getHeight()));
		bounding.x = (int) (x - (x % bounding.getWidth()));
		bounding.y = (int) (y - (y % bounding.getHeight()));
	}

	@Override
	public void actuate(double delta) {
		if (!barrierAlive) {
			barrierAlive = true;
			moveBarrier();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * 
	 */
	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			barrierAlive = false;
			((SnakeHeadModel) actor).increaseLength();
		}
	}

	@Override
	public Rectangle2D getBounding() {
		// TODO Auto-generated method stub
		return bounding;
	}

}
