package View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import Model.FileWriterModel;

public class FileWriterView implements Observer {

	public FileWriterView(){
		
	}
	
	@Override
	public void update(Observable observable, Object object) {
		FileWriterModel fileWriterModel = ((FileWriterModel) observable);
		for(Properties properties : fileWriterModel.getProperVector()) {
			try {
				properties.store(new FileOutputStream(fileWriterModel.getFile()), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
