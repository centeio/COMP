package parser;

public abstract class Statement extends BasicNode implements IStatement {
	protected String label;
	
	public String getLabel() { return label; }
}
