package icpc;
import shapes.Canvas;
import java.awt.geom.Line2D;

/**
 * Let me create and modify Intersections.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 2.0
 */
public class Line
{
    private boolean isVisible;
    private Line2D line;
    /**
     * Constructor for objects of class Line
     */
    public Line(double X1,double Y1,double X2,double Y2){
        line = new Line2D.Double(X1,Y1,X2,Y2);
    }

    /**
     * Draw a line in canvas
     * @ the line's color
     */
    public void draw(String color){
        isVisible = true;
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, line);
        }
    }

    /**
     * Erase a line in canvas
     * @ the line's color
     */   
    public void erase(){
        isVisible = false;
        if(!isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}