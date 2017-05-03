package parser;

public class Literal extends Expression {
	private String value;
	
	public String toString(String prefix) {
		return prefix + "Literal\n" + prefix + " Value:\n" + prefix + "  " + value;
	}
	
	public String getValue() { return value; }
}
