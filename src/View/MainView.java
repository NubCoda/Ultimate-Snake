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
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		OptionsModel optionsModel = ((OptionsModel) observable);
		this.setSize(optionsModel.getDimension());
		repaint();
	}
}