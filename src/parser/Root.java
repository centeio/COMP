package parser;

import java.util.List;

import main.Visitor;

public class Root extends BasicNode {
	protected List<CompilationUnit> compilation_units;
	
	public String toString(String prefix) {
		String str = prefix + "Root";
		
		if(compilation_units != null) {
			str += "\n" + prefix + " CompilationUnits:";
			for(CompilationUnit unit: compilation_units)
				str += "\n" + unit.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<CompilationUnit> getCompilationUnits() { return compilation_units; }
}
