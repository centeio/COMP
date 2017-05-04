package main;

import java.util.HashMap;
import java.util.List;

import parser.ArrayRead;
import parser.ArrayTypeReference;
import parser.ArrayWrite;
import parser.Assignment;
import parser.BinaryOperator;
import parser.Block;
import parser.Class;
import parser.Comment;
import parser.CompilationUnit;
import parser.Constructor;
import parser.ExecutableReference;
import parser.FieldRead;
import parser.For;
import parser.If;
import parser.Invocation;
import parser.Literal;
import parser.LocalVariable;
import parser.LocalVariableReference;
import parser.Method;
import parser.NewArray;
import parser.NullNode;
import parser.Parameter;
import parser.Root;
import parser.TypeAccess;
import parser.TypeReference;
import parser.UnaryOperator;
import parser.VariableRead;
import parser.VariableWrite;
import parser.While;
import parser.IBasicNode;

public class FindPattern implements Visitor {
	List<IBasicNode> patternsFound;
	HashMap<String, List<IBasicNode>> patternsToFind;
	
	public FindPattern(List<IBasicNode> patternsFound, HashMap<String, List<IBasicNode>> patternsToFind) {
		this.patternsFound = patternsFound;
		this.patternsToFind = patternsToFind;
	}

	@Override
	public void visit(Root root) {
		findPatterns(root);
	}

	@Override
	public void visit(CompilationUnit cu) {
		findPatterns(cu);
	}

	@Override
	public void visit(Comment comment) {
		findPatterns(comment);	
	}

	@Override
	public void visit(ArrayRead ar) {
		findPatterns(ar);
	}

	@Override
	public void visit(Class c) {
		findPatterns(c);		
	}

	@Override
	public void visit(Constructor constructor) {
		findPatterns(constructor);
	}

	@Override
	public void visit(Block block) {
		findPatterns(block);		
	}

	@Override
	public void visit(Invocation invocation) {
		findPatterns(invocation);		
	}

	@Override
	public void visit(ExecutableReference er) {
		findPatterns(er);
	}

	@Override
	public void visit(Method method) {
		findPatterns(method);	
	}

	@Override
	public void visit(Parameter parameter) {
		findPatterns(parameter);		
	}

	@Override
	public void visit(ArrayTypeReference atr) {
		findPatterns(atr);
	}

	@Override
	public void visit(If i) {
		findPatterns(i);		
	}

	@Override
	public void visit(Assignment assignment) {
		findPatterns(assignment);
	}

	@Override
	public void visit(VariableWrite vw) {
		findPatterns(vw);
	}

	@Override
	public void visit(NullNode nn) {
		findPatterns(nn);	
	}

	@Override
	public void visit(LocalVariable lv) {
		findPatterns(lv);		
	}

	@Override
	public void visit(TypeReference tr) {
		findPatterns(tr);		
	}

	@Override
	public void visit(LocalVariableReference lvr) {
		findPatterns(lvr);	
	}

	@Override
	public void visit(BinaryOperator bo) {
		findPatterns(bo);		
	}

	@Override
	public void visit(Literal literal) {
		findPatterns(literal);	
	}

	@Override
	public void visit(VariableRead vr) {
		findPatterns(vr);			
	}

	@Override
	public void visit(For f) {
		findPatterns(f);	
	}

	@Override
	public void visit(UnaryOperator uo) {
		findPatterns(uo);			
	}

	@Override
	public void visit(NewArray na) {
		findPatterns(na);			
	}

	@Override
	public void visit(ArrayWrite aw) {
		findPatterns(aw);		
	}

	@Override
	public void visit(FieldRead fr) {
		findPatterns(fr);			
	}

	@Override
	public void visit(TypeAccess ta) {
		findPatterns(ta);	
	}

	@Override
	public void visit(While w) {
		findPatterns(w);
	}
	
	public void findPatterns(IBasicNode node) {
		if(patternsToFind.containsKey(node.getNodeType())) {
			List<IBasicNode> patterns = patternsToFind.get(node.getNodeType());
			
			for(int i = 0; i < patterns.size(); i++) {
				PatternMatcher matcher = new PatternMatcher(patterns.get(i));
				
				node.accept(matcher);				
				
				if(matcher.isMatch())
					patternsFound.add(node);
			}
		}	
	}

}
