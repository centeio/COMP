package main;

import parser.*;
import parser.Class;

public interface Visitor {
	void visit(Root root);
	void visit(CompilationUnit cu);
	void visit(Comment comment);
	void visit(ArrayRead ar);
	void visit(Class c);
	void visit(Constructor constructor);
	void visit(Block block);
	void visit(Invocation invocation);
	void visit(ExecutableReference er);
	void visit(Method method);
	void visit(Parameter parameter);
	void visit(ArrayTypeReference atr);
	void visit(If i);
	void visit(Assignment assignment);
	void visit(VariableWrite vw);
	void visit(NullNode nn);
	void visit(LocalVariable lv);
	void visit(TypeReference tr);
	void visit(LocalVariableReference lvr);
	void visit(BinaryOperator bo);
	void visit(Literal literal);
	void visit(VariableRead vr);
	void visit(For f);
	void visit(UnaryOperator uo);
	void visit(NewArray na);
	void visit(ArrayWrite aw);
	void visit(FieldRead fr);
	void visit(FieldReference fr);
	void visit(FieldWrite fw);
	void visit(TypeAccess ta);
	void visit(While w);
}
