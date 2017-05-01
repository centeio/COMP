package parser;

import com.google.gson.annotations.SerializedName;

public class TypeReference extends Reference {
	@SerializedName("package")
	private String _package;
	
	public String toString(String prefix) {
		return prefix + "TypeReference\n" + prefix + " Name:\n" + prefix + "  " + name;
	}
	
	public String getPackage() { return _package; }
}
