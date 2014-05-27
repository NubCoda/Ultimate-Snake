package Model.Interface;

/**
 * 
 * 
 */
public interface IMenu {
	/**
	 * 
	 * @return
	 */
	boolean isFocused();

	/**
	 * 
	 */
	void focus();

	/**
	 * 
	 */
	void defocus();

	/**
	 * 
	 * @return
	 */
	String getText();
}