package main;

import java.util.HashMap;

import parser.*;
import parser.Class;

public class PatternMatcher implements Visitor {
	IBasicNode pattern;
	HashMap<String,String> variables_found;
	boolean match = true;


	@Override
	public void visit(Root root) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(CompilationUnit cu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayRead ar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Class c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Constructor constructor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Block block) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Invocation invocation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ExecutableReference er) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Method method) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Parameter parameter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayTypeReference atr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(If i) {
		if(!(pattern instanceof If)){
			match = false;
			return;
		}
		
		//Compare condition
		If p = (If) pattern;
		pattern = p.getCondition();
		i.getCondition().accept(this);
		if(!match)
			return;
		
		//Compare then
		pattern = p.getThen();
		i.getThen().accept(this);
		if(!match)
			return;
		
		//Compare Else
		pattern = p.getElse();
		i.getElse().accept(this);
	}

	@Override
	public void visit(Assignment assignment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableWrite vw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NullNode nn) {
		match = (pattern instanceof NullNode);
	}

	@Override
	public void visit(LocalVariable lv) {
		// TODO Auto-generated method stub
		if(!(pattern instanceof LocalVariable)){
			match = false;
			return;
		}
		
		LocalVariable p = (LocalVariable) pattern;
		
		//Check if variables are consistent
		if(this.variables_found.get(p.getName()) == null){
			this.variables_found.put(p.getName(), lv.getName());
		}
		else{
			if(!this.variables_found.get(p.getName()).equals(lv.getName())){
				match = false;
				return;
			}
		}
		
		pattern = p.getType();
		lv.getType().accept(this);
		if(!match)
			return;
		
		pattern = p.getInit();
		lv.getInit().accept(this);
	}

	@Override
	public void visit(TypeReference tr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LocalVariableReference lvr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryOperator bo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Literal literal) {
		if(pattern instanceof Literal){
			Literal p = (Literal) pattern;
			
			match = (p.getValue() == literal.getValue());
		}
		
		//TODO: Literal Variable Comparison
	}

	@Override
	public void visit(VariableRead vr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(For f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UnaryOperator uo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NewArray na) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayWrite aw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FieldRead fr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypeAccess ta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(While w) {
		// TODO Auto-generated method stub

	}	

	public boolean isMatch() {
		return match;
	}
}
