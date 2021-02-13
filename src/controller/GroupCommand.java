package controller;

import java.util.ArrayList;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IUndoable;


public class GroupCommand implements ICommand, IUndoable {
    ShapeList selected;
    ShapeList GroupList;
    ShapeList shapeList;
    ShapeList outlineList;
    ShapeFactory shapeFactory;
    IShapeAction group;
    public GroupCommand(ShapeList selected, ShapeList GroupList, ShapeList shapeList, ShapeList outlineList){
        this.selected = selected;
        this.GroupList = GroupList;
        this.shapeList = shapeList;
        this.outlineList = outlineList;

        shapeFactory = new ShapeFactory();
    }

    @Override
    public void run(){
        for(IShapeAction out : outlineList.getShapesList()){
            selected.remove(out);
        }
        outlineList.clear();
        outlineList.reset();
        group = new GroupList(selected,GroupList);
        for(IShapeAction shape : selected.getShapesList()){
            shapeList.remove(shape);
        }
        selected.reset();
        GroupList.add(group);
        ShapeOptions temp = new ShapeOptions();
        temp.setPrimaryShapeColor(ShapeColor.BLACK);
        temp.setShapeType(ShapeType.RECTANGLE);
        temp.setShapeShadingType(ShapeShadingType.OUTLINE);
        temp.setStartPoint(group.getAdjustedStartPoint());
        temp.setEndPoint(group.getAdjustedEndPoint());
        IShapeAction outline = shapeFactory.createShape(temp);
        this.outlineList.add(outline);
        selected.add(group);
        shapeList.drawList();
        GroupList.drawList();
        this.outlineList.drawList();
        CommandHistory.add(this);
    }
    public void redo(){
        GroupList.add(group);
        for(IShapeAction shape : selected.getShapesList()){
            shapeList.remove(shape);
        }
        selected.reset();
        ShapeOptions temp = new ShapeOptions();
        temp.setPrimaryShapeColor(ShapeColor.BLACK);
        temp.setShapeType(ShapeType.RECTANGLE);
        temp.setShapeShadingType(ShapeShadingType.OUTLINE);
        temp.setStartPoint(group.getAdjustedStartPoint());
        temp.setEndPoint(group.getAdjustedEndPoint());
        IShapeAction outline = shapeFactory.createShape(temp);
        this.outlineList.add(outline);
        selected.add(group);
        shapeList.drawList();
        GroupList.drawList();
        this.outlineList.drawList();

    }
    public void undo(){
        selected.reset();
        for(IShapeAction shape : GroupList.getShapesList()){
            GroupList cur = (GroupList)shape;
            ArrayList<IShapeAction> groupList = cur.getGroup();
            for(IShapeAction ori : groupList){
                shapeList.add(ori);
                selected.add(ori);
            }
        }
        GroupList.reset();
        GroupList.clear();
        outlineList.reset();
        shapeList.drawList();
        GroupList.drawList();

    }
}