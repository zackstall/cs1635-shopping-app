package com.shawnhanna.shop;

import java.util.*;

/*
 * CLASS DESCRIPTION
 * A hashmap of int id's to aisleview data.
 */

public class AisleMap {

	//NOTE: I'm not sure what we're storing as a aisleview location... a path to the aisleview image?
	private Map <Integer, Object> map;
	
	public AisleMap(){
		map = new HashMap <Integer, Object>();
	}
	
	public Object getViewFromID(int ID){
		return map.get(ID);
	}
	public Object getViewFromID(Integer ID){
		return map.get(ID);
	}
	public Object setViewAtID(int ID, Object loc){
		return map.put(ID, loc);
	}
	public Object setViewAtID(Integer ID, Object loc){
		return map.put(ID, loc);
	}
}
