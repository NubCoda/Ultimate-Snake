package Controller;

import java.io.File;
import java.util.Properties;

import Model.FileModel;
import View.FileView;

public class FileController {
	private static FileController fileController;
	private FileModel fileModel;
	
	private FileController() {
		fileModel = new FileModel();
	}
	
	public static FileController getInstance() {
		if(fileController == null) {
			fileController = new FileController();
		}
		
		return fileController;
	}
	
	public void writeToIniFile(FileView fileView, File file, Properties properties) {
		fileModel.addProperty(properties);
		fileModel.setFile(file);
		fileModel.addObserver(fileView);
		fileModel.setChanges();
	}
	
}
