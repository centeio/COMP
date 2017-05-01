package parser;

public class BinaryOperator extends Expression { 
    private String operator;
    private Expression lhs;
    private Expression rhs;
    
    public String toString(String prefix) {
    	String str = prefix + "BinaryOperator";
    	
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

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Expression getLhs() {
		return lhs;
	}

	public void setLhs(Expression lhs) {
		this.lhs = lhs;
	}

	public Expression getRhs() {
		return rhs;
	}

	public void setRhs(Expression rhs) {
		this.rhs = rhs;
	}

	@Override
	public BasicNode[] getChildren() {
		return new BasicNode[0];
	}
}