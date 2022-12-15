package Presentation;

import java.awt.Color;
import java.awt.GridLayout;

/**
 * Me crea la parte visual de la casilla de tipo Teleport.
 * @author Sebastian Zamora Urrego.
 * @author Daniel Rojas Hernandez.
 */
public class TeleportGUI extends CasillaGUI{
	public TeleportGUI(){
        setBackground(Color.PINK);
        setLayout(new GridLayout(1,1));
    }

}