package domain;


/**
 * Rounded, va a crear nuevas Rounded alrededor de la que creamos al principio 
 * y se van a activar y desactivar de la misma forma que una célula normal. 
 */
public class Rounded extends Cell
{
    private AManufacturing am;
    
    /**
     * Constructor for objects of class Rounded
     */
    public Rounded(AManufacturing am,int row,int column,boolean active,boolean actual)
    {
        super(am,row,column,active);
        int limFila = am.getSize()-1;
        int limColumna = am.getSize()-1;
        color = color.red;
        if(actual){
            for (int x=Math.max(0, row - 1); x<=Math.min(row + 1,limFila); x++) {
                for (int y=Math.max(0,column - 1); y<=Math.min(column + 1,limColumna); y++){
                    if (am.isEmpty(x,y)){
                        new Rounded(am,x,y,true,false);
                    }
                }
            }
        }
    }
    
    
}
    

