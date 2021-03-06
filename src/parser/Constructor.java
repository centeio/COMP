package parser;

import java.util.List;

import main.Visitor;

public class Constructor extends Member {
	private List<Parameter> parameters;
	private IStatement body;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(parameters != null) {
			str += "\n" + prefix + " Parameters:";
			for(Parameter parameter: parameters)
				str += "\n" + parameter.toString(prefix + "  ");
		}
		
		if(body != null) {
			str += "\n" + prefix + " Body:";
			str += "\n" + body.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<Parameter> getParameters() { return parameters; }
	public IStatement getBody() { return body; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
