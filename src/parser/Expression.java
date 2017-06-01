package parser;

public abstract class Expression extends BasicNode implements IExpression {
    protected IReference type;
    
    public IBasicNode getType() { return type; }
}