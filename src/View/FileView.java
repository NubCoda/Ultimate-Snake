package View;

import java.util.Observable;
import java.util.Observer;

import Model.FileModel;

/**
 * 
 * 
 */
public class FileView implements Observer {
	/**
	 * 
	 */
	public FileView() {

	}

	// DAS ist falsch
	@Override
	public void update(Observable observable, Object object) {
		FileModel fileModel = ((FileModel) observable);
		fileModel.writeToIniFile();
		fileModel.clearVector();
	}

}
