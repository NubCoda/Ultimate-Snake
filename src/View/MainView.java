package View;

import java.awt.BorderLayout;
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
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(gamePanelView, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setVisible(true);
//		GraphicsEnvironment env = GraphicsEnvironment
//				.getLocalGraphicsEnvironment();
//		GraphicsDevice device = env.getDefaultScreenDevice();
//		add(gamePanelView);
//		setUndecorated(true);
//		device.setFullScreenWindow(this);
//		setSize(gamePanelView.getWidth(), gamePanelView.getHeight());
		
//		device.setDisplayMode(new DisplayMode(800, 600, device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
//		requestFocus();
//		setVisible(true);
//		repaint();
//		validate();
//		 Display.setFullscreen(true);
//	        Display.create();
//		setUndecorated(true);
//        setResizable(false);
//        if (isFullScreen) {
            // Full-screen mode
//            device.setFullScreenWindow(this);
            
//		dispose();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
////		setDefaultLookAndFeelDecorated(true);
//		setUndecorated(true);
//		device.setFullScreenWindow(this);
//		device.setDisplayMode(new DisplayMode(1280, 720, DisplayMode.BIT_DEPTH_MULTI, DisplayMode.REFRESH_RATE_UNKNOWN));
		
//		
//		validate();
//		requestFocus();
//		setResizable(false);
//		device.setFullScreenWindow(this);
//		setSize(800, 600);
//		show();
		
	}

	@Override
	public void update(Observable observable, Object argObject) {
		OptionsModel optionsModel = ((OptionsModel) observable);
		this.setSize(optionsModel.getDimension());
		repaint();
	}
}