package controller;

import java.util.ArrayList;

public class ShapeList {
    private ArrayList<IShapeAction> shapesList;
    private DrawShape drawShape;

    public ShapeList(DrawShape drawShape) {
        this.drawShape = drawShape;
        shapesList = new ArrayList<IShapeAction>();
    }

    public void add(IShapeAction shape) {
        shapesList.add(shape);
        
    }

    public void remove(IShapeAction shape) {
        shapesList.remove(shape);
        drawShape.clear();
        drawShape.draw(this);
    }
    

    public ArrayList<IShapeAction> getShapesList() {
        return shapesList;
    }
    
    public void drawList() {
    	drawShape.draw(this);
    }
    
    public void clear() {
    	drawShape.clear();
    }

	public void reset() {
		shapesList = new ArrayList<IShapeAction>();
	}
}
