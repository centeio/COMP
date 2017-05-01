package parser;

public class FieldRead extends Expression {
	private Expression target;
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
	
	public Expression getTarget() {
		return target;
	}
	public void setTarget(Expression target) {
		this.target = target;
	}
	public Reference getVar() {
		return var;
	}
	public void setVar(Reference var) {
		this.var = var;
	}
	
	
}
