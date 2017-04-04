package parser;

public class Literal extends Expression {
	private String value;
	
	public String toString(String prefix) {
		return prefix + "Literal";
	}
}
