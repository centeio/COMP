package main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;import java.util.HashMap;import java.util.List;import pack.MyNewGrammar;import pt.up.fe.specs.spoon.SpoonASTLauncher;
import parser.*;
public class PAT {
	public static void main(String args[]) throws IOException {				String content = new String(Files.readAllBytes(Paths.get("patterns.txt")));				MyNewGrammar.createjjt(content);
		String testJson = SpoonASTLauncher.java2json(Paths.get("Test.java").toString(), null, false);
		String patternsJson = SpoonASTLauncher.java2json(Paths.get("MyPatternTest.java").toString(), null, false);				Parser parser = new Parser();		Root rootTest = parser.parse(testJson);		Root rootPatterns = parser.parse(patternsJson);				ExtractPatterns extract = new ExtractPatterns();				extract.visit(rootPatterns);		HashMap<String, List<IBasicNode>> patternsToFind = extract.getPatterns();				// DEBUG		/*Set<String> keys = patternsToFind.keySet();		for(String key : keys) {				for(IBasicNode node : patternsToFind.get(key)) {				System.out.println(node.toString());				System.out.println("--------------------------------------------------");			}		}*/				FindPattern findPatterns = new FindPattern(patternsToFind);				findPatterns.visit(rootTest);		findPatterns.awaitTermination();
	}
}