package controller;

import java.util.ArrayList;

import view.interfaces.IUndoable;

public class DeleteCommand implements ICommand, IUndoable {
    ShapeList selectedList;
    ShapeList shapeList;
    ShapeList outlineList;
    ShapeList GroupList;
    ArrayList<IShapeAction> undo_redo_list;
    public DeleteCommand (ShapeList selectedList, ShapeList shapeList,ShapeList outlineList,ShapeList GroupList){
        this.selectedList = selectedList;
        this.shapeList = shapeList;
        this.outlineList = outlineList;
        this.GroupList = GroupList;
        this.undo_redo_list = new ArrayList<>();
    }
    public void run(){
        for(IShapeAction shape : selectedList.getShapesList()){
            shapeList.remove(shape);
            this.undo_redo_list.add(shape);
        }
        CommandHistory.add(this);
        shapeList.clear();
        shapeList.drawList();
        GroupList.drawList();
    }
    public void undo(){
        for(IShapeAction shape : undo_redo_list){
            shapeList.add(shape);
        }
        shapeList.drawList();
        GroupList.drawList();
    }
    public void redo(){
        for(IShapeAction shape : undo_redo_list){
            shapeList.remove(shape);
        }
        shapeList.clear();
        shapeList.drawList();
        GroupList.drawList();

    }
    
}