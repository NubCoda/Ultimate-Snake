package Model;

import java.io.File;
import java.util.Observable;
import java.util.Properties;
import java.util.Vector;

public class FileWriterModel extends Observable {
	private Vector<Properties> properVector;
	private File file;
	
	public FileWriterModel() {
		properVector = new Vector<Properties>();
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