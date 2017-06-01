package parser;

public abstract class Reference extends BasicNode implements IReference {
	protected String name;
	
	public String toString(String prefix) {
		return prefix + "Reference";
	}
	
	public String getName() {
		return name;
	}
}
