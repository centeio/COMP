package main;

import java.util.ArrayList;
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
import parser.IBasicNode;
import parser.IExpression;
import parser.IStatement;

/**
 * Visitor responsible for extracting patterns from the pattern java file
 */
public class ExtractPatterns implements Visitor {
	
	/** The patterns extracted */
	HashMap<String, List<Pattern>> patterns;

	/**
	 * Instantiates a new extract patterns.
	 */
	public ExtractPatterns() {
		this.patterns = new HashMap<>();
	} 

	/* 
	 * Extracts patterns starting from Root
	 * @see main.Visitor#visit(parser.Root)
	 */
	@Override
	public void visit(Root root) {		
		if(root.getCompilationUnits() != null) {
			List<CompilationUnit> cp = root.getCompilationUnits();

			for(int i = 0; i < cp.size(); i++)
				cp.get(i).accept(this);
		}
	}

	/* 
	 * Extracts patterns starting from Compilation Unit
	 * @see main.Visitor#visit(parser.CompilationUnit)
	 */
	@Override
	public void visit(CompilationUnit cu) {

		if(cu.getTypes() != null) {

			List<Type> types = cu.getTypes();

			for(int i = 0; i < types.size(); i++)
				types.get(i).accept(this);
		}
	}

	@Override
	public void visit(Comment comment) {
	}

	/* 
	 * Extracts patterns starting from Array Read
	 * @see main.Visitor#visit(parser.ArrayRead)
	 */
	@Override
	public void visit(ArrayRead ar) {

		if(ar.getTarget() != null)
			ar.getTarget().accept(this);

		if(ar.getIndex() != null)
			ar.getIndex().accept(this);
	}

