package Controller;

import java.io.IOException;

import Model.OptionsModel;

/**
 * 
 * 
 */
public class OptionsController {
	private static OptionsController OPTIONS_CONTROLLER;
	private OptionsModel optionsModel;

	/**
	 * 
	 */
	private OptionsController() {
		optionsModel = new OptionsModel();
	}

	/**
	 * 
	 * @return
	 */
	public static OptionsController getInstance() {
		if (OPTIONS_CONTROLLER == null) {
			OPTIONS_CONTROLLER = new OptionsController();
		}
		return OPTIONS_CONTROLLER;
	}
	
	/**
	 * 
	 * @param propertyName
	 * @param property
	 */
	public void setOption(String propertyName, String property){
		optionsModel.setOption(propertyName, property);
	}
	
	/**
	 * 
	 * @param propertyName
	 * @return
	 */
	public String getOption(String propertyName){
		return optionsModel.getOption(propertyName);
	}
	
	public void storeOptions() throws IOException{
		optionsModel.storeOptions();
	}
}