package parser;

import main.Visitor;

public class NullNode extends Statement implements IReference {

	public String toString(String prefix) {
		return prefix + nodetype;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public String getName() {
		return null;
	}
}
