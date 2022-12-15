import java.util.ArrayList;

/**
 * Me permite crear y manipular cada barco.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas
 * @version 1.0
 */
public class Barco extends Maquina {
    private int numero;
    private ArrayList<Marinos> marinos = new ArrayList<>();

    public Barco() {
        tipo = "Barco";
    }

    /**
     * Me permite saber si un barco puede ser destruido
     * 
     * @param La posicion longitud
     * @param La posicion latitud
     * @return Si sera destruido o no
     */
    public boolean seranDestruidas(int longitud, int latitud) {
        boolean destruido = false;
        if (super.getUbicacion().seranDestruidas(longitud, latitud)) {
            destruido = true;
        }
        return destruido;
    }

    /**
     * Me permite saber si un barco tiene los suficientes marinos.
     * 
     * @return Si tiene o no los marinos suficientes para funcionar.
     */
    public boolean maquinasDebiles() {
        if (marinos.size() < 5) {
            return true;
        }
        return false;
    }

    public ArrayList<Marinos> marino() {
        return marinos;
    }
}
