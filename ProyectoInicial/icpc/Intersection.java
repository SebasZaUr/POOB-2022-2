package icpc;
import shapes.Circle;
import java.util.HashMap;

/**
 * Let me create and modify Intersections.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 3.0
 */
public abstract class Intersection{
    protected Circle intersection;
    protected HashMap<String,Intersection> partners = new HashMap<>();
    protected HashMap<String,Route> routes = new HashMap<>();
    protected String type = "Normal";
    
    /**
     * Let me create a route.
     * @param the other intersection what conect whit the route: partner
     * @param the type of the route: type.
     * @throws if the route exist: ICPCException
     */
    public abstract void addRoute(Intersection partner,String type)throws ICPCException;
    
    /**
     * Let me remove all the routes that has the intersection.
     */
    public abstract void removeRoutes();
    
    /**
     * Let me remove a route of the intersection.
     * @param the key o name of the route; intAB.
     * @throws if the route doesn't exist: ICPCException
     */
    public abstract void removeRoute(String intAB) throws ICPCException;

    protected void delPartner(String partner){
        if(partners.containsKey(partner))partners.remove(partner);
    }

    protected void delPartners(){
        partners.clear();
    }

    /**
     * Let me know which is the tyoe of the intersection that I have.
     */
    public String getType(){
        return type;
    }
    
    /**
     * Draw an intersection on Canvas
     */
    public void makeVisibleInt(){
        if(intersection != null)intersection.makeVisible();
        for(String i: routes.keySet()){
            routes.get(i).makeVisibleRoute();
        }
    }
    
    /**
     * Erase an intersection on Canvas
     */
    public void makeInvisibleInt(){
        intersection.makeInvisible();
    }
    
    /**
     * Get the xPosition of the Intersection's Center
     * @return the xPosition
     */
    public int getCoordCenterX(){
        return intersection.getCenter()[0];
    }

    /**
     * Get the yPosition of the Intersection's Center
     * @return the yposition 
     */
    public int getCoordCenterY(){
        return intersection.getCenter()[1];
    }

    /**
     * Get the color of an Intersection
     * @return the string of intersection's color
     */
    public String getColor(){
        return intersection.getColor();
    }

    public HashMap<String,Route> getRoutes(){
        return routes;
    }

    /**
     * Get the xPosition of an Intersection
     * @return the xposition
     */
    public int positionX(){
        return intersection.getPositionX();
    }

    /**
     * Get the yPosition of an Intersection
     * @return the yposition
     */
    public int positionY(){
        return intersection.getPositionY();
    }

    /**
     * Let me now if the intersection have a partner
     */
    public boolean isPartner(Intersection inter){
        if(partners.containsValue(inter))return true;
        return false;
    }

    /**
     * Join two intersections
     * @param other
     */
    public void setPartner(Intersection other){
        partners.put(other.getColor(),other);
    }

    public void setRoute(String key,Route route){
        routes.put(key,route);
    }

    /**
     * Let me change the type of the intersection
     */
    public void setType(String type){
        this.type = type;
    }
}

