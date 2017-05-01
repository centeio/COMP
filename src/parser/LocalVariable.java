package parser;

public class LocalVariable extends Statement {
	private Reference type;
	private Expression init;
	
	public String toString(String prefix) {
		String str = prefix + "LocalVariable";
		
		if(type != null) {
			str += "\n" + prefix + " Type";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		if(init != null) {
			str += "\n" + prefix + " Init";
			str += "\n" + init.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public Reference getType() { return type; }
	public Expression getInit() { return init; }
}
