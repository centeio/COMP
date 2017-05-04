package parser;

import main.Visitor;

public class TypeAccess extends Expression {
	private TypeReference target;
	
	public String toString(String prefix) {
    	String str = prefix + nodetype; 
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target:";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	return str;
    }

	public TypeReference getTarget() {
		return target;
	}

	public void setTarget(TypeReference target) {
		this.target = target;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
