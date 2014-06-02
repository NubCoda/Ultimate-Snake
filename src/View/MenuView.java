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
public class MenuView extends SpriteView implements Observer {
	private String text;
	private boolean focused;
	private float size;
	private double height = 0;
	private double width = 0;

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
		this.size = size;
	}

	/**
	 * 
	 * @return
	 */
	public double getX() {
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ((x * gamePanelView.getWidth() / 100) - (gamePanelView
				.getGraphics().getFontMetrics(f)
				.getStringBounds(text, gamePanelView.getGraphics()).getWidth() / 2));
	}

	/**
	 * 
	 * @return
	 */
	public double getY() {
		return (y * gamePanelView.getHeight() / 100) - getHeight();
	}

	/**
	 * 
	 * @return
	 */
	public double getWidth() {
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gamePanelView.getGraphics().getFontMetrics(f)
				.getStringBounds(text, gamePanelView.getGraphics()).getWidth();
	}

	/**
	 * 
	 * @return
	 */
	public double getHeight() {
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gamePanelView.getGraphics().getFontMetrics(f)
				.getStringBounds(text, gamePanelView.getGraphics()).getHeight();
	}

	@Override
	public void update(Observable o, Object arg) {
		IMenu menu = ((IMenu) o);
		focused = menu.isFocused();
	}

	@Override
	public void drawObjects(Graphics graphics) {
		Font f = null;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (focused) {
			graphics.setColor(Color.YELLOW);
		} else {
			graphics.setColor(Color.RED);
		}
		// (int)
		// ((x*gamePanelView.getWidth()/100)-(graphics.getFontMetrics().getStringBounds(text,
		// graphics).getWidth()/2))
		// (int) (y*gamePanelView.getHeight()/100)
		graphics.drawString(text,
				(int) ((x * gamePanelView.getWidth() / 100) - (graphics
						.getFontMetrics(f).getStringBounds(text, graphics)
						.getWidth() / 2)),
				(int) (y * gamePanelView.getHeight() / 100));
	}
}
