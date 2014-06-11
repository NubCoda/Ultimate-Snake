package View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Controller.MainController;
import Controller.OptionsController;
import Model.Interface.Direction;

/**
 * Keylistener, der die Eingaben pr�ft und denen Methoden zuweist.
 */
public class KeyListenerView extends KeyAdapter {
	/*
	 * Horcht die Oberfl�che nach bestimmten Tasten, wie z.B. die zur Bewegung
	 * der Snake ab und f�hrt ensprechende Methoden, wie die
	 * Richtungs�nderungsmethode der Schlange aus.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Integer.valueOf(OptionsController.getInstance()
				.getOption("key_left"))) {
			MainController.getInstance().switchSnakeDirection(Direction.LEFT);
		} else if (e.getKeyCode() == Integer.valueOf(OptionsController
				.getInstance().getOption("key_right"))) {
			MainController.getInstance().switchSnakeDirection(Direction.RIGHT);
		} else if (e.getKeyCode() == Integer.valueOf(OptionsController
				.getInstance().getOption("key_up"))) {
			MainController.getInstance().switchSnakeDirection(Direction.UP);
		} else if (e.getKeyCode() == Integer.valueOf(OptionsController
				.getInstance().getOption("key_down"))) {
			MainController.getInstance().switchSnakeDirection(Direction.DOWN);
		} else if (e.getKeyCode() == Integer.valueOf(OptionsController
				.getInstance().getOption("key_shoot"))) {
			MainController.getInstance().shoot();
		}
	}

}
