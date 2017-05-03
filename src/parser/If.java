package parser;

import com.google.gson.annotations.SerializedName;

public class If extends Statement {
	private Expression condition;
	private Statement then;
	@SerializedName("else")
	private Statement _else;
	
	public String toString(String prefix) {
		String str =  prefix + "If";
	
		if(condition != null) {
			str += "\n" + prefix + " Condition:";
			str += "\n" + condition.toString(prefix + "  ");
		}
		
		if(then != null) {
			str += "\n" + prefix + " Then:";
			str += "\n" + then.toString(prefix + "  ");
		}
		
		if(_else != null) {
			str += "\n" + prefix + " Else:";
			str += "\n" + _else.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public Expression getCondition() { return condition; }
	public Statement getThen() { return then; }
	public Statement getElse() { return _else; }
}
