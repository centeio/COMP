package workers;

import java.util.concurrent.CopyOnWriteArrayList;

import main.PatternMatcher;
import parser.IBasicNode;

public class Worker implements Runnable {
	private IBasicNode node, pattern;
	private CopyOnWriteArrayList<IBasicNode> patternsFound;
	private boolean parcial;
	
	public Worker(IBasicNode node, IBasicNode pattern, boolean parcial, CopyOnWriteArrayList<IBasicNode> patternsFound) {
		this.node = node;
		this.pattern = pattern;
		this.patternsFound = patternsFound;
		this.parcial = parcial;
	}

	@Override
	public void run() {
		System.out.println("[DEBUG] Worker Running");
		System.out.println(node.toString());
		System.out.println(pattern.toString());
		PatternMatcher matcher = new PatternMatcher(pattern, parcial);
		
		node.accept(matcher);
		
		System.out.println("[DEBUG] Worker Testing match");
		if(matcher.isMatch())
			patternsFound.add(node);		
	}

}
