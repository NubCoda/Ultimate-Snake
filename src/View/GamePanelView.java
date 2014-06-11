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
 * Klasse für das Panel, auf dem das Spielfeld gezeichnet wird
 */
@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private CopyOnWriteArrayList<SpriteView> actors = new CopyOnWriteArrayList<SpriteView>();
	private BufferedImage background;
	private Font textFont;

	/**
	 * Konstruktor für das Panel des Spielfeldes
	 * 
	 * @param width
	 *            Breite des Feldes
	 * @param height
	 *            Höhe des Feldes
	 */
	public GamePanelView(int width, int height) {
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		/*
		 * Lädt das BufferedImage
		 */
		try {
			background = ImageIO
					.read(new File("./resources/pic/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Lädt die Schriftart
		 */
		try {
			textFont = Font.createFont(Font.TRUETYPE_FONT, new File(
					"./resources/font/FEASFBI_.TTF"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		textFont = textFont.deriveFont(48.0f);
	}

	/**
	 * Der übergebene Actor wird in die Spriteviewliste im Gamepanelview
	 * hinzugefügt
	 * 
	 * @param actor
	 *            Die Figur, welche zum Spielfeld hinzugefügt werden soll.
	 */
	public void addActor(SpriteView actor) {
		this.actors.add(actor);
	}

	/**
	 * Entfernt alle Actors vom Gamepanel
	 */
	public void clearActors() {
		actors.clear();
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		/*
		 * Zeichnet die übergebene Grafik
		 */
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

		/*
		 * Alle Componenten auf dem Gamepanelview werden gezeichnet
		 */
		if (actors != null) {
			for (IDrawable draw : actors) {
				draw.drawObjects(graphics);
			}
		}
	}

	@Override
	public void update(Observable observable, Object argObject) {
		/*
		 * Alle Komponenten werden aktualisiert(neu gezeichnet)
		 */
		repaint();
	}

	/**
	 * Fügt alle übergebenen Actor's dem Spriteview Vektor im Gamepanelview
	 * hinzu
	 * 
	 * @param actors
	 *            Vektor aller als Actor "markierten" Spriteviews
	 */
	public void addActors(Vector<SpriteView> actors) {
		this.actors.addAll(actors);
	}

	/**
	 * Entfernt einen Actor aus dem Vektor im Gamepanelview
	 * 
	 * @param spriteView
	 *            Das Element "Spriteview", welches entfernt werden soll
	 */
	public void removeActor(SpriteView spriteView) {
		this.actors.remove(spriteView);
	}
}