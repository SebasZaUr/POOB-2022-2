import java.util.ArrayList;

/**
 * Me permite crear y manipular porta aviones.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas Hernandez
 * @version 1.0
 */
public class PortaAvion extends Barco {
   private int capacidad;
   private static ArrayList<Avion> aviones = new ArrayList<>();

   public PortaAvion() {
      tipo = "PortaAvion";
   }

   public static ArrayList<Avion> Aviones() {
      return aviones;
   }
}
