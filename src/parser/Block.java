package parser;

import java.util.List;

import main.Visitor;

public class Block extends Statement {
	private List<IStatement> statements;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(statements != null) {
			str += "\n" + prefix + " Statements:";
			
			for(IStatement statement: statements)
				str += "\n" + statement.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<IStatement> getStatements() { return statements; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
