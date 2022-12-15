
package icpc;

/**
 *Let me create and modify the rebel's routes.
 * 
 * @author Daniel Felipe Rojas Hernandez
 * @author Sebastian Zamora Urrego 
 * @version 1.0
 */
public class Rebel extends Route
{   
    public Rebel(String interA, String interB,Intersection IntersecA,Intersection IntersecB){
        intersections.put(interA, IntersecA);
        intersections.put(interB, IntersecB);
        route = conectIntersection(IntersecA,IntersecB);
        this.type = "Rebel";
    }
    
    public void putSign (String intA, String intB, int speedLimit, String type) throws ICPCException{
        throw new ICPCException(ICPCException.RebelException);
    }
    
    public void removeSign() throws ICPCException{
        throw new ICPCException(ICPCException.SignNotExist);
    }
}
