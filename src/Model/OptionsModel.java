package Model;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Properties;

import Model.Interface.IConstants;
import Model.Interface.IDefaultOptions;

/**
 * 
 * 
 */
public class OptionsModel extends Observable {
	private Properties properties;
	
	/**
	 * 
	 */
	public OptionsModel() {
		try {
			loadProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadProperties() throws FileNotFoundException, IOException{
		properties = new Properties();
		if(new File(IConstants.CONFIG_PATH).exists()){
			properties.load(new BufferedInputStream(new FileInputStream(new File(IConstants.CONFIG_PATH))));
		} else {
			// TODO defaults setzen
			properties.put("", IDefaultOptions.DEFAULT_DIFFICULTY);
			properties.store(new BufferedWriter(new FileWriter(new File(IConstants.CONFIG_PATH))), "");
		}
	}
}