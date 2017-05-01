package parser;

public enum ClassSelector {
	
	ROOT(Root.class),
	COMPILATIONUNIT(CompilationUnit.class),
	CLASS(Class.class),
	CONSTRUCTOR(Constructor.class),
	BLOCK(Block.class),
	INVOCATION(Invocation.class),
	EXECUTABLEREFERENCE(ExecutableReference.class),
	METHOD(Method.class),
	PARAMETER(Parameter.class),
	ARRAYTYPEREFERENCE(ArrayTypeReference.class),
	IF(If.class),
	ASSIGNMENT(Assignment.class),
	VARIABLEWRITE(VariableWrite.class),
	NULLNODE(NullNode.class),
	LOCALVARIABLE(LocalVariable.class),
    TYPEREFERENCE(TypeReference.class),
    LOCALVARIABLEREFERENCE(LocalVariableReference.class),
    BINARYOPERATOR(BinaryOperator.class),
    LITERAL(Literal.class),
    VARIABLEREAD(VariableRead.class),
    FOR(For.class),
    UNARYOPERATOR(UnaryOperator.class),
    NEWARRAY(NewArray.class),
    ARRAYWRITE(ArrayWrite.class),
    FIELDREAD(FieldRead.class),
    TYPEACESS(TypeAccess.class),
    ARRAYREAD(ArrayRead.class),
    WHILE(While.class);
    
    private java.lang.Class<? extends BasicNode> myClass;

    private ClassSelector(java.lang.Class<? extends BasicNode> myClass) {
        this.myClass = myClass;
    }
    
    public java.lang.Class<? extends BasicNode> getMyClass() { return myClass; }
}
