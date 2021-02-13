package controller;

import java.awt.*;

import model.ShapeType;


public interface IShapeAction {
    public void draw(Graphics2D g);	

    public Point getAdjustedStartPoint();
	
	public Point getAdjustedEndPoint();
	
	public ShapeOptions getShapeOptions();
	
	public ShapeType getShapeType();

	public int getWidth();
	
	public int getHeight();

	public void increaseHeight(int x);
	
	public void increaseWidth(int y);

	public void moveAdjustedStartPoint(int dx, int dy);
	
    public void moveAdjustedEndPoint(int dx, int dy);
}