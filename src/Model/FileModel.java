package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Properties;
import java.util.Vector;

public class FileModel extends Observable {
	private Vector<Properties> properVector;
	private File file;
	
	public FileModel() {
		properVector = new Vector<Properties>();
	}
	
	public void clearVector() {
		properVector.clear();
	}
	
	public void writeToIniFile() {
		for(Properties properties : getProperVector()) {
			try {
				properties.store(new FileOutputStream(getFile()), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setChanges() {
		setChanged();
		notifyObservers();
	}
	
	public Vector<Properties> getProperVector() {
		return properVector;
	}
	
	public void addProperty(Properties properties) {
		properVector.add(properties);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
