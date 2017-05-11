package parser;

import com.google.gson.annotations.SerializedName;

import main.Visitor;

public class If extends Statement {
	private IExpression condition;
	private IStatement then;
	@SerializedName("else")
	private IStatement _else;
	
	public String toString(String prefix) {
		String str =  prefix + nodetype;
	
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
	
	public IExpression getCondition() { return condition; }
	public IStatement getThen() { return then; }
	public IStatement getElse() { return _else; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
