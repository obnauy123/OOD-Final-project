package controller;

public class ShapeFactory {
	private IShapeAction shape;
	
    public IShapeAction createShape(ShapeOptions shapeOptions) {
        switch(shapeOptions.getShapeType()){
            case RECTANGLE:
                shape = new DrawRectangle(shapeOptions);
                break;
            case ELLIPSE:
                shape = new DrawEllipse(shapeOptions);
                break;
            case TRIANGLE:
                shape = new DrawTriangle(shapeOptions);
                break;
        }
    	
        return shape;
    }
}