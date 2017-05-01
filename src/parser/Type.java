package parser;

import java.util.List;

public abstract class Type extends Member {
	protected List<TypeReference> formal_type_parameters;
	protected List<TypeReference> interfaces;
	protected List<Member> members;
	
	public String toString(String prefix) {
		String str = prefix + "";
		
		if(name != null) {
			str += "\n" + prefix + " Name\n" + prefix + "  " + name;
		}
		
		if(formal_type_parameters != null) {
			str += "\n" + prefix + " Formal Type Parameters";
			for(TypeReference reference: formal_type_parameters)
				str += "\n" + reference.toString(prefix + "  ");
		}
		
		if(interfaces != null) {
			str += "\n" + prefix + " Interfaces";
			for(TypeReference reference: interfaces)
				str += "\n" + reference.toString(prefix + "  ");
		}
		
		if(members != null) {
			str += "\n" + prefix + " Members";
			for(Member member: members)
				str += "\n" + member.toString(prefix + "  ");
		}
		
		return str;
	}
	
	public List<TypeReference> getFormalTypeParameters() { return formal_type_parameters; }
	public List<TypeReference> getInterfaces() { return interfaces; }
	public List<Member> getMembers() { return members; }
	
	public BasicNode[] getChildren(){
		return members.toArray(new BasicNode[0]);
	}
}
