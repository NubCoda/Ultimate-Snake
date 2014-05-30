package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import Model.Interface.IMenu;

/**
 * 
 * 
 */
public class MenuView extends SpriteView {
	private String text;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param gamePanelView
	 * @param text
	 * @param size
	 */
	public MenuView(double x, double y, GamePanelView gamePanelView,
			String text, float size) {
		super(null, x, y, gamePanelView);
		this.text = text;
	}

	@Override
	public void drawObjects(Graphics graphics) {

		graphics.setColor(Color.RED);
		graphics.drawString(text,
				(int) ((x * gamePanelView.getWidth() / 100) - (graphics
						.getFontMetrics().getStringBounds(text, graphics)
						.getWidth() / 2)),
				(int) (y * gamePanelView.getHeight() / 100));
	}
}
