package Controller;

import java.awt.Dimension;

import Model.OptionsModel;
import View.MainView;

public class OptionsController {
	private static OptionsController optionsController;
	private OptionsModel optionsModel;
	
	private OptionsController() {
		optionsModel = new OptionsModel();
	}
	
	public static OptionsController getInstance() {
		if(optionsController == null) {
			optionsController = new OptionsController();
		}
		return optionsController;
	}
	
	public void setResolution(MainView mainView, Dimension dimension) {
		optionsModel.setDimension(dimension);
		optionsModel.addObserver(mainView);
		optionsModel.setChanges();
	}
}
