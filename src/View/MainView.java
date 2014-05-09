package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Controller.MainController;
import DataAccessObject.DatabaseAccessObjects;
import Model.Interface.IConstants;
import Properties.Player;

@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenuItem menuItemOption;
	private JMenu menuPlayer;
	private JMenuItem menuItemSpielerErstellen;
	private JMenuItem menuItemSpielerWechseln;
	private Player player;

	/**
	 * Create the frame.
	 */
	public MainView() {
		initGUI();
		addPlayerToMenu();
	}
	
	
	private void addPlayerToMenu() {
		DatabaseAccessObjects databaseAccessObjects = new DatabaseAccessObjects();
		databaseAccessObjects.createConnection();
		Vector<Player> playerVector =  databaseAccessObjects.getPlayer();
		for(Player tmp : playerVector) {
			System.out.println(tmp.getPlayerName());
		}
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(IConstants.DEFAULT_RESOLUTION);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuGame = new JMenu("Spiel");
		menuBar.add(menuGame);

		menuItemStart = new JMenuItem("Start");
		menuItemStart.addActionListener(this);
		menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		menuGame.add(menuItemStart);

		menuItemPause = new JMenuItem("Pause");
		menuItemPause.addActionListener(this);
		menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		menuGame.add(menuItemPause);

		menuItemReset = new JMenuItem("Neustarten");
		menuItemReset.addActionListener(this);
		menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		menuGame.add(menuItemReset);

		menuItemOption = new JMenuItem("Optionen");
		menuItemOption.addActionListener(this);
		menuItemOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		menuGame.add(menuItemOption);

		menuPlayer = new JMenu("Spieler");
		menuBar.add(menuPlayer);

		menuItemSpielerErstellen = new JMenuItem("Erstellen");
		menuItemSpielerErstellen.addActionListener(this);
		menuPlayer.add(menuItemSpielerErstellen);
		
		menuItemSpielerWechseln = new JMenuItem("Spieler wechseln");
		menuItemSpielerWechseln.addActionListener(this);
		menuPlayer.add(menuItemSpielerWechseln);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemSpielerWechseln) {
			menuItemSpielerWechselnActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemSpielerErstellen) {
			menuItemSpielerErstellenActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemOption) {
			menuItemOptionActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemReset) {
			menuItemResetActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemPause) {
			menuItemPauseActionPerformed(arg0);
		}
		if (arg0.getSource() == menuItemStart) {
			menuItemStartActionPerformed(arg0);
		}
	}

	protected void menuItemStartActionPerformed(ActionEvent arg0) {
		MainController.getInstance().startGame();
	}

	protected void menuItemPauseActionPerformed(ActionEvent arg0) {
		MainController.getInstance().pauseGame();
	}

	protected void menuItemResetActionPerformed(ActionEvent arg0) {
		System.out.println(((GamePanelView) getContentPane().getComponent(0))
				.getSize());
	}

	protected void menuItemOptionActionPerformed(ActionEvent arg0) {
		OptionView optionView = new OptionView(this);
		optionView.setVisible(true);
	}

	protected void menuItemSpielerErstellenActionPerformed(ActionEvent arg0) {
		player = new Player(
				JOptionPane.showInputDialog("Spielernamen angeben!"));
		if (player.getPlayerName() != null) {
			DatabaseAccessObjects databaseAccessObjects = new DatabaseAccessObjects();
			databaseAccessObjects.createConnection();
			databaseAccessObjects.createPlayer(player);
		}
	}
	protected void menuItemSpielerWechselnActionPerformed(ActionEvent arg0) {
		SwitchPlayerView switchPlayerView = new SwitchPlayerView(player);
		switchPlayerView.setVisible(true);
	}
}
