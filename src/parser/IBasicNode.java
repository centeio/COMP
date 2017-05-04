package parser;

import java.util.List;

import main.Visitor;

public interface IBasicNode {
	public String getNodeType();
    public String getLocation();
    public List<Comment> getComments();
    public void accept(Visitor v);
}
