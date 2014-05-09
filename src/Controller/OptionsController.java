package Controller;

public class OptionsController {
	private static OptionsController optionsController;
	
	private OptionsController() {
		
	}
	
	public static OptionsController getInstance() {
		if(optionsController == null) {
			optionsController = new OptionsController();
		}
		return optionsController;
	}
}
