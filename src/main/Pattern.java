package main;

import parser.IBasicNode;

public class Pattern {
	String name;
	IBasicNode root;
	boolean parcial;
	
	public Pattern(String name, IBasicNode root, boolean parcial) {
		this.name = name;
		this.root = root;
		this.parcial = parcial;
	}
	
	public boolean isParcial() {
		return parcial;
	}
	public IBasicNode getRoot() {
		return root;
	}
	public String getName() {
		return name;
	}
}
