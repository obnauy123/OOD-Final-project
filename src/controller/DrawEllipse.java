package controller;

import java.awt.*;

import model.ShapeShadingType;
import model.ShapeType;
public class DrawEllipse implements IShapeAction {
	
    private ShapeOptions shapeOptions;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shapeShadingType;
    private int width;
    private int height;
    private Point adjustedStartPoint;
    private Point adjustedEndPoint;
    
    public DrawEllipse(ShapeOptions shapeOptions){
        this.shapeOptions = shapeOptions;
        this.primaryColor = Colors.getColor(shapeOptions.getPrimaryShapeColor());
        this.shapeShadingType = shapeOptions.getShapeShadingType();
        this.secondaryColor = Colors.getColor(shapeOptions.getSecondaryShapeColor());
        this.width = shapeOptions.getWidth();
        this.height = shapeOptions.getHeight();
        this.adjustedStartPoint = shapeOptions.getAdjustedStartPoint();
        this.adjustedEndPoint = shapeOptions.getAdjustedEndPoint();
        
    }

    public void draw(Graphics2D g) {
        switch(shapeShadingType){
            case FILLED_IN:
                g.setColor(primaryColor);
                g.fillOval(adjustedStartPoint.getX(), adjustedStartPoint.getY(), width, height);
                break;
            case OUTLINE:
                g.setColor(primaryColor);
                g.setStroke(new BasicStroke(5));
                g.drawOval(adjustedStartPoint.getX(), adjustedStartPoint.getY(), width, height);
                break;
            case  OUTLINE_AND_FILLED_IN:
                g.setColor(secondaryColor);
                g.setStroke(new BasicStroke(5));
                g.drawOval(adjustedStartPoint.getX(), adjustedStartPoint.getY(), width, height);
                g.setColor(primaryColor);
                g.fillOval(adjustedStartPoint.getX(), adjustedStartPoint.getY(), width, height);
                break;
            
        }
    }
    public Point getAdjustedStartPoint(){
        return adjustedStartPoint;
    }
	
	public Point getAdjustedEndPoint(){
        return adjustedEndPoint;
    }
	
	public ShapeOptions getShapeOptions(){
        return shapeOptions;
    }
	
	public ShapeType getShapeType(){
        return shapeOptions.getShapeType();
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public void increaseHeight(int x){
        height+=x;
    };
	
	public void increaseWidth(int y){
        width+=y;
    };
	
	public void moveAdjustedStartPoint(int x, int y){
        this.adjustedStartPoint.setX(x);
        this.adjustedStartPoint.setY(y);
        this.shapeOptions.setStartPoint(this.adjustedStartPoint);
    }
	
    public void moveAdjustedEndPoint(int x, int y){
        this.adjustedEndPoint.setX(x);
        this.adjustedEndPoint.setY(y);
        this.shapeOptions.setEndPoint(this.adjustedEndPoint);

    }
    

}