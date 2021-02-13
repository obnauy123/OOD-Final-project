package controller;

import model.ShapeColor;
import model.ShapeShadingType;
import model.persistence.ApplicationState;

public class SelectShape implements ICommand{
    ShapeList selectedList;
    ShapeList shapeList;
    ShapeList copyList;
    ShapeList pasteList;
    ShapeList outlineList;
    ShapeList GroupList;
    ShapeOptions shapeOption;
    ApplicationState appstate;
    ShapeFactory shapeFactory = new ShapeFactory();
    public SelectShape(ApplicationState appstate, ShapeList shapeList, ShapeList selectedList, ShapeList copyList,ShapeList pasteList,ShapeList outlineList, ShapeList GroupList ){
        this.selectedList = selectedList;
        this.shapeList = shapeList;
        this.copyList = copyList;
        this.appstate = appstate;
        this.pasteList = pasteList;
        this.outlineList = outlineList;
        this.GroupList = GroupList;
    }
    @Override
    public void run(){
        selectedList.reset();
        copyList.reset();
        outlineList.clear();
        outlineList.reset();
        shapeOption = appstate.getCurrentState();
        for (IShapeAction shape : shapeList.getShapesList()) {
			if (shape.getAdjustedStartPoint().getX() < shapeOption.getAdjustedStartPoint().getX() + shapeOption.getWidth() &&
					shape.getAdjustedStartPoint().getX() + shape.getWidth() > shapeOption.getAdjustedStartPoint().getX() && 
					shape.getAdjustedStartPoint().getY() < shapeOption.getAdjustedStartPoint().getY() + shapeOption.getHeight() &&
					shape.getAdjustedStartPoint().getY() + shape.getHeight() > shapeOption.getAdjustedStartPoint().getY()) {
					selectedList.add(shape);
            }
        }
        for (IShapeAction shape : GroupList.getShapesList()) {
			if (shape.getAdjustedStartPoint().getX() < shapeOption.getAdjustedStartPoint().getX() + shapeOption.getWidth() &&
					shape.getAdjustedStartPoint().getX() + shape.getWidth() > shapeOption.getAdjustedStartPoint().getX() && 
					shape.getAdjustedStartPoint().getY() < shapeOption.getAdjustedStartPoint().getY() + shapeOption.getHeight() &&
					shape.getAdjustedStartPoint().getY() + shape.getHeight() > shapeOption.getAdjustedStartPoint().getY()) {
					selectedList.add(shape);
            }
        }
        if(selectedList.getShapesList().size() == 0){
            shapeList.drawList();
            GroupList.drawList();
            return;
        } 
        for (IShapeAction shape : selectedList.getShapesList()) {
            ShapeOptions shapeOptions = shape.getShapeOptions();
            ShapeOptions temp = new ShapeOptions();
			temp.setStartPoint(shape.getAdjustedStartPoint());
			temp.setEndPoint(shape.getAdjustedEndPoint());
			temp.setShapeType(shapeOptions.getShapeType());	
            temp.setShapeShadingType(ShapeShadingType.OUTLINE);
            temp.setPrimaryShapeColor(ShapeColor.BLACK);
            IShapeAction outline = shapeFactory.createShape(temp);
            outlineList.add(outline);
        }
        shapeList.drawList();
        GroupList.drawList();
        outlineList.drawList();
    }


}