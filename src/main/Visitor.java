package main;

import java.util.ArrayList;

import pack.*;
import parser.*;

public class Visitor {
	ArrayList<SimpleNode> patterns;
	
	public Visitor(SimpleNode root) {
		super();
		
		//Ignore Start node
		patterns = new ArrayList<SimpleNode>();
		for(int i = 0; i < root.jjtGetNumChildren(); i++){
			this.patterns.add((SimpleNode) root.jjtGetChild(i));
		}
	}
	
	public boolean equal(BinaryOperator parserCond, ASTCondition searchCond){
		/*parserCond.getLHS();		searchCond.jjtGetChild(0);
		parserCond.getOperator();	searchCond.jjtGetChild(1);
		parserCond.getRHS();		searchCond.jjtGetChild(2);*/
		
		System.out.println("	If Condition Compare");
		if(parserCond.getOperator() == searchCond.jjtGetChild(1).toString())
			return true;
		
		return false;
	}
	
	public boolean equal(If parserNode, ASTIf searchNode){
		System.out.println("If Compare");
		
		ASTCondition cond = (ASTCondition) searchNode.jjtGetChild(0);
		BinaryOperator op = (BinaryOperator) parserNode.getCondition();

		return equal(op, cond);
	}
	
	public boolean equal(BasicNode parserNode, SimpleNode searchNode){
		if(parserNode instanceof If && searchNode instanceof ASTIf){
			If pn = (If) parserNode;
			ASTIf sn = (ASTIf) searchNode;
			
			return equal(pn,sn);
		}
		else{
			System.out.println("Other Compare");
			return false;
		}
	}
	
	public boolean findSubtree(BasicNode parserNode){
		for(int i = 0; i < patterns.size(); i++){
			if(!equal(parserNode, patterns.get(i))){
				BasicNode children[] = parserNode.getChildren();
				for(int j = 0; j < children.length; j++)
					findSubtree(children[j]);
			}
		}

		
		return false;
	}
}
