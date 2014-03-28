package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Controller.MainController;

public class KeyListenerView extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		MainController.getInstance().moveSnake(e.getKeyCode());
	}

}
