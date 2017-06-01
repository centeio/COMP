package parser;

import main.Visitor;

public class ArrayRead extends Expression {
	private IExpression target;
	private IExpression index;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
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
	public IExpression getIndex() {
		return index;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
