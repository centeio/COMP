package parser;

import java.util.List;

public class CompilationUnit extends BasicNode {
	protected List<Type> types;
	
	public String toString(String prefix) {
		String str = prefix + "CompilationUnit";
		
		if(types != null)
			for(Type type: types)
				str += "\n" + type.toString(prefix + " ");
		
		return str;
	}
}
