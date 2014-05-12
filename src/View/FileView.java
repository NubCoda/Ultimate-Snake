package View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import Model.FileModel;

public class FileView implements Observer {

	public FileView(){
		
	}
	
	@Override
	public void update(Observable observable, Object object) {
		FileModel fileModel = ((FileModel) observable);
		for(Properties properties : fileModel.getProperVector()) {
			try {
				properties.store(new FileOutputStream(fileModel.getFile()), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fileModel.cleareVector();
	}

}
