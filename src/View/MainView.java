package View;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import Model.OptionsModel;

/**
 * 
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer {
	/**
	 * 
	 * @param gamePanelView
	 */
	public MainView(GamePanelView gamePanelView) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanelView);
//		setDefaultLookAndFeelDecorated(true);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
//		device.setFullScreenWindow(this);
		setSize(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());
//		device.setDisplayMode(new DisplayMode(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight(), device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
	}

	@Override
	public void update(Observable observable, Object argObject) {
		OptionsModel optionsModel = ((OptionsModel) observable);
		this.setSize(optionsModel.getDimension());
		repaint();
	}
}