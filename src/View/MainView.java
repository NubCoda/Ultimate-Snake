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
	private JMenu menuOption;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;
	private JMenu menuResolution;
	private JMenuItem menuItemVGA;
	private JMenuItem menuItemSVGA;
	private JMenuItem menuItemXGA;
	private JMenuItem menuItemFHD;


	/**
	 * Create the frame.
	 */
	public MainView() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		{
			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				menuOption = new JMenu("Optionen");
				menuBar.add(menuOption);
				{
					menuItemStart = new JMenuItem("Start");
					menuItemStart.addActionListener(this);
					menuItemStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
					menuOption.add(menuItemStart);
				}
				{
					menuItemPause = new JMenuItem("Pause");
					menuItemPause.addActionListener(this);
					menuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
					menuOption.add(menuItemPause);
				}
				{
					menuItemReset = new JMenuItem("Reset");
					menuItemReset.addActionListener(this);
					menuItemReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
					menuOption.add(menuItemReset);
				}
			}
			{
				menuResolution = new JMenu("Aufl\u00F6sung");
				menuBar.add(menuResolution);
				{
					menuItemVGA = new JMenuItem("640x800");
					menuResolution.add(menuItemVGA);
				}
				{
					menuItemSVGA = new JMenuItem("800x600");
					menuResolution.add(menuItemSVGA);
				}
				{
					menuItemXGA = new JMenuItem("1024x768");
					menuResolution.add(menuItemXGA);
				}
				{
					menuItemFHD = new JMenuItem("1920x1080");
					menuResolution.add(menuItemFHD);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
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
	}
}
