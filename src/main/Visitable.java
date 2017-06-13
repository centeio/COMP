package main;

/**
 * The Interface Visitable.
 */
public interface Visitable {
	
	/**
	 * Accept the visitor.
	 *
	 * @param v the visitor
	 */
	void accept(Visitor v);
}
