package parser;

import main.Visitor;

public class LocalVariable extends Statement {
	private String name;
	private Reference type;
	private IExpression init;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(name != null)
			str += "\n" + prefix + " Name:\n" + prefix + "  " + name;
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		if(init != null) {
			str += "\n" + prefix + " Init:";
			str += "\n" + init.toString(prefix + "  ");
		}
		
		return str;
	}

	public String getName() {
		return name;
	}

	public Reference getType() {
		return type;
	}

	public IExpression getInit() {
		return init;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((init == null) ? 0 : init.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LocalVariable)) {
			if(obj instanceof LocalVariableReference){
				LocalVariableReference other = (LocalVariableReference) obj;
				if (name == null) {
					if (other.name != null) {
						return false;
					}
				} else if (!name.equals(other.name)) {
					return false;
				}
				if (type == null) {
					if (other.getType() != null) {
						return false;
					}
				} else if (!type.equals(other.getType())) {
					return false;
				}
				return true;
			}
			return false;
		}
		LocalVariable other = (LocalVariable) obj;
		if (init == null) {
			if (other.init != null) {
				return false;
			}
		} else if (!init.equals(other.init)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
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
