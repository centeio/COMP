package parser;

public class ArrayWrite extends Expression {
	private Expression target;
	private Expression index;
	
	public String toString(String prefix) {
    	String str = prefix + "ArrayWrite";
    	
    	if(type != null) {
			str += "\n" + prefix + " Type";
			str += "\n" + type.toString(prefix + "  ");
		}
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	if(index != null) {
    		str += "\n" + prefix + " Index"; 
    		str += "\n" + index.toString(prefix + "  ");
    	}
    	
    	return str;
    }
	
	
	public Expression getTarget() {
		return target;
	}
	public void setTarget(Expression target) {
		this.target = target;
	}
	public Expression getIndex() {
		return index;
	}
	public void setIndex(Expression index) {
		this.index = index;
	}
	
	

}
