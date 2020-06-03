package ahorcado;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
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

	
	// Creo los elementos de la ventana de la clasificación
	static JFrame clasificacion = new JFrame("Clasificación");
	static JLabel lblclasificacion = new JLabel("Ésta es la clasificación de jugadores");
	static TextArea taClasificacion = new TextArea(20,30);
	static JFrame nombreJugador = new JFrame("Nombre del Jugador");
	static JLabel lblNombreJugador = new JLabel("Nombre del Jugador");
	static JTextField txtNombreJugador = new JTextField(20);
	static JButton btnAceptar = new JButton("Aceptar");
	// Creo los elementos de la interfaz del juego en sí
	static JPanel jPanel1 = new JPanel(); //Panel principal
	static JPanel jPanel2 = new JPanel(); //Panel de menu
	static JButton generarPalabra = new JButton(); //Botón para generar una nueva palabra
	static JButton btnResolver = new JButton(); //Botón para resolver la palabra
	static JButton btnSalir = new JButton(); // Botón para salir
	static JButton mejoresPartidas = new JButton(); //Botón para consultar los mejores jugadores con su puntuación
	static JPanel jPanel3 = new JPanel(); //Panel para unificar los errores y la palabra oculta
	static JPanel jPanel4 = new JPanel(); //Panel palabra oculta
	static JTextField txtPalabra = new JTextField(); // Donde aparece la palabra a adivinar
	static JPanel jPanel5 = new JPanel(); //Panel de errores
	static JLabel errores = new JLabel(); // Donde aparecen los errores
	static JPanel jPanel6 = new JPanel(); // Panel principal donde sale el muñeco ahorcado
	static JLabel Dibujo = new JLabel();  // Label donde sale el muñeco ahorcado
	static JPanel jPanel7 = new JPanel(); // Panel del teclado
	static JButton button_2 = new JButton();
	static JButton button_1 = new JButton();
	static JButton button_3 = new JButton();
	static JButton button_4 = new JButton();
	static JButton button_5 = new JButton();
	static JButton button_6 = new JButton();
	static JButton button_7 = new JButton();
	static JButton button_8 = new JButton();
	static JButton button_9 = new JButton();
	static JButton button_10 = new JButton();
	static JButton button_11 = new JButton();
	static JButton button_12 = new JButton();
	static JButton button_13 = new JButton();
	static JButton button_14 = new JButton();
	static JButton button_16 = new JButton();
	static JButton button_17 = new JButton();
	static JButton button_18 = new JButton();
	static JButton button_19 = new JButton();
	static JButton button_20 = new JButton();
	static JButton button_21 = new JButton();
	static JButton button_22 = new JButton();
	static JButton button_23 = new JButton();
	static JButton button_24 = new JButton();
	static JButton button_25 = new JButton();
	static JButton button_26 = new JButton();
	static JButton button_27 = new JButton();
	static JButton button_15 = new JButton();
	static JMenuBar jMenuBar1 = new JMenuBar(); //Acerca de
	static JMenu jMenu2 = new JMenu(); //Acerca de
	static JMenu jMenu3 = new JMenu(); // Puntuación
	static JButton btnClasificacionVolver = new JButton("Volver");
	
}