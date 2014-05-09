package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Controller.MainController;
import Model.Interface.IConstants;

public class KeyListenerView extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case IConstants.DEFAULT_KEY_LEFT:
			MainController.getInstance().rotateSnake(-5);
			break;
		case IConstants.DEFAULT_KEY_RIGHT:
			MainController.getInstance().rotateSnake(5);
			break;
		}
	}

}
