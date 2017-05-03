package parser;

import java.util.List;

public class NewArray extends Expression {
	private List<TypeReference> type_casts;
	private List<Expression> elements;
	private List<Expression> dimensions;
	
	public String toString(String prefix) {
		String str = prefix + "NewArray";
		
		if(type != null) {
			str += "\n" + prefix + " Type:";
			str += "\n" + type.toString(prefix + "  ");
		}
		
		if(elements != null) {
			str += "\n" + prefix + " Elements:";
			
			for(Expression element: elements)
				str += "\n" + element.toString(prefix + "  ");
		}
		
		if(dimensions != null) {
			str += "\n" + prefix + " Dimensions:";
			
			for(Expression dimension: dimensions)
				str += "\n" + dimension.toString(prefix + "  ");
		}
			
		return str;
	}

	public Reference getType() {
		return type;
	}

	public void setType(Reference type) {
		this.type = type;
	}

	public List<TypeReference> getType_casts() {
		return type_casts;
	}

	public void setType_casts(List<TypeReference> type_casts) {
		this.type_casts = type_casts;
	}

	public List<Expression> getElements() {
		return elements;
	}

	public void setElements(List<Expression> elements) {
		this.elements = elements;
	}

	public List<Expression> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<Expression> dimensions) {
		this.dimensions = dimensions;
	}
	
}
