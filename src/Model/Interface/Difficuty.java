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
		return this == SIMPLE ? "simple" : this == MEDIUM ? "medium"
				: "difficult";
	}
}
