package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IConstants;
import Model.Interface.IDefaultOptions;

/**
 * 
 * 
 */
public class KeyListenerView extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case IDefaultOptions.DEFAULT_KEY_LEFT:
			MainController.getInstance().switchSnakeDirection(Direction.LEFT);
			break;
		case IDefaultOptions.DEFAULT_KEY_RIGHT:
			MainController.getInstance().switchSnakeDirection(Direction.RIGHT);
			break;
		case IDefaultOptions.DEFAULT_KEY_UP:
			MainController.getInstance().switchSnakeDirection(Direction.UP);
			break;
		case IDefaultOptions.DEFAULT_KEY_DOWN:
			MainController.getInstance().switchSnakeDirection(Direction.DOWN);
			break;
		}
	}

}
