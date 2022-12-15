package icpc;
import java.util.HashMap;

/**
 * Let me create and modify Intersections.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 2.0
 */
public abstract class Route
{
    protected Line route;
    protected HashMap<String, Intersection> intersections = new HashMap<>();
    protected HashMap<String, Sign> signs = new HashMap<>();
    protected int speedLimit;
    protected boolean hasSign = false;
    protected String type = "Normal";
    
    /**
     * Let me create the visual representation of the route
     * @param one of the intersection of the route: interA.
     * @param the other intrsection of the route: interB.
     */
    public Line conectIntersection(Intersection interA, Intersection interB){
        if(!interA.isPartner(interB)){
            interA.setPartner(interB);
            interB.setPartner(interA);
            Line connection = new Line((double) interA.getCoordCenterX(), (double) interA.getCoordCenterY(),(double) interB.getCoordCenterX(),(double) interB.getCoordCenterY());
            return connection;
        }
        return null;
    }
    
    /**
     * Let me put a signal in the route.
     * @param one of the intersection of the route: interA.
     * @param the other intrsection of the route: interB.
     * @param the value of the limit that has the signal: speedLimit.
     * @param the type of sign that it put: type.
     * @throws if the signal exist: ICPCException.
    */
    public abstract void putSign(String intA,String intB,int speedLimit,String type) throws ICPCException;
    
    /**
     * Let me remove the signal in the route.
     * @throws If the route doesn't exist: ICPCException.
     */
    public abstract void removeSign() throws ICPCException;
    
    /**
     * get the sign in the route.
     * @return the sign in the route.
     */
    public HashMap<String, Sign> getSign(){
        return signs;
    }

    protected String belongingSigns(){
        String route = ""; 
        for(String i: intersections.keySet())
            route += i;
        return route;
    }
    
    /**
     * get the intersections of the route
     * @return the list of the intersections
     */
    public HashMap<String,Intersection> getIntersections(){
        return intersections;
    }
    
    /**
     * get the line route
     * @return the line that represent the route
     */
    public Line getRoute(){
        return route;
    }
    
    public int getLimit(){
        return speedLimit;
    }
    
    public  void setSpeedLimit(int speed){
        speedLimit = speed;
    }
    
    /**
     * Draw the route
     */
    public void makeVisibleRoute(){
        if(route != null)route.draw("black");
        for(String i: signs.keySet()){
            if(signs.get(i) != null) signs.get(i).makeVisibleSign();
        }
    }
    
    /**
     * erase the route
     */
    public void makeInvisibleRoute(){
        if(route != null)route.erase();
        for(String i: signs.keySet()){
            if(signs.get(i) != null) signs.get(i).makeInvisibleSign();
        }
    }
}
