package Presentation;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import java.util.*;
import javax.swing.plaf.DimensionUIResource;
import java.io.*;
import java.io.ObjectInputFilter.Config;
import java.time.temporal.JulianFields;
import java.awt.Color;
import java.util.Scanner;
import java.util.*;

/**
 * 
 */
public class TantFantGUI extends JFrame {
    private static final int anchoPantalla = 2000;
    private static final int altoPantalla = 2000;
    private static final Dimension dimensiones = new Dimension(anchoPantalla, altoPantalla);
    private JPanel pantallaInicio, juegoPantalla;
    private JMenu menu, configuracion;
    private JMenuBar barraMenu;
    private JMenuItem nuevaPartida, salvarPartida, cargarPartida, salir, cambiarColor1, tamanoTablero, cambiarColor2;
    private JFileChooser selecArchivo;
    private int Tamano = 3;
    private int intentosJugador1 = 0;
    private int intentosJugador2 = 0;
    private Color colorJugador1 = Color.BLACK;
    private Color colorJugador2 = Color.CYAN;

    /**
     * 
     */
    private TantFantGUI() {
        prepareElemnts();
        prepareActions();
    }

    /**
     * 
     */
    public void prepareElemnts() {
        pantallaInicio = new JPanel();
        pantallaInicio.setLayout(null);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        setVisible(true);

        // Creamos el titulo
        JLabel titulo = new JLabel();
        titulo.setBounds(250, 20, 250, 100);
        titulo.setText("TantFant");
        titulo.setFont(new Font("Copperplate Gothic", Font.ITALIC, 40));

        pantallaInicio.add(titulo);
        this.getContentPane().add(pantallaInicio);
        prepareElementsMenu();
        prepareElementsConfiguracion();

    }

    /**
     * Prepara todas las acciones que requiere el Tant Fant
     * 
     */
    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Confirmación cerrar juego
        WindowListener Cerrar = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int Confirmacion = JOptionPane.showConfirmDialog(null, "¿desea cerrar el juego?");
                if (Confirmacion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(Cerrar);

        prepareActionsMenu();
        prepareActionsConfiguracion();
        prepareActionsInicio();
    }

    /**
     * Configura las acciones de los botones del inicio
     * 
     * @param inicio Boton para iniciar una partida.
     * @param config Boton para ir a la pantalla de configuracion.
     */
    public void prepareActionsInicio() {
    }

    /**
     * Creo y configuro los elementos del menu.
     */
    public void prepareElementsMenu() {
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        menu = new JMenu("Menu");
        nuevaPartida = new JMenuItem("Nueva Partida");
        cargarPartida = new JMenuItem("Cargar Partida");
        salvarPartida = new JMenuItem("Guardar Partida");
        salir = new JMenuItem("Salir");

        menu.add(nuevaPartida);
        menu.add(salvarPartida);
        menu.add(cargarPartida);
        menu.add(salir);

        barraMenu.add(menu);
    }

    public void prepareElementsConfiguracion() {
        configuracion = new JMenu("Configuracion");
        cambiarColor1 = new JMenuItem("Cambiar color al jugador 1");
        cambiarColor2 = new JMenuItem("Cambiar color al jugador 2");
        tamanoTablero = new JMenuItem("Tamano del tablero");

        configuracion.add(cambiarColor1);
        configuracion.add(cambiarColor2);
        configuracion.add(tamanoTablero);

        barraMenu.add(configuracion);
    }

    /**
     * Me perite que el menu funcione correctamente
     */
    public void prepareActionsMenu() {
        nuevaPartida.addActionListener(e -> iniciarJuego());
        cargarPartida.addActionListener(e -> cargarPartida());
        salvarPartida.addActionListener(e -> guardarPartida());
        salir.addActionListener(e -> exit());
    }

    /**
     * Me permite salir del juego
     */
    public void exit() {
        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro que desea salir?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Me permite cargar una partida que tenia guardadas
     */
    public void cargarPartida() {
        selecArchivo = new JFileChooser();
        selecArchivo.setVisible(true);
        int action = selecArchivo.showOpenDialog(cargarPartida);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = selecArchivo.getSelectedFile();
            JOptionPane.showMessageDialog(cargarPartida,
                    "El archivo de nombre " + archivo.getName() + " que se trata de abrir en la ruta " + archivo
                            + "\n NO se pudo abrir ya que esta funcion se encuentra en construccion.");
        }
        selecArchivo.setVisible(false);
    }

