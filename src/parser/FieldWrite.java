package parser;

import main.Visitor;

public class FieldWrite extends Expression {
	private Reference var;
	
	public String toString(String prefix) {
    	String str = prefix + nodetype; 
    	
    	if(var != null) {
    		str += "\n" + prefix + " Var:";
    		str += "\n" + var.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	public Reference getVar() {
		return var;
	}

	@Override
	public void accept(Visitor v) {
		//v.visit(this);
	}

}
