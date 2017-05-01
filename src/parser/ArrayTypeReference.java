package parser;

public class ArrayTypeReference extends Reference {
	private TypeReference type;

	public String toString(String prefix) {
		String str = prefix + "ArrayTypeReference";
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}
}
