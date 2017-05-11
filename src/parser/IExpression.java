package parser;

public interface IExpression extends IBasicNode {
	public Reference getType();
	public String toString(String prefix);
}
