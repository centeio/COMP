package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.*;
import parser.Class;

/**
 * Visitor that concludes if code matches a pattern
 */
public class PatternMatcher implements Visitor {
	
	/** The pattern to be matched */
	IBasicNode pattern;
	
	/** The pattern functions found. */
	HashMap<String,String> functions_found;
	
	/** The pattern variables found. */
	HashMap<String,IBasicNode> variables_found;
	
	/** if search is partial or exhaustive */
	boolean partial;
	
	/** whether it is a match. */
	boolean match;
	
	/**
	 * Instantiates a new pattern matcher.
	 *
	 * @param pattern the pattern to be matched
	 * @param partial if search is partial
	 */
	public PatternMatcher(IBasicNode pattern, boolean partial) {
		this(pattern, partial, new HashMap<String,IBasicNode>());
	}

	/**
	 * Instantiates a new pattern matcher.
	 *
	 * @param pattern the pattern to be matched
	 * @param partial if search is partial
	 * @param variables_found the variables found so far
	 */
	public PatternMatcher(IBasicNode pattern, boolean partial, HashMap<String,IBasicNode> variables_found) {
		this.pattern = pattern;
		this.functions_found = new HashMap<String,String>();
		this.variables_found = variables_found;
		this.partial = partial;
		this.match = true;
	}


	/* Check if pattern matches Root and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Root)
	 */
	@Override
	public void visit(Root root) {
		if(!(pattern instanceof Root)){
			match = false;
			return;
		}

		Root p = (Root) pattern;

		for (Iterator<CompilationUnit> root_iterator = root.getCompilationUnits().iterator(), pattern_iterator = p.getCompilationUnits().iterator(); root_iterator.hasNext() && pattern_iterator.hasNext();) {
			CompilationUnit cu = root_iterator.next();
			pattern = pattern_iterator.next();

			cu.accept(this);
		}
	}

	/* Check if pattern matches Compilation Unit and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.CompilationUnit)
	 */
	@Override
	public void visit(CompilationUnit cu) {
		if(!(pattern instanceof CompilationUnit)){
			match = false;
			return;
		}

		CompilationUnit p = (CompilationUnit) pattern;

		for (Iterator<Type> root_iterator = cu.getTypes().iterator(), pattern_iterator = p.getTypes().iterator(); root_iterator.hasNext() && pattern_iterator.hasNext();) {
			Type type = root_iterator.next();
			pattern = pattern_iterator.next();

			type.accept(this);
		}
	}

	/* Check if pattern matches Comment and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Comment)
	 */
	@Override
	public void visit(Comment comment) {
		if(!(pattern instanceof Comment)){
			match = false;
			return;
		}

		Comment p = (Comment) pattern;

		match = (p.getContent().equals(comment.getContent()));
	}

	/* Check if pattern matches Array Read and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.ArrayRead)
	 */
	@Override
	public void visit(ArrayRead ar) {
		if(!(pattern instanceof ArrayRead)){
			match = false;
			return;
		}

		ArrayRead p = (ArrayRead) pattern;

		//Compare Type
		if(!(ar.getType() instanceof NullNode || p.getType() instanceof NullNode)){ 
			pattern = p.getType();
			ar.getType().accept(this);
			if(!match)
				return;
		}

		//Compare Target
		pattern = p.getTarget();
		ar.getTarget().accept(this);
		if(!match)
			return;

		//Compare Index
		pattern = p.getIndex();
		ar.getIndex().accept(this);
	}

	/* Check if pattern matches Class and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Class)
	 */
	@Override
	public void visit(Class c) {
		// TODO visit Class
		System.out.println("visit Class stub");
	}

