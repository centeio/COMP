package parser;

import main.Visitor;

public class FieldRead extends Expression {
	private IExpression target;
	private Reference var;
	
	public String toString(String prefix) {
    	String str = prefix + "FieldRead"; 
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target:";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	if(var != null) {
    		str += "\n" + prefix + " Var:";
    		str += "\n" + var.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	public IExpression getTarget() {
		return target;
	}
	public void setTarget(IExpression target) {
		this.target = target;
	}
	public Reference getVar() {
		return var;
	}
	public void setVar(Reference var) {
		this.var = var;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
