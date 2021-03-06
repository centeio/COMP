package main;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
import parser.Do;
import parser.ExecutableReference;
import parser.FieldRead;
import parser.FieldReference;
import parser.FieldWrite;
import parser.For;
import parser.If;
import parser.Invocation;
import parser.Literal;
import parser.LocalVariable;
import parser.LocalVariableReference;
import parser.Member;
import parser.Method;
import parser.NewArray;
import parser.NullNode;
import parser.Parameter;
import parser.Reference;
import parser.Root;
import parser.Type;
import parser.TypeAccess;
import parser.TypeReference;
import parser.UnaryOperator;
import parser.VariableRead;
import parser.VariableWrite;
import parser.While;
import workers.Worker;
import parser.IBasicNode;
import parser.IExpression;
import parser.IStatement;

/**
 * Finds patterns in code
 */
public class FindPattern implements Visitor {
	
	/** The patterns found. */
	CopyOnWriteArrayList<IBasicNode> patternsFound;
	
	/** The patterns to find. */
	HashMap<String, List<Pattern>> patternsToFind;
	
	/** The executor. */
	ExecutorService executor;
	
	
	/**
	 * Instantiates a new instance of the pattern finder visitor
	 *
	 * @param patternsToFind the patterns to find
	 */
	public FindPattern(HashMap<String, List<Pattern>> patternsToFind) {
		this.patternsFound = new CopyOnWriteArrayList<IBasicNode>();
		this.patternsToFind = patternsToFind;
		executor = Executors.newFixedThreadPool(10);
	}

	/* Tries to find pattern in Root and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Root)
	 */
	@Override
	public void visit(Root root) {
		//System.out.println("[DEBUG] FindPattern: Root");
		findPatterns(root);
		
		if(root.getCompilationUnits() != null) {
			List<CompilationUnit> cp = root.getCompilationUnits();
			
			for(int i = 0; i < cp.size(); i++)
				cp.get(i).accept(this);
		}
	}

	/* Tries to find pattern in compilation unit and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.CompilationUnit)
	 */
	@Override
	public void visit(CompilationUnit cu) {
		//System.out.println("[DEBUG] FindPattern: CompilationUnit");
		findPatterns(cu);
		
		if(cu.getTypes() != null) {
		
			List<Type> types = cu.getTypes();
			
			for(int i = 0; i < types.size(); i++)
				types.get(i).accept(this);
		}
	}

	/* Tries to find pattern in comment
	 * @see main.Visitor#visit(parser.Comment)
	 */
	@Override
	public void visit(Comment comment) {
		//System.out.println("[DEBUG] FindPattern: Comment");
		findPatterns(comment);
	}

	/* Tries to find pattern in array read and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.ArrayRead)
	 */
	@Override
	public void visit(ArrayRead ar) {
		//System.out.println("[DEBUG] FindPattern: ArrayRead");
		findPatterns(ar);
		
		if(ar.getTarget() != null)
			ar.getTarget().accept(this);
		
		if(ar.getIndex() != null)
			ar.getIndex().accept(this);
	}

	/* Tries to find pattern in class and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Class)
	 */
	@Override
	public void visit(Class c) {
		//System.out.println("[DEBUG] FindPattern: Class");
		findPatterns(c);
		
		if(c.getSuperClass() != null)
			c.getSuperClass().accept(this);
		
		if(c.getFormalTypeParameters() != null) {
			List<TypeReference> typeparameters = c.getFormalTypeParameters();
			
			for(int i = 0; i < typeparameters.size(); i++)
				typeparameters.get(i).accept(this);
		}
		
		if(c.getInterfaces() != null) {
			List<TypeReference> interfces = c.getInterfaces();
			
			for(int i = 0; i < interfces.size(); i++)
				interfces.get(i).accept(this);
		}
		
		if(c.getMembers() != null) {
			List<Member> members = c.getMembers();
			
			for(int i = 0; i < members.size(); i++)
				members.get(i).accept(this);
		}
		
	}

	/* Tries to find pattern in constructor and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Constructor)
	 */
	@Override
	public void visit(Constructor constructor) {
		//System.out.println("[DEBUG] FindPattern: Constructor");
		findPatterns(constructor);
		
		if(constructor.getParameters() != null) {
			List<Parameter> parameters = constructor.getParameters();
			
			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}
		
		if(constructor.getBody() != null)
			constructor.getBody().accept(this);
	}

