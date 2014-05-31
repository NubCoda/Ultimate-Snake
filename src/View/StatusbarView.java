package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.StatusbarModel;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class StatusbarView extends JPanel implements Observer{
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private JLabel highscoreLabel;
	private JLabel difficultyLabel;

	/**
	 * 
	 */
	public StatusbarView() {
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY));
		setLayout(new GridLayout());
		playerLabel = new JLabel("Player: ");
		scoreLabel = new JLabel("Score: ");
		highscoreLabel = new JLabel("HighScore: ");
		difficultyLabel = new JLabel("Difficulty: ");
		this.add(playerLabel);
		this.add(scoreLabel);
		this.add(highscoreLabel);
		this.add(difficultyLabel);
	}

	@Override
	public void update(Observable o, Object arg) {
		StatusbarModel statusbarModel = ((StatusbarModel) o);
		playerLabel.setText("Player: " + statusbarModel.getPlayerName());
		scoreLabel.setText("Score: " + statusbarModel.getScore());
		highscoreLabel.setText("HighScore: " + statusbarModel.getHighscore());
		difficultyLabel.setText("Difficulty: " + statusbarModel.getDifficulty().toString());
	}
	
	/**
	 * 
	 */
	public void updateText() {
		playerLabel.setText("Player: Test");
	}
}