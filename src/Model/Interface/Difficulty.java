package Model.Interface;

/**
 * Dies ist ein Enum für die verschiedenen Schwierigkeitsgrade des Spiels,
 * welche in den Optionen der Spieler auswählen kann.
 */
public enum Difficulty {
	SIMPLE, MEDIUM, DIFFICULT;

	@Override
	public String toString() {
		return this == SIMPLE ? "Einfach" : this == MEDIUM ? "Normal"
				: "Schwer";
	}

	/**
	 * Gibt je String den passenden Enum zurück.
	 * 
	 * @param difficulty
	 *            Der String, aus welchem der Enum bestimmt werden soll
	 * @return Das Enum, welches zum String passt. Wenn keins dazu passt wird
	 *         standardmäßig DIFFICULT zurückgegeben.
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