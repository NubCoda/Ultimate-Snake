package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import View.Interface.IDrawable;

@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private Vector<SpriteView> actors = new Vector<SpriteView>();

	public GamePanelView() {
		setFocusable(true);
		addKeyListener(new KeyListenerView());
	}

	public void addActor(SpriteView actor) {
		this.actors.add(actor);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Graphics2D)graphics).setRenderingHint(
		        RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D)graphics).setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		((Graphics2D)graphics).setRenderingHint(
		        RenderingHints.KEY_FRACTIONALMETRICS,
		        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		try {
			graphics.drawImage(ImageIO.read(new File("./resources/pic/background.png")), 0, 0, getWidth(), getHeight(), Color.BLACK, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		graphics.setColor(Color.RED);
		try {
			Font f = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
			f = f.deriveFont(48.0f);
			graphics.setFont(f);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graphics.drawString("SNAKE", (int) ((getWidth()/2)-(graphics.getFontMetrics().getStringBounds("SNAKE", graphics).getWidth()/2)), 50);
		if (actors != null) {
			for (IDrawable draw : actors) {
				draw.drawObjects(graphics);
			}
		}
	}

	@Override
	public void update(Observable observable, Object argObject) {
		repaint();
		// TODO / FIXME : Die Kollisionspr�fung muss in den Modellklassen
		// stattfinden und nict im View
		// for (int i = 0; i < actors.size(); i++) {
		// for (int j = i+1; j < actors.size(); j++) {
		// SpriteView s1 = actors.elementAt(i);
		// SpriteView s2 = actors.elementAt(j);
		// s1.collidedWith(s2);
		// }
		// }
	}

	public void addActors(Vector<SpriteView> actors) {
		this.actors.addAll(actors);
	}
}
