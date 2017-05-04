package parser;

import main.Visitor;

public class UnaryOperator extends Expression {
	private String operator;
	private Expression operand;
	
	public String toString(String prefix) {
    	String str = prefix + "UnaryOperator";
    	
    	if(type != null) {
    		str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
    	}
    	
    	if(operator != null) 
    		str += "\n" + prefix + " Operator:\n" + prefix + "  " + operator;
    	
    	if(operand != null) {
    		str += "\n" + prefix + " Operand:";
    		str += "\n" + operand.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	
    public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Expression getOperand() {
		return operand;
	}
	public void setOperand(Expression operand) {
		this.operand = operand;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