	/* Tries to find pattern in block and propagates the search to it's statements
	 * @see main.Visitor#visit(parser.Block)
	 */
	@Override
	public void visit(Block block) {
		//System.out.println("[DEBUG] FindPattern: Block");
		findPatterns(block);
		
		List<IStatement> statements = block.getStatements();
		
		for(int i = 0; i < statements.size(); i++)
			statements.get(i).accept(this);
	}

	/* Tries to find pattern in invocation and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Invocation)
	 */
	@Override
	public void visit(Invocation invocation) {
		//System.out.println("[DEBUG] FindPattern: Invocation");
		findPatterns(invocation);
		
		if(invocation.getArguments() != null) {		
			List<IExpression> arguments = invocation.getArguments();
			
			for(int i = 0; i < arguments.size(); i++)
				arguments.get(i).accept(this);
		}
		
		if(invocation.getExecutable() != null)
			invocation.getExecutable().accept(this);
		
	}

	/* Tries to find pattern in Executable Reference and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.ExecutableReference)
	 */
	@Override
	public void visit(ExecutableReference er) {
		//System.out.println("[DEBUG] FindPattern: ExecutableReference");
		findPatterns(er);
		
		if(er.getDeclarator() != null)
			er.getDeclarator().accept(this);;
		
		if(er.getType() != null)
			er.getType().accept(this);
		
		if(er.getParameters() != null) {
			
			List<Parameter> parameters = er.getParameters();
			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}
		
		if(er.getArguments() != null) {
			
			List<Reference> arguments = er.getArguments();
			for(int i = 0; i < arguments.size(); i++)
				arguments.get(i).accept(this);
		}
	}

	/* Tries to find pattern in method and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Method)
	 */
	@Override
	public void visit(Method method) {
		//System.out.println("[DEBUG] FindPattern: Method");
		findPatterns(method);
		
		if(method.getType() != null)
			method.getType().accept(this);
		
		if(method.getParameters() != null) {
			List<Parameter> parameters = method.getParameters();
			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}
		
		if(method.getBody() != null)
			method.getBody().accept(this);
	}

	/* Tries to find pattern in parameter and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Parameter)
	 */
	@Override
	public void visit(Parameter parameter) {
		//System.out.println("[DEBUG] FindPattern: Parameter");
		findPatterns(parameter);
		
		if(parameter.getType() != null)
			parameter.getType().accept(this);
	}

	/* Tries to find pattern in Array Type Reference
	 * @see main.Visitor#visit(parser.ArrayTypeReference)
	 */
	@Override
	public void visit(ArrayTypeReference atr) {
		//System.out.println("[DEBUG] FindPattern: ArrayTypeReference");
		findPatterns(atr);
		
		if(atr.getType() != null)
			atr.getType().accept(this);
	}

	/* Tries to find pattern in if and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.If)
	 */
	@Override
	public void visit(If i) {
		//System.out.println("[DEBUG] FindPattern: If");
		findPatterns(i);
		
		if(i.getCondition() != null)
			i.getCondition().accept(this);
		
		if(i.getThen() != null)
			i.getThen().accept(this);
		
		if(i.getElse() != null)
			i.getElse().accept(this);
	}

	/* Tries to find pattern in assignment and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Assignment)
	 */
	@Override
	public void visit(Assignment assignment) {
		//System.out.println("[DEBUG] FindPattern: Assignment");
		findPatterns(assignment);
		
		if(assignment.getType() != null)
			assignment.getType().accept(this);
		
		if(assignment.getLhs() != null)
			assignment.getLhs().accept(this);
		
		if(assignment.getRhs() != null)
			assignment.getRhs().accept(this);
	}

	/* Tries to find pattern in Variable write and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.VariableWrite)
	 */
	@Override
	public void visit(VariableWrite vw) {
		//System.out.println("[DEBUG] FindPattern: VariableWrite");
		findPatterns(vw);
		
		if(vw.getType() != null)
			vw.getType().accept(this);
		
		if(vw.getVar() != null)
			vw.getVar().accept(this);
	}

