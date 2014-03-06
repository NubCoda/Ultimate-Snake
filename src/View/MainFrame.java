package View;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuOption;
	private JMenuItem menuItemStart;
	private JMenuItem menuItemPause;
	private JMenuItem menuItemReset;


	/**
	 * Create the frame.
	 */
	public MainFrame() {
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
					menuOption.add(menuItemStart);
				}
				{
					menuItemPause = new JMenuItem("Pause");
					menuOption.add(menuItemPause);
				}
				{
					menuItemReset = new JMenuItem("Reset");
					menuOption.add(menuItemReset);
				}
			}
		}
	}

}
