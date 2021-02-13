package controller;

import model.persistence.ApplicationState;
import view.interfaces.IUndoable;


public class CreateShapeCommand implements ICommand,IUndoable {
    ShapeFactory shapeFactory = new ShapeFactory();
    private final ApplicationState appState;
    private ShapeOptions shapeOptions;
    private ShapeList shapeList;
    private IShapeAction shape;

    public CreateShapeCommand(ApplicationState appState,ShapeList shapeList) {
        this.appState = appState;
        this.shapeList = shapeList;
    }


    @Override
    public void run(){
        shapeOptions = appState.getCurrentState();
        shape = shapeFactory.createShape(shapeOptions);
        shapeList.add(shape);
        CommandHistory.add(this);
        shapeList.drawList();
    }
    public void redo(){
        shapeList.add(shape);
        shapeList.drawList();
    }
    public void undo(){
        shapeList.remove(shape);
        shapeList.drawList();
    }
}