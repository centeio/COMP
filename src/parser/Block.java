package parser;

import java.util.List;

public class Block extends Statement {
	private List<Statement> statements;
	
	public String toString(String prefix) {
		String str = prefix + "Block";
		
		if(statements != null)
			for(Statement statement: statements)
				str += "\n" + statement.toString(prefix + " ");
		
		return str;
	}
	
	public List<Statement> getStatements() { return statements; }
}
