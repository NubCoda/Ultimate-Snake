package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Controller.MainController;
import Controller.OptionsController;
import Model.Interface.Difficulty;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenuItem menuItemOption;
	private JMenuItem menuItemSpielerErstellen;
	private JMenuItem menuItemRanking;
	private GamePanelView gamePanelView;
	private StatusbarView statusbar;

	/**
	 * 
	 * @param gamePanelView
	 * @param statusbar
	 */
	public MainView(GamePanelView gamePanelView, StatusbarView statusbar) {
		this.statusbar = statusbar;
		this.gamePanelView = gamePanelView;
		initGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Ultimate-Snake");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initGUI() {
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(gamePanelView, BorderLayout.CENTER);
		getContentPane().add(statusbar, BorderLayout.SOUTH);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				menuGame = new JMenu("Spiel");
				menuBar.add(menuGame);
				{
					menuItemStart = new JMenuItem("Start");
					menuItemStart.addActionListener(this);
					menuItemStart.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_S, InputEvent.CTRL_MASK));
					menuGame.add(menuItemStart);
				}
				{
					menuItemPause = new JMenuItem("Pause");
					menuItemPause.addActionListener(this);
					menuItemPause.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_P, InputEvent.CTRL_MASK));
					menuGame.add(menuItemPause);
				}
				{
					menuItemReset = new JMenuItem("Neustarten");
					menuItemReset.addActionListener(this);
					menuItemReset.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_R, InputEvent.CTRL_MASK));
					menuGame.add(menuItemReset);
					menuGame.addSeparator();
				}
				{
					menuItemOption = new JMenuItem("Optionen");
					menuItemOption.addActionListener(this);
					menuItemOption.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_O, InputEvent.CTRL_MASK));
					menuGame.add(menuItemOption);
					menuGame.addSeparator();
				}
				{
					menuItemSpielerErstellen = new JMenuItem(
							"Spieler erstellen");
					menuItemSpielerErstellen.addActionListener(this);
					{
						menuItemRanking = new JMenuItem("Rangliste");
						menuItemRanking.addActionListener(this);
						menuItemRanking.setAccelerator(KeyStroke.getKeyStroke(
								KeyEvent.VK_T, InputEvent.CTRL_MASK));
						menuGame.add(menuItemRanking);
					}
					menuItemSpielerErstellen.setAccelerator(KeyStroke
							.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
					menuGame.add(menuItemSpielerErstellen);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == menuItemRanking) {
			menuItemRankingActionPerformed(arg0);
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

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemStartActionPerformed(ActionEvent arg0) {
		MainController.getInstance().startGame();
	}

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemPauseActionPerformed(ActionEvent arg0) {
		MainController.getInstance().pauseGame();
	}

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemResetActionPerformed(ActionEvent arg0) {
		MainController.getInstance().restartGame();
	}

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemOptionActionPerformed(ActionEvent arg0) {
		MainController.getInstance().pauseGame();
		OptionView optionView = new OptionView(MainController.getInstance()
				.getPlayers(), Difficulty.fromString(OptionsController
				.getInstance().getOption("difficulty")), MainController
				.getInstance().getPlayerName());
		optionView.setVisible(true);
	}

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemSpielerErstellenActionPerformed(ActionEvent arg0) {
		String playerName = JOptionPane
				.showInputDialog("Spielernamen angeben!");
		if (playerName != null && !playerName.isEmpty()) {
			try {
				MainController.getInstance().createPlayer(playerName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param arg0
	 */
	private void menuItemRankingActionPerformed(ActionEvent arg0) {
		MainController.getInstance().displayRanking();
	}
}