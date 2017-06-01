package parser;

import main.Visitor;

public class Do extends Statement {
	private Block body;
	private Expression condition;
	
	public String toString(String prefix) {
		String str =  prefix + nodetype;
	
		if(body != null) {
			str += "\n" + prefix + " Body:";
			str += "\n" + body.toString(prefix + "  ");
		}
		
		if(condition != null) {
			str += "\n" + prefix + " Condition:";
			str += "\n" + condition.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public Block getBody() {
		return body;
	}

	public Expression getCondition() {
		return condition;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