	/* Tries to find pattern in null node
	 * @see main.Visitor#visit(parser.NullNode)
	 */
	@Override
	public void visit(NullNode nn) {
		//System.out.println("[DEBUG] FindPattern: NullNode");
		findPatterns(nn);
	}

	/* Tries to find pattern in local variable and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.LocalVariable)
	 */
	@Override
	public void visit(LocalVariable lv) {
		//System.out.println("[DEBUG] FindPattern: LocalVariable");
		findPatterns(lv);
		
		if(lv.getType() != null)
			lv.getType().accept(this);
		
		if(lv.getInit() != null)
			lv.getInit().accept(this);
	}

	/* Tries to find pattern in Type reference
	 * @see main.Visitor#visit(parser.TypeReference)
	 */
	@Override
	public void visit(TypeReference tr) {
		//System.out.println("[DEBUG] FindPattern: TypeReference");
		findPatterns(tr);
	}

	/* Tries to find pattern in Local Variable Reference
	 * @see main.Visitor#visit(parser.LocalVariableReference)
	 */
	@Override
	public void visit(LocalVariableReference lvr) {
		//System.out.println("[DEBUG] FindPattern: LocalVariableReference");
		findPatterns(lvr);
		
		if(lvr.getType() != null)
			lvr.getType().accept(this);
	}

	/* Tries to find pattern in binary operator and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.BinaryOperator)
	 */
	@Override
	public void visit(BinaryOperator bo) {
		//System.out.println("[DEBUG] FindPattern: BinaryOperator");
		findPatterns(bo);
		
		if(bo.getType() != null)
			bo.getType().accept(this);
		
		if(bo.getLhs() != null)
			bo.getLhs().accept(this);
		
		if(bo.getRhs() != null)
			bo.getRhs().accept(this);
	}

	/* Tries to find pattern in literal
	 * @see main.Visitor#visit(parser.Literal)
	 */
	@Override
	public void visit(Literal literal) {
		//System.out.println("[DEBUG] FindPattern: Literal");
		findPatterns(literal);
		
		if(literal.getType() != null)
			literal.getType().accept(this);
	}

	/* Tries to find pattern in Variable Read and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.VariableRead)
	 */
	@Override
	public void visit(VariableRead vr) {
		//System.out.println("[DEBUG] FindPattern: VariableRead");
		findPatterns(vr);
		
		if(vr.getType() != null)
			vr.getType().accept(this);
		
		if(vr.getVar() != null)
			vr.getVar().accept(this);
	}

	/* Tries to find pattern in for loop and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.For)
	 */
	@Override
	public void visit(For f) {
		//System.out.println("[DEBUG] FindPattern: For");
		findPatterns(f);
		
		if(f.getInit() != null) {
			
			List<IStatement> init = f.getInit();
			for(int i = 0; i < init.size(); i++)
				init.get(i).accept(this);
		}
		
		if(f.getCondition() != null)
			f.getCondition().accept(this);
		
		if(f.getUpdate() != null) {		
			List<IStatement> update = f.getUpdate();
			for(int i = 0; i < update.size(); i++)
				update.get(i).accept(this);
		}
		
		if(f.getBody() != null)
			f.getBody().accept(this);
	}

	/* Tries to find pattern in unary operator and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.UnaryOperator)
	 */
	@Override
	public void visit(UnaryOperator uo) {
		//System.out.println("[DEBUG] FindPattern: UnaryOperator");
		findPatterns(uo);		
		
		if(uo.getType() != null)
			uo.getType().accept(this);
		
		if(uo.getOperand() != null)
			uo.getOperand().accept(this);
	}

	/* Tries to find pattern in new array and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.NewArray)
	 */
	@Override
	public void visit(NewArray na) {
		//System.out.println("[DEBUG] FindPattern: NewArray");
		findPatterns(na);
		
		if(na.getType() != null)
			na.getType().accept(this);
		
		if(na.getType_casts() != null) {
			
			List<TypeReference> castTypes = na.getType_casts();
			for(int i = 0; i < castTypes.size(); i++)
				castTypes.get(i).accept(this);
		}
		
		if(na.getDimensions() != null) {
			
			List<IExpression> dimensions = na.getDimensions();
			for(int i = 0; i < dimensions.size(); i++)
				dimensions.get(i).accept(this);
		}
		
		if(na.getElements() != null) {
			
			List<IExpression> elements = na.getElements();
			for(int i = 0; i < elements.size(); i++)
				elements.get(i).accept(this);
		}
	}

