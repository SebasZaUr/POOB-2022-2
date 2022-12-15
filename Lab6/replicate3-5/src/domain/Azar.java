package domain;
import java.awt.Color;
import java.util.Random;

/**
 * Azar va a reproducirse al azar en los espacios donde no se encuentre nada, y 
 * va a ir cambiando entre un cuadrado y un círculo. Empezara con un color amarillo y 
 * después lo cambiara por un rosado. 
 */
public class Azar implements Thing
{
    private int row, column;
    private Color color;
    private AManufacturing am;
    private int shape;
    private boolean active;

    /**
     * Constructor for objects of class Azar
     */
    public Azar(AManufacturing am,int row,int column)
    {
        this.am = am;
        this.row = row;
        this.column = column;
        shape = 2;
        color = color.yellow;
        am.setThing(row,column,(Thing)this);
        active = true;
    }

    public void decide(){
        shape = (shape == 1 ? 2:1);
        Random rand = new Random();
        int x = rand.nextInt(am.getSize() - 1);
        int y = rand.nextInt(am.getSize() - 1);
        if(am.isEmpty(x,y)){
            new Azar(am,x,y);
        }
    }
   
    public void change(){
        color = color.pink;
        active = (active ? false:true);
    }
    
    public int shape(){
      return shape;
    }
  
    public Color getColor(){
      return color;
    }
  
    public boolean isActive(){
      return active;
    }
     
}
