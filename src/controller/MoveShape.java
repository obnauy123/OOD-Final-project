package controller;

import model.persistence.ApplicationState;
import view.interfaces.IUndoable;

public class MoveShape implements ICommand,IUndoable {
    ShapeList selectedList;
    ShapeList shapeList;
    ShapeOptions shapeOption;
    ApplicationState appstate;
    ShapeList movedShape;
    ShapeList outlineList;
    ShapeList GroupList;
    int x;
    int y;

    public MoveShape(ApplicationState appstate, ShapeList shapeList, ShapeList selectedList, ShapeList movedShape, ShapeList outlineList,ShapeList GroupList){
        this.selectedList = selectedList;
        this.shapeList = shapeList;
        this.appstate = appstate;
        this.movedShape = movedShape;
        this.outlineList = outlineList;
        this.GroupList = GroupList;
    }
    @Override
    public void run(){
        x = appstate.getEndPoint().getX() - appstate.getStartPoint().getX();
		y = appstate.getEndPoint().getY() - appstate.getStartPoint().getY();
		for (IShapeAction selected : selectedList.getShapesList()) {
			selected.moveAdjustedStartPoint(x, y);
            selected.moveAdjustedEndPoint(x, y);
        }
        for(IShapeAction outline : outlineList.getShapesList()){
            outline.moveAdjustedStartPoint(x, y);
            outline.moveAdjustedEndPoint(x, y);
        }

        CommandHistory.add(this);
        shapeList.clear();
        shapeList.drawList();
        GroupList.drawList();
        outlineList.drawList();
        
    }
    public void redo(){
		for (IShapeAction selected : selectedList.getShapesList()) {
            
			selected.moveAdjustedStartPoint(x, y);
            selected.moveAdjustedEndPoint(x, y);
  
        }
        for(IShapeAction outline : outlineList.getShapesList()){
           
            outline.moveAdjustedStartPoint(x, y);
            outline.moveAdjustedEndPoint(x, y);

        }
        shapeList.clear();
        shapeList.drawList();
        GroupList.drawList();
        outlineList.drawList();
    }
    public void undo(){
       
        for (IShapeAction selected : selectedList.getShapesList()) {
            
			selected.moveAdjustedStartPoint(-x, -y);
            selected.moveAdjustedEndPoint(-x, -y);    
        }
        for(IShapeAction outline : outlineList.getShapesList()){

            outline.moveAdjustedStartPoint(-x, -y);
            outline.moveAdjustedEndPoint(-x, -y); 
        }
		shapeList.clear();
        shapeList.drawList();
        GroupList.drawList();
        outlineList.drawList();
        
    }   
}