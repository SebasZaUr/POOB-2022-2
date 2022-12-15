import java.util.ArrayList;
import java.lang.Math;
import java.util.List;


/**
  *Fraction
  * This class implements the Fraction data type; that is, a rational number that can be written in the form p/q, where p and q are integers, with q != 0
  * The implementation is done by immutable objects
  * INV: The fractional is represented irreducibly.
  * @author ESCUELA
  */
public class Fraction {
    
    private List<Integer> Fractions =new ArrayList<Integer>(2);
    
     /**Calculate the greatest common divisor of two integers
     * We will implement it using the recursive algorithm
     * @param a first integer
     * @param b second integer
     * @return the Greatest Common Divisor of a and b
     */
    public static int gcd(int a,int b){
        int min = 0;
        int counter = 2;
        int gcds = 1;
        if (a != 0 && b != 0){
            if (a<b){
                min = Math.abs(a);
            }
            else{
                min = Math.abs(b);
            }
            for (int i = 0; i<= min; i++){
                if (Math.floorMod(Math.abs(a),counter) == 0){
                    if (Math.floorMod(Math.abs(b),counter) == 0){
                        gcds = counter;
                    }
                }
                counter++;
            }
        }
        return gcds;
    }    
    
    /**Create a new fraction, given the numerator and denominator
     * @param numerator
     * @param denominator. denominator <> 0
     */
    public Fraction (int numerator, int denominator) {
        simplify(numerator, denominator);
    }
    
    /**Create a fraction corresponding to an integer
     * @param integer the integer to create
     */
    public Fraction (int integer) {
        Fractions.add(integer);
        Fractions.add(1);
    }

    /**Create a fraction, from its mixed representation.
     * The number created is mixedInteger + mixednumerator/mixeddenominator
     * @param integer the integer part of the number
     * @param numerator the numerator of the fractional part
     * @param denominator the denominator of the fractional part. denominator!=0
     */
    public Fraction (int integer, int numerator, int denominator) {
        if (denominator != 0){
            numerator = (integer * denominator) + numerator;
            simplify(numerator, denominator);
        }
    }
    
    public void determineFraction(int n, int de){
            if (de < 0){
                if (n < 0){
                    Fractions.add(Math.abs(n));
                    Fractions.add(Math.abs(de));
                }
                else{
                    n = -n;
                    Fractions.add(n);
                    Fractions.add(Math.abs(de));
                }
            }
            else{
                Fractions.add(n);
                Fractions.add(de);
            }
    }
    
    public void simplify(int n, int de){
        if (de != 0){
            int mcd = gcd(n,de);
            determineFraction(n/mcd, de/mcd);
        }
    }
    
    /**
     * Return the numerator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The numerator of the simplified fraction
     */
    public int numerator() {
        int numerators = Fractions.get(0);
        return numerators;
    }
    
     /**
     * Return the denominator of the simplified fraction
     * A fractional p/q is written in simplified form if q is a positive integer and
     * The greatest common divisor of p and q is 1.
     * @return The denominator of the simplified fraction
     */   
    public int denominator() {
        int deno = Fractions.get(1);
        return deno;
    }
    
    /**
     * Add this fraction with another fraction
     * @param other is another fractional
     * @return this+other
     */
    public Fraction sume (Fraction other) {
        int num = 0;
        int den = 0;
        if (this.Fractions.get(1) == other.Fractions.get(1)){
            num = this.Fractions.get(0) + other.Fractions.get(0);
            den = this.Fractions.get(1);
        }
        else {
            num = (this.Fractions.get(0) * other.Fractions.get(1)) + (this.Fractions.get(1) * other.Fractions.get(0));
            den = this.Fractions.get(1) * other.Fractions.get(1) ;
        }
        Fraction sumes = new Fraction(num,den);
        return sumes;
    }
    
    /**
     * Substract this fraction with another fraction
     * @param other is another fractional
     * @return this-other
     */
    public Fraction substract (Fraction other) {
        int num = 0;
        int den = 0;
        if (this.Fractions.get(1) == other.Fractions.get(1)){
            num = this.Fractions.get(0) - other.Fractions.get(0);
            den = this.Fractions.get(1);
        }
        else {
            num = (this.Fractions.get(0) * other.Fractions.get(1)) - (this.Fractions.get(1) * other.Fractions.get(0));
            den = this.Fractions.get(1) * other.Fractions.get(1) ;
        }
        Fraction subs = new Fraction(num,den);
        return subs;
    }   
    
    /**
     * Multiply this fraction with another fraction
     * @param other is another fractional
     * @return this*other
     */
    public Fraction multiply (Fraction other) {
        int num = this.Fractions.get(0) * other.Fractions.get(0);
        int den = this.Fractions.get(1) * other.Fractions.get(1);
        Fraction mlp = new Fraction(num,den);
        return mlp;
    }
    
    /**Divide this fraction with another fraction
     * @param other is another fractional
     * @return this/other
     */
    public Fraction divide (Fraction other) {
        int num = this.Fractions.get(0) * other.Fractions.get(1);
        int den = this.Fractions.get(1) * other.Fractions.get(0);
        Fraction div = new Fraction(num,den);
        return div;
    }
    
    @Override
    public boolean equals(Object obj) {
        return equals((Fraction)obj);
    }    
    
      /**Compare this fraction to another fraction
      * @param other eL other fractional
      * @return true if this fraction is mathematically equal to the other fraction, False d.l.c.
      */
    public boolean equals (Fraction other) {
            if (this.equals(other)){
                return true;
            }
            else{
                return false;
            }
    }


    /** Calculate the string representation of a fraction in mixed simplified format
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        String cadena = Integer.toString(Fractions.get(0)) + "/" + Integer.toString(Fractions.get(1));
        System.out.println(cadena);
        return cadena;
    }
    
}
