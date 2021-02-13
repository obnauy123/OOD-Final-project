package controller;

import view.interfaces.IUndoable;

public class PasteCommand implements ICommand,IUndoable {

    ShapeList shapeList;
	ShapeList copiedShapeList;
	ShapeList pastedList;
	ShapeFactory shapeFactory = new ShapeFactory();
	
	public PasteCommand(ShapeList shapeList, ShapeList copiedShapeList,ShapeList pastedList) {
		this.shapeList = shapeList;
		this.copiedShapeList = copiedShapeList;
		this.pastedList = pastedList;
	}

    @Override
    public void run(){
		pastedList.reset();
        for (IShapeAction shape : copiedShapeList.getShapesList()) {

			ShapeOptions shapeOptions = shape.getShapeOptions();
			ShapeOptions temp = new ShapeOptions();
			Point paste_begPoint = new Point(0,0);
			paste_begPoint.setX(shape.getAdjustedStartPoint().getX()+200);
			paste_begPoint.setY(shape.getAdjustedStartPoint().getY()+200);
			Point paste_endPoint = new Point(0,0);
			paste_endPoint.setX(shape.getAdjustedEndPoint().getX()+200);
			paste_endPoint.setY(shape.getAdjustedEndPoint().getY()+200);
			
			temp.setPrimaryShapeColor(shapeOptions.getPrimaryShapeColor());
			temp.setSecondaryShapeColor(shapeOptions.getSecondaryShapeColor());
			temp.setShapeShadingType(shapeOptions.getShapeShadingType());
			temp.setStartPoint(paste_begPoint);
			temp.setEndPoint(paste_endPoint);
			temp.setShapeType(shapeOptions.getShapeType());	
			
			IShapeAction copiedShape = shapeFactory.createShape(temp);
			
			pastedList.add(copiedShape);
			shapeList.add(copiedShape);
		}
		CommandHistory.add(this);
		shapeList.drawList();
	}
	public void redo(){
		for (IShapeAction pasted : pastedList.getShapesList()) {
            shapeList.add(pasted);
		}
        shapeList.drawList();
    }
    public void undo(){
        for (IShapeAction pasted : pastedList.getShapesList()) {
            shapeList.remove(pasted);
		}
		shapeList.drawList();
    }   
}