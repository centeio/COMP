package parser;

public class BinaryOperator extends Expression { 
    private String operator;
    private Expression lhs;
    private Expression rhs;
    
    public String toString(String prefix) {
    	String str = prefix + "BinaryOperator"; 
    	
    	if(lhs != null)
    		str += "\n" + lhs.toString(prefix + " ");
    	
    	if(rhs != null)
    		str += "\n" + rhs.toString(prefix + " ");
    	
    	return str;
    }
    
    public String getOperator() { return operator; }
    public Expression getLHS() { return lhs; }
    public Expression getRHS() { return rhs; }

	@Override
	public BasicNode[] getChildren() {
		return new BasicNode[0];
	}
}