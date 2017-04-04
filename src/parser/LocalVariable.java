package parser;

public class LocalVariable extends Statement {
	private TypeReference type;
	private Expression init;
	
	public String toString(String prefix) {
		String str = prefix + "LocalVariable";
		
		if(type != null)
			str += "\n" + type.toString(prefix + " ");
		
		if(init != null)
			str += "\n" + init.toString(prefix + " ");
		
		return str;
	}
	
	public TypeReference getType() { return type; }
	public Expression getInit() { return init; }
}
