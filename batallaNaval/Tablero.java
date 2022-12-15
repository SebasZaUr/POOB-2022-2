import java.util.ArrayList;
import java.util.List;

/**
 * Me deja saber sobre todo lo que este colocado sobre el tablero.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas Hernadez
 * @version 1.0
 */

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Flota> flotas = new ArrayList<>();
    private String respuesta;

    /**
     * Determina si fue un buen ataque el que lanzo una flota contra otra
     * 
     * @param la posicion logitud
     * @param la posicion latitud
     * @param El nombre de la flota
     * @return Si es bueno o no el ataque
     */
    public boolean esBuenAtaque(int longitud, int latitud, String nombre) {
        boolean ataque = false;
        int contador = 0;
        while (!ataque && contador < flotas.size()) {
            if (!(flotas.get(contador).getNombre() == nombre)) {
                ataque = flotas.get(contador).comprobar(longitud, latitud);
            }
            contador++;
        }
        return ataque;
    }

    /**
     * Me permite saber cuantas flotas se pudieron mover al norte completas.
     * 
     * @return La cantidad de flotas Completos
     */
    public int alNorte() {
        int Completos = 0;
        for (Flota i : flotas) {
            i.alNorte();
            if (i.getRespuesta() == "Inicio") {
                Completos++;
            }
        }
        return Completos;
    }

    /**
     * Me deja saber cuales son las flotas que tienen pilotos infiltrados
     * 
     * @return Un arreglo de flotas flotasInfiltradas
     */
    public ArrayList<Flota> infiltrados() {
        ArrayList<Flota> flotasInfiltradas = new ArrayList<>();
        for (Flota i : flotas) {
            try {
                i.PilotosComprueba();
                flotaVacia(i);
            } catch (BatallaNavalExcepcion excepcion) {
                if (excepcion.getMessage() == "NoEsta") {
                    flotasInfiltradas.add(i);
                } else if (excepcion.getMessage() == "Cero") {
                    respuesta = BatallaNavalExcepcion.infiltradosProblem;
                    System.out.println(respuesta);
                }
            }
        }
        return flotasInfiltradas;
    }

    /**
     * Verifica si la flota tiene marineros asignados.
     * 
     * @param la flota i
     * @throws BatallaNavalExcepcion
     */
    public void flotaVacia(Flota i) throws BatallaNavalExcepcion {
        if (i.getTripulacion().size() == 0) {
            throw new BatallaNavalExcepcion("Cero");
        }
    }

    /**
     * Calcula el potencial total del tablero.
     * 
     * @return El potencial del tablero potencialTotal
     * @throws BatallaNavalExcepcion
     */
    public int potencia() throws BatallaNavalExcepcion {
        int potenciaTotal = 0;
        for (Flota i : flotas) {
            potenciaTotal += i.potencia();
        }
        try {
            mitad();
        } catch (BatallaNavalExcepcion excepcion) {
            respuesta = BatallaNavalExcepcion.potenciaProblem;
            System.out.println(respuesta);
        }
        return potenciaTotal;
    }

    /**
     * Verifica si mas de la mitad de las flotas tienen problemas de potencia.
     * 
     * @throws BatallaNavalExcepcion
     */
    public void mitad() throws BatallaNavalExcepcion {
        int problemasFlota = 0;
        for (Flota i : flotas) {
            if (i.getTripulacion().size() < i.getMaquinas().size()) {
                problemasFlota++;
            }
        }
        if (problemasFlota > (flotas.size()) / 2) {
            throw new BatallaNavalExcepcion(BatallaNavalExcepcion.potenciaProblem);
        }

    }
}