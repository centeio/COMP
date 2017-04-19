package parser;

import java.util.List;

public class Constructor extends Member {
	private List<Parameter> parameters;
	private Statement body;
	
	public String toString(String prefix) {
		String str = prefix + "Constructor";
		
		for(Parameter parameter: parameters) {
			str += "\n" + parameter.toString(prefix + " ");
		}
		
		str += "\n" + body.toString(prefix + " ");
		
		return str;
	}
	
	public List<Parameter> getParameters() { return parameters; }
	public Statement getBody() { return body; }

	@Override
	public BasicNode[] getChildren() {
		BasicNode children[] = new BasicNode[1];
		children[0] = body;
		return children;
	}
}
