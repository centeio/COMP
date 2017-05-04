package parser;

import main.Visitor;

public class ArrayTypeReference extends Reference {
	private TypeReference type;

	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
