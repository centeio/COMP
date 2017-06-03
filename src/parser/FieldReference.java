package parser;

import main.Visitor;

public class FieldReference extends Reference {
	private IReference declarator;
	private Reference type;
	
	public String toString(String prefix) {
    	String str = prefix + nodetype; 
    	
    	if(name != null) {
    		str += "\n" + prefix + " Name: " + name;
    	}
    	
    	if(type != null) {
    		str += "\n" + prefix + " Type:";
    		str += "\n" + type.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	public IReference getDeclarator() {
		return declarator;
	}

	public Reference getType() {
		return type;
	}

	@Override
	public void accept(Visitor v) {
		//v.visit(this);
	}

}
