package parser;

public interface IStatement extends IBasicNode {
	public String getLabel();
	public String toString(String prefix);
}
