package parser;

public abstract class Expression extends BasicNode implements IExpression {
    protected IBasicNode type;
    
    public IBasicNode getType() { return type; }
}