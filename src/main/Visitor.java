package main;

import parser.*;
import parser.Class;

/**
 * The Interface Visitor.
 */
public interface Visitor {
	
	/**
	 * Visit root.
	 *
	 * @param root the root
	 */
	void visit(Root root);
	
	/**
	 * Visit Compilation Unit.
	 *
	 * @param cu the compilation unit
	 */
	void visit(CompilationUnit cu);
	
	/**
	 * Visit comment.
	 *
	 * @param comment the comment
	 */
	void visit(Comment comment);
	
	/**
	 * Visit Array Read.
	 *
	 * @param ar the Array Read
	 */
	void visit(ArrayRead ar);
	
	/**
	 * Visit class.
	 *
	 * @param c the class
	 */
	void visit(Class c);
	
	/**
	 * Visit constructor.
	 *
	 * @param constructor the constructor
	 */
	void visit(Constructor constructor);
	
	/**
	 * Visit block.
	 *
	 * @param block the block
	 */
	void visit(Block block);
	
	/**
	 * Visit invocation.
	 *
	 * @param invocation the invocation
	 */
	void visit(Invocation invocation);
	
	/**
	 * Visit Executable Reference.
	 *
	 * @param er the Executable Reference
	 */
	void visit(ExecutableReference er);
	
	/**
	 * Visit method.
	 *
	 * @param method the method
	 */
	void visit(Method method);
	
	/**
	 * Visit parameter.
	 *
	 * @param parameter the parameter
	 */
	void visit(Parameter parameter);
	
	/**
	 * Visit Array Type Reference.
	 *
	 * @param atr the Array Type Reference
	 */
	void visit(ArrayTypeReference atr);
	
	/**
	 * Visit if.
	 *
	 * @param i the if
	 */
	void visit(If i);
	
	/**
	 * Visit assignment.
	 *
	 * @param assignment the assignment
	 */
	void visit(Assignment assignment);
	
	/**
	 * Visit Variable Write.
	 *
	 * @param vw the Variable Write
	 */
	void visit(VariableWrite vw);
	
	/**
	 * Visit Null Node.
	 *
	 * @param nn the Null Node
	 */
	void visit(NullNode nn);
	
	/**
	 * Visit Local Variable. 
	 *
	 * @param lv the Local Variable
	 */
	void visit(LocalVariable lv);
	
	/**
	 * Visit Type Reference.
	 *
	 * @param tr the Type Reference
	 */
	void visit(TypeReference tr);
	
	/**
	 * Visit Local Variable Reference.
	 *
	 * @param lvr the Local Variable Reference
	 */
	void visit(LocalVariableReference lvr);
	
	/**
	 * Visit Binary Operator.
	 *
	 * @param bo the Binary Operator
	 */
	void visit(BinaryOperator bo);
	
	/**
	 * Visit literal.
	 *
	 * @param literal the literal
	 */
	void visit(Literal literal);
	
	/**
	 * Visit Variable Read.
	 *
	 * @param vr the Variable Read
	 */
	void visit(VariableRead vr);
	
	/**
	 * Visit for loop.
	 *
	 * @param f the for loop
	 */
	void visit(For f);
	
	/**
	 * Visit Unary Operator.
	 *
	 * @param uo the Unary Operator
	 */
	void visit(UnaryOperator uo);
	
	/**
	 * Visit New Array.
	 *
	 * @param na the New Array
	 */
	void visit(NewArray na);
	
	/**
	 * Visit Array Write.
	 *
	 * @param aw the Array Write
	 */
	void visit(ArrayWrite aw);
	
	/**
	 * Visit Field Read.
	 *
	 * @param fr the Field Read
	 */
	void visit(FieldRead fr);
	
	/**
	 * Visit Field Reference.
	 *
	 * @param fr the Field Reference
	 */
	void visit(FieldReference fr);
	
	/**
	 * Visit Field Write.
	 *
	 * @param fw the Field Write
	 */
	void visit(FieldWrite fw);
	
	/**
	 * Visit Type Access.
	 *
	 * @param ta the Type Access
	 */
	void visit(TypeAccess ta);
	
	/**
	 * Visit while loop.
	 *
	 * @param w the while loop
	 */
	void visit(While w);
	
	/**
	 * Visit do while loop.
	 *
	 * @param do1 the do while loop
	 */
	void visit(Do do1);
}
