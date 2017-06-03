package parser;

public abstract class Reference extends BasicNode implements IReference {
	protected String name;
	
	public String toString(String prefix) {
		return prefix + "Reference";
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Reference)) {
			return false;
		}
		
		Reference other = (Reference) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
