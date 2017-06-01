package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import pack.MyNewGrammar;
import pack.SimpleNode;
import parser.Parser;
import pt.up.fe.specs.spoon.SpoonASTLauncher;
import tests.PatternMatchingTests;
import parser.*;

public class PAT {
	
	public static void main(String args[]) throws IOException {
		Parser parser = new Parser();
		
		String testJson = SpoonASTLauncher.java2json(Paths.get("Test.java").toString(), null, false);
		String patternsJson = SpoonASTLauncher.java2json(Paths.get("MyPatternTest.java").toString(), null, false);
		
		System.out.println(testJson);
		System.out.println(patternsJson);
		
		Root rootTest = parser.parse(testJson);
		
        String content; 
        
        content = new String(Files.readAllBytes(Paths.get("patterns.txt"))); 
        System.out.println(content); 
     
        MyNewGrammar.createjjt(content); 
       
        SimpleNode n = MyNewGrammar.n; 
		Root rootPatterns = parser.parse(patternsJson);		

        
     	Visitor v = new Visitor(n);
        
     	v.findSubtree(root);
		
		//Testing FindPattern
		System.out.println("-----------------------------------------------");
		FindPattern find = new FindPattern();
		root.accept(find);*/
		
		//PatternMatchingTests test = new PatternMatchingTests();
	}
	
}
