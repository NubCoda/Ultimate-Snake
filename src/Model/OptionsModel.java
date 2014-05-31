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
 * 
 * 
 */
public class OptionsModel extends Observable {
	private Properties properties;
	
	/**
	 * 
	 */
	public OptionsModel() {
		properties = new Properties();
		try {
			loadProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void loadProperties() throws FileNotFoundException, IOException{
		if(new File(IConstants.CONFIG_PATH).exists()){
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(IConstants.CONFIG_PATH));
			properties.load(in);
			in.close();
		} else {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(IConstants.CONFIG_PATH));
			properties.setProperty("player_id", String.valueOf(IDefaultOptions.DEFAULT_PLAYER_ID));
			properties.setProperty("difficulty", String.valueOf(IDefaultOptions.DEFAULT_DIFFICULTY));
			properties.setProperty("key_down", String.valueOf(IDefaultOptions.DEFAULT_KEY_DOWN));
			properties.setProperty("key_up", String.valueOf(IDefaultOptions.DEFAULT_KEY_UP));
			properties.setProperty("key_right", String.valueOf(IDefaultOptions.DEFAULT_KEY_RIGHT));
			properties.setProperty("key_left", String.valueOf(IDefaultOptions.DEFAULT_KEY_LEFT));
			properties.setProperty("key_shoot", String.valueOf(IDefaultOptions.DEFAULT_KEY_SHOOT));
			properties.store(out, "");
			out.close();
		}
	}
	
	/**
	 * 
	 * @param propertyKey
	 * @return
	 */
	public String getOption(String propertyKey){
		return properties.getProperty(propertyKey);
	}

	/**
	 * 
	 * @param propertyName
	 * @param property
	 */
	public void setOption(String propertyName, String property) {
		properties.setProperty(propertyName, property);
	}
}