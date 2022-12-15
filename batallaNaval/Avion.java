import java.util.ArrayList;
import java.util.List;

/**
 * Me permite crear y manipular aviones.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas
 * @version 1.0
 */
public abstract class Avion extends Maquina {
    private String placa;
    private boolean enAire;
    private Marinos piloto;

    public Avion() {
        tipo = "Avion";
    }

    /**
     * Me permite saber si el avion esta en aire
     * 
     * @return Si esta en el aire el avion o no
     */
    public boolean inAir() {
        return enAire;
    }

    /**
     * Me permite saber si un avion puede ser destruido
     * 
     * @param La posicion longitud
     * @param La posicion latitud
     * @return Si sera destruido o no
     */
    public boolean seranDestruidas(int longitud, int latitud) {
        boolean destruido = false;
        if (!inAir()) {
            if (super.getUbicacion().seranDestruidas(longitud, latitud)) {
                destruido = true;
            }
        }
        return destruido;
    }

    /**
     * Me permite saber si un avion tiene piloto
     * 
     * @return Si tiene piloto o no
     */
    public boolean maquinasDebiles() {
        if (piloto != null) {
            return true;
        }
        return false;
    }

    public String getPlaca() {
        return placa;
    }

    public ArrayList<Marinos> marino() {
        ArrayList<Marinos> pilotos = new ArrayList<>();
        pilotos.add(piloto);
        return pilotos;
    }
}
