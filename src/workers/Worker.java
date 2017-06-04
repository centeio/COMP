package workers;

import java.util.concurrent.CopyOnWriteArrayList;

import main.PatternMatcher;
import parser.IBasicNode;

public class Worker implements Runnable {
	private IBasicNode node, pattern;
	private CopyOnWriteArrayList<IBasicNode> patternsFound;
	
	public Worker(IBasicNode node, IBasicNode pattern, CopyOnWriteArrayList<IBasicNode> patternsFound) {
		this.node = node;
		this.pattern = pattern;
		this.patternsFound = patternsFound;
	}

	@Override
	public void run() {
		System.out.println("[DEBUG] Worker Running");
		System.out.println(node.toString());
		System.out.println(pattern.toString());
		PatternMatcher matcher = new PatternMatcher(pattern);
		
		node.accept(matcher);
		
		System.out.println("[DEBUG] Worker Testing match");
		if(matcher.isMatch())
			patternsFound.add(node);		
	}

}
