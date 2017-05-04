package parser;

import java.util.List;

import main.Visitor;

public class Method extends Member {
	private TypeReference type;
	private List<Parameter> parameters;
	private IStatement body;
	
	public String toString(String prefix) {
		String str = prefix + "Method";
		
		if(name != null)
			str += "\n" + prefix + " Name:\n" + prefix + "  " + name;
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		
		if(parameters != null) {
			str += "\n" + prefix + " Parameters:";
			for(Parameter parameter: parameters) {
				str += "\n" + parameter.toString(prefix + "  ");
			}
		}
		
		if(body != null) {
			str += "\n" + prefix + " Body:"; 
			str += "\n" + body.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public TypeReference getType() { return type; }
	public List<Parameter> getParameters() { return parameters; }
	public IStatement getBody() { return body; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
