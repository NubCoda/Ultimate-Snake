package Model;

import java.awt.Dimension;
import java.util.Observable;

import View.GamePanelView;
import View.MainView;

public class OptionsModel extends Observable {
	private MainView mainView;
	private Dimension dimension;
	
	public OptionsModel() {
	}
	
	
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}


	public Dimension getDimension() {
		return dimension;
	}
	
	public void setChanges() {
		setChanged();
		notifyObservers();
	}
	
	
}
