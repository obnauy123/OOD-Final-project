package controller;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class MyMouseHandler extends MouseAdapter{
    Point begin;
    Point end;
    PaintCanvasBase paint;
    ApplicationState appstate;
    ShapeList shapeList;
    ShapeList selectedList;
    ShapeList moveList;
    ShapeList copyList;
    ShapeList pasteList;
    ShapeList outlineList;
    ShapeList GroupList;
    public MyMouseHandler(PaintCanvasBase p, ApplicationState aps, ShapeList shapeList, ShapeList selectedList, ShapeList moveList, ShapeList copyList,ShapeList pasteList,ShapeList outlineList,ShapeList Grouplist) {
        this.paint = p;
        this.appstate = aps;
        this.shapeList = shapeList;
        this.selectedList = selectedList;
        this.moveList = moveList;
        this.copyList = copyList;
        this.pasteList = pasteList;
        this.outlineList = outlineList;
        this.GroupList = Grouplist;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        final int a = e.getX();
        final int b = e.getY();
        begin = new Point(a, b);
        appstate.setStartPoint(begin);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        final int a = e.getX();
        final int b = e.getY();
        end = new Point(a, b);
        appstate.setEndPoint(end);

        switch(appstate.getActiveMouseMode()) {
            case DRAW:
                ICommand createShape = new CreateShapeCommand(this.appstate, this.shapeList);
                createShape.run();
                break;
            case SELECT:
                SelectShape selectShape = new SelectShape(appstate, shapeList, selectedList,copyList,pasteList,outlineList,GroupList);
                selectShape.run();
                break;
            case MOVE:
                MoveShape moveShapes = new MoveShape(appstate, shapeList, selectedList, moveList, outlineList,GroupList);
                moveShapes.run();
                break;
            

        }
        
    }
}