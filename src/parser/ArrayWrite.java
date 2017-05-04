package parser;

import main.Visitor;

public class ArrayWrite extends Expression {
	private IExpression target;
	private IExpression index;
	
	public String toString(String prefix) {
    	String str = prefix + "ArrayWrite";
    	
    	if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target:";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	if(index != null) {
    		str += "\n" + prefix + " Index:"; 
    		str += "\n" + index.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	
	public IExpression getTarget() {
		return target;
	}
	public void setTarget(IExpression target) {
		this.target = target;
	}
	public IExpression getIndex() {
		return index;
	}
	public void setIndex(IExpression index) {
		this.index = index;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
