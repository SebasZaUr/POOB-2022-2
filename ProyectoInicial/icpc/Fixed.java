package icpc;

/**
 *Let me create and modify the fixed's routes.
 * 
 * @author Daniel Felipe Rojas Hernandez
 * @author Sebastian Zamora Urrego 
 * @version 1.0
 */
public class Fixed extends Route
{   
    /**
     * Let me create a fixed's route.
     * 
     */
    public Fixed(String interA, String interB,Intersection IntersecA,Intersection IntersecB){
        intersections.put(interA, IntersecA);
        intersections.put(interB, IntersecB);
        route = conectIntersection(IntersecA,IntersecB);
        this.type = "Fixed";
    }

    public void putSign (String intA, String intB, int speedLimit,String type) throws ICPCException{
        if(!hasSign){
            this.hasSign = true;
            int coordX = 0;
            int coordY = 0;
            String key = intA+","+intB+","+String.valueOf(speedLimit);
            for(Intersection i: intersections.values()){
                coordX += i.getCoordCenterX();
                coordY += i.getCoordCenterY();
            }
            if(type == "NormalSing"){
                signs.put(key, new NormalSign(coordX,coordY,speedLimit));
            }
            else if(type == "Twin"){
                int coordXa = intersections.get(intA).getCoordCenterX();
                int coordYa = intersections.get(intA).getCoordCenterY();
                int coordXb = intersections.get(intB).getCoordCenterX();
                int coordYb = intersections.get(intB).getCoordCenterY();
                signs.put(key,new Twin(coordXa,coordYa,coordXb,coordYb,speedLimit));
            }
            else if(type == "Caution"){
                signs.put(key,new Cautious(coordX,coordY,speedLimit));
            }
        }
        else{
            throw new ICPCException(ICPCException.SignExist);
        }
    }

    public void removeSign() throws ICPCException{
        throw new ICPCException(ICPCException.FixedException);
    }
}
