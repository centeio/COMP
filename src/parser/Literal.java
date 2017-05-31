package parser;

import main.Visitor;

/**
 * The Class Literal.
 */
public class Literal extends Expression {
	
	/** The value. */
	private String value;
	
	/* 
	 * @see parser.Expression#toString(java.lang.String)
	 */
	public String toString(String prefix) {
		return prefix + nodetype + "\n" + prefix + " Value:\n" + prefix + "  " + value;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() { return value; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
