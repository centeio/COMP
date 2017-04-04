package main;

import pack.MyNewGrammar;
import parser.Parser;
import parser.Root;

public class PAT {
	
	public static void main(String args[]) {
		Parser parser = new Parser();
		
		parser.parse();
		
		Root root = parser.getRoot();
		
		MyNewGrammar.createJJTree("if(x == null) { x = 3 }");
	}
}
