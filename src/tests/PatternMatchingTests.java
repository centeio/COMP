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

public class PatternMatchingTests {
	private Root testRoot = null;
	private Root patternRoot = null;
	
	void start() throws IOException{
		Parser parser = new Parser();
		String testJson = new String(Files.readAllBytes(Paths.get("Test.json")));
		testRoot = parser.parse(testJson);
		
		String patternJson = new String(Files.readAllBytes(Paths.get("Test_Patterns.json")));	
		patternRoot = parser.parse(patternJson);
	}
	
	@Test
	public void compareLocalVariables() throws IOException {
		start();
		Member m = testRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		List<IStatement>  test_statements = ((Block) ((Method) m).getBody()).getStatements();
		
		IStatement lv1 = test_statements.get(0);
        IStatement lv2 = test_statements.get(1);
        IStatement lv3 = test_statements.get(2);
        IStatement lv4 = test_statements.get(5);
		
        PatternMatcher pm = new PatternMatcher(lv1);
        lv1.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(lv2);
        lv2.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(lv3);
        lv3.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(lv4);
        lv4.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(lv1);
        lv4.accept(pm);
        assertFalse(pm.isMatch());
	}
	
	@Test
	public void compareIf() throws IOException {
		start();
		Member m = testRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		List<IStatement>  test_statements = ((Block) ((Method) m).getBody()).getStatements();
		
		m = patternRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		List<IStatement>  pattern_statements = ((Block) ((Method) m).getBody()).getStatements();
        
		IStatement if1 = test_statements.get(3);
		IStatement if2 = pattern_statements.get(0);
        IStatement for1 = test_statements.get(4);
		
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(if1,if1));

        assertTrue(pm.compare(if1,if2));

        assertFalse(pm.compare(if1,for1));
	}
	
	@Test
	public void compareFor() throws IOException {
		start();
		Member m = testRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		List<IStatement>  test_statements = ((Block) ((Method) m).getBody()).getStatements();
		
		m = patternRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(2);
		List<IStatement>  pattern_statements = ((Block) ((Method) m).getBody()).getStatements();
		
		
        IStatement lv1 = test_statements.get(0);
        IStatement for1 = test_statements.get(4);
        IStatement for2 = pattern_statements.get(0);
        
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(for1,for1));
        
        System.out.println(for2.toString(""));
        
        assertTrue(pm.compare(for1,for2));
        
        assertFalse(pm.compare(for1,lv1));
	}
	
	@Test
	public void compareWhile() throws IOException {
		start();
		Member m = testRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		List<IStatement>  test_statements = ((Block) ((Method) m).getBody()).getStatements();
		
		//m = patternRoot.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
		//List<IStatement>  pattern_statements = ((Block) ((Method) m).getBody()).getStatements();
        
        IStatement while1 = test_statements.get(6);
        
        PatternMatcher pm = new PatternMatcher(null);
        assertTrue(pm.compare(while1,while1));
	}

}
