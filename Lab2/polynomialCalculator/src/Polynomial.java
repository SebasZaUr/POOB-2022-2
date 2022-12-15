import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 * Polinomial. 
 * This class implements a polinomical data type, this is a fraction's group in this format ax^n + bx^n-1.....fx^1+h. 
 * @author Daniel Felipe Rojas Hernández
 * @author Sebastián Zamora Urrego
 * @version 09/09/2022
 */
public class Polynomial
{
    private List<Integer> polinomyals = new ArrayList<Integer>();
    private List<Fraction> fractions = new ArrayList<Fraction>();
    
    /**
     * Create a new polinomial, given the term´s number
     * @param termNumber
     */
    public Polynomial(int termNumber)
    {
        for (int i = 0; i < termNumber; i++){
            Scanner entrada = new Scanner(System.in);
            String vir = JOptionPane.showInputDialog("Digite de la sigiente manera: numerador,denominador,exponente  :");
            String[] lis = vir.split(",");
            if (lis.length == 2){
                Fraction fraction = new Fraction(Integer.parseInt(lis[0]));
                polinomyals.add(Integer.parseInt(lis[1]));
                fractions.add(fraction);
            }
            else if (lis.length == 3){
                Fraction fraction = new Fraction(Integer.parseInt(lis[0]),Integer.parseInt(lis[1]));
                polinomyals.add(Integer.parseInt(lis[2]));
                fractions.add(fraction);
            }
            else if (lis.length == 4){
                Fraction fraction = new Fraction(Integer.parseInt(lis[0]),Integer.parseInt(lis[1]),Integer.parseInt(lis[2]));
                polinomyals.add(Integer.parseInt(lis[3]));
                fractions.add(fraction);
            }
        }
    }

    /**
     * Search each coefficients that that accompany the variable
     */
    public List<String> Search(){
        List<String> coefficients = new ArrayList<String>();
        int aux1 = 0;
        int aux2 = 0;
        for (int i = 0; i < polinomyals.size();i++){
            if (aux1 < polinomyals.get(i)){
                aux1 = polinomyals.get(i);
            }
        }
        int control = aux1;
        for (int i = 0; i <= aux1; i++){
            if (polinomyals.contains(control) == true){
                coefficients.add(fractions.get(aux2).toString());
                aux2++;
            }
            else{
                coefficients.add("0");
            }
            control--;
            System.out.println(coefficients);
        }
        return coefficients;
    }
    
    /**
     * 
     */
    public Polynomial polinomyalPlus(Polynomial other){
        int control = 0;
        Fraction prov = new Fraction(0,1);
        for (int i = 0; i < polinomyals.size();i++){
            Fraction sumFraction = new Fraction(fractions.get(control).numerator(),fractions.get(control).denominator());
            prov = prov.sume(sumFraction);
            control++;
        }
        return pro
    }
}
