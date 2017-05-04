package main;

import pack.MyNewGrammar;
import pack.SimpleNode;
import parser.Parser;
import parser.*;

public class PAT {
	
	public static void main(String args[]) {
		Parser parser = new Parser();
		
		Root root = parser.parse();
		
		/*MyNewGrammar.createjjt("if(x == null) { x = 3 }");
	
        SimpleNode n = MyNewGrammar.n;
        printChildren(n);*/
		
		//Testing FindPattern
		System.out.println("-----------------------------------------------");
		FindPattern find = new FindPattern();
		root.accept(find);
		
		//Testing PatternMatcher
        Member m = root.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
        Block b = (Block) ((Method) m).getBody();
        IStatement lv1 = b.getStatements().get(0);
        IStatement lv2 = b.getStatements().get(1);
        IStatement lv3 = b.getStatements().get(2);
        IStatement lv4 = b.getStatements().get(5);
        IStatement if1 = b.getStatements().get(3);
        IStatement for1 = b.getStatements().get(4);
        IStatement while1 = b.getStatements().get(6);
        System.out.println("-----------------------------------------------");
        System.out.println(for1.toString(""));
        //System.out.println(lv3.toString(""));
        
        PatternMatcher pm = new PatternMatcher(for1);
        for1.accept(pm);
        System.out.println(pm.isMatch());
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
