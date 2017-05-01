package parser;

import java.util.List;

public class For extends Statement {
	private List<Statement> init;
	private Expression condition;
	private List<BasicNode> update;
	private Statement body;
	
	public String toString(String prefix) {
		String str = prefix + "For";
		
		if(init != null) {
			str += "\n" + prefix + " init:";
			
			for(Statement statement: init)
				str += "\n" + statement.toString(prefix + "  ");
			
		}
		
		if(condition != null) {
			str += "\n" + prefix + " Condition:";
			str += "\n" + condition.toString(prefix + "  ");
		}
		
		if(update != null) {
			str += "\n" + prefix + " Update:";
			
			for(BasicNode statement: update)
				str += "\n" + statement.toString(prefix + "  ");
		}
		
		if(body != null) {
			str += "\n" + prefix + " Body:";
			str += "\n" + body.toString(prefix + "  ");
		}
		
		return str;
	}

	public List<Statement> getInit() {
		return init;
	}

	public void setInit(List<Statement> init) {
		this.init = init;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<BasicNode> getUpdate() {
		return update;
	}

	public void setUpdate(List<BasicNode> update) {
		this.update = update;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}
	
	

}
