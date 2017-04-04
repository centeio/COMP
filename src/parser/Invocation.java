package parser;

public class Invocation extends Statement {
	private Reference executable;
	
	public String toString(String prefix) {
		String str = prefix + "Invocation";
		
		if(executable != null)
			str += "\n" + executable.toString(prefix + " ");
		
		return str;
	}
}
