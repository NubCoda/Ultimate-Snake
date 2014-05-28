package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.OptionsController;
import Model.Interface.IConstants;
import Properties.Player;
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
	Vector<Player> playerVector;

	/**
	 * 
	 * @param mainView
	 * @param playerVector
	 */
	public OptionView(Vector<Player> playerVector) {
		initGUI();
		this.setLocationRelativeTo(null);
		this.playerVector = playerVector;
		fillComboBox();
	}

	/**
	 * Create the dialog.
	 */
	public OptionView() {
		initGUI();
	}

	/**
	 * 
	 */
	private void initGUI() {
		setModal(true);
		setBounds(100, 100, 221, 108);
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
			comboBoxPlayer.setBounds(82, 7, 102, 22);
			contentPanel.add(comboBoxPlayer);
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
	private void fillComboBox() {
		for (Player tmp : playerVector) {
			comboBoxPlayer.addItem(tmp.getPlayerName());
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
		playerHighscore = OptionsController.getInstance().getSinglePlayer((String) comboBoxPlayer
				.getSelectedItem());
		OptionsController.getInstance().savePlayerToFile(playerHighscore);
		this.dispose();
	}
}
