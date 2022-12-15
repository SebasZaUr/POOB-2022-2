package icpc;
import shapes.Canvas;
import shapes.Triangle;

/**
 * Let me create and modify signs.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 2.5
 */
public abstract class Sign
{
    protected int SpeedLimit;
    protected String usefull = "yes";
    protected int coordX;
    protected int coordY;
    protected String type = "Normal";
    protected boolean isVisible = true;
    /**
     * get the limit that has the sign
     * @return the value of limit: SpeedLimit.
     */
    public int getLimit(){
        return SpeedLimit;
    }
    
    /**
     * Let me define if the signal is wrong or unnesesary
     * @param the usefulness of the sign: tipo
    */
    
    public void isUseful(String tipo){
        usefull = tipo;
    }
    
    /**
     * get the usefulness of the sign.
     * @return the usefulness of the sign: usefull.
     */
    public String getType(){
        return usefull;
    }
    
    /**
     * Draw the sign in Canvas
     */
    public abstract void makeVisibleSign();
    
    /**
     * Let me errase all signs on canvas.
     */
    public void makeInvisibleSign(){
        isVisible = false;
        if(!isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Let me identify the way of draw the sign
     * 
     */
    protected void pintar(int cordx,int cordy){
        Canvas canvas = Canvas.getCanvas();
        if(this.SpeedLimit != 0 ){
            if(usefull == "UnNecesary"){
                canvas.print(Integer.toString(SpeedLimit),(cordx),(cordy)+20,"darkGray");
            }
            if(usefull == "Wrong"){
                canvas.print(Integer.toString(SpeedLimit),(cordx),(cordy)+20,"red"); 
            }
            if(usefull == "yes"){
                canvas.print(Integer.toString(SpeedLimit),(cordx),(cordy),"black"); 
            }
        }
    }
}