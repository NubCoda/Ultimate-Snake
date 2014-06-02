package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Controller.MainController;

/**
 * 
 * 
 */
public class MouseListenerView implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MainController.getInstance().mouseClick(arg0.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		MainController.getInstance().mouseMove(arg0.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		MainController.getInstance().mouseMove(arg0.getPoint());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}
}
