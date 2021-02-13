package controller;
import model.ShapeColor;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class Colors {
    private static final Map<ShapeColor, Color> color = new HashMap<ShapeColor, Color>();
    
    static {
        color.put(ShapeColor.BLACK, Color.BLACK);
        color.put(ShapeColor.BLUE, Color.BLUE);
        color.put(ShapeColor.CYAN, Color.CYAN);
        color.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        color.put(ShapeColor.GRAY, Color.GRAY);
        color.put(ShapeColor.GREEN, Color.GREEN);
        color.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        color.put(ShapeColor.MAGENTA, Color.MAGENTA);
        color.put(ShapeColor.ORANGE, Color.ORANGE);
        color.put(ShapeColor.PINK, Color.PINK);
        color.put(ShapeColor.RED, Color.RED);
        color.put(ShapeColor.WHITE, Color.WHITE);
        color.put(ShapeColor.YELLOW, Color.YELLOW);
    }
    
    public static Color getColor(ShapeColor shapeColor) {
        return color.get(shapeColor);
    }

}