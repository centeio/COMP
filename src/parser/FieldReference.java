package parser;

import main.Visitor;

public class FieldReference extends Reference {
	private IReference declarator;
	private Reference type;
	
	public String toString(String prefix) {
    	String str = prefix + nodetype; 
    	
    	if(type != null) {
    		str += "\n" + prefix + " Target:";
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
