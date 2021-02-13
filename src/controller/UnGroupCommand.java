package controller;

import java.util.ArrayList;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IUndoable;

public class UnGroupCommand implements ICommand, IUndoable {
    ShapeList selected;
    ShapeList GroupList;
    ShapeList shapeList;
    ShapeList outlineList;
    ShapeFactory shapeFactory;
    IShapeAction group;
    public UnGroupCommand(ShapeList selected, ShapeList GroupList, ShapeList shapeList, ShapeList outlineList){
        this.selected = selected;
        this.GroupList = GroupList;
        this.shapeList = shapeList;
        this.outlineList = outlineList;

        shapeFactory = new ShapeFactory();
    }

    @Override
    public void run(){
        ArrayList<IShapeAction> breakups = new ArrayList<>();
        ArrayList<IShapeAction> cur_group = new ArrayList<>();
        for(IShapeAction shape : selected.getShapesList()){
            if(shape instanceof GroupList){
                cur_group.add(shape);
                GroupList cur = (GroupList)shape;
                ArrayList<IShapeAction> groupList = cur.getGroup();
                for(IShapeAction ori : groupList){
                    shapeList.add(ori);
                    breakups.add(ori);
                }
            }
        }
        selected.reset();
        for(IShapeAction cur : cur_group){
            GroupList.remove(cur);
        }
        for(IShapeAction breakup : breakups){
            selected.add(breakup);
        }
        GroupList.clear();
        outlineList.reset();
        for (IShapeAction shape : selected.getShapesList()) {
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
        CommandHistory.add(this);
    }
    public void redo(){
        run();
    }
    public void undo(){
        


    }
}