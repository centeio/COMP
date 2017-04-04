package parser;

import java.util.List;

public class ExecutableReference extends Reference {
	private TypeReference declarator;
	private TypeReference type;
	private List<Parameter> parameters;
	private List<Reference> arguments;
	
	public String toString(String prefix) {
		String str = prefix + "ExecutableReference\n" + declarator.toString(prefix + " ") + "\n" + type.toString(prefix + " ");
		
		if(parameters != null)
			for(Parameter parameter: parameters)
				str += "\n" + parameter.toString(prefix + " ");
		
		if(arguments != null)
			for(Reference argument: arguments)
			str += "\n" + argument.toString(prefix + " ");
		
		return str;
	}
	
	public TypeReference getDeclarator() { return declarator; }
	public TypeReference getType() { return type; }
	public List<Parameter> getParameters() { return parameters; }
	public List<Reference> getArguments() { return arguments; }
	
}
