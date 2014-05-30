package Model;

import java.awt.Dimension;
import java.util.Observable;

/**
 * 
 * 
 */
public class OptionsModel extends Observable {
	private Dimension dimension;

	/**
	 * 
	 */
	public OptionsModel() {
	}

	/**
	 * 
	 * @param dimension
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * 
	 * @return
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * 
	 */
	public void setChanges() {
		setChanged();
		notifyObservers();
	}
}