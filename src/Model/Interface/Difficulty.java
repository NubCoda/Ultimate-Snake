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
		if (difficulty.equals("Einfach")) {
			return SIMPLE;
		} else if (difficulty.equals("Normal")) {
			return MEDIUM;
		} else {
			return DIFFICULT;
		}
	}
}
