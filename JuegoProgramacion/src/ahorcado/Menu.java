package ahorcado;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")

public class Menu extends JFrame {

	Clip clip;
	String ruta = "/imagenesysonidos/";
	
	public Menu() {
		
		
		setSize(600, 500); // Tama�o de la ventana
		this.setLocationRelativeTo(null);// La ventana nos saldr� en el centro de la pantalla
		setUndecorated(true); // Lo uso para quitar los elementos de minimizar, cerrar y maximizar la ventana.
		// Bot�n ayuda
		Vista.ayuda.setBackground(new Color(255, 255, 255));
		Vista.ayuda.setFont(new Font("Dialog", 1, 24));
		Vista.ayuda.setForeground(new Color(0, 0, 0));
		Vista.ayuda.setText("AYUDA");
		getContentPane().add(Vista.ayuda);
		Vista.ayuda.setBounds(70, 290, 200, 70);
		Vista.ayuda.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent evt) 
			
			{
				new Sonido("click2");
				ImageIcon g = new ImageIcon(getClass().getResource("/imagenesysonidos/univer.png"));
				JOptionPane.showMessageDialog(null,
						"Jugadores: 1 (Aunque pueden ser m�s de uno si se turnan para adivinar la palabra)\n"
								+ "Objetivo: Descubrir la palabra" + "\n" + "Gameplay:\n"
								+ "- Al inicio, el programa elegir� de forma aleatoria una palabra entre las que dispone el programa\n"
								+ "- El jugador deber� hacer click en cada letra que crea que puede contener la palabra a adivinar. \n"
								+ "- Si la letra est�, el programa la a�ade sobre la l�nea que ocupa su lugar en la palabra a adivinar.\n"
								+ "- Si la letra no est�, el programa la a�ade sobre la horca y dibujar� una parte del mu�eco.\n"
								+ "- El mu�eco se dibuja en 5 partes (cabeza, tronco y extremidades), por lo que el jugador tiene 5 fallos permitidos.\n"
								+ "- Si el jugador acierta la palabra, antes de tener 5 fallos, gana.\n"
								+ "- Si el jugador acumula 5 fallos, entonces el programa  dibujar� el mu�eco en su totalidad, es decir, el jugador ha perdido.\n",
								"Manual de uso", JOptionPane.INFORMATION_MESSAGE, g);
				new Sonido("click2");

			}
		});

		// Bot�n jugar
		Vista.jugar.setBackground(new Color(255, 255, 255));
		Vista.jugar.setFont(new Font("Dialog", 1, 24));
		Vista.jugar.setForeground(new Color(0, 0, 0));
		Vista.jugar.setText("JUGAR");
		Vista.jugar.addActionListener(new ActionListener()

		{
			
			public void actionPerformed(ActionEvent evt)
			
			{
				new Sonido("click2");
				if (evt.getSource().equals(Vista.jugar)) 
				{
					Vista.txtNombreJugador.setText("");
					Vista.nombreJugador.setLayout(new FlowLayout());
					Vista.nombreJugador.setSize(350, 100);
					Vista.nombreJugador.setResizable(false);
					Vista.btnAceptar.addActionListener(this);
					Vista.nombreJugador.add(Vista.txtNombreJugador);
					Vista.nombreJugador.add(Vista.btnAceptar);
					Vista.nombreJugador.setLocationRelativeTo(null);
					Vista.nombreJugador.setVisible(true);
						
				}
				
				else if(evt.getSource().equals(Vista.btnAceptar)) 
				{
					
					if(!Vista.txtNombreJugador.getText().equals("")) 
					{
						
						Controlador ah = new Controlador();
						ah.setVisible(true);
						setVisible(false);
						Vista.nombreJugador.setVisible(false);
					}
					
				} 

			}
		});

		getContentPane().add(Vista.jugar);
		Vista.jugar.setBounds(70, 120, 200, 70);

		// Bot�n salir
		Vista.salir.setBackground(new Color(255, 255, 255));
		Vista.salir.setFont(new Font("Texto", 1, 24));
		Vista.salir.setForeground(new Color(0, 0, 0));
		Vista.salir.setText("SALIR");
		getContentPane().add(Vista.salir);
		Vista.salir.setBounds(70, 200, 200, 70);
		Vista.salir.addActionListener(new ActionListener()

		{

			public void actionPerformed(ActionEvent evt)

			{

				new Sonido("click2");

				if (JOptionPane.showConfirmDialog(rootPane, "�Desea salir de la aplicaci�n?", "Ahorcado",
						JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) 
				{

					new Sonido("click2");
					System.exit(0);

				} 
				
				else

				{

					new Sonido("click2");
					setDefaultCloseOperation(0);

				}

			}
		});

		// Texto de la ventana inicial
		
		Vista.Label2.setFont(new Font("Texto", 1, 36)); // Tipo de fuente
		Vista.Label2.setForeground(new Color(0, 0, 0)); // Color
		Vista.Label2.setText("EL AHORCADO"); // Texto
		getContentPane().add(Vista.Label2);
		Vista.Label2.setBounds(40, 30, 266, 47); // Posici�n del texto
		// Imagen de fondo
		
		Vista.Label1.setIcon(new ImageIcon(getClass().getResource("/imagenesysonidos/ahorcado2.png")));

		getContentPane().add(Vista.Label1);

	}

	
	public static void main(String args[]) {

		// A�ado el tema Nimbus Look and Feel para que el juego se vea igual sin
		// importar donde se ejecute, adem�s de personalizarlo m�s
		// Si no funciona el juego es completamente funcional, solo cambia la est�tica
		// un poco
		// https://docs.oracle.com/javase/8/docs/technotes/guides/swing/nimbus_laf.html

		try 
		{
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
			{
				if ("Nimbus".equals(info.getName())) 
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} 
		
		catch (ClassNotFoundException ex) 
		{
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		catch (InstantiationException ex) 
		{
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		catch (IllegalAccessException ex) 
		{
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		catch (UnsupportedLookAndFeelException ex)
		{
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		}

		new Menu().setVisible(true);
	}

}