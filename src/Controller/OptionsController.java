package Controller;

import java.io.IOException;

import Model.OptionsModel;

/**
 * Der OptionController als Singelton. Diese Klasse dient zum Sichern und �ndern
 * von verschiedenen Optionen
 */
public class OptionsController {
	/*
	 * Variabeln.
	 */
	private static OptionsController OPTIONS_CONTROLLER;
	private OptionsModel optionsModel;

	/**
	 * Privater Konstruktor! Verhindert das manuelle Erzeugen von Objekten
	 * dieser Klasse.
	 */
	private OptionsController() {
		/*
		 * Erzeuge das Model f�r die Optionen
		 */
		optionsModel = new OptionsModel();
	}

	/**
	 * Wenn die Instance des Controllers null ist, wird er einmalig erzeugt zur
	 * Laufzeit und danach nicht mehr.
	 * 
	 * @return Gibt das Controller-Objekt zur�ck
	 */
	public static OptionsController getInstance() {
		if (OPTIONS_CONTROLLER == null) {
			OPTIONS_CONTROLLER = new OptionsController();
		}
		return OPTIONS_CONTROLLER;
	}

	/**
	 * Funktion zum Setzen der Optionen
	 * 
	 * @param propertyName
	 *            Der Name des Property, welches gesetzt werden soll
	 * @param property
	 *            Der Wert der Property als String
	 */
	public void setOption(String propertyName, String property) {
		/*
		 * F�hrt die Logik aus
		 */
		optionsModel.setOption(propertyName, property);
	}

	/**
	 * Methode, die eine bestimmte Property zur�ck gibt
	 * 
	 * @param propertyName
	 *            Der Name f�r das Property
	 * @return Der Wert der bestimmten Property
	 */
	public String getOption(String propertyName) {
		return optionsModel.getOption(propertyName);
	}

	/**
	 * Sichert die Optionen
	 * 
	 * @throws IOException
	 *             Schreibt in eine Datei, weshalb es zu einer IOException
	 *             kommen k�nnte
	 */
	public void storeOptions() throws IOException {
		optionsModel.storeOptions();
	}
}