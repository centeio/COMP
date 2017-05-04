package parser;

import java.util.List;

import main.Visitable;

public abstract class BasicNode implements IBasicNode {
	    private String nodetype;
	    private String location;
	    private List<Comment> comments;
	    
	    public String getNodeType() { return nodetype; }
	    public String getLocation() { return location; }
	    public List<Comment> getComments() { return comments; }
}


