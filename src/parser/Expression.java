package parser;

public abstract class Expression extends BasicNode {
    protected Reference type; 
    
    public String toString(String prefix) {
    	return prefix + "Expression";
    }
    
    public Reference getType() { return type; }
}