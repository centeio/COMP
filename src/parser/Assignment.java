package parser;

import main.Visitor;

public class Assignment extends Statement {
	private TypeReference type;
	private Expression lhs;
    private Expression rhs;
    
    public String toString(String prefix) {
    	String str = prefix + "Assignment";
    			
    	if(type!= null) {
    		str += "\n" + prefix + " Type:";
    		str += "\n" + type.toString(prefix + "  ");
    	}
    	
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
    
    public Expression getlhs() { return lhs; }
    public Expression getrhs() { return rhs; }
    public TypeReference type() { return type; }
    
    @Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
