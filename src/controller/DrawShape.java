package controller;

import view.interfaces.*;

import java.awt.*;
import model.persistence.ApplicationState;


public class DrawShape {
    private final PaintCanvasBase paintCanvas;
    private final ApplicationState appState;

    public DrawShape(PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }
    public void draw(ShapeList shapeList) {
        Graphics2D g = paintCanvas.getGraphics2D();
        for(IShapeAction shape : shapeList.getShapesList())
            shape.draw(g);
    }
    
    public void clear() {
    	Graphics2D g = paintCanvas.getGraphics2D();
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, 5000, 5000);
    }
}
