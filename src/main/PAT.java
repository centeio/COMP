package main;

import pack.MyNewGrammar;
import pack.SimpleNode;
import parser.Parser;
import parser.Root;

public class PAT {
	
	public static void main(String args[]) {
		Parser parser = new Parser();
		
		Root root = parser.parse();
		
		MyNewGrammar.createjjt("if(x == null) { x = 3 }");
	
        SimpleNode n = MyNewGrammar.n;
        printChildren(n);
        
        Visitor v = new Visitor(n);
        
        v.findSubtree(root);
		
	}
	
	public static void printChildren(SimpleNode node){
		if(node.jjtGetNumChildren()==0){
			//System.out.println(node.type + " " + node.jjtGetValue());
			return;
		}
		
		int i = 0;

		while(i<node.jjtGetNumChildren()){
			printChildren((SimpleNode)node.jjtGetChild(i));
			i++;
		}
	}
}
