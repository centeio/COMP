package parser;

import com.google.gson.annotations.SerializedName;

import main.Visitor;

public class Class extends Type {
	@SerializedName("package")
	private String _package;
	@SerializedName("super")
	private TypeReference _super;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(_super != null) {
			str += "\n" + prefix + " Super:";
			str += "\n" + _super.toString(prefix + "  ");
		}
		
		str += super.toString(prefix + " ");
		
		return str;
	}
	
	public String getPackage() { return _package; }
	public TypeReference getSuperClass() { return _super; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
