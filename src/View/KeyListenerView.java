package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ViewInterface.IConstants;
import Controller.MainController;

public class KeyListenerView extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 39:	// -> Right
				MainController.getInstance().moveSnake(IConstants.RIGHT);
				break;
			case 38:	// -> Top
				break;

			case 37: 	// -> Left
				break;

			case 40:	// -> Bottom
				break;
		default:
			break;
		}
	}

}
