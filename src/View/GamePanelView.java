package View;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import ViewInterface.IDrawable;

@SuppressWarnings("serial")
public class GamePanelView extends JPanel implements Observer {
	private Vector<SpriteView> actors;

	public GamePanelView(int width, int height) {
	}

	public void setActors(Vector<SpriteView> actors) {
		this.actors = actors;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		// TODO Auto-generated method stub
		super.paintComponent(graphics);
		if (actors != null) {
			for (IDrawable draw : actors) {
				System.out.println(draw);
				draw.drawObjects(graphics);
			}
		}
	}

	@Override
	public void update(Observable observable, Object argObject) {
		// TODO Auto-generated method stub
	}
}
