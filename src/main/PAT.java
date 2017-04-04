package main;

import pack.MyNewGrammar;
import pack.Node;
import pack.SimpleNode;
import parser.Parser;

public class PAT {
	
	public static void main(String args[]) {
		Parser parser = new Parser();
		
		parser.parse();
		MyNewGrammar.createjjt("if(x==null){x=3}");
		
		SimpleNode n = MyNewGrammar.n;
		printChildren(n);
		
	}
	
	public static void printChildren(SimpleNode node){
		if(node.jjtGetNumChildren()==0){
			System.out.println(node.type + " " + node.jjtGetValue());
			return;
		}
		
		int i = 0;

		while(i<node.jjtGetNumChildren()){
			printChildren((SimpleNode)node.jjtGetChild(i));
			i++;
		}
	}
}
