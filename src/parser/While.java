package parser;

import main.Visitor;

public class While extends Statement {
	private IExpression condition;
	private IStatement body;
	
	public String toString(String prefix) {
		String str =  prefix + nodetype;
	
		if(condition != null) {
			str += "\n" + prefix + " Condition:";
			str += "\n" + condition.toString(prefix + "  ");
		}
		
		if(body != null) {
			str += "\n" + prefix + " Body:";
			str += "\n" + body.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public IExpression getCondition() {
		return condition;
	}
	public void setCondition(IExpression condition) {
		this.condition = condition;
	}
	public IStatement getBody() {
		return body;
	}
	public void setBody(IStatement body) {
		this.body = body;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
