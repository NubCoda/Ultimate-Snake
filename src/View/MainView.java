package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import Controller.MainController;

@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenuItem menuItemOption;


	/**
	 * Create the frame.
	 */
	public MainView() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
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
					menuItemReset = new JMenuItem("Reset");
					menuItemReset.addActionListener(this);
					menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
					menuGame.add(menuItemReset);
				}
				{
					menuItemOption = new JMenuItem("Optionen");
					menuItemOption.addActionListener(this);
					menuItemOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
					menuGame.add(menuItemOption);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
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
		MainController.getInstance().IsGameRunning = true;
		System.out.println("Game started");
	}
	protected void menuItemPauseActionPerformed(ActionEvent arg0) {
		MainController.getInstance().IsGameRunning = false;
		System.out.println("Game paused");
	}
	protected void menuItemResetActionPerformed(ActionEvent arg0) {
		System.out.println(((GamePanelView)getContentPane().getComponent(0)).getSize());
	}
	protected void menuItemOptionActionPerformed(ActionEvent arg0) {
		OptionView optionView = new OptionView(this);
		optionView.setVisible(true);
	}
}
