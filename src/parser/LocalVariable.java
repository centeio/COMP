package parser;

public class LocalVariable extends Statement {
	private String name;
	private Reference type;
	private Expression init;
	
	public String toString(String prefix) {
		String str = prefix + "LocalVariable";
		
		if(name != null)
			str += "\n" + prefix + " Name:\n" + prefix + "  " + name;
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		if(init != null) {
			str += "\n" + prefix + " Init:";
			str += "\n" + init.toString(prefix + "  ");
		}
		
		return str;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reference getType() {
		return type;
	}

	public void setType(Reference type) {
		this.type = type;
	}

	public Expression getInit() {
		return init;
	}

	public void setInit(Expression init) {
		this.init = init;
	}
	
	
}
