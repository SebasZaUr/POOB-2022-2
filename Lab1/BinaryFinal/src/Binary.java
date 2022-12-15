

/**
 * Write a description of class Binary here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Binary
{
    
    private String binaryString;
    private int number;
    private Rectangle[] uno;
    private Circle[] cero;
    

    public Binary  (int decimal){
        binaryString = Integer.toBinaryString(decimal);
        number = decimal;
        uno = new Rectangle[binaryString.length()];
        cero = new Circle[binaryString.length()];
    }
    
    
    public Binary(boolean[] bool){
        binaryString = "";
        for (int i = 0; i < bool.length; i++){
            if (bool[i] == true){
                binaryString += "1";
            }
            else{
                binaryString += "0";
            }
        }
        uno = new Rectangle[binaryString.length()];
        cero = new Circle[binaryString.length()];
    }
    
    
    public int getDecimal (){
        int decimal=Integer.parseInt(binaryString,2);  
        return decimal;
    }
    
    
    public boolean[] getBinary(){
        boolean[] boolList = new boolean[binaryString.length()];
        String[] c = binaryString.split("");
        for (int i = 0; i < boolList.length; i++){
            int recomvert = Integer.parseInt(c[i]);
            if (recomvert == 1){
                boolList[i] = true;
            }
            else{
                boolList[i] = false;
            }
        }
        
       return boolList;
    }
    
    
    public void next(){
        number += 1;
    }
    
    
    public void change(int decimals){
        Binary changes = new Binary (decimals);
    }
    
    
    public void makeVisible(){
        String[] c = binaryString.split("");
        int counter = -60;
        for (int i = 0; i < binaryString.length(); i++){
            int recomvert = Integer.parseInt(c[i]);
            if (recomvert == 1){
                Rectangle one = new Rectangle();
                one.changeSize(30,10);
                one.moveHorizontal(counter);
                uno[i]= one;
                uno[i].makeVisible();
            }
            else{
                Circle zero = new Circle();
                zero.moveHorizontal(40);
                zero.moveHorizontal(counter);
                cero[i] = zero;
                cero[i].makeVisible();
            }
            counter += 35;
        }
    }
    
    
    public void makeInvisible(){
        String[] c = binaryString.split("");
        for (int i = 0; i < binaryString.length(); i++){
            int recomvert = Integer.parseInt(c[i]);
            if (recomvert == 1){
                uno[i].makeInvisible();
            }
            else{
                cero[i].makeInvisible();
            }
        }
    }
    
    
    public void moveTo (int x, int y){
        String[] c = binaryString.split("");
        for (int i = 0; i < binaryString.length(); i++){
            int recomvert = Integer.parseInt(c[i]);
            if (recomvert == 1){
                uno[i].moveHorizontal(x);
                uno[i].moveVertical(y);
            }
            else{
                cero[i].moveHorizontal(x);
                cero[i].moveVertical(y);
            }
        }
    }
    
    public void changeColor(String color){
        String[] c = binaryString.split("");
        for (int i = 0; i < binaryString.length(); i++){
            int recomvert = Integer.parseInt(c[i]);
            if (recomvert == 1){
                uno[i].changeColor(color);
            }
            else{
                cero[i].changeColor(color);
            }
        }
    }
    
}
