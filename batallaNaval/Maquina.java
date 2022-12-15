import java.util.List;
import java.util.ArrayList;

/**
 * Me permite crear y manipular todas las maquinas.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas Hernadez
 * @version 1.0
 */
public abstract class Maquina {
    protected String tipo;
    private Ubicacion ubicacion = new Ubicacion();

    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la ubicacion de la maquina.
     * 
     * @return Devuelve la ubicacion de la maquina ubicacion
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Mueve una posicion al norte la maquina
     */
    public void alNorte() throws BatallaNavalExcepcion {
        try {
            ubicacion.alNorte();
        } catch (BatallaNavalExcepcion excepcion) {
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.alNorteProblem);
        }
    }

    /**
     * Hace avanzar n posiciones a la maquina
     * 
     * @param dLon
     * @param dLat
     */
    public void avance(int dLon, int dLat) {
        ubicacion.avance(dLon, dLat);
    }

    /**
     * Determina si en esa ubicacion hay una maquina
     * 
     * @param longitud
     * @param latitud
     * @return Si hay una maquina o no
     */
    abstract boolean seranDestruidas(int longitud, int latitud);

    /**
     * Determina si en esa ubicacion hay una maquina debil
     * 
     * @param longitud
     * @param latitud
     * @return Si hay una maquina o no
     */
    abstract boolean maquinasDebiles();

    public ArrayList<Avion> aviones() {
        return PortaAvion.Aviones();
    }

    abstract ArrayList<Marinos> marino();
}
