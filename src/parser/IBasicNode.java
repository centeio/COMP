package parser;

import java.util.List;

import main.Visitable;
import main.Visitor;

public interface IBasicNode extends Visitable {
	public String getNodeType();
    public String getLocation();
    public List<Comment> getComments();
    public void accept(Visitor v);
}
