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
		if(!parserCond.getOperator().equals(((SimpleNode) searchCond.jjtGetChild(1)).jjtGetValue().toString()))
			return false;
		
		if(!equal((Expression) parserCond.getLHS(), (ASTExpression) searchCond.jjtGetChild(0)))
			return false;
		
		if(!equal((Expression) parserCond.getRHS(), (ASTExpression) searchCond.jjtGetChild(2)))
			return false;
		
		return true;
	}
	
	public boolean equal(Expression parserExpression, ASTExpression searchExpression){
		SimpleNode child = (SimpleNode) searchExpression.jjtGetChild(0);
		if(parserExpression instanceof VariableRead && child instanceof ASTVar){
			System.out.println("-----> Comparing var");
		}
		
		if(parserExpression instanceof Literal && child instanceof ASTVar){
			System.out.println("-----> Comparing var");
		}
	}
	
	public boolean equal(Statement parserThen, ASTOperation searchThen){
		System.out.println("	If Then Compare");
		return true;
	}
	
	public boolean equal(If parserNode, ASTIf searchNode){
		
		//Compare condition
		ASTCondition cond1 = (ASTCondition) searchNode.jjtGetChild(0);
		BinaryOperator cond2 = (BinaryOperator) parserNode.getCondition();
		
		if(!equal(cond2, cond1))
			return false;
		
		//Compare then
		ASTOperation then1 = (ASTOperation) searchNode.jjtGetChild(1);
		Statement then2 = (Statement) parserNode.getThen();
		
		//Compare else (if it exists)

		return equal(then2, then1);
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
