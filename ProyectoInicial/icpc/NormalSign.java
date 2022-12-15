package icpc;

/**
 *Let me create and modify the Normal's sign.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class NormalSign extends Sign
{
    /**
     * Let me create a Normal's sign
     * @Param one of the intersections on the route of the sign: intersectionA.
     * @Param The other intersection of the route: intersectionB.
     * @Param The limit that show the sign: SpeedLimit
     */
    public NormalSign(int coordX,int coordY, int speedLimit){
        this.SpeedLimit = speedLimit;
        this.coordX = coordX/2;
        this.coordY = coordY/2;
    }
    
    /**
     * Let me draw a simple sign on canvas
     */
    public void makeVisibleSign(){
        pintar((coordX),(coordY));
    }
}
