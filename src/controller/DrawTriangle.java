package controller;

import java.awt.*;

import model.ShapeShadingType;
import model.ShapeType;
public class DrawTriangle implements IShapeAction {
	
    private ShapeOptions shapeOption;
    private Color primaryColor;
    private Color secondaryColor;
    private ShapeShadingType shapeShadingType;
    private Point adjustedStartPoint;
    private Point adjustedEndPoint;
    private int[] x = new int[3];
    private int[] y = new int[3];
    private int width;
    private int height;


    
    public DrawTriangle(ShapeOptions shapeOption){
        this.shapeOption = shapeOption;
        this.primaryColor = Colors.getColor(shapeOption.getPrimaryShapeColor());
        this.secondaryColor = Colors.getColor(shapeOption.getSecondaryShapeColor());
        this.shapeShadingType = shapeOption.getShapeShadingType();
        this.adjustedStartPoint = shapeOption.getAdjustedStartPoint();
        this.adjustedEndPoint = shapeOption.getAdjustedEndPoint();
        this.height = shapeOption.getHeight();
        this.width = shapeOption.getWidth();
        x[0] = this.adjustedEndPoint.getX();
        x[1] = this.adjustedEndPoint.getX();
        x[2] = this.adjustedStartPoint.getX();
        y[0] = this.adjustedStartPoint.getY();
        y[1] = this.adjustedEndPoint.getY();
        y[2] = this.adjustedEndPoint.getY();
    }

    public void draw(Graphics2D g) {
        switch(shapeShadingType){
            case FILLED_IN:
                g.setColor(primaryColor);
                g.fillPolygon(x, y, 3);
                break;
            case OUTLINE:
                g.setColor(primaryColor);
                g.setStroke(new BasicStroke(5));
                g.drawPolygon(x, y, 3);
                break;
            case  OUTLINE_AND_FILLED_IN:
                g.setColor(secondaryColor);
                g.setStroke(new BasicStroke(5));
                g.drawPolygon(x, y, 3);
                g.setColor(primaryColor);
                g.fillPolygon(x, y, 3);
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
        return shapeOption;
    }
	
	public ShapeType getShapeType(){
        return shapeOption.getShapeType();
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
        this.x[2] = this.adjustedStartPoint.getX();
        this.y[0] = this.adjustedStartPoint.getY();
        this.shapeOption.setStartPoint(this.adjustedStartPoint);
    }
	
    public void moveAdjustedEndPoint(int x, int y){
        this.adjustedEndPoint.setX(x);
        this.adjustedEndPoint.setY(y);
        this.y[1] = this.adjustedEndPoint.getY();
        this.y[2] = this.adjustedEndPoint.getY();
        this.x[0] = this.adjustedEndPoint.getX();
        this.x[1] = this.adjustedEndPoint.getX();
        this.shapeOption.setEndPoint(this.adjustedEndPoint);
    }

}