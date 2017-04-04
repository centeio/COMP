package parser;

public class Assignment extends Statement {
	private TypeReference type;
	private Expression lhs;
    private Expression rhs;
    
    public String toString(String prefix) {
    	String str = prefix + "Assignment";
    			
    	if(type!= null) 
    		str += "\n" + type.toString(prefix + " ");
    	
    	if(lhs != null)
    		str += "\n" + lhs.toString(prefix + " ");
    	
    	if(rhs != null)
    		str += "\n" + rhs.toString(prefix + " ");
    	
    	return str;
    }
    
    public Expression getlhs() { return lhs; }
    public Expression getrhs() { return rhs; }
    public TypeReference type() { return type; }
    
}
