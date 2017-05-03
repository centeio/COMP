package parser;

import java.util.List;

import main.Visitor;

public class Block extends Statement {
	private List<BasicNode> statements;
	
	public String toString(String prefix) {
		String str = prefix + "Block";
		
		if(statements != null) {
			str += "\n" + prefix + " Statements:";
			
			for(BasicNode statement: statements)
				str += "\n" + statement.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<BasicNode> getStatements() { return statements; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
