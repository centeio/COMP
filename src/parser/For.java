package parser;

import java.util.List;

import main.Visitor;

public class For extends Statement {
	private List<IStatement> init;
	private IExpression condition;
	private List<IStatement> update;
	private IStatement body;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(init != null) {
			str += "\n" + prefix + " init:";
			
			for(IStatement statement: init)
				str += "\n" + statement.toString(prefix + "  ");
			
		}
		
		if(condition != null) {
			str += "\n" + prefix + " Condition:";
			str += "\n" + condition.toString(prefix + "  ");
		}
		
		if(update != null) {
			str += "\n" + prefix + " Update:";
			
			for(IStatement statement: update)
				str += "\n" + statement.toString(prefix + "  ");
		}
		
		if(body != null) {
			str += "\n" + prefix + " Body:";
			str += "\n" + body.toString(prefix + "  ");
		}
		
		return str;
	}

	public List<IStatement> getInit() {
		return init;
	}

	public IExpression getCondition() {
		return condition;
	}

	public List<IStatement> getUpdate() {
		return update;
	}
	
	public IStatement getBody() {
		return body;
	}
	
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
