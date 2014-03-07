package Controller;

import View.MainView;

public class MainController {
	
	private static MainController mainController;
	
	private MainController() {
		// Nicht instanzieren
	}

	public static void main(String[] args) {
		MainView mainView = new MainView();
		mainView.setVisible(true);
	}
	
	public static MainController getInstance() {
		if(mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

}
