package main;

import pack.MyNewGrammar;
import parser.Parser;
import parser.Root;

public class PAT {
	
	public static void main(String args[]) {
		Parser parser = new Parser();
		
		Root root = parser.parse();
		
		MyNewGrammar.createJJTree("if(x == null) { x = 3 }");
	}
}
