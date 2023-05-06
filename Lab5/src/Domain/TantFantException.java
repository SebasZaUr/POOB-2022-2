package domain;

public class TantFantException extends Exception{
    public static final String WRONG_MOVEMENT = "Jugada invalida";

    public TantFantException(String msm){
        super(msm);
    }
}
