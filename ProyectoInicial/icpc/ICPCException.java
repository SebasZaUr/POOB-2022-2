package icpc;


/**
 * Write a description of class ICPCExeption here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ICPCException extends Exception
{
    public static final String IntersectionExist = "Ya existe la interseccion";
    public static final String IntersectionNotExist = "No existe la interseccion";
    public static final String IntersectionRouteIntersection = "No existe alguna de las dos intersecciones para crear la ruta";
    public static final String RouteExist = "Ya existe la ruta";
    public static final String RouteNotExist = "No existe la ruta";
    public static final String SignExist = "Ya existe la señal";
    public static final String SignNotExist = "No existe la señal";
    public static final String RebelException = "No se pueden agregar señales a una ruta rebel";
    public static final String FixedException = "No se pueden quitar señales a una ruta fixed";
    public static final String HermitException = "Ya tiene una ruta";
    
    /**
     * Lanza las exepciones creadas.
     * @param Recibe un mensaje String msm
    */
    public ICPCException(String msm){
        super(msm);
    }
}
