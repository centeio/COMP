package tests;

import static org.junit.Assert.*;

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
	Parser parser = new Parser();
	Root root = parser.parse();
	
	Member m = root.getCompilationUnits().get(0).getTypes().get(0).getMembers().get(1);
	List<IStatement>  statements = ((Block) ((Method) m).getBody()).getStatements();

	@Test
	public void compareLocalVariables() {
		IStatement lv1 = statements.get(0);
        IStatement lv2 = statements.get(1);
        IStatement lv3 = statements.get(2);
        IStatement lv4 = statements.get(5);
		
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
	public void compareIf() {
        IStatement if1 = statements.get(3);
        IStatement for1 = statements.get(4);
		
        PatternMatcher pm = new PatternMatcher(if1);
        if1.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(for1);
        if1.accept(pm);
        assertFalse(pm.isMatch());
	}
	
	@Test
	public void compareFor() {
        IStatement lv1 = statements.get(0);
        IStatement for1 = statements.get(4);
		
        PatternMatcher pm = new PatternMatcher(for1);
        for1.accept(pm);
        assertTrue(pm.isMatch());
        
        pm = new PatternMatcher(lv1);
        for1.accept(pm);
        assertFalse(pm.isMatch());
	}
	
	@Test
	public void compareWhile() {
        IStatement while1 = statements.get(6);
		
        PatternMatcher pm = new PatternMatcher(while1);
        while1.accept(pm);
        assertTrue(pm.isMatch());
	}

}
