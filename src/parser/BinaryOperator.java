package parser;

import main.Visitor;

public class BinaryOperator extends Expression { 
    private String operator;
    private IExpression lhs;
    private IExpression rhs;
    
    public String toString(String prefix) {
    	String str = prefix + nodetype;
    	
    	if(operator != null)
    		str += "\n" + prefix + " Operator:\n" + prefix + "  " + operator;
    	
    	if(lhs != null) {
    		str += "\n" + prefix + " lhs:";
    		str += "\n" + lhs.toString(prefix + "  ");
    	}
    	
    	if(rhs != null) {
    		str += "\n" + prefix + " rhs:";
    		str += "\n" + rhs.toString(prefix + "  ");
    	}
    	
    	return str;
    }

	public String getOperator() {
		return operator;
	}

	public IExpression getLhs() {
		return lhs;
	}

	public IExpression getRhs() {
		return rhs;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}