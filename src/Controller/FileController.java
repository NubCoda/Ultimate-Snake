package Controller;

import java.io.File;
import java.util.Properties;

import Model.FileModel;
import View.FileView;

public class FileController {
	private static FileController fileController;
	private FileModel fileWriterModel;
	
	private FileController() {
		fileWriterModel = new FileModel();
	}
	
	public static FileController getInstance() {
		if(fileController == null) {
			fileController = new FileController();
		}
		
		return fileController;
	}
	
	public void writeToIniFile(FileView fileWriterView, File file, Properties properties) {
		fileWriterModel.addProperty(properties);
		fileWriterModel.setFile(file);
		fileWriterModel.addObserver(fileWriterView);
		fileWriterModel.setChanges();
	}
	
}
