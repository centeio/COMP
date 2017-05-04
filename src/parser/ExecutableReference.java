package parser;

import java.util.List;

import main.Visitor;

public class ExecutableReference extends Reference {
	private TypeReference declarator;
	private TypeReference type;
	private List<Parameter> parameters;
	private List<Reference> arguments;
	
	public String toString(String prefix) {
		String str = prefix + "ExecutableReference";
		
		if(name != null)
			str += "\n" + prefix + " Name:\n" + prefix + "  " + name;
	
		if(declarator != null) {
			str += "\n" + prefix + " Declarator:";
			str += "\n" + declarator.toString(prefix + "  ");
		}
		
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		
		if(parameters != null) {
			str += "\n" + prefix + " Parameters:";
			for(Parameter parameter: parameters)
				str += "\n" + parameter.toString(prefix + "  ");
		}
		
		if(arguments != null) {
			str += "\n" + prefix + " Arguments:";
			for(Reference argument: arguments)
				str += "\n" + argument.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public TypeReference getDeclarator() { return declarator; }
	public TypeReference getType() { return type; }
	public List<Parameter> getParameters() { return parameters; }
	public List<Reference> getArguments() { return arguments; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
