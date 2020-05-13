package pruebasjuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")

public class Ini extends JFrame {
	
	Clip clip;
	String ruta="/imagenesysonidos/";
	
	public Ini() 
	{
		
		VentanaInicio();
		setSize(600, 500); // Tama�o de la ventana
		this.setLocationRelativeTo(null);// La ventana nos saldr� en el centro de la pantalla
		
	}
	
	
	public void VentanaInicio() {
		
		JButton ayuda = new JButton();
		JButton jugar = new JButton();
		JButton salir = new JButton();
		JLabel Label2 = new JLabel();
		JLabel Label1 = new JLabel();
		
		setUndecorated(true); // Lo uso para quitar los elementos de minimizar, cerrar y maximizar la ventana.
		
		//Bot�n ayuda
		ayuda.setBackground(new Color(255, 255, 255));
		ayuda.setFont(new Font("Dialog", 1, 24));
		ayuda.setForeground(new Color(0, 0, 0));
		ayuda.setText("AYUDA");
		getContentPane().add(ayuda);
		ayuda.setBounds(70, 290, 200, 70);
		ayuda.addActionListener(new ActionListener() 
		
			{
			
				public void actionPerformed(ActionEvent evt)
					{
					
						ayudaActionPerformed(evt);
						
					}
			});
		
		//Bot�n jugar
		jugar.setBackground(new Color(255, 255, 255));
		jugar.setFont(new Font("Dialog", 1, 24)); 
		jugar.setForeground(new Color(0, 0, 0));
		jugar.setText("JUGAR");
		jugar.addActionListener(new ActionListener() 
		
			{
			
				public void actionPerformed(ActionEvent evt) 
				
				{
					
					jugarActionPerformed(evt);
					
				}
			});
		
		
		getContentPane().add(jugar);
		jugar.setBounds(70, 120, 200, 70); 
		
		//Bot�n salir
		salir.setBackground(new Color(255, 255, 255));
		salir.setFont(new Font("Texto", 1, 24)); 
		salir.setForeground(new Color(0, 0, 0));
		salir.setText("SALIR");
		getContentPane().add(salir);
		salir.setBounds(70, 200, 200, 70);
		salir.addActionListener(new ActionListener() 
		
			{
			
				public void actionPerformed(ActionEvent evt)
				
					{
						
						salirActionPerformed(evt);
						
					}
			});
		
		
		//Texto de la ventana inicial
		Label2.setFont(new Font("Texto", 1, 36));  // Tipo de fuente
		Label2.setForeground(new Color(0, 0, 0));  //  Color
		Label2.setText("EL AHORCADO");		      //   Texto	   
		getContentPane().add(Label2);
		Label2.setBounds(40, 30, 266, 47);       //	   Posici�n del texto
		
		//Imagen de fondo
		Label1.setIcon(new ImageIcon(getClass().getResource("/imagenesysonidos/ahorcado2.png"))); 
		
		getContentPane().add(Label1);

		
	}

	public void sonido (String archivo) 
	{
		try 
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta+archivo+".wav")));
			clip.start();
		} 
		
		catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			
		
	}
	
			
		
	
	
	public void ayudaActionPerformed(ActionEvent evt) 
		{
		
			sonido("click");
			Icon g = new ImageIcon(getClass().getResource("/imagenesysonidos/univer.png"));
			JOptionPane.showMessageDialog(null,"Aqu� va la ayuda","Ahorcado", JOptionPane.INFORMATION_MESSAGE, g);
			sonido("click");
			
		}

	public void salirActionPerformed(ActionEvent evt) 
	
		{	
			sonido("click");
			
			if (JOptionPane.showConfirmDialog(rootPane, "�Desea salir de la aplicaci�n?", "Ahorcado", JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) 
					{	
				
						sonido("click");
						System.exit(0);
						
					} 
			else 
				
				{	
				
					sonido("click");
					setDefaultCloseOperation(0);
					
				}

		}

	public void jugarActionPerformed(ActionEvent evt) //Enlace con el juego
		{
			//Juego_normal jn = new Juego_normal();
			//jn.setVisible(true);
			//this.setVisible(false);

		}

	
	public static void main(String args[]) 
		{
			//	A�ado el tema Nimbus Look and Feel para que el juego se vea igual sin importar donde se ejecute, adem�s de para personalizarlo m�s
			//	Si no funciona el juego es completamente funcional, solo cambia la est�tica un poco
			//	https://docs.oracle.com/javase/8/docs/technotes/guides/swing/nimbus_laf.html
			
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
				Logger.getLogger(Ini.class.getName()).log(Level.SEVERE, null, ex);
			} 
			catch (InstantiationException ex) 
			{
				Logger.getLogger(Ini.class.getName()).log(Level.SEVERE, null, ex);
			} 
			catch (IllegalAccessException ex) 
			{
				Logger.getLogger(Ini.class.getName()).log(Level.SEVERE, null, ex);
			} 
			catch (UnsupportedLookAndFeelException ex) 
			{
				Logger.getLogger(Ini.class.getName()).log(Level.SEVERE, null, ex);
			}

	new Ini().setVisible(true);
	}

}
