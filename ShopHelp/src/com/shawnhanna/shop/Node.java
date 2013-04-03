package com.shawnhanna.shop;

import java.util.ArrayList;

public class Node {
	private int id;
	private double xCoord;
	private double yCoord;
	private ArrayList<MapDot> mapDots = new ArrayList<MapDot>();
	
	public Node()
	{
	}
	
	public void setId(int n)
	{
		id = n;
	}
	
	public void setCoords(double x, double y)
	{
		xCoord = x;
		yCoord = y;
		
	}

	public int getId()
	{
		return id;
	}
	
	public double getXCoord()
	{
		return xCoord;
	}
	
	public double getYCoord()
	{
		return yCoord;
	}
	
	public void addDot(MapDot m)
	{
		mapDots.add(m);
	}
}
