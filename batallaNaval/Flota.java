import java.util.List;
import java.util.ArrayList;

/**
 * Me permite crear y manipular toda la flota.
 *
 * @author Sebastian Zamora Urrego
 * @author Daniel Felipe Rojas Hernandez
 * @version 1.0
 */
public class Flota {
    private ArrayList<Maquina> maquinas = new ArrayList<>();
    private ArrayList<Marinos> Tripulantes = new ArrayList<>();
    private String nombre;
    private Tablero tablero;
    private String respuesta = "Inicio";

    /**
     * Mover todas las maquinas una posicion al norte
     */
    public void alNorte() {
        for (Maquina i : maquinas) {
            try {
                i.alNorte();
            } catch (BatallaNavalExcepcion ex) {
                respuesta = BatallaNavalExcepcion.alNorteProblem;
                System.out.println(respuesta);
            }
        }
    }

    /**
     * Mover todas las maquinas a una posicion definida
     * 
     * @param la posicion logitud
     * @param la posicion latitud
     */
    public void avance(int dLon, int dLat) {
        for (Maquina i : maquinas) {
            i.avance(dLon, dLat);
        }
    }

    /**
     * Comprueba si alguna maquina podra verse afecta
     * 
     * @param la posicion logitud
     * @param la posicion latitud
     * @return La lista de maquinas que fueron afectadas
     */
    public ArrayList<Maquina> seranDestruidas(int longitud, int latitud) {
        ArrayList<Maquina> destruidas = new ArrayList<>();
        for (Maquina i : maquinas) {
            if (i.seranDestruidas(longitud, latitud)) {
                destruidas.add(i);
            }
        }
        return destruidas;
    }

    /**
     * Me permite saber que maquinas debiles tengo en mi flota
     * 
     * @return El arreglo de las maquinas Debiles
     */
    public ArrayList<Maquina> maquinasDebiles() {
        ArrayList<Maquina> maquinaDebil = new ArrayList<>();
        for (Maquina i : maquinas) {
            if (i.maquinasDebiles()) {
                maquinaDebil.add(i);
            }
        }
        return maquinaDebil;
    }

    /**
     * Devuelve el nombre de la flota
     * 
     * @return El nombre de la flota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Comprueba si hubo alguna maquina afectada en esa posicion
     * 
     * @param la posicion logitud
     * @param la posicion latitud
     * @return Un buleano que me indica si fue un buen ataque o no
     */
    public boolean esBuenAtaque(int Longitud, int Latitud) {
        return tablero.esBuenAtaque(Longitud, Latitud, nombre);
    }

    /**
     * Comprueba si hubo alguna maquina afectada
     * 
     * @param la posicion logitud
     * @param la posicion latitud
     * @return Un buleano que me dice si hubo maquinas afectadas
     */
    public boolean comprobar(int longitud, int latitud) {
        ArrayList<Maquina> ataque = seranDestruidas(longitud, latitud);
        if (ataque.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Mueve cada maquina paso a paso hasta la posicion de ataque
     * 
     * @param la distancia log
     * @param la distancia lat
     */
    public void ataque(int log, int lat) {
        List<Maquina> maquinasDebiles = new ArrayList<Maquina>();
        maquinasDebiles = maquinasDebiles();
        for (Maquina i : maquinas) {
            if (!(maquinasDebiles.contains(i))) {
                maquinas.remove(i);
            }
        }
        avance(log, lat);
    }

    /**
     * Me permite saber la lista de pilotos de mi flota.
     * 
     * @return La lista de pilotos de la flota.
     * @throws BatallaNavalExcepcion
     */
    public ArrayList<Marinos> pilotos() throws BatallaNavalExcepcion {
        ArrayList<Marinos> pilotos = new ArrayList<>();
        try {
            pilotos = PilotosComprueba();
        } catch (BatallaNavalExcepcion excepcion) {
            if (excepcion.getMessage() == "Piloto no esta asignado al porta avion") {
                respuesta = BatallaNavalExcepcion.pilotosPortaAvionProblem;
                System.out.println(respuesta);
            } else if (excepcion.getMessage() == "Piloto no es marino") {
                respuesta = BatallaNavalExcepcion.pilotosProblem;
                System.out.println(respuesta);
            } else if (excepcion.getMessage() == "Piloto esta asignado a mas de un avion") {
                respuesta = BatallaNavalExcepcion.pilotoAvionesProblem;
                System.out.println(respuesta);
            }
        }
        return pilotos;
    }

    /**
     * Me permite comprobar si los pilotos de los aviones si pertenecen a la flota,
     * si son marineros del portavion indicado o si esta asignado a mas de un avion.
     * 
     * @return la lista de pilotos de la flota.
     * @throws BatallaNavalExcepcion
     */
    public ArrayList<Marinos> PilotosComprueba() throws BatallaNavalExcepcion {
        ArrayList<Marinos> pilotos = new ArrayList<>();
        for (Maquina i : maquinas) {
            if (i.getTipo() == "PortaAvion") {
                ArrayList<Avion> aviones = i.aviones();
                ArrayList<Marinos> piloto = i.marino();
                for (Avion j : aviones) {
                    if (piloto.contains(j.marino().get(0))) {
                        pilotos.add(j.marino().get(0));
                    } else {
                        throw new BatallaNavalExcepcion(BatallaNavalExcepcion.pilotosPortaAvionProblem);
                    }
                }
            } else if (i.getTipo() == "Avion") {
                int confirm = 0;
                for (int j = 0; j < Tripulantes.size(); j++) {
                    if (i.marino().get(0) == Tripulantes.get(j))
                        confirm++;
                }
                if (confirm == 0) {
                    throw new BatallaNavalExcepcion(BatallaNavalExcepcion.infiltradosProblem);
                } else if (confirm >= 2) {
                    throw new BatallaNavalExcepcion(BatallaNavalExcepcion.pilotosProblem);
                }
            }
        }
        return pilotos;
    }

    /**
     * Me deja saber cuantas maquinas en buen estado tengo.
     * 
     * @return El numero de maquinas fuertes que tengo
     */
    public int potencia() {
        ArrayList<Maquina> maquinasFuertes = new ArrayList<>();
        ArrayList<Maquina> maquinasDebiles = maquinasDebiles();
        for (Maquina i : maquinas) {
            if (!(maquinasDebiles.contains(i))) {
                maquinasFuertes.add(i);
            }
        }
        try {
            maquinasMarineros();
        } catch (BatallaNavalExcepcion excepcion) {
            respuesta = BatallaNavalExcepcion.marinerosMenosMaquinarias;
            System.out.println(respuesta);
        }
        return maquinasFuertes.size();
    }

    /**
     * Me permite saber si tengo menos marineros que maquinas fuertes en mi flota.
     * 
     * @param lita de maquinas Fuertes.
     * @throws BatallaNavalExcepcion
     */
    public void maquinasMarineros() throws BatallaNavalExcepcion {
        if (Tripulantes.size() < maquinas.size()) {
            throw new BatallaNavalExcepcion(null);
        }
    }

    /**
     * Devuelve el Arreglo de marinos de la flota
     * 
     * @return El arreglo de marinos
     */
    public ArrayList<Marinos> getTripulacion() {
        return Tripulantes;
    }

    /**
     * Me deja saber todas las maquinas de la flota
     * 
     * @return El arreglo de flotas
     */
    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }

    /**
     * Devuelve la respuesta de la excepcion.
     * 
     * @return la respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }
}
