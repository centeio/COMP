package parser;

public abstract class Expression extends BasicNode implements IExpression {
    protected Reference type;
    
    public Reference getType() { return type; }
}