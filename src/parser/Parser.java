package parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {
	
	public Parser() {}
	
	public Root parse(String jsonString) {

		NodeDeserializer typeAdapter = new NodeDeserializer();
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(BasicNode.class, typeAdapter)
			.registerTypeAdapter(Reference.class, typeAdapter)
			.registerTypeAdapter(Expression.class, typeAdapter)
			.registerTypeAdapter(Statement.class, typeAdapter)
			.registerTypeAdapter(Member.class, typeAdapter)
			.registerTypeAdapter(Type.class, typeAdapter)
			.registerTypeAdapter(IBasicNode.class, typeAdapter)
			.registerTypeAdapter(IStatement.class, typeAdapter)
			.registerTypeAdapter(IExpression.class, typeAdapter)
			.create();
		
		try{
			Root root = gson.fromJson(jsonString, Root.class);
			//System.out.println(root.toString(""));
			return root;
		} catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
			
	}
	
	public String readFile(String json) throws IOException {
		return new String(Files.readAllBytes(Paths.get(json)), StandardCharsets.UTF_8);
	}
		
}
