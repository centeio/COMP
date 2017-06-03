package parser;

import main.Visitor;

public class FieldRead extends Expression {
	private IExpression target;
	private Reference var;
	
	public String toString(String prefix) {
    	String str = prefix + nodetype; 
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target:";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	if(var != null) {
    		str += "\n" + prefix + " Var:";
    		str += "\n" + var.toString(prefix + "  ");
    	}
    	
    	if(type != null) {
    		str += "\n" + prefix + " Type:";
    		str += "\n" + type.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	public IExpression getTarget() {
		return target;
	}
	public Reference getVar() {
		return var;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
