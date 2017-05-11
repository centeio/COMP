package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import pack.MyNewGrammar;
import pack.SimpleNode;
import parser.Parser;
import parser.Root;

public class PAT {
	
	public static void main(String args[]) throws IOException {
		Parser parser = new Parser();
		
		Root root = parser.parse();
		
		String content;

		content = new String(Files.readAllBytes(Paths.get("patterns.txt")));
		System.out.println(content);

		MyNewGrammar.createjjt(content);
	
        SimpleNode n = MyNewGrammar.n;

        
     //   Visitor v = new Visitor(n);
        
     //   v.findSubtree(root);
		
	}
	
}
