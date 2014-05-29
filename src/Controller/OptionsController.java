package Controller;

import Model.OptionsModel;

/**
 * 
 * 
 */
public class OptionsController {
	private static OptionsController optionsController;
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
		if (optionsController == null) {
			optionsController = new OptionsController();
		}
		return optionsController;
	}
	
	public void setProperty(String propertyName, Object property){
		
	}
	
	public Object getProperty(String propertyName){
		return null;
	}
}