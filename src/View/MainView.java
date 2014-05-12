package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import Model.Interface.IConstants;

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
	public MainView(GamePanelView gamePanelView) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(new BorderLayout());
		add(gamePanelView); //, BorderLayout.CENTER
		setUndecorated(true);
		setResizable(false);
		// setIgnoreRepaint(true);
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
			device.setDisplayMode(new DisplayMode(IConstants.DEFAULT_RESOLUTION.width, IConstants.DEFAULT_RESOLUTION.height, DisplayMode.BIT_DEPTH_MULTI,
					DisplayMode.REFRESH_RATE_UNKNOWN));
//			setSize(new Dimension(640, 480));
//			validate();

		// System.out.println(device.isFullScreenSupported());
		// toFront();
		// if(device.isDisplayChangeSupported()){
		// System.out.println("change displaymode");
		// device.setDisplayMode(new DisplayMode(800, 600, 32, 0));
		// }
		initGUI();
	}

	private void initGUI() {

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
}
