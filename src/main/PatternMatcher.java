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
		// TODO visit Root
		System.out.println("visit Root stub");
	}

	@Override
	public void visit(CompilationUnit cu) {
		// TODO visit CompilationUnit
		System.out.println("visit CompilationUnit stub");
	}

	@Override
	public void visit(Comment comment) {
		// TODO visit Comment
		System.out.println("visit Comment stub");
	}

	@Override
	public void visit(ArrayRead ar) {
		if(!(pattern instanceof ArrayRead)){
			match = false;
			return;
		}

		ArrayRead p = (ArrayRead) pattern;

		//Compare Type
		pattern = p.getType();
		ar.getType().accept(this);
		if(!match)
			return;

		//Compare Target
		pattern = p.getTarget();
		ar.getTarget().accept(this);
		if(!match)
			return;

		//Compare Index
		pattern = p.getIndex();
		ar.getIndex().accept(this);	
	}

	@Override
	public void visit(Class c) {
		// TODO visit Class
		System.out.println("visit Class stub");
	}

	@Override
	public void visit(Constructor constructor) {
		// TODO visit Constructor
		System.out.println("visit Constructor stub");
	}

	@Override
	public void visit(Block block) {
		if(!(pattern instanceof Block)){
			match = false;
			return;
		}

		Block p = (Block) pattern;
		int j = 0;
		boolean ignore = false;
		
		for (int i = 0; i < block.getStatements().size(); i++) {
			pattern = p.getStatements().get(j);
			
			if(pattern instanceof Invocation && ((Invocation) pattern).getExecutable().getName().equals("ignore")){
				//TODO: implement ignore (now it just doesn't compare)
				ignore = true;
				
				j++;
			}
			else{
				IStatement statement = block.getStatements().get(i);
	
				statement.accept(this);
				
				if(!match && ignore){
					match = true;
				}
				else if(!match && !ignore){
					break;
				}
				else if(match){
					ignore = false;
					j++;
				}
			}
		}
	}

	@Override
	public void visit(Invocation invocation) {
		if(!(pattern instanceof Invocation)){
			match = false;
			return;
		}

		Invocation p = (Invocation) pattern;

		//Compare Executable
		pattern = p.getExecutable();
		invocation.getExecutable().accept(this);
		if(!match)
			return;

		//Compare Arguments
		if(p.getArguments().size() != invocation.getArguments().size()){
			match = false;
			return;
		}

		for(int i = 0; i < p.getArguments().size(); i++){
			pattern = p.getArguments().get(i);
			invocation.getArguments().get(i).accept(this);
			if(!match)
				return;
		}
	}

	@Override
	public void visit(ExecutableReference er) {
		if(!(pattern instanceof ExecutableReference)){
			match = false;
			return;
		}

		ExecutableReference p = (ExecutableReference) pattern;

		//Compare name
		if(!p.getName().equals(er.getName())){
			match = false;
			return;
		}

		//Compare declarator
		pattern = p.getDeclarator();
		er.getDeclarator().accept(this);
		if(!match)
			return;

		//Compare type
		pattern = p.getType();
		er.getType().accept(this);
		if(!match)
			return;

		//Compare Parameters
		if(p.getParameters().size() != er.getParameters().size()){
			match = false;
			return;
		}

		for(int i = 0; i < p.getParameters().size(); i++){
			pattern = p.getParameters().get(i);
			er.getParameters().get(i).accept(this);
			if(!match)
				return;
		}
	}

	@Override
	public void visit(Method method) {
		// TODO visit Method
		System.out.println("visit Method stub");
	}

	@Override
	public void visit(Parameter parameter) {
		if(!(pattern instanceof Parameter)){
			match = false;
			return;
		}

		Parameter p = (Parameter) pattern;

		//Compare name
		if(!p.getName().equals(parameter.getName())){
			match = false;
			return;
		}
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
		if(!(pattern instanceof Assignment)){
			match = false;
			return;
		}

		Assignment p = (Assignment) pattern;
		pattern = p.getType();
		assignment.getType().accept(this);
		if(!match)
			return;
		
		pattern = p.getLhs();
		assignment.getLhs().accept(this);
		if(!match)
			return;

		pattern = p.getRhs();
		assignment.getRhs().accept(this);	
	}

	@Override
	public void visit(VariableWrite vw) {
		if(!(pattern instanceof VariableWrite || pattern instanceof FieldWrite)){
			match = false;
			return;
		}

		if(pattern instanceof VariableWrite){
			VariableWrite p = (VariableWrite) pattern;
			pattern = p.getVar();
			vw.getVar().accept(this);
		}
		else{
			FieldWrite p = (FieldWrite) pattern;
			pattern = p.getVar();
			vw.getVar().accept(this);
		}
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
		
		if(!tr.getName().equals(p.getName()) && p.getName() != null)
			match = false;
	}

	@Override
	public void visit(LocalVariableReference lvr) {
		if(!(pattern instanceof LocalVariableReference || pattern instanceof FieldReference)){
			match = false;
			return;
		}

		Reference p = (Reference) pattern;
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
		if(pattern instanceof FieldRead){

			pattern = ((FieldRead) pattern).getVar();
		}
		
		if(pattern instanceof Literal){
			
			Literal p = (Literal) pattern;

			match = (p.getValue().equals(literal.getValue()));
		}else if(pattern instanceof Reference){

			Reference p = (Reference) pattern;
			//Check if variables are consistent
			if(this.variables_found.get(p.getName()) == null){
				this.variables_found.put(p.getName(), literal.getValue());
			}
			else{
				if(!this.variables_found.get(p.getName()).equals(literal.getValue())){
					match = false;
					return;
				}
			}
		}
		else{
			match = false;
		}
	}

	@Override
	public void visit(VariableRead vr) {
		if(!(pattern instanceof VariableRead || pattern instanceof FieldRead)){
			match = false;
			return;
		}
		if(pattern instanceof VariableRead){	
			VariableRead p = (VariableRead) pattern;
			pattern = p.getVar();
			vr.getVar().accept(this);	
		}else{
			FieldRead p = (FieldRead) pattern;
			pattern = p.getVar();
			vr.getVar().accept(this);
		}

	}

	@Override
	public void visit(For f) {
		if(!(pattern instanceof For)){
			match = false;
			return;
		}

		For p = (For) pattern;

		//TODO: Exhaustive search done only
		//Compare init
		if(p.getInit().size() != f.getInit().size()){
			match = false;
			return;
		}

		for (int i = 0; i < p.getInit().size(); i++) {
			pattern = p.getInit().get(i);
			
			f.getInit().get(i).accept(this);

			if(!match)
				return;
		}

		//Compare condition
		pattern = p.getCondition();
		f.getCondition().accept(this);

		if(!match)
			return;

		//Compare update
		if(p.getUpdate().size() != f.getUpdate().size()){
			match = false;
			return;
		}

		for (int i = 0; i < p.getUpdate().size(); i++) {
			pattern = p.getUpdate().get(i);
			f.getUpdate().get(i).accept(this);

			if(!match)
				return;
		}

		//Compare
		pattern = p.getBody();
		f.getBody().accept(this);
	}

	@Override
	public void visit(UnaryOperator uo) {
		if(!(pattern instanceof UnaryOperator)){
			match = false;
			return;
		}

		UnaryOperator p = (UnaryOperator) pattern;

		if(!p.getOperator().equals(uo.getOperator())){
			match = false;
			return;
		}

		pattern = p.getOperand();
		uo.getOperand().accept(this);
	}

	@Override
	public void visit(NewArray na) {
		// TODO visit NewArray
		System.out.println("visit NewArray stub");
	}

	@Override
	public void visit(ArrayWrite aw) {
		if(!(pattern instanceof ArrayWrite)){
			match = false;
			return;
		}

		ArrayWrite p = (ArrayWrite) pattern;

		//Compare type
		pattern = p.getType();
		if(!(pattern instanceof NullNode)){
			aw.getType().accept(this);
			if(!match)
				return;
		}

		//Compare target
		pattern = p.getTarget();
		aw.getTarget().accept(this);
		if(!match)
			return;

		//Compare index
		pattern = p.getIndex();
		aw.getIndex().accept(this);
	}

	@Override
	public void visit(FieldRead fr) {
		// TODO visit FieldRead
		System.out.println("visit FieldRead stub");
	}

	@Override
	public void visit(TypeAccess ta) {
		// TODO visit TypeAccess
		System.out.println("visit TypeAccess stub");
	}

	@Override
	public void visit(While w) {
		if(!(pattern instanceof While)){
			match = false;
			return;
		}

		While p = (While) pattern;

		//Compare condition
		pattern = p.getCondition();
		w.getCondition().accept(this);

		if(!match)
			return;

		//Compare body
		pattern = p.getBody();
		w.getBody().accept(this);
	}	
	
	@Override
	public void visit(FieldReference fr) {
		// TODO visit FieldReference
		System.out.println("visit FieldReference stub");
	}

	@Override
	public void visit(FieldWrite fw) {
		// TODO vistit FieldWrite
		System.out.println("visit FieldWrite stub");
	}

	public boolean isMatch() {
		return match;
	}
	
	public boolean compare(IBasicNode code, IBasicNode pattern){
		this.match = true;
		this.pattern = pattern;
		variables_found.clear();
		
		code.accept(this);

		System.out.println(this.variables_found.toString());
		
		//TODO This is for debug purposes
		if(!match){
			System.out.println(this.pattern.getClass().getName());
		}
		return match;
	}
}
