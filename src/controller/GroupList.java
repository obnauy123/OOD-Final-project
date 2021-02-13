package controller;

import model.*;
import java.awt.*;
import java.util.ArrayList;
public class GroupList implements IShapeAction {
    private ShapeOptions shapeOptions;
    private int width;
    private int height;
    private Point adjustedStartPoint;
    private Point adjustedEndPoint;
    private int minx;
    private int maxx;
    private int miny;
    private int maxy;
    private ArrayList<IShapeAction> group;
    private ShapeFactory shapeFactory = new ShapeFactory();

    public GroupList(ShapeList selected,ShapeList groupList){
        group = new ArrayList<>();
        Grouping(selected);
        ArrayList<IShapeAction> temp = selected.getShapesList();
        minx = temp.get(0).getAdjustedStartPoint().getX();
        maxx = temp.get(0).getAdjustedEndPoint().getX();
        miny = temp.get(0).getAdjustedStartPoint().getY();
        maxy = temp.get(0).getAdjustedEndPoint().getY();
        for(int i = 1; i<temp.size(); i++){
            IShapeAction shape = temp.get(i);
            Point shape_starPoint = shape.getAdjustedStartPoint();
            Point shape_endPoint = shape.getAdjustedEndPoint();
            minx = (shape_starPoint.getX()<minx) ? shape_starPoint.getX() : minx;
            maxx = (shape_endPoint.getX()>maxx) ? shape_endPoint.getX() : maxx;
            miny = (shape_starPoint.getY()<miny) ? shape_starPoint.getY() : miny;
            maxy = (shape_endPoint.getY()>maxy) ? shape_endPoint.getY() : maxy;
        }
        this.adjustedStartPoint = new Point(minx-5, miny-5);
        this.adjustedEndPoint = new Point(maxx+5,maxy+5);
        this.shapeOptions = new ShapeOptions();
        this.shapeOptions.setPrimaryShapeColor(ShapeColor.WHITE);
        this.shapeOptions.setShapeType(ShapeType.RECTANGLE);
        this.shapeOptions.setShapeShadingType(ShapeShadingType.OUTLINE);
        this.shapeOptions.setStartPoint(adjustedStartPoint);
        this.shapeOptions.setEndPoint(adjustedEndPoint);
    }

    public void draw(Graphics2D g) {
        for(IShapeAction shape : group){
            shape.draw(g);
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
        return ShapeType.RECTANGLE;
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    public void increaseHeight(int x){
        height+=x;
        for(IShapeAction shape : group){
            shape.increaseHeight(x);
        }
    };
	
	public void increaseWidth(int y){
        width+=y;
        for(IShapeAction shape : group){
            shape.increaseHeight(y);
        }
    };
	
	public void moveAdjustedStartPoint(int x, int y){
        this.adjustedStartPoint.setX(x);
        this.adjustedStartPoint.setY(y);
        for(IShapeAction shape : group){
            shape.moveAdjustedStartPoint(x, y);
        }
    }
	
    public void moveAdjustedEndPoint(int x, int y){
        this.adjustedEndPoint.setX(x);
        this.adjustedEndPoint.setY(y);
        for(IShapeAction shape : group){
            shape.moveAdjustedEndPoint(x, y);
        }

    }

    private void Grouping(ShapeList selected){
        for(IShapeAction shape : selected.getShapesList()){
            this.group.add(shapeFactory.createShape(shape.getShapeOptions()));
        }
    }
    public ArrayList getGroup(){
        return this.group;
    }
}