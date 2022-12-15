package icpc;
import shapes.Circle;

/**
 * Let me create and modify Normal's Intersection.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class NormalIntersection extends Intersection
{
    /**
     * Let me create a normal's intersection.
     * 
     * @param the color of the intersection: color.
     * @param the Xposition of the intersection: x.
     * @param the Yposition of the intersection: y.
    */
    public NormalIntersection(String color, int x, int y){
        intersection = new Circle(color, x, y);
    }
    
    /**
     * Let me create a route.
     * @param the other intersection what conect whit the route: partner
     * @param the type of the route: type.
     * @throws if the route exist: ICPCException
     */
    public void addRoute(Intersection partner,String type)throws ICPCException{
        String colors = this.getColor()+","+partner.getColor();
        if(!(routes.containsKey(colors))){
            if(type == "Fixed"){
                routes.put(colors, new Fixed(this.getColor(),partner.getColor(),this,partner));
            }
            else if(type == "Rebel"){
                routes.put(colors, new Rebel(this.getColor(),partner.getColor(),this,partner));
            }
            else if(type == "Normal"){
                routes.put(colors, new NormalRoute(this.getColor(),partner.getColor(),this,partner));
            }
            partner.setRoute(colors,this.getRoutes().get(colors));
        }
        else{
            throw new ICPCException(ICPCException.RouteExist);
        }
    }
    
    /**
     * Let me remove all the routes that has the intersection.
     * 
     */
    public void removeRoutes(){
        routes.clear();
        delPartners();
    }
    
    /**
     * Let me remove a route of the intersection.
     * @param the key o name of the route; intAB.
     * @throws if the route doesn't exist: ICPCException
     */
    public void removeRoute(String intAB) throws ICPCException{
        String[] values = intAB.split(",");
        if(this.getColor() == values[0]){
            delPartner(values[1]);
        }
        else{
            delPartner(values[0]);
        }
        if(routes.containsKey(intAB)){
            routes.remove(intAB);
        }
        else{
            throw new ICPCException(ICPCException.RouteNotExist);
        }
    }
}


