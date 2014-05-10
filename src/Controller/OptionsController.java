package Controller;

import java.awt.Dimension;

import Model.OptionsModel;
import View.MainView;

public class OptionsController {
	private static OptionsController optionsController;
	private OptionsModel optionsModel;
	private MainView mainView;
	
	private OptionsController() {
		optionsModel = new OptionsModel(mainView);
	}
	
	public static OptionsController getInstance() {
		if(optionsController == null) {
			optionsController = new OptionsController();
		}
		return optionsController;
	}
	
	public void setResolution(MainView mainView, Dimension dimension) {
		optionsModel = new OptionsModel(mainView);
		optionsModel.addObserver(mainView);
		optionsModel.setResolution(dimension);
		System.out.println("Test");
	}
}
