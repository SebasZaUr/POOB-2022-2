package icpc;
import shapes.Circle;
import shapes.Canvas;

/**
 * Let me create and modify Needy's Intersection.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class Needy extends Intersection{
    /**
     * Let me create a normal's intersection.
     * 
     * @param the color of the intersection: color.
     * @param the Xposition of the intersection: x.
     * @param the Yposition of the intersection: y.
    */
    public Needy(String tipo,String color, int x,int y)
    {
        type = tipo;
        intersection = new Circle(color,x,y);
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
        }
        else{
            throw new ICPCException(ICPCException.RouteExist);
        }
    }
    
    /**
     * Let me remove a route of the intersection. If the intersection doesn't have a route inmediaty
     * errase the intersection
     * @param the key o name of the route; intAB.
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
        if(routes.isEmpty()){
            intersection = null;
        }
    }
    
    /**
     * Let me remove all routes of the intersection. If the intersection doesn't have a route inmediaty
     * erase the intersection
     */
    public void removeRoutes(){
        delPartners();
        routes.clear();
        erase();
        intersection = null;
    }
    
    /**
     * Let me errase an intersection on Canvas
     */
    private void erase(){
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(this.intersection);
    }
    
    /**
     * Let me know if the intersection has routes
     */
    public boolean routesEmpty(){
        if(routes.isEmpty()) return true;
        return false;
    }
}

