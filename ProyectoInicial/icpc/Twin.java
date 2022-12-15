package icpc;

/**
 *Let me create and modify the Twin's sign.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class Twin extends Sign 
{
    private int coordXa;
    private int coordXb;
    private int coordYa;
    private int coordYb;
    /**
     * Let me create the Twin's sign.
     * @Param one of the intersections on the route of the sign: intersectionA.
     * @Param The other intersection of the route: intersectionB.
     * @Param The limit that show the sign: SpeedLimit
     */
    public Twin(int coordXa,int coordYa,int coordXb,int coordYb, int SpeedLimit){
        this.SpeedLimit = SpeedLimit;
        this.coordXa = coordXa;
        this.coordYa = coordYa+45;
        this.coordXb = coordXb;
        this.coordYb = coordYb+45;
        this.type = "Twin";
    }    
    
    /**
     * Draw a sign on each intersection.
     */
    public void makeVisibleSign(){
        pintar((coordXa),(coordYa));
        pintar((coordXb),(coordYb));
    }
}