	/* Tries to find pattern in array write and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.ArrayWrite)
	 */
	@Override
	public void visit(ArrayWrite aw) {
		//System.out.println("[DEBUG] FindPattern: ArrayWrite");
		findPatterns(aw);
		
		if(aw.getType() != null)
			aw.getType().accept(this);
		
		if(aw.getTarget() != null)
			aw.getTarget().accept(this);
		
		if(aw.getIndex() != null)
			aw.getIndex().accept(this);
	}

	/* Tries to find pattern in field read and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.FieldRead)
	 */
	@Override
	public void visit(FieldRead fr) {
		//System.out.println("[DEBUG] FindPattern: FieldRead");
		findPatterns(fr);
		
		if(fr.getType() != null)
			fr.getType().accept(this);
		
		if(fr.getTarget() != null)
			fr.getTarget().accept(this);
		
		if(fr.getVar() != null)
			fr.getVar().accept(this);
	}

	/* Tries to find pattern in Type access and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.TypeAccess)
	 */
	@Override
	public void visit(TypeAccess ta) {
		//System.out.println("[DEBUG] FindPattern: TypeAccess");
		findPatterns(ta);
		
		if(ta.getType() != null)
			ta.getType().accept(this);
		
		if(ta.getTarget() != null)
			ta.getTarget().accept(this);
	}

	/* Tries to find pattern in while and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.While)
	 */
	@Override
	public void visit(While w) {
		//System.out.println("[DEBUG] FindPattern: While");
		findPatterns(w);
		
		if(w.getCondition() != null)
			w.getCondition().accept(this);
		
		if(w.getBody() != null)
			w.getBody().accept(this);
	}
	
	/* Tries to find pattern in field reference and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.FieldReference)
	 */
	@Override
	public void visit(FieldReference fr) {
		//System.out.println("[DEBUG] FindPattern: FieldReference");
		findPatterns(fr);
		
		if(fr.getDeclarator() != null)
			fr.getDeclarator().accept(this);
		
		if(fr.getType() != null)
			fr.getType().accept(this);
		
	}

	/* Tries to find pattern in field write and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.FieldWrite)
	 */
	@Override
	public void visit(FieldWrite fw) {
		//System.out.println("[DEBUG] FindPattern: FieldWrite");
		findPatterns(fw);
		
		if(fw.getVar() != null)
			fw.getVar().accept(this);
		
		if(fw.getType() != null)
			fw.getType().accept(this);
	}
	
	/* Tries to find pattern in do while loop and propagates the search to it's children nodes
	 * @see main.Visitor#visit(parser.Do)
	 */
	@Override
	public void visit(Do doNode) {
		//System.out.println("[DEBUG] FindPattern: Do");
		findPatterns(doNode);
		
		if(doNode.getBody() != null)
			doNode.getBody().accept(this);
		
		if(doNode.getCondition() != null)
			doNode.getCondition().accept(this);
	}
	
	/**
	 * if code possibly matches one pattern, starts a new thread to compare the code with the pattern add it to patterns found
	 *
	 * @param node code which possibly matches
	 */
	public void findPatterns(IBasicNode node) {

		if(patternsToFind.containsKey(node.getNodeType())) {
			List<Pattern> patterns = patternsToFind.get(node.getNodeType());
			
			for(int i = 0; i < patterns.size(); i++)
				executor.submit(new Worker(node, patterns.get(i).getRoot(), patterns.get(i).isPartial(), patternsFound));
		}
	}
	
	/**
	 * Awaits termination of all the pattern comparison threads
	 */
	public void awaitTermination() {
		executor.shutdown();
		try {
			executor.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printMatches();
	}
	
	/**
	 * Prints the line of the patterns found
	 */
	private void printMatches() {
		System.out.println("------------------------------------------------------------");
		System.out.println("----------------------- Matches Found ----------------------");
		System.out.println("------------------------------------------------------------");
		
		for(IBasicNode node : patternsFound) {
			System.out.println(node.getNodeType() + " pattern found in line " +
					node.getLocation().substring(node.getLocation().lastIndexOf('/') + 1, node.getLocation().length()));
			System.out.println("------------------------------------------------------------");
		}
	}
}
