package Presentation;

/**
 * Me crea la parte visual del comodin de tipo Gun.
 * @author Sebastian Zamora Urrego.
 * @author Daniel Rojas Hernandez.
 *@version 1.0
 */
public class GunGUI extends ComodinGUI{
    private static GunGUI instance;
    private GunGUI(){
        message = "Gun: Dar doble click sobre la ficha enemiga a la que quiere eliminar";
    }

    /**
     * Me genera la instancia del comodin.
     * @return instance la instancia del comodin.
     */
    public static GunGUI getInstance(){
        if(instance == null){
            instance = new GunGUI();
        }
        return instance;
    }

}
