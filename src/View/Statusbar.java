package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;

public class Statusbar extends JPanel implements Observer{
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private JLabel highscoreLabel;
	private JLabel difficultcyLabel;

	public Statusbar() {
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		setLayout(new GridLayout());
		playerLabel = new JLabel("Player: ");
		scoreLabel = new JLabel("Score: ");
		highscoreLabel = new JLabel("HighScore: ");
		difficultcyLabel = new JLabel("Difficultcy: ");
		this.add(playerLabel);
		this.add(scoreLabel);
		this.add(highscoreLabel);
		this.add(difficultcyLabel);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO 1. Zu dem statuspanel werden verschiedene models hinzugefuegt!
		//      2. hier werden dann die daten des jeweiligen models ausgeselen und
		//         in den passenden label geschrieben
	}
	
	public void updateText() {
		playerLabel.setText("Player: Test");
	}
}