package parser;

public class VariableWrite extends Expression {
	private Reference var;
	
	public String toString(String prefix) {
		String str = prefix + "VariableWrite";
		
		if(var != null)
			str += "\n" + var.toString(prefix + " ");
		
		return str;
	}
}
