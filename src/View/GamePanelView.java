package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import View.Interface.IDrawable;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private CopyOnWriteArrayList<SpriteView> actors = new CopyOnWriteArrayList<SpriteView>();
	private BufferedImage background;
	private Font textFont;

	/**
	 * 
	 */
	public GamePanelView(int width, int height) {
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));

		try {
			background = ImageIO
					.read(new File("./resources/pic/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textFont = textFont.deriveFont(48.0f);
	}

	/**
	 * 
	 * @param actor
	 */
	public void addActor(SpriteView actor) {
		this.actors.add(actor);
	}

	/**
	 * 
	 */
	public void clearActors() {
		actors.clear();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Graphics2D) graphics).setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) graphics).setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D) graphics).setRenderingHint(
				RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics.drawImage(background, 0, 0, getWidth(), getHeight(),
				Color.BLACK, this);
		graphics.setFont(textFont);
		if (actors != null) {
			for (IDrawable draw : actors) {
				draw.drawObjects(graphics);
			}
		}
	}

	@Override
	public void update(Observable observable, Object argObject) {
		repaint();
	}

	/**
	 * 
	 * @param actors
	 */
	public void addActors(Vector<SpriteView> actors) {
		this.actors.addAll(actors);
	}

	public void removeActor(SpriteView spriteView) {
		this.actors.remove(spriteView);
	}
}