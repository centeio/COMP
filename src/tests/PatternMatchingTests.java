package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import main.PatternMatcher;
import parser.Block;
import parser.IStatement;
import parser.Member;
import parser.Method;
import parser.Parser;
import parser.Root;
import pt.up.fe.specs.spoon.SpoonASTLauncher;

public class PatternMatchingTests {
	private List<IStatement>  test_statements = null;
	private List<Member> patternMethods = null;
	
	void start() throws IOException{
		Parser parser = new Parser();
		
		String testJson = SpoonASTLauncher.java2json(Paths.get("Test.java").toString(), null, false);
		String patternJson = SpoonASTLauncher.java2json(Paths.get("MyPatternTest.java").toString(), null, false);
		
		Member m = parser.parse(testJson).getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);

		test_statements = ((Block) ((Method) m).getBody()).getStatements();
		patternMethods = parser.parse(patternJson).getCompilationUnits().get(0).getTypes().get(0).getMembers();
	}
	
	/*@Test
	public void compareLocalVariables() throws IOException {
		start();
		
		IStatement lv1 = test_statements.get(0);
        IStatement lv2 = test_statements.get(1);
        IStatement lv3 = test_statements.get(2);
        IStatement lv4 = test_statements.get(5);
        
        PatternMatcher pm = new PatternMatcher(null);

        assertTrue(pm.compare(lv1,lv1));
        
        assertTrue(pm.compare(lv2,lv2));
        
        assertTrue(pm.compare(lv3,lv3));
        
        assertTrue(pm.compare(lv4,lv4));
        
        assertFalse(pm.compare(lv4, lv1));
	}
	
	@Test
	public void compareIf() throws IOException {
		start();
        
		IStatement if1 = test_statements.get(3);
        IStatement for1 = test_statements.get(4);
        
		IStatement pattern1 = ((Block) ((Method) patternMethods.get(1)).getBody()).getStatements().get(0);
		IStatement pattern6 = ((Block) ((Method) patternMethods.get(6)).getBody()).getStatements().get(0);
		IStatement pattern7 = ((Block) ((Method) patternMethods.get(7)).getBody()).getStatements().get(0);
		
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(if1,if1));

        assertTrue(pm.compare(if1,pattern1));
        
        assertFalse(pm.compare(if1,pattern6));
        
        //assertTrue(pm.compare(if1,pattern7));

        assertFalse(pm.compare(if1,for1));
	}*/
	
	@Test
	public void compareFor() throws IOException {
		start();
		
        IStatement lv1 = test_statements.get(0);
        IStatement for1 = test_statements.get(4);
        IStatement for2 = test_statements.get(8);
        IStatement for3 = test_statements.get(9);
        
        IStatement pattern2 = ((Block) ((Method) patternMethods.get(2)).getBody()).getStatements().get(0);
        IStatement pattern10 = ((Block) ((Method) patternMethods.get(10)).getBody()).getStatements().get(0);
        
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(for1,for1));
        
        assertTrue(pm.compare(for1,pattern2));
        
        assertTrue(pm.compare(for2,pattern10));
        
        assertTrue(pm.compare(for3,pattern10));
        
        assertFalse(pm.compare(for1,lv1));
	}
	
	/*@Test
	public void compareWhile() throws IOException {
		start();
		
		IStatement while1 = test_statements.get(6);
		IStatement doWhile1 = test_statements.get(7);
		IStatement pattern3 = ((Block) ((Method) patternMethods.get(3)).getBody()).getStatements().get(0);
		IStatement pattern4 = ((Block) ((Method) patternMethods.get(4)).getBody()).getStatements().get(0);
		IStatement pattern8 = ((Block) ((Method) patternMethods.get(8)).getBody()).getStatements().get(0);
		IStatement pattern9 = ((Block) ((Method) patternMethods.get(9)).getBody()).getStatements().get(0);
        
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(while1,while1));
        
        assertTrue(pm.compare(while1,pattern3));
        
        assertTrue(pm.compare(while1,pattern4));
        
        assertTrue(pm.compare(pattern8,pattern8));
        
        //assertTrue(pm.compare(doWhile1,pattern8));
        
        assertTrue(pm.compare(doWhile1,pattern9));
        
        assertFalse(pm.compare(while1,pattern8));
	}*/

}
