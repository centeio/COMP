package parser;

public class LocalVariableReference extends Reference {
	private TypeReference type;
	
	public String toString(String prefix) {
		String str = prefix + "LocalVariableReference"; 
	
		if(type != null)
			str += "\n" + type.toString(prefix + " ");
		
		return str;
	}
}
