package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListenerView extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 39:	// -> Right
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
