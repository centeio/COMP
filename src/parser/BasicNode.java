package parser;

import java.util.List;

public abstract class BasicNode {
	    private String nodetype;
	    private String location;
	    private List<Comment> comments;
	    
	    public String toString(String prefix) {
	    	return prefix + "BasicNode";
	    }
}


