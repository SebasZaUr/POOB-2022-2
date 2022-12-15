package icpc;

/**
 *Let me create and modify the Caution's sign.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class Cautious extends Sign
{
    /**
     * Let me create the Cautious sign
     * @Param one of the intersections on the route of the sign: intersectionA.
     * @Param The other intersection of the route: intersectionB.
     * @Param The limit that show the sign: SpeedLimit
    */
    public Cautious(int coordX,int coordY, int SpeedLimit){
        this.SpeedLimit = SpeedLimit;
        this.coordX = coordX/2;
        this.coordY = coordY/2;
        this.type = "Cautious";
    }
    
    /**
     * Let me show the Cautious's sign.
    */
    public void makeVisibleSign(){
        pintar((coordX),(coordY));
    }
}
