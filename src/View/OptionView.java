package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileView;

import Controller.FileWriterController;
import Controller.OptionsController;
import DataAccessObject.DatabaseAccessObjects;
import Model.Interface.IConstants;
import Properties.Player;

@SuppressWarnings("serial")
public class OptionView extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel labelResolution;
	private JComboBox<String> comboBoxResolution;
	private JButton buttonCancel;
	private JButton buttonOk;
	private MainView mainView;
	private int newWidth;
	private int newHeight;
	private Player player;
	private JLabel labelPlayer;
	private JComboBox<String> comboBoxPlayer;

	public OptionView(MainView mainView, Player player) {
		initGUI();
		this.mainView = mainView;
		this.newWidth = mainView.getWidth();
		this.newHeight = mainView.getHeight();
		this.player = player;
		fillComboBox();
	}

	/**
	 * Create the dialog.
	 */
	public OptionView() {
		initGUI();
	}

	private void initGUI() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			labelResolution = new JLabel("Aufl\u00F6sung:");
			labelResolution.setBounds(10, 11, 62, 14);
			contentPanel.add(labelResolution);
		}
		{
			comboBoxResolution = new JComboBox<String>();
			comboBoxResolution.addActionListener(this);
			comboBoxResolution.setModel(new DefaultComboBoxModel<String>(
					new String[] { "800x600", "1024x768", "1280x720" }));
			comboBoxResolution.setBounds(82, 7, 102, 22);
			contentPanel.add(comboBoxResolution);
		}
		{
			labelPlayer = new JLabel("Spieler:");
			labelPlayer.setBounds(10, 44, 62, 14);
			contentPanel.add(labelPlayer);
		}
		{
			comboBoxPlayer = new JComboBox<String>();
			comboBoxPlayer.setBounds(82, 40, 102, 22);
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
	
	private void fillComboBox() {
		DatabaseAccessObjects databaseAccessObjects = new DatabaseAccessObjects();
		Vector<Player> playerVector = databaseAccessObjects.getPlayers();
		for (Player tmp : playerVector) {
			// System.out.println(tmp.getPlayerName());
			comboBoxPlayer.addItem(tmp.getPlayerName());
		}

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttonOk) {
			buttonOkActionPerformed(arg0);
		}
		if (arg0.getSource() == buttonCancel) {
			buttonCancelActionPerformed(arg0);
		}
		if (arg0.getSource() == comboBoxResolution) {
			comboBoxResolutionActionPerformed(arg0);
		}
	}

	protected void comboBoxResolutionActionPerformed(ActionEvent arg0) {
		String selectedItem = comboBoxResolution.getSelectedItem().toString();
		int positionOfMulti = selectedItem.indexOf("x");
		newWidth = Integer.valueOf(selectedItem.substring(0, positionOfMulti));
		newHeight = Integer.valueOf(selectedItem.substring(positionOfMulti + 1,
				selectedItem.length()));

	}

	protected void buttonCancelActionPerformed(ActionEvent arg0) {
		this.dispose();
	}

	protected void buttonOkActionPerformed(ActionEvent arg0) {
		DatabaseAccessObjects databaseAccessObjects = new DatabaseAccessObjects();
		player = databaseAccessObjects
				.getSinglePlayer((String) comboBoxPlayer.getSelectedItem());
		OptionsController.getInstance().setResolution(mainView, new Dimension(newWidth, newHeight));
		Properties properties = new Properties();
		properties.setProperty("player", player.getPlayerName());
		properties.setProperty("height", String.valueOf(newHeight));
		properties.setProperty("width", String.valueOf(newWidth));
		File file = new File(IConstants.CONFIG_PATH);
		FileWriterController.getInstance().writeToIniFile(new FileWriterView(), file, properties); 
		this.dispose();
	}
}
