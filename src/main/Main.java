package main;

import controller.ShapeList;
import controller.DrawShape;
import controller.IJPaintController;
import controller.JPaintController;
import controller.MyMouseHandler;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;




public class Main {
    
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        DrawShape drawShape = new DrawShape(paintCanvas,appState);
        ShapeList copyList = new ShapeList(drawShape);
        ShapeList shapeList = new ShapeList(drawShape);
        ShapeList pasteList = new ShapeList(drawShape);
        ShapeList selectedList = new ShapeList(drawShape);
        ShapeList outlineList = new ShapeList(drawShape);
        ShapeList moveList = new ShapeList(drawShape);
        ShapeList GroupList = new ShapeList(drawShape);
        IJPaintController controller = new JPaintController(uiModule, appState,shapeList,copyList,selectedList,pasteList,outlineList,GroupList);
        controller.setup();
        MyMouseHandler mouse = new MyMouseHandler(paintCanvas,appState,shapeList,selectedList, moveList,copyList,pasteList,outlineList,GroupList);
        paintCanvas.addMouseListener(mouse);
        // For example purposes only; remove all lines below from your final project.
        

        // try {
        //     Thread.sleep(500);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // // Filled in rectangle
        // Graphics2D graphics2d = paintCanvas.getGraphics2D();
        // graphics2d.setColor(Color.GREEN);
        // graphics2d.fillRect(12, 13, 200, 400);

        // // Outlined rectangle
        // graphics2d.setStroke(new BasicStroke(5));
        // graphics2d.setColor(Color.BLUE);
        // graphics2d.drawRect(12, 13, 200, 400);

        // // Selected Shape
        // Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        // graphics2d.setStroke(stroke);
        // graphics2d.setColor(Color.BLACK);
        // graphics2d.drawRect(7, 8, 210, 410);
    }
}
