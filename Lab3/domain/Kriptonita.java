package domain;

/**
 * Write a description of class Kriptonita here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kriptonita extends Cell
{
    private AManufacturing am;

    /**
     * Constructor for objects of class Kriptonita
     */
    public Kriptonita(AManufacturing am, int row, int column)
    {
        super(am, row, column,am.neighborsActive(row,column) >= 1? true:false);
        this.am = am;
        color = color.green;
    }
    
    @Override
    public void decide(){
        int limFila = am.getSize()-1;
        int limColumna = am.getSize()-1;
        int neighActives = am.neighborsActive(this.getRow(),this.getColumn());
        if(neighActives >=1 && this.isActive()){
            for (int x=Math.max(0, getRow()-1); x<=Math.min(getRow()+1,limFila); x++) {
                for (int y=Math.max(0,getColumn()-1); y<=Math.min(getColumn()+1,limColumna); y++){
                    if (am.isEmpty(x,y)){
                        new Kriptonita(am,x,y);
                    }
                }
            }
        }
    }
    
    /**
     * 
     */
   public final int shape() {
        return Thing.ROUND;
    }
}
