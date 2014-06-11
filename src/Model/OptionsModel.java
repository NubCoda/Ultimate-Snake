package Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;

import Model.Interface.IConstants;
import Model.Interface.IDefaultOptions;

/**
 * Diese Klasse ist das Model für die Optionen. Es ist ein Observable zur
 * Implementierung nach Observer-Pattern.
 */
public class OptionsModel extends Observable {
	private Properties properties;

	/**
	 * Der Konstruktor der Klasse
	 */
	public OptionsModel() {
		/*
		 * Erzeuge die Properties
		 */
		properties = new Properties();
		try {
			loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese Funktion lädt die Properties aus der INI-Datei
	 * 
	 * @throws FileNotFoundException
	 *             Eirft eine FileNotFoundException, wenn die Datei nicht
	 *             gefunden wird
	 * @throws IOException
	 *             Bei Ein-/Ausgabeproblemen wird diese Exception geworfen
	 */
	private void loadProperties() throws FileNotFoundException, IOException {
		/*
		 * Prüfe, ob Datei vorhanden ist Wenn ja, erzeuge neuen
		 * BufferdInputStream mit der Datei und lade sie. Schließe den
		 * InputStream nach Abschluss.
		 */
		if (new File(IConstants.CONFIG_PATH).exists()) {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(IConstants.CONFIG_PATH));
			properties.load(in);
			in.close();
		} else {
			/*
			 * Wenn die Datei nicht vorhanden ist setze die Properties mit den
			 * Default werten.
			 */
			properties.setProperty("player", IDefaultOptions.DEFAULT_PLAYER);
			properties.setProperty("difficulty",
					String.valueOf(IDefaultOptions.DEFAULT_DIFFICULTY));
			properties.setProperty("key_down",
					String.valueOf(IDefaultOptions.DEFAULT_KEY_DOWN));
			properties.setProperty("key_up",
					String.valueOf(IDefaultOptions.DEFAULT_KEY_UP));
			properties.setProperty("key_right",
					String.valueOf(IDefaultOptions.DEFAULT_KEY_RIGHT));
			properties.setProperty("key_left",
					String.valueOf(IDefaultOptions.DEFAULT_KEY_LEFT));
			properties.setProperty("key_shoot",
					String.valueOf(IDefaultOptions.DEFAULT_KEY_SHOOT));
			/*
			 * Sichere die Option in der INI-Datei!
			 */
			storeOptions();
		}
	}

	/**
	 * Gibt einen bestimmten Optionswert zurück
	 * 
	 * @param propertyKey
	 *            Der Schlüssel, der ausgelesen werden soll. Zum Beispiel der
	 *            KeyCode, der für das Schießen verwendet werden soll
	 * @return Der Wert, der ausgelesen wurde.
	 */
	public String getOption(String propertyKey) {
		return properties.getProperty(propertyKey);
	}

	/**
	 * Setzt eine bestimmte Option
	 * 
	 * @param propertyName
	 *            Der Name der Property, die gesetzt werden soll, zum Beispiel
	 *            "player_name"
	 * @param property
	 *            Der Wert, für den Key. Zum Beispiel "Sami"
	 */
	public void setOption(String propertyName, String property) {
		properties.setProperty(propertyName, property);
	}

	/**
	 * Die Funktion zum Sichern der Optionen
	 * 
	 * @throws IOException
	 *             Bei Ein-/Ausgabeproblemen wird diese Exception geworfen
	 */
	public void storeOptions() throws IOException {
		/*
		 * Erzeugt einen OutputStreamReader und sichert die Daten in der
		 * INI-Datei.
		 */
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(IConstants.CONFIG_PATH));
		properties.store(out, "");
		/*
		 * Schließt den Stream.
		 */
		out.close();
	}
}