package Model;

import java.awt.Dimension;
import java.util.Observable;

import View.GamePanelView;
import View.MainView;

public class OptionsModel extends Observable {
	private MainView mainView;
	
	public OptionsModel(MainView mainView) {
		this.mainView = mainView;
	}
	
	public void setResolution(Dimension dimension) {
		mainView.setSize(dimension);
		mainView.setLocationRelativeTo(null);
		this.setChanges();
	}
	
	public void setChanges() {
		setChanged();
		notifyObservers();
	}
}
