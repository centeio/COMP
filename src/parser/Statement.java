package parser;

public abstract class Statement extends BasicNode {
	protected String label;
	
	public String toString(String prefix) {
		return prefix + "Statement";
	}
}
