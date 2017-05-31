package parser;

public interface IExpression extends IBasicNode {
	public IBasicNode getType();
	public String toString(String prefix);
}
