package parser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class NodeDeserializer implements JsonDeserializer<BasicNode> {
	
	 @Override
	 public BasicNode deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		 try {
			 JsonObject jsonObj = jsonElement.getAsJsonObject();
			 JsonElement nodeTypeEl = jsonObj.get("nodetype");
			 if (nodeTypeEl == null) { throw new RuntimeException("Nodetype property must be defined!"); }
			 
			 String nodeType = nodeTypeEl.getAsString();
			 java.lang.Class<? extends BasicNode> classToUse = getClassToUse(nodeType);
			 return jsonDeserializationContext.deserialize(jsonElement, classToUse);
			} catch (Exception e) {
				throw new JsonParseException(e);
		}
	 }
	 
	 private java.lang.Class<? extends BasicNode> getClassToUse(String nodeType) {
		 return ClassSelector.valueOf(nodeType.toUpperCase()).getMyClass();
	 }
}
