package parser;

import main.Visitor;

public class NullNode extends Statement {

	public String toString(String prefix) {
		return prefix + "NullNode";
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
