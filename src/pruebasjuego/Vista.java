package pruebasjuego;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Vista extends JFrame
{
		
    private static final long serialVersionUID = 1L;
    //Botones menu
    static JButton ayuda = new JButton();
	static JButton jugar = new JButton();
	static JButton salir = new JButton();
	static JLabel Label2 = new JLabel();
	static JLabel Label1 = new JLabel();
    // Creo los frames

    static JFrame Clasificacion = new JFrame("Clasificaci�n");
    // Creo los elementos de la ventana de la clasificaci�n
    static JFrame clasificacion = new JFrame("Clasificaci�n");
    static JLabel lblclasificacion = new JLabel("�sta es la clasificaci�n de jugadores");
    static TextArea taClasificacion = new TextArea(10,30);
    static JFrame nombreJugador = new JFrame("Nombre del Jugador");
    static JLabel lblNombreJugador = new JLabel("Nombre del Jugador");
static JTextField txtNombreJugador = new JTextField(20);
static JButton btnAceptar = new JButton("Aceptar");
}