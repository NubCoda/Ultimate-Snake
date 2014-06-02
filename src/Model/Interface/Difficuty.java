package Model.Interface;

/**
 * 
 *
 */
public enum Difficuty {
	SIMPLE,
	MEDIUM,
	DIFFICULT;

	@Override
	public String toString() {
		return this == SIMPLE ? "einfach" : this == MEDIUM ? "normal"
				: "schwer";
	}
	
	/**
	 * 
	 * @param difficulty
	 * @return
	 */
	public static Difficuty fromString(String difficulty){
		if(difficulty.equals("einfach")){
			return SIMPLE;
		} else if(difficulty.equals("normal")){
			return MEDIUM;
		} else {
			return DIFFICULT;
		}
	}
}
