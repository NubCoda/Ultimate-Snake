package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

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
//		Font f = null;
//		try {
//			f = Font.createFont(Font.TRUETYPE_FONT, new File(
//					"./resources/font/FEASFBI_.TTF"));
//			f = f.deriveFont(48.0f);
//		} catch (FontFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (focused) {
//			graphics.setColor(Color.YELLOW);
//		} else {
			graphics.setColor(Color.RED);
//		}
		// (int)
		// ((x*gamePanelView.getWidth()/100)-(graphics.getFontMetrics().getStringBounds(text,
		// graphics).getWidth()/2))
		// (int) (y*gamePanelView.getHeight()/100)
		graphics.drawString(text,
				(int) ((x * gamePanelView.getWidth() / 100) - (graphics
						.getFontMetrics().getStringBounds(text, graphics)
						.getWidth() / 2)),
				(int) (y * gamePanelView.getHeight() / 100));
	}
}
