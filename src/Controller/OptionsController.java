package Controller;

import java.util.HashMap;

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
	
	public void setProperty(String propertyName, Object property){
		
	}
	
	public Object getProperty(String propertyName){
		return null;
	}
}