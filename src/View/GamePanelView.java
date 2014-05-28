package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import View.Interface.IDrawable;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private Vector<SpriteView> actors = new Vector<SpriteView>();

	/**
	 * 
	 */
	public GamePanelView(int width, int height) {
		setFocusable(true);
		addKeyListener(new KeyListenerView());
		setPreferredSize(new Dimension(width, height));
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
		actors.removeAllElements();
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
		try {
			graphics.drawImage(
					ImageIO.read(new File("./resources/pic/background.png")),
					0, 0, getWidth(), getHeight(), Color.BLACK, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
			graphics.setFont(f);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
//		graphics.drawRect(0, 60, getWidth(), getHeight()-60);
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
}