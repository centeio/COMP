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

public class FindPattern implements Visitor {
	List<IBasicNode> patternsFound;
	HashMap<String, List<IBasicNode>> patternsToFind;
	
	public FindPattern(HashMap<String, List<IBasicNode>> patternsToFind) {
		this.patternsFound = new ArrayList<IBasicNode>();
		this.patternsToFind = patternsToFind;
	}
	
	public FindPattern() {
		this.patternsFound = new ArrayList<IBasicNode>();
		this.patternsToFind = new HashMap<>();
		
		List<IBasicNode> patterns = new ArrayList<>();
		patterns.add(new Root());
		patterns.add(new Root());
		
		this.patternsToFind.put("For", patterns);
		this.patternsToFind.put("While", patterns);
		this.patternsToFind.put("If", patterns);
		this.patternsToFind.put("Condition", patterns);
	} 

	@Override
	public void visit(Root root) {
		findPatterns(root);
		
		if(root.getCompilationUnits() != null) {
			List<CompilationUnit> cp = root.getCompilationUnits();
			
			for(int i = 0; i < cp.size(); i++)
				cp.get(i).accept(this);
		}
	}

	@Override
	public void visit(CompilationUnit cu) {
		findPatterns(cu);
		
		if(cu.getTypes() != null) {
		
			List<Type> types = cu.getTypes();
			
			for(int i = 0; i < types.size(); i++)
				types.get(i).accept(this);
		}
	}

	@Override
	public void visit(Comment comment) {
		findPatterns(comment);
	}

	@Override
	public void visit(ArrayRead ar) {
		findPatterns(ar);
		
		if(ar.getTarget() != null)
			ar.getTarget().accept(this);
		
		if(ar.getIndex() != null)
			ar.getIndex().accept(this);
	}

	@Override
	public void visit(Class c) {
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

	@Override
	public void visit(Constructor constructor) {
		findPatterns(constructor);
		
		if(constructor.getParameters() != null) {
			List<Parameter> parameters = constructor.getParameters();
			
			for(int i = 0; i < parameters.size(); i++)
				parameters.get(i).accept(this);
		}
		
		if(constructor.getBody() != null)
			constructor.getBody().accept(this);
	}

	@Override
	public void visit(Block block) {
		findPatterns(block);
		
		List<IStatement> statements = block.getStatements();
		
		for(int i = 0; i < statements.size(); i++)
			statements.get(i).accept(this);
	}

	@Override
	public void visit(Invocation invocation) {
		findPatterns(invocation);
		
		if(invocation.getArguments() != null) {		
			List<IExpression> arguments = invocation.getArguments();
			
			for(int i = 0; i < arguments.size(); i++)
				arguments.get(i).accept(this);
		}
		
		if(invocation.getExecutable() != null)
			invocation.getExecutable().accept(this);
		
	}

	@Override
	public void visit(ExecutableReference er) {
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

	@Override
	public void visit(Method method) {
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

	@Override
	public void visit(Parameter parameter) {
		findPatterns(parameter);
		
		if(parameter.getType() != null)
			parameter.getType().accept(this);
	}

	@Override
	public void visit(ArrayTypeReference atr) {
		findPatterns(atr);
		
		if(atr.getType() != null)
			atr.getType().accept(this);
	}

	@Override
	public void visit(If i) {
		findPatterns(i);
		
		if(i.getCondition() != null)
			i.getCondition().accept(this);
		
		if(i.getThen() != null)
			i.getThen().accept(this);
		
		if(i.getElse() != null)
			i.getElse().accept(this);
	}

	@Override
	public void visit(Assignment assignment) {
		findPatterns(assignment);
		
		if(assignment.getType() != null)
			assignment.getType().accept(this);
		
		if(assignment.getLhs() != null)
			assignment.getLhs().accept(this);
		
		if(assignment.getRhs() != null)
			assignment.getRhs().accept(this);
	}

	@Override
	public void visit(VariableWrite vw) {
		findPatterns(vw);
		
		if(vw.getType() != null)
			vw.getType().accept(this);
		
		if(vw.getVar() != null)
			vw.getVar().accept(this);
	}

	@Override
	public void visit(NullNode nn) {
		findPatterns(nn);
	}

	@Override
	public void visit(LocalVariable lv) {
		findPatterns(lv);
		
		if(lv.getType() != null)
			lv.getType().accept(this);
		
		if(lv.getInit() != null)
			lv.getInit().accept(this);
	}

	@Override
	public void visit(TypeReference tr) {
		findPatterns(tr);
	}

	@Override
	public void visit(LocalVariableReference lvr) {
		findPatterns(lvr);
		
		if(lvr.getType() != null)
			lvr.getType().accept(this);
	}

	@Override
	public void visit(BinaryOperator bo) {
		findPatterns(bo);
		
		if(bo.getType() != null)
			bo.getType().accept(this);
		
		if(bo.getLhs() != null)
			bo.getLhs().accept(this);
		
		if(bo.getRhs() != null)
			bo.getRhs().accept(this);
	}

	@Override
	public void visit(Literal literal) {
		findPatterns(literal);
		
		if(literal.getType() != null)
			literal.getType().accept(this);
	}

	@Override
	public void visit(VariableRead vr) {
		findPatterns(vr);
		
		if(vr.getType() != null)
			vr.getType().accept(this);
		
		if(vr.getVar() != null)
			vr.getVar().accept(this);
	}

	@Override
	public void visit(For f) {
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

	@Override
	public void visit(UnaryOperator uo) {
		findPatterns(uo);		
		
		if(uo.getType() != null)
			uo.getType().accept(this);
		
		if(uo.getOperand() != null)
			uo.getOperand().accept(this);
	}

	@Override
	public void visit(NewArray na) {
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

	@Override
	public void visit(ArrayWrite aw) {
		findPatterns(aw);
		
		if(aw.getType() != null)
			aw.getType().accept(this);
		
		if(aw.getTarget() != null)
			aw.getTarget().accept(this);
		
		if(aw.getIndex() != null)
			aw.getIndex().accept(this);
	}

	@Override
	public void visit(FieldRead fr) {
		findPatterns(fr);
		
		if(fr.getType() != null)
			fr.getType().accept(this);
		
		if(fr.getTarget() != null)
			fr.getTarget().accept(this);
		
		if(fr.getVar() != null)
			fr.getVar().accept(this);
	}

	@Override
	public void visit(TypeAccess ta) {
		findPatterns(ta);
		
		if(ta.getType() != null)
			ta.getType().accept(this);
		
		if(ta.getTarget() != null)
			ta.getTarget().accept(this);
	}

	@Override
	public void visit(While w) {
		findPatterns(w);
		
		if(w.getCondition() != null)
			w.getCondition().accept(this);
		
		if(w.getBody() != null)
			w.getBody().accept(this);
	}
	
	@Override
	public void visit(FieldReference fr) {
		findPatterns(fr);
		
		if(fr.getDeclarator() != null)
			fr.getDeclarator().accept(this);
		
		if(fr.getType() != null)
			fr.getType().accept(this);
		
	}

	@Override
	public void visit(FieldWrite fw) {
		findPatterns(fw);
		
		if(fw.getVar() != null)
			fw.getVar().accept(this);
		
		if(fw.getType() != null)
			fw.getType().accept(this);
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
