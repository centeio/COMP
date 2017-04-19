package parser;

public abstract class Expression extends BasicNode {
    protected TypeReference type; 
    
    public String toString(String prefix) {
    	return prefix + "Expression";
    }
    
    public TypeReference getType() { return type; }
    
    public BasicNode[] getChildren(){
    	return new BasicNode[0];
    }
}