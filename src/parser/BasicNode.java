package parser;

import java.util.List;

public abstract class BasicNode implements IBasicNode {
    protected String nodetype;
    protected String location;
    protected List<Comment> comments;
    
    public String getNodeType() { return nodetype; }
    public String getLocation() { return location; }
    public List<Comment> getComments() { return comments; }
    
    @Override
	public String toString() {
		return toString("")+"\n";
	}
}


