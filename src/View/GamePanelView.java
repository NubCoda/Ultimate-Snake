package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import View.Interface.IDrawable;

@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private Vector<SpriteView> actors = new Vector<SpriteView>();

	public GamePanelView(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		addKeyListener(new KeyListenerView());
	}

	public void addActor(SpriteView actor) {
		this.actors.add(actor);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO Auto-generated method stub
		super.paintComponent(graphics);
		if (actors != null) {
			for (IDrawable draw : actors) {
				// System.out.println(draw);
				draw.drawObjects(graphics);
			}

		}
	}

	@Override
	public void update(Observable observable, Object argObject) {
		repaint();
		// TODO / FIXME : Die Kollisionspr�fung muss in den Modellklassen
		// stattfinden und nict im View
		for (int i = 0; i < actors.size(); i++) {
			for (int j = i + 1; j < actors.size(); j++) {
				SpriteView s1 = actors.elementAt(i);
				SpriteView s2 = actors.elementAt(j);
				s1.collidedWith(s2);
			}
		}
	}

	public void addActors(Vector<SpriteView> actors) {
		this.actors.addAll(actors);
	}
}
