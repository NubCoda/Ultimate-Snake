package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Controller.MainController;
import Controller.OptionsController;
import Properties.PlayerHighscore;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class OptionView extends JDialog implements ActionListener {
	private final JPanel contentPanel = new JPanel();
	private JButton buttonCancel;
	private JButton buttonOk;
	private PlayerHighscore playerHighscore;
	private JLabel labelPlayer;
	private JComboBox<String> comboBoxPlayer;
	private Vector<PlayerHighscore> playerVector;
	private JRadioButton radioButtonSlow;
	private JRadioButton radioButtonNormal;
	private JRadioButton radioButtonFast;
	private JLabel labelDifficulty;

	/**
	 * 
	 * @param mainView
	 * @param playerVector
	 */
	public OptionView(Vector<PlayerHighscore> playerVector, int difficulty,
			String playerName) {
		initGUI();
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonSlow);
		buttonGroup.add(radioButtonNormal);
		buttonGroup.add(radioButtonFast);
		this.setLocationRelativeTo(null);
		this.playerVector = playerVector;
		this.fillComboBox(playerName);
		this.selectDifficulty(difficulty);
	}

	/**
	 * 
	 */
	private void initGUI() {
		setModal(true);
		setBounds(100, 100, 369, 231);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			labelPlayer = new JLabel("Spieler:");
			labelPlayer.setBounds(10, 11, 62, 14);
			contentPanel.add(labelPlayer);
		}
		{
			comboBoxPlayer = new JComboBox<String>();
			comboBoxPlayer.setBounds(10, 25, 102, 22);
			contentPanel.add(comboBoxPlayer);
		}
		{
			radioButtonSlow = new JRadioButton("Einfach");
			radioButtonSlow.setBounds(10, 78, 102, 23);
			contentPanel.add(radioButtonSlow);
		}
		{
			radioButtonNormal = new JRadioButton("Normal");
			radioButtonNormal.setBounds(10, 104, 102, 23);
			contentPanel.add(radioButtonNormal);
		}
		{
			radioButtonFast = new JRadioButton("Schwer");
			radioButtonFast.setBounds(10, 130, 102, 23);
			contentPanel.add(radioButtonFast);
		}
		{
			labelDifficulty = new JLabel("Schwierigkeit:");
			labelDifficulty.setBounds(10, 49, 102, 22);
			contentPanel.add(labelDifficulty);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonOk = new JButton("OK");
				buttonOk.addActionListener(this);
				buttonOk.setActionCommand("OK");
				buttonPane.add(buttonOk);
				getRootPane().setDefaultButton(buttonOk);
			}
			{
				buttonCancel = new JButton("Abbrechen");
				buttonCancel.addActionListener(this);
				buttonCancel.setActionCommand("Cancel");
				buttonPane.add(buttonCancel);
			}
		}
	}

	/**
	 * 
	 */
	private void fillComboBox(String playerName) {
		for (PlayerHighscore tmp : playerVector) {
			comboBoxPlayer.addItem(tmp.getPlayer().getPlayerName());
		}
		comboBoxPlayer.setSelectedItem((String) playerName);
	}

	private void selectDifficulty(int difficulty) {
		System.out.println(difficulty);
		switch (difficulty) {
		case 1:
			radioButtonSlow.setSelected(true);
			break;
		case 2:
			radioButtonNormal.setSelected(true);
			break;
		case 3:
			radioButtonFast.setSelected(true);
			break;
		}
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttonOk) {
			buttonOkActionPerformed(arg0);
		}
		if (arg0.getSource() == buttonCancel) {
			buttonCancelActionPerformed(arg0);
		}
	}

	/**
	 * 
	 * @param arg0
	 */
	protected void buttonCancelActionPerformed(ActionEvent arg0) {
		this.dispose();
	}

	/**
	 * 
	 * @param arg0
	 */
	protected void buttonOkActionPerformed(ActionEvent arg0) {
		int difficulty;
		if (radioButtonSlow.isSelected()) {
			difficulty = 1;
		} else if (radioButtonNormal.isSelected()) {
			difficulty = 2;
		} else {
			difficulty = 3;
		}

		if (MainController.getInstance().getCurrentPlayerInfo()
				.getCurrentScore() > 0
				&& ((!MainController.getInstance().getCurrentPlayerInfo()
						.getPlayer().getPlayerName()
						.equals((String) comboBoxPlayer.getSelectedItem())) || MainController
						.getInstance().getCurrentGameSettings().getDifficulty() != difficulty)) {
			int selection = JOptionPane
					.showConfirmDialog(
							null,
							"Sie haben Spieleinstellungen geändert! Das Spiel wird zurück gesetzt wenn Sie fortfahren. Wirklich fortfahren?",
							"Achtung", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
			if (selection == JOptionPane.YES_OPTION) {
				OptionsController.getInstance().updateHighScore(MainController.getInstance().getCurrentPlayerInfo());
				playerHighscore = OptionsController.getInstance().getSinglePlayer(
						(String) comboBoxPlayer.getSelectedItem());
				OptionsController.getInstance().saveToFile(playerHighscore, difficulty);
				MainController.getInstance().restartGame(false);
			}
		} else {
			playerHighscore = OptionsController.getInstance().getSinglePlayer(
					(String) comboBoxPlayer.getSelectedItem());
			OptionsController.getInstance().saveToFile(playerHighscore,
					difficulty);
		}
		this.dispose();
	}
}
