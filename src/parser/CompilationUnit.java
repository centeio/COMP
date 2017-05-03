package parser;

import java.util.List;

import main.Visitor;

public class CompilationUnit extends BasicNode {
	protected List<Type> types;
	
	public String toString(String prefix) {
		String str = prefix + "CompilationUnit";
		
		if(types != null) {
			str += "\n" + prefix + " Types:";
			
			for(Type type: types)
				str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<Type> getTypes() { return types; }
}
