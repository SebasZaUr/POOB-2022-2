package icpc;
import shapes.Circle;

/**
 * Let me create and modify Hermit's Intersection.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class Hermit extends Intersection
{
    
    /**
     * Let me create a normal's intersection.
     * 
     * @param the color of the intersection: color.
     * @param the Xposition of the intersection: x.
     * @param the Yposition of the intersection: y.
    */
    public Hermit(String type,String color, int x, int y)
    {
        this.type = type;
        intersection = new Circle(color,x,y);
    }

    /**
     * Let me add one route in the intersection.
     * @param the other intersection what conect whit the route: partner
     * @param the type of the route: type.
     * @throws if the route exist: ICPCException
     */
    public void addRoute(Intersection partner,String type)throws ICPCException{
        if(partners.isEmpty()){
            partners.put(partner.getColor(), partner);
            String colors = this.getColor()+","+partner.getColor();
            if(type == "Fixed"){
                routes.put(colors, new Fixed(this.getColor(),partner.getColor(),this,partner));
            }
            else if(type == "Rebel"){
                routes.put(colors, new Rebel(this.getColor(),partner.getColor(),this,partner));
            }
            else if(type == "Normal"){
                routes.put(colors, new NormalRoute(this.getColor(),partner.getColor(),this,partner));
            }
        }
        else{
            throw new ICPCException(ICPCException.HermitException);
        }
    }

    /**
     * Let me remove a route of the intersection.
     * @param the key o name of the route; route.
     * @throws if the route doesn't exist: ICPCException
     */
    public void removeRoute(String intAB) throws ICPCException{
        if(routes.containsKey(intAB)){
            routes.remove(intAB);
        }
        else{
            throw new ICPCException(ICPCException.RouteNotExist);
        }
        String[] values = intAB.split(",");
        if(this.getColor() == values[0]){
            delPartner(values[1]);
        }
        else{
            delPartner(values[0]);
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
}
