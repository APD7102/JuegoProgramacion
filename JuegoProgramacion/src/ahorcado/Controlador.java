package ahorcado;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Controlador extends JFrame {


	public ImageIcon imgs[];
	public JButton btns[];
	public String palabra[];
	public int ran;
	public int err;
	public int err2;
	public String res[];
	int puntuacion=0;
	String nombreJugador="";
	
	public Controlador() {
		
		Componentes();
		setSize(650, 707);// Tamaño de la ventana
		this.setLocationRelativeTo(null);// Posición de la ventana
		Image icon = new ImageIcon(getClass().getResource("/imagenesysonidos/ahorcado2.png")).getImage(); //Imagen del programa
		setIconImage(icon);

		imgs = new ImageIcon[12];
		btns = new JButton[28];
		palabra = new String[20]; 

		// Imágenes del ahorcado
		imgs[0] = new ImageIcon(getClass().getResource("/imagenesysonidos/im1.jpg"));
		imgs[1] = new ImageIcon(getClass().getResource("/imagenesysonidos/im2.jpg"));
		imgs[2] = new ImageIcon(getClass().getResource("/imagenesysonidos/im3.jpg"));
		imgs[3] = new ImageIcon(getClass().getResource("/imagenesysonidos/im4.jpg"));
		imgs[4] = new ImageIcon(getClass().getResource("/imagenesysonidos/im5.jpg"));
		imgs[5] = new ImageIcon(getClass().getResource("/imagenesysonidos/im6.jpg"));
		imgs[6] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo0.png"));
		imgs[7] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo1.png"));
		imgs[8] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo2.png"));
		imgs[9] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo3.png"));
		imgs[10] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo4.png"));
		imgs[11] = new ImageIcon(getClass().getResource("/imagenesysonidos/fallo5.png"));
		
		// Botones para las letras
		btns[1] = Vista.button_1;
		btns[2] = Vista.button_2;
		btns[3] = Vista.button_3;
		btns[4] = Vista.button_4;
		btns[5] = Vista.button_5;
		btns[6] = Vista.button_6;
		btns[7] = Vista.button_7;
		btns[8] = Vista.button_8;
		btns[9] = Vista.button_9;
		btns[10] = Vista.button_10;
		btns[11] = Vista.button_11;
		btns[12] = Vista.button_12;
		btns[13] = Vista.button_13;
		btns[14] = Vista.button_14;
		btns[15] = Vista.button_16;
		btns[16] = Vista.button_17;
		btns[17] = Vista.button_18;
		btns[18] = Vista.button_19;
		btns[19] = Vista.button_20;
		btns[20] = Vista.button_21;
		btns[21] = Vista.button_22;
		btns[22] = Vista.button_23;
		btns[23] = Vista.button_24;
		btns[24] = Vista.button_25;
		btns[25] = Vista.button_26;
		btns[26] = Vista.button_27;
		btns[27] = Vista.button_15;

		// Palabras que usa el juego
		palabra[0] = "Casa".toUpperCase();
		palabra[1] = "Barcelona".toUpperCase();
		palabra[2] = "Madrid".toUpperCase();
		palabra[3] = "Coronavirus".toUpperCase();
		palabra[4] = "Streaming".toUpperCase();
		palabra[5] = "Studium".toUpperCase();
		palabra[6] = "Informatica".toUpperCase();
		palabra[7] = "Filosofia".toUpperCase();
		palabra[8] = "Ahorcado".toUpperCase();
		palabra[9] = "Programacion".toUpperCase();
		palabra[10] = "Helicoptero".toUpperCase();
		palabra[11] = "Google".toUpperCase();
		palabra[12] = "Telefono".toUpperCase();
		palabra[13] = "Futbol".toUpperCase();
		palabra[14] = "Tenis de mesa".toUpperCase();
		palabra[15] = "Asno".toUpperCase();
		palabra[16] = "Bus".toUpperCase();
		palabra[17] = "Coche".toUpperCase();
		palabra[18] = "Variable".toUpperCase();
		palabra[19] = "Teclado".toUpperCase();

		// Se asigna un evento a cada letra para comprobar que exista en la palabra a
		// adivinar
		for (int i = 1; i < 28; i++)
		{
			btns[i].addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					comprobarLetra(e);
				}
			});
		}
		iniciar();
	}

	// Funcion para comenzar los parametros del juego o iniciar una nueva partida
	
	public void iniciar() 
	{
		// ERRORES EN 0
		err = 0;
		err2 = 6;
		Vista.Dibujo.setIcon(imgs[0]);
		Vista.txtPalabra.setText("");
		Vista.errores.setIcon(imgs[6]);
		
		// Para activar las letras del tablero
		
		for (int i = 1; i < 28; i++) 
		{
			btns[i].setEnabled(true);
		}
		
		// Para generar una palabra aleatoriamente 
		
		ran = (int) 0 + (int) (Math.random() * ((palabra.length - 1) + 1));
		
		// SEPARA EL MENSAJE POR PALABRAS
		
		String pal[] = palabra[ran].split(" ");
		res = new String[palabra[ran].length() + 1];
		int j = 0;
		
		// Guiones debajo de las letras
		
		for (String pal1 : pal) 
		{
			for (int i = 0; i < pal1.length(); i++) 
			{
				Vista.txtPalabra.setText(Vista.txtPalabra.getText() + "_ ");
				res[j++] = "_";
			}
			Vista.txtPalabra.setText(Vista.txtPalabra.getText() + "\n");
			res[j++] = " ";
		}
	}

	// Al presionar una letra, se busca si la palabra a adivinar la contiene, de lo contrario se marca un error
	
	public void comprobarLetra(ActionEvent e) 
	{
		Icon cp = new ImageIcon(getClass().getResource("/imagenesysonidos/copa.png"));// icono de la copa
		Icon cara = new ImageIcon(getClass().getResource("/imagenesysonidos/cara.png"));// icono de la cara
		JButton bt = (JButton) e.getSource();
		char c[];
		
		// Busca la letra en la palabra despues de haber sido presionada
		
		for (int i = 1; i < 28; i++) {
			if (bt == btns[i]) 
			{
				// La tecla es inicializada
				c = Character.toChars(64 + i);
				
				// Busca si la letra esta en la palabra
				
				boolean esta = false;
				for (int j = 0; j < palabra[ran].length(); j++) 
				{
					if (c[0] == palabra[ran].charAt(j)) 
					{
						res[j] = c[0] + "";
						esta = true;
					}
				}
				
				// SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
				if (esta) 
				{
					Vista.txtPalabra.setText("");
					for (String re : res) 
					{
						if (" ".equals(re)) 
						{
							Vista.txtPalabra.setText(Vista.txtPalabra.getText() + "\n");
						}
						else 
						{
							Vista.txtPalabra.setText(Vista.txtPalabra.getText() + re + " ");
						}
					}
					
					// Hace una comprobacion de las letras restantes y en caso de que ya no haya letras sera ganador
					
					boolean gano = true;
					for (String re : res) 
					{
						if (re.equals("_")) 
						{
							gano = false;
							break;
						}
					}
					
					// Al ser correcta se muestra un mensaje, se suma la puntuación y se reinicia el juego

					if (gano) 
					{
						puntuacion = puntuacion + 5;
						Vista.jMenu3.setText("Puntuación: " + puntuacion);
						new Sonido("ganador1");
						JOptionPane.showMessageDialog(this, "¡Eres un máquina, has ganado!","GANADOR", JOptionPane.INFORMATION_MESSAGE, cp);
						iniciar();
						return;
					}
					
					// SI LA LETRA NO ESTA EN EL MENSAJE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
				} 
				else 
				{
					Vista.Dibujo.setIcon(imgs[++err]);
					Vista.errores.setIcon(imgs[++err2]);
					
					// Si llega a 5 errores, se pierde la partida. Se restará la puntuación y saldrá un aviso
					
					if (err == 5) 
					{
						puntuacion = puntuacion - 5;
						Vista.jMenu3.setText("Puntuación: " + puntuacion);
						new Sonido("perdedor1");
						JOptionPane.showMessageDialog(this," Has perdido\n Prueba la petanca, es más facil\n La palabra es: \n " + palabra[ran], "PERDEDOR",JOptionPane.INFORMATION_MESSAGE, cara);
						iniciar();
						return;
					}
				}
				
				// Esta es la linea que desactiva las letras despues de ser usadas 
				
				bt.setEnabled(false);
				break;
			}
		}

	}


	private void Componentes() {


		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(null);

		Vista.jPanel1.setBackground(new Color(255, 255, 255));
		Vista.jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Vista.jPanel2.setBackground(new Color(255, 255, 255));
		Vista.jPanel2.setBorder(BorderFactory.createTitledBorder(null, "MENÚ", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 
		Vista.jPanel2.setForeground(new Color(0, 0, 0));
		Vista.generarPalabra.setBackground(new Color(255, 255, 255));
		Vista.generarPalabra.setFont(new Font("Dialog", 1, 18)); 
		Vista.generarPalabra.setForeground(new Color(0, 0, 0));
		Vista.generarPalabra.setText("GENERAR PALABRA NUEVA");
		Vista.generarPalabra.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				IniciarActionPerformed(evt);
			}
		});

		Vista.btnResolver.setBackground(new Color(255, 255, 255));
		Vista.btnResolver.setFont(new Font("Dialog", 1, 18)); 
		Vista.btnResolver.setForeground(new Color(0, 0, 0));
		Vista.btnResolver.setText("RESOLVER");
		Vista.btnResolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				btnResolverActionPerformed(evt);
			}
		});

		Vista.btnSalir.setBackground(new Color(255, 255, 255));
		Vista.btnSalir.setFont(new Font("Dialog", 1, 18)); 
		Vista.btnSalir.setForeground(new Color(0, 0, 0));
		Vista.btnSalir.setText("SALIR");
		Vista.btnSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				btnSalirActionPerformed(evt);
			}
		});

		Vista.mejoresPartidas.setBackground(new Color(255, 255, 255));
		Vista.mejoresPartidas.setFont(new Font("Dialog", 1, 18)); 
		Vista.mejoresPartidas.setForeground(new Color(0, 0, 0));
		Vista.mejoresPartidas.setText("CLASIFICACIÓN");
		Vista.mejoresPartidas.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				ClasificacionActionPerformed(evt);
			}
		});
		
		//Configuración de los distintos paneles para que encajen a la perfección. Gracias a internet no morí en el intento.

		GroupLayout jPanel2Layout = new GroupLayout(Vista.jPanel2);
		Vista.jPanel2.setLayout(jPanel2Layout);

		jPanel2Layout
		.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.generarPalabra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addGroup(jPanel2Layout.createSequentialGroup()
		.addComponent(Vista.btnResolver, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Vista.btnSalir,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		.addComponent(Vista.mejoresPartidas, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel2Layout.createSequentialGroup().addComponent(Vista.generarPalabra)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.btnResolver).addComponent(Vista.btnSalir))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Vista.mejoresPartidas)
		.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		Vista.jPanel3.setBackground(new Color(255, 255, 255));
		Vista.jPanel3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

		Vista.jPanel4.setBackground(new Color(255, 255, 255));
		Vista.jPanel4.setBorder(BorderFactory.createTitledBorder(null, "PALABRA OCULTA", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		Vista.txtPalabra.setEditable(false);
		Vista.txtPalabra.setBackground(new Color(255, 255, 255));
		Vista.txtPalabra.setFont(new Font("Dialog", 0, 24)); 
		Vista.txtPalabra.setForeground(new Color(0, 0, 0));
		Vista.txtPalabra.setHorizontalAlignment(JTextField.CENTER);

		GroupLayout jPanel4Layout = new GroupLayout(Vista.jPanel4);
		Vista.jPanel4.setLayout(jPanel4Layout);
		
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
		jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(Vista.txtPalabra).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
		.addComponent(Vista.txtPalabra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
		.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		Vista.jPanel5.setBackground(new Color(255, 255, 255));
		Vista.jPanel5.setBorder(BorderFactory.createTitledBorder(null, "ERRORES", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		GroupLayout jPanel5Layout = new GroupLayout(Vista.jPanel5);
		Vista.jPanel5.setLayout(jPanel5Layout);
		
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel5Layout.createSequentialGroup().addGap(46, 46, 46)
		.addComponent(Vista.errores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.errores, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));

		GroupLayout jPanel3Layout = new GroupLayout(Vista.jPanel3);
		Vista.jPanel3.setLayout(jPanel3Layout);
		
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
		.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
		.addComponent(Vista.jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
		.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
		.addComponent(Vista.jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Vista.jPanel5,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		.addContainerGap(7, Short.MAX_VALUE)));

		Vista.jPanel6.setBackground(new Color(255, 255, 255));
		Vista.jPanel6.setBorder(BorderFactory.createTitledBorder(null, "AHORCADO", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		GroupLayout jPanel6Layout = new GroupLayout(Vista.jPanel6);
		Vista.jPanel6.setLayout(jPanel6Layout);
		
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
		.addComponent(Vista.Dibujo, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addContainerGap()));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel6Layout.createSequentialGroup()
		.addComponent(Vista.Dibujo, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE).addContainerGap()));

		Vista.jPanel7.setBackground(new Color(255, 255, 255));
		Vista.jPanel7.setBorder(BorderFactory.createTitledBorder(null, "TECLADO", TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		Vista.button_2.setBackground(new Color(255, 255, 255));
		Vista.button_2.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_2.setForeground(new Color(0, 0, 0));
		Vista.button_2.setText("B");
		Vista.button_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_2ActionPerformed(evt);
			}
		});

		Vista.button_1.setBackground(new Color(255, 255, 255));
		Vista.button_1.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_1.setForeground(new Color(0, 0, 0));
		Vista.button_1.setText("A");
		Vista.button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				button_1ActionPerformed(evt);
			}
		});

		Vista.button_3.setBackground(new Color(255, 255, 255));
		Vista.button_3.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_3.setForeground(new Color(0, 0, 0));
		Vista.button_3.setText("C");
		Vista.button_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_3ActionPerformed(evt);
			}
		});

		Vista.button_4.setBackground(new Color(255, 255, 255));
		Vista.button_4.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_4.setForeground(new Color(0, 0, 0));
		Vista.button_4.setText("D");
		Vista.button_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_4ActionPerformed(evt);
			}
		});

		Vista.button_5.setBackground(new Color(255, 255, 255));
		Vista.button_5.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_5.setForeground(new Color(0, 0, 0));
		Vista.button_5.setText("E");
		Vista.button_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_5ActionPerformed(evt);
			}
		});

		Vista.button_6.setBackground(new Color(255, 255, 255));
		Vista.button_6.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_6.setForeground(new Color(0, 0, 0));
		Vista.button_6.setText("F");
		Vista.button_6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_6ActionPerformed(evt);
			}
		});

		Vista.button_7.setBackground(new Color(255, 255, 255));
		Vista.button_7.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_7.setForeground(new Color(0, 0, 0));
		Vista.button_7.setText("G");
		Vista.button_7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_7ActionPerformed(evt);
			}
		});

		Vista.button_8.setBackground(new Color(255, 255, 255));
		Vista.button_8.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_8.setForeground(new Color(0, 0, 0));
		Vista.button_8.setText("H");
		Vista.button_8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_8ActionPerformed(evt);
			}
		});

		Vista.button_9.setBackground(new Color(255, 255, 255));
		Vista.button_9.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_9.setForeground(new Color(0, 0, 0));
		Vista.button_9.setText("I");
		Vista.button_9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_9ActionPerformed(evt);
			}
		});

		Vista.button_10.setBackground(new Color(255, 255, 255));
		Vista.button_10.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_10.setForeground(new Color(0, 0, 0));
		Vista.button_10.setText("J");
		Vista.button_10.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_10ActionPerformed(evt);
			}
		});

		Vista.button_11.setBackground(new Color(255, 255, 255));
		Vista.button_11.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_11.setForeground(new Color(0, 0, 0));
		Vista.button_11.setText("K");
		Vista.button_11.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_11ActionPerformed(evt);
			}
		});

		Vista.button_12.setBackground(new Color(255, 255, 255));
		Vista.button_12.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_12.setForeground(new Color(0, 0, 0));
		Vista.button_12.setText("L");
		Vista.button_12.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				button_12ActionPerformed(evt);
			}
		});

		Vista.button_13.setBackground(new Color(255, 255, 255));
		Vista.button_13.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_13.setForeground(new Color(0, 0, 0));
		Vista.button_13.setText("M");
		Vista.button_13.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_13ActionPerformed(evt);
			}
		});

		Vista.button_14.setBackground(new Color(255, 255, 255));
		Vista.button_14.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_14.setForeground(new Color(0, 0, 0));
		Vista.button_14.setText("N");
		Vista.button_14.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
{
				button_14ActionPerformed(evt);
			}
		});

		Vista.button_16.setBackground(new Color(255, 255, 255));
		Vista.button_16.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_16.setForeground(new Color(0, 0, 0));
		Vista.button_16.setText("O");
		Vista.button_16.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_16ActionPerformed(evt);
			}


		});

		Vista.button_17.setBackground(new Color(255, 255, 255));
		Vista.button_17.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_17.setForeground(new Color(0, 0, 0));
		Vista.button_17.setText("P");
		Vista.button_17.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_17ActionPerformed(evt);
			}
		});

		Vista.button_18.setBackground(new Color(255, 255, 255));
		Vista.button_18.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_18.setForeground(new Color(0, 0, 0));
		Vista.button_18.setText("Q");
		Vista.button_18.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_18ActionPerformed(evt);
			}
		});

		Vista.button_19.setBackground(new Color(255, 255, 255));
		Vista.button_19.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_19.setForeground(new Color(0, 0, 0));
		Vista.button_19.setText("R");
		Vista.button_19.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt)
			{
				button_19ActionPerformed(evt);
			}
		});

		Vista.button_20.setBackground(new Color(255, 255, 255));
		Vista.button_20.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_20.setForeground(new Color(0, 0, 0));
		Vista.button_20.setText("S");
		Vista.button_20.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_20ActionPerformed(evt);
			}
		});

		Vista.button_21.setBackground(new Color(255, 255, 255));
		Vista.button_21.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_21.setForeground(new Color(0, 0, 0));
		Vista.button_21.setText("T");
		Vista.button_21.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_21ActionPerformed(evt);
			}
		});

		Vista.button_22.setBackground(new Color(255, 255, 255));
		Vista.button_22.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_22.setForeground(new Color(0, 0, 0));
		Vista.button_22.setText("U");
		Vista.button_22.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_22ActionPerformed(evt);
			}
		});

		Vista.button_23.setBackground(new Color(255, 255, 255));
		Vista.button_23.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_23.setForeground(new Color(0, 0, 0));
		Vista.button_23.setText("V");
		Vista.button_23.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_23ActionPerformed(evt);
			}
		});

		Vista.button_24.setBackground(new Color(255, 255, 255));
		Vista.button_24.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_24.setForeground(new Color(0, 0, 0));
		Vista.button_24.setText("W");
		Vista.button_24.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_24ActionPerformed(evt);
			}
		});

		Vista.button_25.setBackground(new Color(255, 255, 255));
		Vista.button_25.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_25.setForeground(new Color(0, 0, 0));
		Vista.button_25.setText("X");
		Vista.button_25.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_25ActionPerformed(evt);
			}
		});

		Vista.button_26.setBackground(new Color(255, 255, 255));
		Vista.button_26.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_26.setForeground(new Color(0, 0, 0));
		Vista.button_26.setText("Y");
		Vista.button_26.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_26ActionPerformed(evt);
			}
		});

		Vista.button_27.setBackground(new Color(255, 255, 255));
		Vista.button_27.setFont(new Font("Dialog", 1, 18)); 
		Vista.button_27.setForeground(new Color(0, 0, 0));
		Vista.button_27.setText("Z");
		Vista.button_27.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_27ActionPerformed(evt);
			}
		});

		Vista.button_15.setBackground(new Color(255, 255, 255));
		Vista.button_15.setFont(new Font("Dialog", 1, 18));
		Vista.button_15.setForeground(new Color(0, 0, 0));
		Vista.button_15.setText("Ñ");
		Vista.button_15.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				button_15ActionPerformed(evt);
			}
		});

		GroupLayout jPanel7Layout = new GroupLayout(Vista.jPanel7); 
		Vista.jPanel7.setLayout(jPanel7Layout);

		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.button_25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addComponent(Vista.button_19, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addComponent(Vista.button_7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addComponent(Vista.button_13, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addComponent(Vista.button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel7Layout.createSequentialGroup()
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    .addComponent(Vista.button_20, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addComponent(Vista.button_26, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
		.addComponent(Vista.button_21, GroupLayout.DEFAULT_SIZE, 89,Short.MAX_VALUE)
		.addComponent(Vista.button_27, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_22, GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE))
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	
		.addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
		.addComponent(Vista.button_2, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_3, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_4, GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_5, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_6, GroupLayout.PREFERRED_SIZE, 91,GroupLayout.PREFERRED_SIZE))
		.addComponent(Vista.button_24, GroupLayout.Alignment.TRAILING,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
		.addGroup(GroupLayout.Alignment.TRAILING,jPanel7Layout.createSequentialGroup().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
		.addComponent(Vista.button_8,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_9,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_10,GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_11, GroupLayout.PREFERRED_SIZE,89, GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
		.addGroup(jPanel7Layout.createSequentialGroup()
		.addComponent(Vista.button_14,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.button_15,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
		.addComponent(Vista.button_16,GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.button_23,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addComponent(Vista.button_17,GroupLayout.Alignment.TRAILING,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE))
		.addGap(2, 2, 2)))
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.button_18,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
		.addComponent(Vista.button_12,GroupLayout.PREFERRED_SIZE, 91,GroupLayout.PREFERRED_SIZE)))))
		.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.button_1).addComponent(Vista.button_2).addComponent(Vista.button_5)
		.addComponent(Vista.button_4).addComponent(Vista.button_3).addComponent(Vista.button_6))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.button_7).addComponent(Vista.button_8).addComponent(Vista.button_11)
		.addComponent(Vista.button_10).addComponent(Vista.button_9).addComponent(Vista.button_12))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.button_13).addComponent(Vista.button_14).addComponent(Vista.button_15)
		.addComponent(Vista.button_16).addComponent(Vista.button_17).addComponent(Vista.button_18))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.button_20).addComponent(Vista.button_19).addComponent(Vista.button_21)
		.addComponent(Vista.button_22).addComponent(Vista.button_23).addComponent(Vista.button_24))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		.addComponent(Vista.button_26).addComponent(Vista.button_27).addComponent(Vista.button_25))
		.addGap(11, 11, 11)));

		GroupLayout jPanel1Layout = new GroupLayout(Vista.jPanel1); 
		Vista.jPanel1.setLayout(jPanel1Layout);
		
		jPanel1Layout
		.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
		.addGroup(GroupLayout.Alignment.LEADING,jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(Vista.jPanel7,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		.addGroup(GroupLayout.Alignment.LEADING,jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
		.addComponent(Vista.jPanel2, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
		.addComponent(Vista.jPanel3, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
		.addComponent(Vista.jPanel6, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		.addGap(0, 69, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addGroup(jPanel1Layout.createSequentialGroup().addGap(12, 12, 12)
		.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		.addComponent(Vista.jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
		.addGroup(jPanel1Layout.createSequentialGroup()
		.addComponent(Vista.jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Vista.jPanel3,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))
		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(Vista.jPanel7,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		.addContainerGap(26, Short.MAX_VALUE)));

		getContentPane().add(Vista.jPanel1);
		Vista.jPanel1.setBounds(0, 0, 650, 680);


		Vista.jMenu2.setForeground(new Color(0, 0, 0));
		Vista.jMenu2.setText("Acerca de ");
		Vista.jMenu2.setFont(new Font("Dialog", 1, 14)); 
		Vista.jMenu2.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt) 
			{
				jMenu2MouseClicked(evt);
			}
		});
		
		Vista.jMenu3.setForeground(new Color(0, 0, 0));
		Vista.jMenu3.setText("Puntuación: " + puntuacion);
		Vista.jMenu3.setFont(new Font("Dialog", 1, 14));
		Vista.jMenuBar1.add(Vista.jMenu2);
		Vista.jMenuBar1.add(Vista.jMenu3);
		setJMenuBar(Vista.jMenuBar1);

	}


	private void btnSalirActionPerformed(ActionEvent evt) 
	{
		new Sonido("click2");
		if (JOptionPane.showConfirmDialog(rootPane,"¿Estas seguro de querer regresar al menu principal?\n Se perdera todo su progreso..", "Ahorcado",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) 
		{
			new Sonido("click2");
			Menu m = new Menu();
			m.setVisible(true);
			this.setVisible(false);

			try
			{
				nombreJugador = Vista.txtNombreJugador.getText();
				Modelo.ConexionBD();
				Modelo.sentencia = "INSERT INTO usuarios (nombreUsuario,puntuacionUsuario) VALUES ('"+nombreJugador+"',"+puntuacion+")";
				Modelo.statement.executeUpdate(Modelo.sentencia);
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				try
				{
					if(Modelo.connection!=null)
					{
						Modelo.connection.close();
					}
				}
				catch (SQLException e)
				{
					System.out.println("Error 3-"+e.getMessage());
				}
			}
		} 
		else 
		{	
			new Sonido("click2");
			setDefaultCloseOperation(0);
		}

	}

	private void button_25ActionPerformed(ActionEvent evt)
	{
		new Sonido("teclas");
	}

	private void IniciarActionPerformed(ActionEvent evt) 
	{
		new Sonido("click2");
		if (JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de querer una palabra nueva?", "Ahorcado",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) 
		{
			new Sonido("click2");
			iniciar();
		} 
		else 
		{
			new Sonido("click2");
			setDefaultCloseOperation(0);
		}

	}

	private void btnResolverActionPerformed(ActionEvent evt) 
	{
		new Sonido("click2");
		JOptionPane.showMessageDialog(this, "La palabra es: " +  palabra[ran] , "Has recurrido al método fácil...", JOptionPane.INFORMATION_MESSAGE);
		new Sonido("click2");
		iniciar();
		return;
	}

	private void ClasificacionActionPerformed(ActionEvent evt)
	{
		
		new Sonido("click2");
		if(evt.getSource().equals(Vista.mejoresPartidas)) 
		{
			new Puntuacion();
		}
		
	}

	private void button_1ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");

	}

	private void button_2ActionPerformed(ActionEvent evt)
	{
		new Sonido("teclas");
	}

	private void button_3ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_4ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_5ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_6ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_7ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_8ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_9ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_10ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_11ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_12ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_13ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_14ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_17ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_18ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_19ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_20ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_21ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_22ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_23ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_24ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_26ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_27ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_15ActionPerformed(ActionEvent evt) 
	{
		new Sonido("teclas");
	}

	private void button_16ActionPerformed(ActionEvent evt) 
	{

		new Sonido("teclas");
	}
	



	private void jMenu2MouseClicked(MouseEvent evt) 
	{
		new Sonido("click2");
		JOptionPane.showMessageDialog(null, "Hecho por: Alejandro Polinario Delgado" + "\n" + "Profesor: Jorge Rodríguez" + "\n" + "GrupoStudium 2019-2020", "Práctica final programación", JOptionPane.INFORMATION_MESSAGE);
		new Sonido("click2");
	}


	public static void main(String args[]) {


		// Añado el tema Nimbus Look and Feel para que el juego se vea igual sin
		// importar donde se ejecute, además de para personalizarlo más
		// Si no funciona el juego es completamente funcional, solo cambia la estética
		// un poco
		// https://docs.oracle.com/javase/8/docs/technotes/guides/swing/nimbus_laf.html

		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		}

		new Controlador().setVisible(true);
	}


}