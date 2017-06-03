package parser;

import main.Visitor;

public class LocalVariableReference extends Reference {
	private Reference type;
	
	public String toString(String prefix) {
		String str = prefix + nodetype; 
		
		if(name != null)
			str += "\n" + prefix + " Name:\n" + prefix + "  " + name;
	
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		return str;
	}

	public Reference getType() {
		return type;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LocalVariableReference)) {

			System.out.println("Got here2");
			/*if(obj instanceof LocalVariable){
				LocalVariable objLv = (LocalVariable) obj; 
				if(objLv.getName().equals(name) && objLv.getType().equals(type)){
					return true;
				}
			}*/
			return false;
		}
		LocalVariableReference other = (LocalVariableReference) obj;
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}	
	
}
