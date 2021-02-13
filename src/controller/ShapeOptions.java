package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public class ShapeOptions {
    private ShapeColor primaryShapeColor;
    private ShapeColor secondaryShapeColor;
    private ShapeShadingType shapeShadingType;
    private Point startPoint;
    private Point endPoint;
    private ShapeType shapeType;
    
    
    
    public void setPrimaryShapeColor(ShapeColor primaryShapeColor) {
        this.primaryShapeColor = primaryShapeColor;
    }
    public void setSecondaryShapeColor(ShapeColor secondaryShapeColor){
        this.secondaryShapeColor = secondaryShapeColor;
    }
    public void setShapeShadingType(ShapeShadingType shapeShadingType){
        this.shapeShadingType = shapeShadingType;
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public ShapeColor getSecondaryShapeColor(){
        return secondaryShapeColor;
    }
    public ShapeShadingType getShapeShadingType(){
        return shapeShadingType;
    }
    
    public ShapeColor getPrimaryShapeColor() {
        return primaryShapeColor;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public Point getAdjustedStartPoint() {
        int Begin_x = Math.min(startPoint.getX(), endPoint.getX());
        int Begin_y = Math.min(startPoint.getY(), endPoint.getY());
        return new Point(Begin_x, Begin_y);
    }

    public Point getAdjustedEndPoint() {
        int End_x = Math.max(startPoint.getX(), endPoint.getX());
        int End_y = Math.max(startPoint.getY(), endPoint.getY());
        return new Point(End_x, End_y);
    }

    public int getWidth() {
        Point adjustedStart = getAdjustedStartPoint();
        Point adjustedEnd = getAdjustedEndPoint();
        return adjustedEnd.getX() - adjustedStart.getX();
    }

    public int getHeight() {
        Point adjustedStart = getAdjustedStartPoint();
        Point adjustedEnd = getAdjustedEndPoint();
        return adjustedEnd.getY() - adjustedStart.getY();
    }



}