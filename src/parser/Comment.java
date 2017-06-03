package parser;

import main.Visitor;

public class Comment extends BasicNode {
	String type;
	String content;
	String position;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(type != null)
			str += "\n" + prefix + " Type:\n" + prefix + "  " + type;
		
		if(content != null) {
			str += "\n" + prefix + " Content:\n" + prefix + "  " + content;
		}
		
		if(position != null) {
			str += "\n" + prefix + " Position:\n" + prefix + "  " + position;
		}
		
		return str;
	}
	
	public String getType() { return type; }
	public String getContent() { return content; }
	public String getPosition() { return position; }
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
