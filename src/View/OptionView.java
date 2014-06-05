package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.MainController;
import Controller.OptionsController;
import Model.Interface.Difficuty;
import Properties.Player;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class OptionView extends JDialog implements ActionListener {
	private final JPanel contentPanel = new JPanel();
	private JButton buttonCancel;
	private JButton buttonOk;
	private JLabel labelPlayer;
	private JComboBox<String> comboBoxPlayer;
	private Vector<Player> playerVector;
	private JRadioButton radioButtonSlow;
	private JRadioButton radioButtonNormal;
	private JRadioButton radioButtonFast;
	private JLabel labelDifficulty;
	private JLabel labelControls;
	private JLabel labelRight;
	private JLabel labelLeft;
	private JLabel labelUp;
	private JLabel labelDown;
	private JLabel labelShoot;
	private JTextField textFieldRight;
	private JTextField textFieldLeft;
	private JTextField textFieldUp;
	private JTextField textFieldDown;
	private JTextField textFieldShoot;
	private HashMap<String, Integer> keys;

	/**
	 * 
	 * @param mainView
	 * @param playerVector
	 */
	public OptionView(Vector<Player> playerVector, Difficuty difficulty,
			String playerName) {
		initGUI();
		setTitle("Optionen");
		setModal(true);
		setBounds(100, 100, 425, 300);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.playerVector = playerVector;
		this.fillComboBox(playerName);
		this.selectDifficulty(difficulty);
		keys = new HashMap<String, Integer>();
	}

	/**
	 * 
	 */
	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 102, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 0, 22, 22, 23, 23, 23, 0,
				0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			labelPlayer = new JLabel("Spieler:");
			GridBagConstraints gbc_labelPlayer = new GridBagConstraints();
			gbc_labelPlayer.anchor = GridBagConstraints.WEST;
			gbc_labelPlayer.insets = new Insets(0, 0, 5, 5);
			gbc_labelPlayer.gridx = 0;
			gbc_labelPlayer.gridy = 0;
			contentPanel.add(labelPlayer, gbc_labelPlayer);
		}
		{
			comboBoxPlayer = new JComboBox<String>();
			GridBagConstraints gbc_comboBoxPlayer = new GridBagConstraints();
			gbc_comboBoxPlayer.fill = GridBagConstraints.BOTH;
			gbc_comboBoxPlayer.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxPlayer.gridx = 1;
			gbc_comboBoxPlayer.gridy = 0;
			contentPanel.add(comboBoxPlayer, gbc_comboBoxPlayer);
		}
		{
			labelDifficulty = new JLabel("Schwierigkeit:");
			GridBagConstraints gbc_labelDifficulty = new GridBagConstraints();
			gbc_labelDifficulty.fill = GridBagConstraints.BOTH;
			gbc_labelDifficulty.insets = new Insets(0, 0, 5, 5);
			gbc_labelDifficulty.gridx = 0;
			gbc_labelDifficulty.gridy = 1;
			contentPanel.add(labelDifficulty, gbc_labelDifficulty);
		}
		{
			radioButtonSlow = new JRadioButton("Einfach");
			GridBagConstraints gbc_radioButtonSlow = new GridBagConstraints();
			gbc_radioButtonSlow.anchor = GridBagConstraints.NORTH;
			gbc_radioButtonSlow.fill = GridBagConstraints.HORIZONTAL;
			gbc_radioButtonSlow.insets = new Insets(0, 0, 5, 5);
			gbc_radioButtonSlow.gridx = 0;
			gbc_radioButtonSlow.gridy = 2;
			contentPanel.add(radioButtonSlow, gbc_radioButtonSlow);
		}
		{
			radioButtonNormal = new JRadioButton("Normal");
			GridBagConstraints gbc_radioButtonNormal = new GridBagConstraints();
			gbc_radioButtonNormal.anchor = GridBagConstraints.NORTH;
			gbc_radioButtonNormal.fill = GridBagConstraints.HORIZONTAL;
			gbc_radioButtonNormal.insets = new Insets(0, 0, 5, 5);
			gbc_radioButtonNormal.gridx = 1;
			gbc_radioButtonNormal.gridy = 2;
			contentPanel.add(radioButtonNormal, gbc_radioButtonNormal);
		}
		{
			radioButtonFast = new JRadioButton("Schwer");
			GridBagConstraints gbc_radioButtonFast = new GridBagConstraints();
			gbc_radioButtonFast.insets = new Insets(0, 0, 5, 0);
			gbc_radioButtonFast.anchor = GridBagConstraints.NORTH;
			gbc_radioButtonFast.fill = GridBagConstraints.HORIZONTAL;
			gbc_radioButtonFast.gridx = 2;
			gbc_radioButtonFast.gridy = 2;
			contentPanel.add(radioButtonFast, gbc_radioButtonFast);
		}
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButtonSlow);
		buttonGroup.add(radioButtonNormal);
		buttonGroup.add(radioButtonFast);
		{
			labelControls = new JLabel("Steuerung:");
			GridBagConstraints gbc_labelSteuerung = new GridBagConstraints();
			gbc_labelSteuerung.anchor = GridBagConstraints.WEST;
			gbc_labelSteuerung.insets = new Insets(0, 0, 5, 5);
			gbc_labelSteuerung.gridx = 0;
			gbc_labelSteuerung.gridy = 3;
			contentPanel.add(labelControls, gbc_labelSteuerung);
		}
		{
			labelRight = new JLabel("Rechts:");
			GridBagConstraints gbc_labelRechts = new GridBagConstraints();
			gbc_labelRechts.anchor = GridBagConstraints.WEST;
			gbc_labelRechts.insets = new Insets(0, 0, 5, 5);
			gbc_labelRechts.gridx = 0;
			gbc_labelRechts.gridy = 4;
			contentPanel.add(labelRight, gbc_labelRechts);
		}
		{
			textFieldRight = new JTextField(KeyEvent.getKeyText(Integer
					.valueOf(OptionsController.getInstance().getOption(
							"key_right"))));
			textFieldRight.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					textFieldRightKeyPressed(arg0);
				}
			});
			textFieldRight.setEditable(false);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 4;
			contentPanel.add(textFieldRight, gbc_textField);
			textFieldRight.setColumns(10);
		}
		{
			labelLeft = new JLabel("Links:");
			GridBagConstraints gbc_labelLinks = new GridBagConstraints();
			gbc_labelLinks.anchor = GridBagConstraints.WEST;
			gbc_labelLinks.insets = new Insets(0, 0, 5, 5);
			gbc_labelLinks.gridx = 0;
			gbc_labelLinks.gridy = 5;
			contentPanel.add(labelLeft, gbc_labelLinks);
		}
		{
			textFieldLeft = new JTextField(KeyEvent.getKeyText(Integer
					.valueOf(OptionsController.getInstance().getOption(
							"key_left"))));
			textFieldLeft.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					textFieldLeftKeyPressed(arg0);
				}
			});
			textFieldLeft.setColumns(10);
			textFieldLeft.setEditable(false);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 5;
			contentPanel.add(textFieldLeft, gbc_textField_1);
		}
		{
			labelUp = new JLabel("Hoch:");
			GridBagConstraints gbc_labelHoch = new GridBagConstraints();
			gbc_labelHoch.anchor = GridBagConstraints.WEST;
			gbc_labelHoch.insets = new Insets(0, 0, 5, 5);
			gbc_labelHoch.gridx = 0;
			gbc_labelHoch.gridy = 6;
			contentPanel.add(labelUp, gbc_labelHoch);
		}
		{
			textFieldUp = new JTextField(KeyEvent.getKeyText(Integer
					.valueOf(OptionsController.getInstance()
							.getOption("key_up"))));
			textFieldUp.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					textFieldUpKeyPressed(e);
				}
			});
			textFieldUp.setColumns(10);
			textFieldUp.setEditable(false);
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 5);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 6;
			contentPanel.add(textFieldUp, gbc_textField_2);
		}
		{
			labelDown = new JLabel("Runter:");
			GridBagConstraints gbc_labelRunter = new GridBagConstraints();
			gbc_labelRunter.anchor = GridBagConstraints.WEST;
			gbc_labelRunter.insets = new Insets(0, 0, 5, 5);
			gbc_labelRunter.gridx = 0;
			gbc_labelRunter.gridy = 7;
			contentPanel.add(labelDown, gbc_labelRunter);
		}
		{
			textFieldDown = new JTextField(KeyEvent.getKeyText(Integer
					.valueOf(OptionsController.getInstance().getOption(
							"key_down"))));
			textFieldDown.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					textFieldDownKeyPressed(e);
				}
			});
			textFieldDown.setColumns(10);
			textFieldDown.setEditable(false);
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 5);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 7;
			contentPanel.add(textFieldDown, gbc_textField_3);
		}
		{
			// KeyEvent.getKeyText(Integer.valueOf(OptionsController.getInstance().getOption("key_shoot")))
			textFieldShoot = new JTextField(KeyEvent.getKeyText(Integer
					.valueOf(OptionsController.getInstance().getOption(
							"key_shoot"))));
			textFieldShoot.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					textFieldShootKeyPressed(e);
				}
			});
			System.out.println();
			textFieldShoot.setColumns(10);
			textFieldShoot.setEditable(false);
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 5);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 8;
			contentPanel.add(textFieldShoot, gbc_textField_4);
		}
		{
			labelShoot = new JLabel("Schießen:");
			GridBagConstraints gbc_labelSchieen = new GridBagConstraints();
			gbc_labelSchieen.anchor = GridBagConstraints.WEST;
			gbc_labelSchieen.insets = new Insets(0, 0, 0, 5);
			gbc_labelSchieen.gridx = 0;
			gbc_labelSchieen.gridy = 8;
			contentPanel.add(labelShoot, gbc_labelSchieen);
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
		for (Player tmp : playerVector) {
			comboBoxPlayer.addItem(tmp.getPlayerName());
		}
		comboBoxPlayer.setSelectedItem((String) playerName);
	}

	/**
	 * 
	 * @param difficulty
	 */
	private void selectDifficulty(Difficuty difficulty) {
		switch (difficulty) {
		case SIMPLE:
			radioButtonSlow.setSelected(true);
			break;
		case MEDIUM:
			radioButtonNormal.setSelected(true);
			break;
		case DIFFICULT:
			radioButtonFast.setSelected(true);
			break;
		}
	}

	@Override
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
	private void buttonCancelActionPerformed(ActionEvent arg0) {
		this.dispose();
	}

	/**
	 * 
	 * @param arg0
	 */
	private void buttonOkActionPerformed(ActionEvent arg0) {
		Difficuty difficulty;
		if (radioButtonSlow.isSelected()) {
			difficulty = Difficuty.SIMPLE;
		} else if (radioButtonNormal.isSelected()) {
			difficulty = Difficuty.MEDIUM;
		} else {
			difficulty = Difficuty.DIFFICULT;
		}

		int selection = JOptionPane
				.showConfirmDialog(
						null,
						"Die Spieleinstellungen wurden geändert! Das Spiel wird zurückgesetzt, wenn Sie fortfahren. Wollen Sie wirklich fortfahren?",
						"Achtung", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
		if (selection == JOptionPane.YES_OPTION) {
			OptionsController.getInstance().setOption("difficulty",
					difficulty.toString());
			MainController.getInstance().changePlayer(
					(String) comboBoxPlayer.getSelectedItem());
			for (Entry<String, Integer> entry : keys.entrySet()) {
				OptionsController.getInstance().setOption(entry.getKey(),
						String.valueOf(entry.getValue()));
			}
			try {
				MainController.getInstance().optionsChanged();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.dispose();
	}

	protected void textFieldRightKeyPressed(KeyEvent arg0) {
		textFieldRight.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
		keys.put("key_right", arg0.getKeyCode());
	}

	protected void textFieldLeftKeyPressed(KeyEvent arg0) {
		textFieldLeft.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
		keys.put("key_left", arg0.getKeyCode());
	}

	protected void textFieldUpKeyPressed(KeyEvent arg0) {
		textFieldUp.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
		keys.put("key_up", arg0.getKeyCode());
	}

	protected void textFieldDownKeyPressed(KeyEvent arg0) {
		textFieldDown.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
		keys.put("key_down", arg0.getKeyCode());
	}

	protected void textFieldShootKeyPressed(KeyEvent arg0) {
		textFieldShoot.setText(KeyEvent.getKeyText(arg0.getKeyCode()));
		keys.put("key_shoot", arg0.getKeyCode());
	}
}