package parser;

public class UnaryOperator extends Expression {
	private String operator;
	private Expression operand;
	
	public String toString(String prefix) {
    	String str = prefix + "UnaryOperator"; 
    	
    	if(operand != null) {
    		str += "\n" + prefix + " Operand";
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
	
}
