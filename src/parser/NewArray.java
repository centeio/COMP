package parser;

import java.util.List;

import main.Visitor;

public class NewArray extends Expression {
	private List<TypeReference> type_casts;
	private List<IExpression> elements;
	private List<IExpression> dimensions;
	
	public String toString(String prefix) {
		String str = prefix + nodetype;
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		if(elements != null) {
			str += "\n" + prefix + " Elements:";
			
			for(IExpression element: elements)
				str += "\n" + element.toString(prefix + "  ");
		}
		
		if(dimensions != null) {
			str += "\n" + prefix + " Dimensions:";
			
			for(IExpression dimension: dimensions)
				str += "\n" + dimension.toString(prefix + "  ");
		}
			
		return str;
	}

	public IBasicNode getType() {
		return type;
	}

	public List<TypeReference> getType_casts() {
		return type_casts;
	}

	public List<IExpression> getElements() {
		return elements;
	}

	public List<IExpression> getDimensions() {
		return dimensions;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
