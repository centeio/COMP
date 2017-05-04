package parser;

import main.Visitor;

public class LocalVariable extends Statement {
	private String name;
	private Reference type;
	private IExpression init;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
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

	public IExpression getInit() {
		return init;
	}

	public void setInit(IExpression init) {
		this.init = init;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
