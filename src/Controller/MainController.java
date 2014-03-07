package Controller;

import java.awt.Color;

import View.GamePanelView;
import View.MainView;

public class MainController {
	
	private static MainController mainController;
	
	/**
	 * Makes it impossible to instanti
	 */
	private MainController() {
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainView mainView = new MainView();
		GamePanelView gamePanelView = new GamePanelView(mainView.getWidth(), mainView.getHeight());
		gamePanelView.setBackground(Color.CYAN);
		mainView.add(gamePanelView);
		mainView.setVisible(true);
	}
	
	public static MainController getInstance() {
		if(mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

}
