package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Controller.MainController;
import Controller.OptionsController;
import Model.OptionsModel;
import Properties.Player;
import Properties.PlayerHighscore;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer, ActionListener {
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenuItem menuItemOption;
	private JMenu menuPlayer;
	private JMenuItem menuItemSpielerErstellen;
	private PlayerHighscore playerHighscore;

	/**
	 * 
	 * @param gamePanelView
	 */
	public MainView(GamePanelView gamePanelView) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(gamePanelView, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		initializeMenu();
		setVisible(true);
	}

	private void initializeMenu() {
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
	}

	public void actionPerformed(ActionEvent arg0) {
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
		MainController.getInstance().restartGame();
	}

	protected void menuItemOptionActionPerformed(ActionEvent arg0) {

		OptionView optionView = new OptionView(OptionsController
				.getInstance().getPlayers());
		optionView.setVisible(true);
	}

	protected void menuItemSpielerErstellenActionPerformed(ActionEvent arg0) {
		String playerName = JOptionPane
				.showInputDialog("Spielernamen angeben!");
		if (!playerName.isEmpty()) {
			OptionsController.getInstance().createPlayer(playerName);
		}
		setVisible(true);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		OptionsModel optionsModel = ((OptionsModel) observable);
		this.setSize(optionsModel.getDimension());
		repaint();
	}
}