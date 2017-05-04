package parser;

import java.util.List;

import main.Visitor;

public class Invocation extends Statement implements IExpression {
	private Reference executable;
	private List<IExpression> arguments;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(executable != null) {
			str += "\n" + prefix + " Executable:";
			str += "\n" + executable.toString(prefix + "  ");
		}
		
		if(arguments != null) {
			str += "\n" + prefix + " Arguments:";
			for(IExpression argument: arguments)
				str += "\n" + argument.toString(prefix + "  ");
		}
		
		return str;
	}

	public Reference getExecutable() {
		return executable;
	}

	public void setExecutable(Reference executable) {
		this.executable = executable;
	}

	public List<IExpression> getArguments() {
		return arguments;
	}

	public void setArguments(List<IExpression> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public Reference getType() { return null; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
