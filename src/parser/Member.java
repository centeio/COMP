package parser;

public abstract class Member extends BasicNode {
	protected String name;
	
	public String toString(String prefix) {
		return prefix + "Member";
	}
}
