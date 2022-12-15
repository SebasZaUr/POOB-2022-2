package domain;
import java.awt.Color;

/**
 * Clase mimos, imita el comportamiento de las celulas cercanas
 **/
public class Mimos extends Cell
{
    private AManufacturing am;
    private int state = 0;
    private int statex,statey;
    private int[] patronRow = {-1,-1,0,1,1,1,0,1};
    private int[] patronColumn = {0,1,1,1,0,-1,-1,-1};
    
    /**
     * Constructor for objects of class mimos
     */
    public Mimos(AManufacturing am, int row, int column, boolean active)
    {
        super(am, row, column,active);
        this.am = am;
        color = color.orange;
    }
    
    @Override
    public void decide(){
        int limFila = am.getSize()-1;
        int limColumna = am.getSize()-1;
        Thing neighbor;
        while(true){
            if(state == 8){
                    state = 0;
            }
            if(this.getRow() + patronRow[state] < 0 || this.getRow() > limFila || 
            this.getColumn() + patronColumn[state] < 0 || this.getColumn() > limColumna){
                state ++;
                continue;
            }
            else{
                statex = getRow() + patronRow[state];
                statey = getColumn() + patronColumn[state];
                state++;
                break;
            }
        }
        neighbor = am.getThing(statex, statey);
        if (neighbor != null && !neighbor.isActive()) {
            nextState = 'd';
        } 
        else if (neighbor != null) {
            nextState = 'a';
        }
    }
}            