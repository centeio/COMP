package parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parser {
	
	public Parser() {}
	
	public Root parse() {

		NodeDeserializer typeAdapter = new NodeDeserializer();
		Gson gson = new GsonBuilder()
			.registerTypeAdapter(BasicNode.class, typeAdapter)
			.registerTypeAdapter(Reference.class, typeAdapter)
			.registerTypeAdapter(Expression.class, typeAdapter)
			.registerTypeAdapter(Statement.class, typeAdapter)
			.registerTypeAdapter(Member.class, typeAdapter)
			.registerTypeAdapter(Type.class, typeAdapter)
			.create();
		
		try {
			
			String jsonString = readFile("ast.json");
			Root root = gson.fromJson(jsonString, Root.class);
			System.out.println(root.toString(""));
			return root;
			
		} catch(IOException e) { e.printStackTrace(); }
		
		return null;		
	}
	
	public String readFile(String json) throws IOException {
		return new String(Files.readAllBytes(Paths.get(json)), StandardCharsets.UTF_8);
	}
		
}