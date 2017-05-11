package parser;

import com.google.gson.annotations.SerializedName;

import main.Visitor;

public class TypeReference extends Reference {
	@SerializedName("package")
	private String _package;
	
	public String toString(String prefix) {
		return prefix + nodetype + "\n" + prefix + " Name:\n" + prefix + "  " + name;
	}
	
	public String getPackage() { return _package; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
