public class BatallaNavalExcepcion extends Exception {
    public static final String alNorteProblem = "Una flota no se puede mover";
    public static final String pilotosProblem = "Piloto no es marino";
    public static final String pilotosPortaAvionProblem = "Piloto no esta asignado al porta avion";
    public static final String pilotoAvionesProblem = "Piloto esta asignado a mas de un avion";
    public static final String marinerosMenosMaquinarias = "Hay menos marineros que maquinas en la flota";
    public static final String infiltradosProblem = "Flota no tiene marinos";
    public static final String potenciaProblem = "Mas de la mitad de la flota tiene problemas de potencia";

    public BatallaNavalExcepcion(String sms) {
        super(sms);
    }
}
