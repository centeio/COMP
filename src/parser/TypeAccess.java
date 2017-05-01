package parser;

public class TypeAccess extends Expression {
	public TypeReference target;
	
	public String toString(String prefix) {
    	String str = prefix + "TypeAccess"; 
    	
    	if(target != null) {
    		str += "\n" + prefix + " Target";
    		str += "\n" + target.toString(prefix + "  ");
    	}
    	
    	return str;
    }

	public TypeReference getTarget() {
		return target;
	}

	public void setTarget(TypeReference target) {
		this.target = target;
	}
	
	

}
