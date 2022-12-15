package Presentation;

import java.awt.Color;
import java.awt.GridLayout;


/**
 * Me crea la parte visual de la casilla de tipo Normal.
 * @author Sebastian Zamora Urrego.
 * @author Daniel Rojas Hernandez.
 */
public class NCasillaGUI extends CasillaGUI{

    /**
	 * Me crea casillas de tipo Normal.
	 */
	public NCasillaGUI(){
        setBackground(Color.GRAY);
        setLayout(new GridLayout(1,1));
    }

}