	/* Check if pattern matches Constructor and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Constructor)
	 */
	@Override
	public void visit(Constructor constructor) {
		if(!(pattern instanceof Constructor)){
			match = false;
			return;
		}

		Constructor p = (Constructor) pattern;

		if(constructor.getParameters() != null) {
			for (Iterator<Parameter> root_iterator = constructor.getParameters().iterator(), pattern_iterator = p.getParameters().iterator(); root_iterator.hasNext() && pattern_iterator.hasNext();) {
				Parameter parameter = root_iterator.next();
				pattern = pattern_iterator.next();

				parameter.accept(this);			
			}
		}

		if(constructor.getBody() != null) {
			pattern = p.getBody();
			constructor.getBody().accept(this);
		}
	}

	/* Check if pattern matches Block and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Block)
	 */
	@Override
	public void visit(Block block) {
		if(!(pattern instanceof Block)){
			match = false;
			return;
		}

		Block p = (Block) pattern;
		boolean ignore = partial;

		match = verifyBlock(block, p, 0, 0, ignore, variables_found);
	}

	/**
	 * Auxiliary function to verify if block matches pattern starting in statement i.
	 *
	 * @param block the code block 
	 * @param p the pattern 
	 * @param i the index of the block statement to start the verification
	 * @param j the index of the pattern statement to start the verification
	 * @param ignore if statement can be ignored
	 * @param var the variables found so far
	 * @return true, if block matches from i to end.
	 */
	private boolean verifyBlock(Block block, Block p, int i, int j, boolean ignore, HashMap<String, IBasicNode> var) {
		for (; i < block.getStatements().size() && j < p.getStatements().size(); i++) {
			pattern = p.getStatements().get(j);

			if(pattern instanceof Invocation && ((Invocation) pattern).getExecutable().getName().equals("ignore")){
				ignore = true;

				j++;
			}

			if(j < p.getStatements().size()){
				pattern = p.getStatements().get(j);
			}
			else{
				pattern = null;
			}

			HashMap<String, IBasicNode> var1 = new HashMap<String, IBasicNode>(var);
			PatternMatcher pm = new PatternMatcher(pattern, partial, var1);
			block.getStatements().get(i).accept(pm);
			boolean aux_match = pm.isMatch();

			if(aux_match){
				HashMap<String, IBasicNode> var2 = new HashMap<String, IBasicNode>(pm.getVariables_found());

				boolean ignore2 = ignore;
				if(!partial)
					ignore2 = false;

				if(verifyBlock(block, p, i+1, j+1, ignore2, var2)){
					if(!joinVars(var, var2)){
						if(!ignore){
							return false;
						}
					}
					else{
						var.putAll(var2);
						return true;
					}
				}else if(!ignore){
					return false;
				}
			}
		}
		if(j != p.getStatements().size()){
			IBasicNode tmp = p.getStatements().get(j);

			if(j == p.getStatements().size() - 1 && (tmp instanceof Invocation && ((Invocation) tmp).getExecutable().getName().equals("ignore"))){

				return true;
			}
			else{
				return false;
			}
		}
		return true;
	}


	/**
	 * Merge variables found if possible.
	 *
	 * @param var the current variables found
	 * @param var2 the new variables to be added
	 * @return true, if merge is possible and successful
	 */
	private boolean joinVars(HashMap<String, IBasicNode> var, HashMap<String, IBasicNode> var2) {
		Iterator<Entry<String, IBasicNode>> it = var2.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, IBasicNode> pair = (Entry<String, IBasicNode>)it.next();
			if(var.containsKey(pair.getKey())){
				if(!var.get(pair.getKey()).equals(pair.getValue())){
					return false;
				}
			}
		}