    /**
     * Me permite guardar partidas.
     */
    public void guardarPartida() {
        selecArchivo = new JFileChooser();
        selecArchivo.setVisible(true);
        int action = selecArchivo.showSaveDialog(salvarPartida);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = selecArchivo.getSelectedFile();
            JOptionPane.showMessageDialog(salvarPartida,
                    "El archivo de nombre " + archivo.getName() + " que se trata de abrir en la ruta " + archivo
                            + "\n NO se pudo abrir ya que esta funcion se encuentra en construccion.");
        }
        selecArchivo.setVisible(false);
    }

    /**
     * Me crea las acciones de la pantalla de configuracion.
     */
    public void prepareActionsConfiguracion() {
        cambiarColor1.addActionListener(e -> cambiarColor("1"));
        cambiarColor2.addActionListener(e -> cambiarColor("2"));
        tamanoTablero.addActionListener(e -> tamanoTableros());
    }

    public void tamanoTableros() {
        Tamano = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamano que quiere para el tablero"));
    }

    /**
     * Me permite cambiar el color al jugador seleccionado.
     * 
     * @param jugador El boton del jugador seleccionado
     */
    public void cambiarColor(String jugador) {
        Color colorJugador = JColorChooser.showDialog(this, "Escoja un color", Color.BLACK);
        if (colorJugador != null) {
            if (jugador == "1") {
                colorJugador1 = colorJugador;
            } else {
                colorJugador2 = colorJugador;
            }
        }
    }

    public void iniciarJuego() {
        pantallaInicio.removeAll();

        juegoPantalla = new JPanel();
        Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel botones = new JPanel();
        botones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Opciones")));
        JPanel informacionJugador1 = new JPanel();
        informacionJugador1.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Jugador 1")));
        JPanel informacionJugador2 = new JPanel();
        informacionJugador2.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Jugador 1")));
        JLabel titulo = new JLabel("TANT FANT");
        titulo.setFont(new Font("Copperplate Gothic", Font.ITALIC, 40));
        titulo.setBounds(100, 100, 32, 31);
        JLabel jugador1 = new JLabel("Jugador 1");
        JLabel jugador2 = new JLabel("Jugador 2");
        JLabel intentosJ1 = new JLabel(Integer.toString(intentosJugador1));
        JLabel intentosJ2 = new JLabel(Integer.toString(intentosJugador2));
        JPanel tablero = crearTablero();
        JButton reiniciar = new JButton("Reinicar Juego");
        JButton finalizar = new JButton("Finalizar Juego");
        JButton colorJ1 = new JButton();
        JButton colorJ2 = new JButton();
        colorJ1.setBackground(colorJugador1);
        colorJ2.setBackground(colorJugador2);

        botones.setLayout(new GridLayout(1, 2));
        botones.add(reiniciar);
        botones.add(finalizar);

        informacionJugador1.setLayout(new GridLayout(3, 1));
        informacionJugador1.add(jugador1);
        informacionJugador1.add(intentosJ1);
        informacionJugador1.add(colorJ1);

        informacionJugador2.setLayout(new GridLayout(3, 1));
        informacionJugador2.add(jugador2);
        informacionJugador2.add(intentosJ2);
        informacionJugador2.add(colorJ2);

        juegoPantalla.setLayout(new BorderLayout());
        juegoPantalla.add(titulo, BorderLayout.NORTH);
        juegoPantalla.add(informacionJugador1, BorderLayout.WEST);
        juegoPantalla.add(informacionJugador2, BorderLayout.EAST);
        juegoPantalla.add(tablero, BorderLayout.CENTER);
        juegoPantalla.add(botones, BorderLayout.SOUTH);

        juegoPantalla.setSize(dimensiones.width / 2, dimensiones.height / 2);
        juegoPantalla.setLocation(0, 0);

        pantallaInicio.add(juegoPantalla);
        pantallaInicio.revalidate();
        pantallaInicio.repaint();
    }

    /**
     * Me crea el tablero con las dimensiones predeterminadas.
     * 
     * @return Un JPanel el cual contiene el tablero.
     */
    public JPanel crearTablero() {
        JButton[][] Matriz = new JButton[Tamano][Tamano];
        JPanel tablero = new JPanel();
        tablero.setLayout(new GridLayout(Tamano, Tamano));
        int tamano = 10 / Tamano;
        for (int ContX = 0; ContX < Tamano; ContX++) {
            for (int ContY = 0; ContY < Tamano; ContY++) {
                JButton casilla = new JButton();
                casilla.setSize(tamano, tamano);
                casilla.setToolTipText(Integer.toString(ContX) + "," + Integer.toString(ContY));
                Matriz[ContX][ContY] = casilla;
                tablero.add(Matriz[ContX][ContY]);
                tablero.validate();
                tablero.repaint();
            }
        }
        for (int x = 0; x < Tamano; x++) {
            Matriz[0][x].setBackground(colorJugador1);
            Matriz[Tamano - 1][x].setBackground(colorJugador2);
        }
        return tablero;
    }

    /**
     * 
     */
    public static final void main(String args[]) {
        TantFantGUI pantalla = new TantFantGUI();
        pantalla.setTitle("TantFant");
        pantalla.setVisible(true);
    }
}
