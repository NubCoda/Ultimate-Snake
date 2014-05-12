package Controller;

import java.io.File;
import java.util.Properties;

import Model.FileWriterModel;
import View.FileWriterView;

public class FileWriterController {
	private static FileWriterController fileController;
	private FileWriterModel fileWriterModel;
	
	private FileWriterController() {
		fileWriterModel = new FileWriterModel();
	}
	
	public static FileWriterController getInstance() {
		if(fileController == null) {
			fileController = new FileWriterController();
		}
		
		return fileController;
	}
	
	public void writeToIniFile(FileWriterView fileWriterView, File file, Properties properties) {
		fileWriterModel.addProperty(properties);
		fileWriterModel.setFile(file);
		fileWriterModel.addObserver(fileWriterView);
		fileWriterModel.setChanges();
	}
	
}
