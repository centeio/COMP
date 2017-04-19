package parser;

public abstract class Reference extends BasicNode {
	protected String name;
	
	public String toString(String prefix) {
		return prefix + "Reference";
	}
	
	public String getName() { return name; }
	
	public BasicNode[] getChildren(){
		return new BasicNode[0];
	}
}
