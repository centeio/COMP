package parser;

public class While extends Statement {
	private Expression condition;
	private Statement body;
	
	public String toString(String prefix) {
		String str =  prefix + "While";
	
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
	
	public Expression getCondition() {
		return condition;
	}
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	
	
}
