package workers;

import java.util.concurrent.CopyOnWriteArrayList;

import main.PatternMatcher;
import parser.IBasicNode;

/**
 * Worker for concurrent pattern matching.
 */
public class Worker implements Runnable {
	
	/** The pattern to be matched. */
	private IBasicNode node, pattern;
	
	/** The patterns found so far. */
	private CopyOnWriteArrayList<IBasicNode> patternsFound;
	
	/** is search partial. */
	private boolean partial;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param node the code
	 * @param pattern the pattern
	 * @param partial is search partial
	 * @param patternsFound the patterns found so far
	 */
	public Worker(IBasicNode node, IBasicNode pattern, boolean partial, CopyOnWriteArrayList<IBasicNode> patternsFound) {
		this.node = node;
		this.pattern = pattern;
		this.patternsFound = patternsFound;
		this.partial = partial;
	}

	/* 
	 * Check if pattern matches code and if so adds it to patterns found
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		PatternMatcher matcher = new PatternMatcher(pattern, partial);
		
		node.accept(matcher);
		
		if(matcher.isMatch())
			patternsFound.add(node);		
	}

}
