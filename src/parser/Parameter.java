package parser;

public class Parameter extends BasicNode {
	private String name;
	private Reference type;
	
	public String toString(String prefix) {
		String str = prefix + "Parameter";
		
		if(type != null) {
			str += "\n" + prefix + " Type";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public String getName() { return name; }
	public Reference getType() { return type; }

	@Override
	public BasicNode[] getChildren() {
		return new BasicNode[0];
	}
}
