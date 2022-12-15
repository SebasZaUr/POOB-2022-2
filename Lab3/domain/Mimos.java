package domain;
import java.awt.Color;

/**
 * Write a description of class mimos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mimos extends Cell
{
    private AManufacturing am;
    
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
        for (int x=Math.max(0, getRow()-1); x<=Math.min(getRow()+1,limFila); x++) {
            for (int y=Math.max(0,getColumn()-1); y<=Math.min(getColumn()+1,limColumna); y++) {
                neighbor = am.getThing(x, y); // Encontrando las vecinas
                if (neighbor != this) {
                    if (neighbor != null && !neighbor.isActive()) {
                        nextState = 'd';
                    } 
                    else if (neighbor != null) {
                        nextState = 'a';
                    }
                }
            }
        }
    }
}
