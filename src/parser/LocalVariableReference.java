package parser;

public class LocalVariableReference extends Reference {
	private Reference type;
	
	public String toString(String prefix) {
		String str = prefix + "LocalVariableReference"; 
	
		if(type != null) {
			str += "\n" + prefix + " Type";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}

	public Reference getType() {
		return type;
	}

	public void setType(Reference type) {
		this.type = type;
	}
	
}
