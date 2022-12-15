package Presentation;

import java.awt.Color;
import java.awt.*;


/**
 * Me crea la parte visual de la casilla de tipo Mine.
 * @author Sebastian Zamora Urrego.
 * @author Daniel Rojas Hernandez.
 */
public class MineGUI extends CasillaGUI{

    /**
	 * Me crea una casilla de tipo Mine 
	 */
    public MineGUI() {
        setBackground(Color.YELLOW);
        setLayout(new GridLayout(1,1));
    }

}
