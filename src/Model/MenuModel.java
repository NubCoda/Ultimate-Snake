package Model;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Model.Interface.IActor;
import Model.Interface.IMenu;
import View.GamePanelView;

/**
 * 
 * 
 */
public class MenuModel extends Observable implements IActor, IMenu {
	private Rectangle2D.Double bounding;
	private boolean focus = false;
	private GamePanelView gamePanelView;
	private String text;

	/**
	 * 
	 * @param gamePanelView
	 * @param x
	 * @param y
	 * @param height
	 * @param width
	 * @param text
	 */
	public MenuModel(GamePanelView gamePanelView, double x, double y,
			double height, double width, String text) {
		this.gamePanelView = gamePanelView;
		bounding = new Rectangle2D.Double(x, y, width, height);
		this.text = text;
	}

	@Override
	public void actuate(double delta) {
	}

	@Override
	public void checkCollision(IActor actor) {
	}

	@Override
	public Rectangle2D getBounding() {
		return this.bounding;
	}

	@Override
	public boolean checkPosition(Point point) {
		// bounding.x = gamePanelView.getWidth()*bounding.x/100;
		// bounding.y = gamePanelView.getHeight()*bounding.y/100;
		return bounding.contains(point);
	}

	@Override
	public boolean isFocused() {
		return focus;
	}

	@Override
	public void focus() {
		this.focus = true;
		setChanged();
		notifyObservers();
	}

	@Override
	public void defocus() {
		this.focus = false;
		setChanged();
		notifyObservers();
	}

	@Override
	public String getText() {
		return text;
	}
}