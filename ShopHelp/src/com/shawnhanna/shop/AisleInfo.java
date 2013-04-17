package com.shawnhanna.shop;

public class AisleInfo {

	public String name;
	public String left;
	public String right;
	public String up;
	public boolean hasUp;
	
	public AisleInfo(String nName, String nLeft, String nRight){
		name = nName;
		left = nLeft;
	    right = nRight;
		up = null;
		hasUp = false; 
	}
	public AisleInfo(String nName, String nLeft, String nRight, String nUp){
		name = nName;
		left = nLeft;
		right = nRight;
		up = nUp;
		hasUp = true;
	}
}
