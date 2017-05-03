package parser;

// TODO: Auto-generated Javadoc
/**
 * The Class Literal.
 */
public class Literal extends Expression {
	
	/** The value. */
	private String value;
	
	/* (non-Javadoc)
	 * @see parser.Expression#toString(java.lang.String)
	 */
	public String toString(String prefix) {
		return prefix + "Literal\n" + prefix + " Value:\n" + prefix + "  " + value;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() { return value; }
}
