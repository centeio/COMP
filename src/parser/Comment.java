package parser;

public class Comment extends BasicNode {
	String type;
	String content;
	String position;
	
	public String toString(String prefix) {
		return prefix + "Comment";
	}
	
	public String getType() { return type; }
	public String getContent() { return content; }
	public String getPosition() { return position; }
}
