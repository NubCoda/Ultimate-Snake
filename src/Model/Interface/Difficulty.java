package Model.Interface;

/**
 * 
 *
 */
public enum Difficulty {
	SIMPLE, MEDIUM, DIFFICULT;

	@Override
	public String toString() {
		return this == SIMPLE ? "Einfach" : this == MEDIUM ? "Normal"
				: "Schwer";
	}

	/**
	 * 
	 * @param difficulty
	 * @return
	 */
	public static Difficulty fromString(String difficulty) {
		if (difficulty.equals("einfach")) {
			return SIMPLE;
		} else if (difficulty.equals("normal")) {
			return MEDIUM;
		} else {
			return DIFFICULT;
		}
	}
}
