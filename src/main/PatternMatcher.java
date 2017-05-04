package main;

import java.util.HashMap;

import parser.*;
import parser.Class;

public class PatternMatcher implements Visitor {
	IBasicNode pattern;
	HashMap<String,String> variables_found;
	boolean match;
	
	public PatternMatcher(IBasicNode pattern) {
		this.pattern = pattern;
		this.variables_found = new HashMap<String,String>();
		this.match = true;
	}


	@Override
	public void visit(Root root) {
		// TODO Auto-generated method stub
		System.out.println("visit Root stub");
	}

	@Override
	public void visit(CompilationUnit cu) {
		// TODO Auto-generated method stub
		System.out.println("visit CompilationUnit stub");
	}

	@Override
	public void visit(Comment comment) {
		// TODO Auto-generated method stub
		System.out.println("visit Comment stub");
	}

	@Override
	public void visit(ArrayRead ar) {
		// TODO Auto-generated method stub
		System.out.println("visit ArrayRead stub");
	}

	@Override
	public void visit(Class c) {
		// TODO Auto-generated method stub
		System.out.println("visit Class stub");
	}

	@Override
	public void visit(Constructor constructor) {
		// TODO Auto-generated method stub
		System.out.println("visit Constructor stub");
	}

	@Override
	public void visit(Block block) {
		// TODO Auto-generated method stub
		System.out.println("visit Block stub");
	}

	@Override
	public void visit(Invocation invocation) {
		// TODO Auto-generated method stub
		System.out.println("visit Invocation stub");
	}

	@Override
	public void visit(ExecutableReference er) {
		// TODO Auto-generated method stub
		System.out.println("visit ExecutableReference stub");
	}

	@Override
	public void visit(Method method) {
		// TODO Auto-generated method stub
		System.out.println("visit Method stub");
	}

	@Override
	public void visit(Parameter parameter) {
		// TODO Auto-generated method stub
		System.out.println("visit Parameter stub");
	}

	@Override
	public void visit(ArrayTypeReference atr) {
		if(!(pattern instanceof ArrayTypeReference)){
			match = false;
			return;
		}
		
		ArrayTypeReference p = (ArrayTypeReference) pattern;
		pattern = p.getType();
		atr.getType().accept(this);
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
		System.out.println("visit Assignment stub");
	}

	@Override
	public void visit(VariableWrite vw) {
		// TODO Auto-generated method stub
		System.out.println("visit VariableWrite stub");
	}

	@Override
	public void visit(NullNode nn) {
		match = (pattern instanceof NullNode);
	}

	@Override
	public void visit(LocalVariable lv) {
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
		if(!(pattern instanceof TypeReference)){
			match = false;
			return;
		}
		
		TypeReference p = (TypeReference) pattern;
		if(tr.getName() != p.getName())
			match = false;
	}

	@Override
	public void visit(LocalVariableReference lvr) {
		if(!(pattern instanceof LocalVariableReference)){
			match = false;
			return;
		}
		
		LocalVariableReference p = (LocalVariableReference) pattern;
		//Check if variables are consistent
		if(this.variables_found.get(p.getName()) == null){
			this.variables_found.put(p.getName(), lvr.getName());
		}
		else{
			if(!this.variables_found.get(p.getName()).equals(lvr.getName())){
				match = false;
				return;
			}
		}
	}

	@Override
	public void visit(BinaryOperator bo) {
		if(!(pattern instanceof BinaryOperator)){
			match = false;
			return;
		}
		
		BinaryOperator p = (BinaryOperator) pattern;
		
		//Compare operator
		if(!p.getOperator().equals(bo.getOperator())){
			match = false;
			return;
		}
		
		//Compare Left hand side
		pattern = p.getLhs();
		bo.getLhs().accept(this);
		if(!match){
			return;
		}
		
		//Compare Left hand side
		pattern = p.getRhs();
		bo.getRhs().accept(this);
	}

	@Override
	public void visit(Literal literal) {
		if(pattern instanceof Literal){
			Literal p = (Literal) pattern;
			
			match = (p.getValue() == literal.getValue());
		}
		
		//TODO: Literal vs Variable Comparison
	}

	@Override
	public void visit(VariableRead vr) {
		if(!(pattern instanceof VariableRead)){
			match = false;
			return;
		}
		
		VariableRead p = (VariableRead) pattern;
		pattern = p.getVar();
		vr.getVar().accept(this);		
	}

	@Override
	public void visit(For f) {
		// TODO Auto-generated method stub
		System.out.println("visit For stub");
	}

	@Override
	public void visit(UnaryOperator uo) {
		// TODO Auto-generated method stub
		System.out.println("visit UnaryOperator stub");
	}

	@Override
	public void visit(NewArray na) {
		// TODO Auto-generated method stub
		System.out.println("visit NewArray stub");
	}

	@Override
	public void visit(ArrayWrite aw) {
		// TODO Auto-generated method stub
		System.out.println("visit ArrayWrite stub");
	}

	@Override
	public void visit(FieldRead fr) {
		// TODO Auto-generated method stub
		System.out.println("visit FieldRead stub");
	}

	@Override
	public void visit(TypeAccess ta) {
		// TODO Auto-generated method stub
		System.out.println("visit TypeAccess stub");
	}

	@Override
	public void visit(While w) {
		// TODO Auto-generated method stub
		System.out.println("visit While stub");
	}	

	public boolean isMatch() {
		System.out.println(this.variables_found.toString());
		return match;
	}
}
