package domain;
import java.awt.Color;

/**
 *  La kriptonita es redonda y verde, sólo se activa si tiene un vecino. En cada etapa se 
 *  reproduce en las celdas vecinas vacías.
 */
public class Kriptonita extends Artefact implements Thing
{
    private AManufacturing am;
    private int row;
    private int column;
    private Color color;
    private int shape;
    private char nextState;

    /**
     * Constructor for objects of class Kriptonita
     */
    public Kriptonita(AManufacturing am, int row, int column, boolean active, char next)
    {
        state = (active ? Artefact.ACTIVE: Artefact.INACTIVE);
        nextState = next;
        this.am = am;
        this.row = row;
        this.column = column;
        color = color.green;
        am.setThing(row,column,(Thing)this);
        active = true;
    }
    
    @Override
    public void decide(){
        int limFila = am.getSize()-1;
        int limColumna = am.getSize()-1;
        int neighActives = am.neighborsActive(row,column);
        if(neighActives >=1 && this.isActive()){
            for (int x=Math.max(0, row-1); x<=Math.min(row+1,limFila); x++) {
                for (int y=Math.max(0,column-1); y<=Math.min(column+1,limColumna); y++){
                    if (am.isEmpty(x,y)){
                        new Kriptonita(am,x,y,false,'a');
                    }
                }
            }
        }
    }
    
    @Override
    public final void change(){
        step();
        state=nextState;
    }
    
    public Color getColor(){
        return color;
    }

    /**
     * 
     */
   public final int shape() {
        return Thing.ROUND;
    }
}
