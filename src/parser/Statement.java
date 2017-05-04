package parser;

public abstract class Statement extends BasicNode{
	protected String label;
	
	public String toString(String prefix) {
		return prefix + "Statement";
	}
	
	public String getLabel() { return label; }

	@Override
	public BasicNode[] getChildren() {
		return new BasicNode[0];
	}
}