		return true;
	}


	/* Check if pattern matches Invocation and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Invocation)
	 */
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
			if(!match){
				return;
			}
		}
	}

	/* Check if pattern matches Executable Reference and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.ExecutableReference)
	 */
	@Override
	public void visit(ExecutableReference er) {
		if(!(pattern instanceof ExecutableReference)){
			match = false;
			return;
		}

		ExecutableReference p = (ExecutableReference) pattern;

		//Compare name
		Pattern pat = Pattern.compile("a\\d*");
		Matcher m = pat.matcher(p.getName());

		if(m.matches()){

			if(functions_found.get(p.getName()) == null){
				functions_found.put(p.getName(),er.getName());
			}else if(!functions_found.get(p.getName()).equals(er.getName())){
				match = false;
				return;
			}		
		}else if(!p.getName().equals(er.getName())){
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

			if(!match){
				return;
			}
		}
	}

	/* Check if pattern matches Method and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Method)
	 */
	@Override
	public void visit(Method method) {
		if(!(pattern instanceof Method)){
			match = false;
			return;
		}

		Method p = (Method) pattern;

		if(method.getName() != null)
			match = method.getName().equals(p.getName());

		if(method.getType() != null) {
			pattern = p.getType();
			method.getType().accept(this);
		}


		if(method.getParameters() != null) {
			for (Iterator<Parameter> root_iterator = method.getParameters().iterator(), pattern_iterator = p.getParameters().iterator(); root_iterator.hasNext() && pattern_iterator.hasNext();) {
				Parameter parameter = root_iterator.next();
				pattern = pattern_iterator.next();

				parameter.accept(this);			
			}
		}

		if(method.getBody() != null) {
			pattern = p.getBody();
			method.getBody().accept(this);
		}
	}

	/* Check if pattern matches parameter and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Parameter)
	 */
	@Override
	public void visit(Parameter parameter) {
		if(!(pattern instanceof Parameter)){
			match = false;
			return;
		}

		Parameter p = (Parameter) pattern;

		//Compare name
		if(p.getName() != null && !p.getName().equals("null") && !p.getName().equals(parameter.getName())){
			match = false;
			return;
		}
	}

	/* Check if pattern matches Array Type Reference and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.ArrayTypeReference)
	 */
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

	/* Check if pattern matches If and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.If)
	 */
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

	/* Check if pattern matches assignment and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Assignment)
	 */
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

	/* Check if pattern matches Variable Write and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.VariableWrite)
	 */
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

	/* Check if pattern matches Null node
	 * @see main.Visitor#visit(parser.NullNode)
	 */
	@Override
	public void visit(NullNode nn) {
		match = (pattern instanceof NullNode);
	}

	/* Check if pattern matches Local Variable and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.LocalVariable)
	 */
	@Override
	public void visit(LocalVariable lv) {
		if(!(pattern instanceof LocalVariable)){
			match = false;
			return;
		}

		LocalVariable p = (LocalVariable) pattern;

		checkVariableConsistency(lv, p.getName());
		if(!match)
			return;

		pattern = p.getType();
		lv.getType().accept(this);
		if(!match)
			return;

		pattern = p.getInit();
		lv.getInit().accept(this);
	}

	/* Check if pattern matches Type Reference and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.TypeReference)
	 */
	@Override
	public void visit(TypeReference tr) {
		if(!(pattern instanceof TypeReference)){
			match = false;
			return;
		}

		TypeReference p = (TypeReference) pattern;

		if(p.getName() != null && !tr.getName().equals(p.getName()))
			match = false;
	}

	/* Check if pattern matches Local Variable Reference and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.LocalVariableReference)
	 */
	@Override
	public void visit(LocalVariableReference lvr) {
		if(!(pattern instanceof LocalVariableReference || pattern instanceof FieldReference)){
			match = false;
			return;
		}

		Reference p = (Reference) pattern;

		checkVariableConsistency(lvr, p.getName());
	}

	/* Check if pattern matches Binary Operator and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.BinaryOperator)
	 */
	@Override
	public void visit(BinaryOperator bo) {
		if(!(pattern instanceof BinaryOperator)){
			if(pattern instanceof FieldRead){
				FieldRead p = (FieldRead) pattern;

				checkVariableConsistency(bo, p.getVar().getName());

				return;
			}
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

	/* Check if pattern matches literal and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Literal)
	 */
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

			checkVariableConsistency(literal, p.getName());
		}
		else{
			match = false;
		}
	}

	/* Check if pattern matches Variable Read and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.VariableRead)
	 */
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

	/* Check if pattern matches for loop and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.For)
	 */
	@Override
	public void visit(For f) {
		if(!(pattern instanceof For)){
			match = false;
			return;
		}

		For p = (For) pattern;

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

	/* Check if pattern matches Unary Operator and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.UnaryOperator)
	 */
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

	/* Check if pattern matches New Array and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.NewArray)
	 */
	@Override
	public void visit(NewArray na) {
		if(!(pattern instanceof NewArray)){
			match = false;
			return;
		}

		NewArray p = (NewArray) pattern;

		//Compare type
		pattern = p.getType();
		na.getType().accept(this);
		if(!match)
			return;

		//Compare dimensions
		if(p.getDimensions() != null && na.getDimensions() != null) {
			if(p.getDimensions().size() != na.getDimensions().size()){
				match = false;
				return;
			}
			Iterator<IExpression> na_iterator = na.getDimensions().iterator();
			for (Iterator<IExpression> pattern_iterator = p.getDimensions().iterator(); pattern_iterator.hasNext() && na_iterator.hasNext();) {
				pattern = pattern_iterator.next();
				na_iterator.next().accept(this);

				if(!match)
					return;
			}
		}

		//Compare Elements
		else if(p.getElements() != null && na.getElements() != null){
			if(p.getElements().size() != na.getElements().size()){
				match = false;
				return;
			}
			Iterator<IExpression> na_iterator = na.getElements().iterator();
			for (Iterator<IExpression> pattern_iterator = p.getElements().iterator(); pattern_iterator.hasNext() && na_iterator.hasNext();) {
				pattern = pattern_iterator.next();
				na_iterator.next().accept(this);

				if(!match)
					return;
			}
		}
		//One has elements and the other dimensions
		else{
			match = false;
		}
	}

	/* Check if pattern matches Array Write and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.ArrayWrite)
	 */
	@Override
	public void visit(ArrayWrite aw) {
		if(!(pattern instanceof ArrayWrite)){
			if(pattern instanceof FieldWrite){
				FieldWrite p = (FieldWrite) pattern;

				checkVariableConsistency(aw, p.getVar().getName());

				return;
			}
			
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

	/* Check if pattern matches Field Read and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.FieldRead)
	 */
	@Override
	public void visit(FieldRead fr) {
		if(!(pattern instanceof FieldRead)){
			match = false;
			return;
		}

		// TODO Compared only children. Possibly compare string values?

		FieldRead p = (FieldRead) pattern;

		//Compare type
		pattern = p.getType();
		if(!(pattern instanceof NullNode || fr.getType() == null)){
			fr.getType().accept(this);
			if(!match)
				return;
		}

		//Compare target
		if(p.getTarget() != null && fr.getTarget() != null){
			pattern = p.getTarget();
			fr.getTarget().accept(this);
			if(!match)
				return;
		}

		//Compare index
		pattern = p.getVar();
		fr.getVar().accept(this);
	}

	/* Check if pattern matches Type Access and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.TypeAccess)
	 */
	@Override
	public void visit(TypeAccess ta) {
		if(!(pattern instanceof TypeAccess)){
			match = false;
			return;
		}

		// TODO Compared only children. Possibly compare string values?

		TypeAccess p = (TypeAccess) pattern;

		//Compare type
		pattern = p.getType();
		if(!(pattern instanceof NullNode)){
			ta.getType().accept(this);
			if(!match)
				return;
		}

		//Compare target
		pattern = p.getTarget();
		ta.getTarget().accept(this);
		if(!match)
			return;
	}

	/* Check if pattern matches while loop and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.While)
	 */
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

	/* Check if pattern matches Field Reference and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.FieldReference)
	 */
	@Override
	public void visit(FieldReference fr) {
		if(!(pattern instanceof FieldReference)){
			match = false;
			return;
		}

		// TODO Compared only children. Possibly compare string values?

		FieldReference p = (FieldReference) pattern;

		//Compare type
		pattern = p.getType();
		if(!(pattern instanceof NullNode)){
			fr.getType().accept(this);
			if(!match)
				return;
		}

		//Compare target
		pattern = p.getDeclarator();
		if(!(pattern instanceof NullNode)){
			fr.getType().accept(this);
			if(!match)
				return;
		}
	}

	/* Check if pattern matches Field Write and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.FieldWrite)
	 */
	@Override
	public void visit(FieldWrite fw) {
		if(!(pattern instanceof FieldWrite)){
			match = false;
			return;
		}

		// TODO Compared only children. Possibly compare string values?

		FieldWrite p = (FieldWrite) pattern;

		//Compare type
		pattern = p.getType();
		if(!(pattern instanceof NullNode)){
			fw.getType().accept(this);
			if(!match)
				return;
		}

		//Compare target
		pattern = p.getVar();
		fw.getVar().accept(this);
		if(!match)
			return;

	}

	/* Check if pattern matches do while loop and all it's relevant children nodes
	 * @see main.Visitor#visit(parser.Do)
	 */
	@Override
	public void visit(Do do1) {
		if(!(pattern instanceof Do)){
			match = false;
			return;
		}

		Do p = (Do) pattern;

		//Compare condition
		pattern = p.getCondition();
		do1.getCondition().accept(this);
		if(!match)
			return;

		//Compare body
		pattern = p.getBody();
		do1.getBody().accept(this);
	}

	/**
	 * Check for variable consistency.
	 *
	 * @param code the code which may match variable
	 * @param pattern_name the pattern's variable name
	 */
	private void checkVariableConsistency(IBasicNode code, String pattern_name) {
		//Compare name
		Pattern pat = Pattern.compile("a\\d*");
		Matcher m = pat.matcher(pattern_name);
		
		if(m.matches()){
		if(!variables_found.containsKey(pattern_name)){
			variables_found.put(pattern_name, code);
		}
		else{
			if(!variables_found.get(pattern_name).equals(code)){
				match = false;
				return;
			}
		}
		}
		else{
			if(code instanceof Reference && ((Reference) code).getName().equals(pattern_name)){
				match = true;
				return;
			}else if(code instanceof LocalVariable && ((LocalVariable) code).getName().equals(pattern_name)){
				match = true;
				return;
			}
		}
	}

	/**
	 * Compare the pattern with the code. Exactily the same as instanciating a new PatternMatcher and making the node accept it
	 *
	 * @param code the code
	 * @param pattern the pattern
	 * @return true, if they match
	 */
	public boolean compare(IBasicNode code, IBasicNode pattern){
		return compare(code,pattern, false);
	}

	public boolean compare(IBasicNode code, IBasicNode pattern, boolean debug){
		this.match = true;
		this.pattern = pattern;
		variables_found.clear();
		functions_found.clear();

		code.accept(this);

		if(debug){
			System.out.println("Var found:");
			System.out.println(this.variables_found.toString());

			System.out.println("Functions found:");
			System.out.println(this.functions_found.toString());

			if(!match){
				System.out.println(this.pattern.getClass().getSimpleName());
			}
		}

		return match;
	}

	/**
	 * Checks if is a match.
	 *
	 * @return true, if the code matches the pattern
	 */
	public boolean isMatch() {
		return match;
	}


	/**
	 * Gets the functions found.
	 *
	 * @return the functions found
	 */
	public HashMap<String, String> getFunctions_found() {
		return functions_found;
	}


	/**
	 * Gets the variables found.
	 *
	 * @return the variables found
	 */
	public HashMap<String, IBasicNode> getVariables_found() {
		return variables_found;
	}


	/**
	 * Gets the class name where pattern failed (for debug mostly).
	 *
	 * @return the class name where pattern failed
	 */
	public String getPatternClassName() {
		return pattern.getClass().getSimpleName();
	}

}
