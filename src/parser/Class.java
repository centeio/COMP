package parser;

import com.google.gson.annotations.SerializedName;

public class Class extends Type {
	@SerializedName("package")
	private String _package;
	@SerializedName("super")
	private TypeReference _super;
	
	public String toString(String prefix) {
		String str = prefix + "Class";
		
		if(_super != null)
			str += "\n" + _super.toString(prefix + " ");
		
		str += super.toString(prefix + " ");
		
		return str;
	}
	
	public String getPackage() { return _package; }
	public TypeReference getSuperClass() { return _super; }
}
