package icpc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class icpcTest.
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version
 * @version 1.0
 */
public class icpcTest
{
    
    @Test
    public void shouldCreateIntersections(){
        ICPC icpc = new ICPC(500,500);
        try{
            icpc.addIntersection("blue",10,20);
            icpc.addIntersection("black",100,200);
            icpc.addIntersection("orange",130,250);
            icpc.addIntersection("green",105,202);
            icpc.addIntersection("yellow",310,250);
            icpc.addIntersection("gray",130,80);
            icpc.routeSpeedLimit("gray","orange",13);
            icpc.routeSpeedLimit("orange","yellow",12);
            icpc.routeSpeedLimit("gray","green",10);
        }
        catch(ICPCException e){
            String excepcion = e.getMessage();
            assertEquals("Ya existe la interseccion",excepcion);
        }
    }
    
    @Test
    public void shouldCreateRoutes(){
        ICPC icpc = new ICPC(500,500);
        try{
            icpc.addIntersection("blue",10,20);
            icpc.addIntersection("black",100,200);
            icpc.addIntersection("orange",130,250);
            icpc.addIntersection("green",105,202);
            icpc.addIntersection("yellow",310,250);
            icpc.addIntersection("gray",130,80);
            icpc.routeSpeedLimit("gray","orange",13);
            icpc.routeSpeedLimit("orange","yellow",12);
            icpc.routeSpeedLimit("gray","green",10);
        }
        catch(ICPCException e){
            String excepcion = e.getMessage();
            assertEquals("Ya existe la interseccion",excepcion);
        }
    }
    
    @Test
    public void shouldCreateSigns(){
        ICPC icpc = new ICPC(500,500);
        try{
            icpc.addIntersection("blue",10,20);
            icpc.addIntersection("black",100,200);
            icpc.addIntersection("orange",130,250);
            icpc.addIntersection("green",105,202);
            icpc.addIntersection("yellow",310,250);
            icpc.addIntersection("gray",130,80);
            icpc.routeSpeedLimit("gray","orange",13);
            icpc.routeSpeedLimit("orange","yellow",12);
            icpc.routeSpeedLimit("gray","green",10);
        }
        catch(ICPCException e){
            String excepcion = e.getMessage();
            assertEquals("Ya existe la interseccion",excepcion);
        }
    }
    
    @Test
    public void shouldDeleteIntersectionsRoutesAndSings(){
        ICPC icpc = new ICPC(500,500);
        try{
            icpc.addIntersection("blue",10,20);
            assertEquals(true,icpc.ok());
            icpc.addIntersection("black",100,200);
            assertEquals(true,icpc.ok());
            icpc.addIntersection("pink",105,220);
            assertEquals(true,icpc.ok());
            icpc.addIntersection("orange",130,250);
            assertEquals(true,icpc.ok());
            icpc.addRoute("blue","black");
            assertEquals(true,icpc.ok());
            icpc.addRoute("orange","black");
            assertEquals(true,icpc.ok());
            icpc.putSign("blue","black",8);
            assertEquals(true,icpc.ok());
            icpc.putSign("pink","blue",37);
            assertEquals(true,icpc.ok());
            icpc.delIntersection("pink");
            assertEquals(true,icpc.ok());
            icpc.delRoad("orange","black");
            assertEquals(true,icpc.ok());
        }
        catch(ICPCException e){
            fail("Threw an Exception");
        }
    }
}
