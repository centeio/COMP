package parser;

import java.util.List;

public class Invocation extends Statement {
	private Reference executable;
	private List<Expression> arguments;
	
	public String toString(String prefix) {
		String str = prefix + "Invocation";
		
		if(executable != null) {
			str += "\n" + prefix + " Executable";
			str += "\n" + executable.toString(prefix + "  ");
		}
		
		if(arguments != null) {
			str += "\n" + prefix + " Arguments";
			for(Expression argument: arguments)
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

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

}
