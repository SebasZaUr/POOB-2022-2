
/**
 * Me permite crear y manipular todas las maquinas.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas Hernadez
 * @version 1.0
 */
public class Ubicacion {

    private int longitud;
    private int latitud;

    /**
     * Me permite sumarle uno a mi latitud
     */
    public void alNorte() throws BatallaNavalExcepcion {
        if (latitud < 90 && latitud > -90) {
            latitud++;
        }
        if (latitud == 90) {
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.alNorteProblem);
        }
    }

    /**
     * Me permite sumarle o restarle a mi longitud y latitud dos posiciones dadas
     * por el usuario
     * 
     * @param la distancia que voy avanzar o retroceder en longitud dLon
     * @param la distancia que voy avanzar o retroceder en latitud dLat
     */
    public void avance(int dLon, int dLat) {
        if (latitud <= 90 && latitud >= -90) {
            if (longitud <= 180 && longitud >= 0) {
                longitud = dLon;
                latitud = dLat;
            }
        }
    }

    /**
     * Me verifica si en la posicion dada hay alguna maquina aliada
     * 
     * @param La posicion que quiero comprovar longitud
     * @param La posicion que quiero comprovar Latitud
     * @return Si fue destruida la maquina o no
     */
    public boolean seranDestruidas(int longitud, int latitud) {
        boolean destruidos = false;
        if (longitud == this.longitud && latitud == this.latitud) {
            destruidos = true;
        }
        return destruidos;
    }
}
