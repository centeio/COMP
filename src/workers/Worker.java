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
		PatternMatcher matcher = new PatternMatcher(pattern);
		
		node.accept(matcher);				
		
		if(matcher.isMatch())
			patternsFound.add(node);		
	}

}
