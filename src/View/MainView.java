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

@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer, ActionListener {

	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenuItem menuItemOption;
	private JMenuItem menuItemSpielerErstellen;
	private JMenuItem menuItemRanking;

	
	public MainView(GamePanelView gamePanelView, StatusbarView statusbar) {
		initGUI();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(gamePanelView, BorderLayout.CENTER);
		getContentPane().add(statusbar, BorderLayout.SOUTH);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				menuGame = new JMenu("Spiel");
				menuBar.add(menuGame);
				{
					menuItemStart = new JMenuItem("Start");
					menuItemStart.addActionListener(this);
					menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
					menuGame.add(menuItemStart);
				}
				{
					menuItemPause = new JMenuItem("Pause");
					menuItemPause.addActionListener(this);
					menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
					menuGame.add(menuItemPause);
				}
				{
					menuItemReset = new JMenuItem("Neustarten");
					menuItemReset.addActionListener(this);
					menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
					menuGame.add(menuItemReset);
					menuGame.addSeparator();
				}
				{
					menuItemOption = new JMenuItem("Optionen");
					menuItemOption.addActionListener(this);
					menuItemOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
					menuGame.add(menuItemOption);
					menuGame.addSeparator();
				}
				{
					menuItemSpielerErstellen = new JMenuItem("Spieler erstellen");
					menuItemSpielerErstellen.addActionListener(this);
					{
						menuItemRanking = new JMenuItem("Rangliste");
						menuItemRanking.addActionListener(this);
						menuItemRanking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
						menuGame.add(menuItemRanking);
					}
					menuItemSpielerErstellen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
					menuGame.add(menuItemSpielerErstellen);
				}
			}
		}
	}
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

	protected void menuItemStartActionPerformed(ActionEvent arg0) {
		MainController.getInstance().startGame();
	}

	protected void menuItemPauseActionPerformed(ActionEvent arg0) {
		MainController.getInstance().pauseGame();
	}

	protected void menuItemResetActionPerformed(ActionEvent arg0) {
		MainController.getInstance().restartGame(true);
	}

	protected void menuItemOptionActionPerformed(ActionEvent arg0) {
		MainController.getInstance().pauseGame();
		OptionView optionView = new OptionView(OptionsController.getInstance()
				.getPlayers(), MainController.getInstance()
				.getCurrentGameSettings().getDifficulty(), MainController
				.getInstance().getCurrentPlayerInfo().getPlayer()
				.getPlayerName());
		optionView.setVisible(true);
	}

	protected void menuItemSpielerErstellenActionPerformed(ActionEvent arg0) {
		boolean created = false;
		String playerName = JOptionPane
				.showInputDialog("Spielernamen angeben!");
		if (playerName != null && !playerName.isEmpty()) {
			created = OptionsController.getInstance().createPlayer(playerName);
			while (!created) {
				playerName = JOptionPane
						.showInputDialog("Der Spieler existiert bereits!\nNeuen Spielernamen angeben!");
				created = OptionsController.getInstance().createPlayer(playerName);
			}
		}
		setVisible(true);
	}
	
	protected void menuItemRankingActionPerformed(ActionEvent arg0) {
		MainController.getInstance().displayRanking();
	}

	@Override
	public void update(Observable observable, Object argObject) {
		OptionsModel optionsModel = ((OptionsModel) observable);
		this.setSize(optionsModel.getDimension());
		repaint();
	}
	
}