	/* 
	 * Extracts patterns starting from Class
	 * @see main.Visitor#visit(parser.Class)
	 */
	@Override
	public void visit(Class c) {

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

	/* 
	 * Extracts patterns starting from Constructor
	 * @see main.Visitor#visit(parser.Constructor)
	 */
	@Override
	public void visit(Constructor constructor) {

		if(constructor.getParameters() != null) {
			List<Parameter> parameters = constructor.getParameters();

			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}

		if(constructor.getBody() != null)
			constructor.getBody().accept(this);
	}

	/* 
	 * Extracts patterns starting from Block
	 * @see main.Visitor#visit(parser.Block)
	 */
	@Override
	public void visit(Block block) {

		List<IStatement> statements = block.getStatements();

		for(int i = 0; i < statements.size(); i++)
			statements.get(i).accept(this);
	}

	/* 
	 * Extracts patterns starting from Invocation
	 * @see main.Visitor#visit(parser.Invocation)
	 */
	@Override
	public void visit(Invocation invocation) {

		if(invocation.getArguments() != null) {		
			List<IExpression> arguments = invocation.getArguments();

			for(int i = 0; i < arguments.size(); i++)
				arguments.get(i).accept(this);
		}

		if(invocation.getExecutable() != null)
			invocation.getExecutable().accept(this);

	}

	/* 
	 * Extracts patterns starting from Executable Reference
	 * @see main.Visitor#visit(parser.ExecutableReference)
	 */
	@Override
	public void visit(ExecutableReference er) {

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

	/* 
	 * Extracts patterns starting from method
	 * @see main.Visitor#visit(parser.Method)
	 */
	@Override
	public void visit(Method method) {

		if(method.getType() != null)
			method.getType().accept(this);

		if(method.getParameters() != null) {
			List<Parameter> parameters = method.getParameters();
			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}

		if(method.getBody() != null) {
			IBasicNode root = method.getBody();
			boolean partial = method.getParameters().get(0).getName().equals("Partial");

			if(method.getBody() instanceof Block && ((Block) method.getBody()).getStatements().size() == 1){
				root = ((Block) method.getBody()).getStatements().get(0);
			}

			if(!patterns.containsKey(root.getNodeType()))
				patterns.put(root.getNodeType(), new ArrayList<Pattern>());
			patterns.get(root.getNodeType()).add(new Pattern(method.getName(), root, partial));
		}
	}

	/* 
	 * Extracts patterns starting from Parameter
	 * @see main.Visitor#visit(parser.Parameter)
	 */
	@Override
	public void visit(Parameter parameter) {

		if(parameter.getType() != null)
			parameter.getType().accept(this);
	}

	/* 
	 * Extracts patterns starting from Array Read
	 * @see main.Visitor#visit(parser.ArrayTypeReference)
	 */
	@Override
	public void visit(ArrayTypeReference atr) {

		if(atr.getType() != null)
			atr.getType().accept(this);
	}

	/* 
	 * Extracts patterns starting from If
	 * @see main.Visitor#visit(parser.If)
	 */
	@Override
	public void visit(If i) {

		if(i.getCondition() != null)
			i.getCondition().accept(this);

		if(i.getThen() != null)
			i.getThen().accept(this);

		if(i.getElse() != null)
			i.getElse().accept(this);
	}

	/* 
	 * Extracts patterns starting from assignment
	 * @see main.Visitor#visit(parser.Assignment)
	 */
	@Override
	public void visit(Assignment assignment) {

		if(assignment.getType() != null)
			assignment.getType().accept(this);

		if(assignment.getLhs() != null)
			assignment.getLhs().accept(this);

		if(assignment.getRhs() != null)
			assignment.getRhs().accept(this);
	}

	/* 
	 * Extracts patterns starting from Variable Write
	 * @see main.Visitor#visit(parser.VariableWrite)
	 */
	@Override
	public void visit(VariableWrite vw) {

		if(vw.getType() != null)
			vw.getType().accept(this);

		if(vw.getVar() != null)
			vw.getVar().accept(this);
	}

	@Override
	public void visit(NullNode nn) {
	}

	/* Extracts patterns starting from Local Variable
	 * @see main.Visitor#visit(parser.LocalVariable)
	 */
	@Override
	public void visit(LocalVariable lv) {

		if(lv.getType() != null)
			lv.getType().accept(this);

		if(lv.getInit() != null)
			lv.getInit().accept(this);
	}

	@Override
	public void visit(TypeReference tr) {
	}

	/* Extracts patterns starting from Local Variable Reference
	 * @see main.Visitor#visit(parser.LocalVariableReference)
	 */
	@Override
	public void visit(LocalVariableReference lvr) {

		if(lvr.getType() != null)
			lvr.getType().accept(this);
	}

	/* Extracts patterns starting from Binary Operator
	 * @see main.Visitor#visit(parser.BinaryOperator)
	 */
	@Override
	public void visit(BinaryOperator bo) {

		if(bo.getType() != null)
			bo.getType().accept(this);

		if(bo.getLhs() != null)
			bo.getLhs().accept(this);

		if(bo.getRhs() != null)
			bo.getRhs().accept(this);
	}

	/* Extracts patterns starting from Literal
	 * @see main.Visitor#visit(parser.Literal)
	 */
	@Override
	public void visit(Literal literal) {

		if(literal.getType() != null)
			literal.getType().accept(this);
	}

	/* Extracts patterns starting from Variable Read
	 * @see main.Visitor#visit(parser.VariableRead)
	 */
	@Override
	public void visit(VariableRead vr) {

		if(vr.getType() != null)
			vr.getType().accept(this);

		if(vr.getVar() != null)
			vr.getVar().accept(this);
	}

	/* Extracts patterns starting from For loop
	 * @see main.Visitor#visit(parser.For)
	 */
	@Override
	public void visit(For f) {

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

	/* Extracts patterns starting from Unary Operator
	 * @see main.Visitor#visit(parser.UnaryOperator)
	 */
	@Override
	public void visit(UnaryOperator uo) {	

		if(uo.getType() != null)
			uo.getType().accept(this);

		if(uo.getOperand() != null)
			uo.getOperand().accept(this);
	}

	/* Extracts patterns starting from New Array
	 * @see main.Visitor#visit(parser.NewArray)
	 */
	@Override
	public void visit(NewArray na) {

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

	/* Extracts patterns starting from Array Write
	 * @see main.Visitor#visit(parser.ArrayWrite)
	 */
	@Override
	public void visit(ArrayWrite aw) {

		if(aw.getType() != null)
			aw.getType().accept(this);

		if(aw.getTarget() != null)
			aw.getTarget().accept(this);

		if(aw.getIndex() != null)
			aw.getIndex().accept(this);
	}

	/* Extracts patterns starting from Field Read
	 * @see main.Visitor#visit(parser.FieldRead)
	 */
	@Override
	public void visit(FieldRead fr) {

		if(fr.getType() != null)
			fr.getType().accept(this);

		if(fr.getTarget() != null)
			fr.getTarget().accept(this);

		if(fr.getVar() != null)
			fr.getVar().accept(this);
	}

	/* Extracts patterns starting from Type Access
	 * @see main.Visitor#visit(parser.TypeAccess)
	 */
	@Override
	public void visit(TypeAccess ta) {

		if(ta.getType() != null)
			ta.getType().accept(this);

		if(ta.getTarget() != null)
			ta.getTarget().accept(this);
	}

	/* Extracts patterns starting from While loop
	 * @see main.Visitor#visit(parser.While)
	 */
	@Override
	public void visit(While w) {

		if(w.getCondition() != null)
			w.getCondition().accept(this);

		if(w.getBody() != null)
			w.getBody().accept(this);
	}

	/* Extracts patterns starting from Field Reference
	 * @see main.Visitor#visit(parser.FieldReference)
	 */
	@Override
	public void visit(FieldReference fr) {

		if(fr.getDeclarator() != null)
			fr.getDeclarator().accept(this);

		if(fr.getType() != null)
			fr.getType().accept(this);

	}

	/* Extracts patterns starting from Field Write
	 * @see main.Visitor#visit(parser.FieldWrite)
	 */
	@Override
	public void visit(FieldWrite fw) {

		if(fw.getVar() != null)
			fw.getVar().accept(this);

		if(fw.getType() != null)
			fw.getType().accept(this);
	}

	/* Extracts patterns starting from Do loop
	 * @see main.Visitor#visit(parser.Do)
	 */
	@Override
	public void visit(Do doNode) {

		if(doNode.getBody() != null)
			doNode.getBody().accept(this);

		if(doNode.getCondition() != null)
			doNode.getCondition().accept(this);
	}

	/**
	 * Gets the patterns extracted.
	 *
	 * @return patterns extracted
	 */
	public HashMap<String, List<Pattern>> getPatterns() { return patterns; }
}
