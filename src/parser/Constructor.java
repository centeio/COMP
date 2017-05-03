package parser;

import java.util.List;

public class Constructor extends Member {
	private List<Parameter> parameters;
	private Statement body;
	
	public String toString(String prefix) {
		String str = prefix + "Constructor";
		
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
	public Statement getBody() { return body; }
}
