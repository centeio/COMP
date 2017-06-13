package main;

import parser.IBasicNode;

/**
 * class to represent a pattern.
 */
public class Pattern {
	
	/** The pattern name. */
	String name;
	
	/** The pattern root. */
	IBasicNode root;
	
	/** is search partial. */
	boolean partial;
	
	/**
	 * Instantiates a new pattern.
	 *
	 * @param name the pattern's name
	 * @param root the pattren's root
	 * @param parcial is search to be partial
	 */
	public Pattern(String name, IBasicNode root, boolean partial) {
		this.name = name;
		this.root = root;
		this.partial = partial;
	}
	
	/**
	 * Checks if search is to be partial.
	 *
	 * @return true, if search is partial
	 */
	public boolean isPartial() {
		return partial;
	}
	
	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public IBasicNode getRoot() {
		return root;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
