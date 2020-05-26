package pruebasjuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Ahorcado extends JFrame {
	Clip clip;
	String ruta="/imagenesysonidos/";

	public ImageIcon imgs[];
	public JButton btns[];
	public String msgs[];
	public int ran;
	public int err;
	public int err2;
	public String res[];

	public Ahorcado() {
		Componentes();
		setSize(650, 707);// Tamaño de la ventana
		this.setLocationRelativeTo(null);// Posición de la ventana

		Image icon = new ImageIcon(getClass().getResource("/imagenesysonidos/ahorcado2.png")).getImage(); //Imagen del programa
		setIconImage(icon);

		imgs = new ImageIcon[12];
		btns = new JButton[28];
		msgs = new String[20]; 

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
		btns[1] = button_1;
		btns[2] = button_2;
		btns[3] = button_3;
		btns[4] = button_4;
		btns[5] = button_5;
		btns[6] = button_6;
		btns[7] = button_7;
		btns[8] = button_8;
		btns[9] = button_9;
		btns[10] = button_10;
		btns[11] = button_11;
		btns[12] = button_12;
		btns[13] = button_13;
		btns[14] = button_14;
		btns[15] = button_16;
		btns[16] = button_17;
		btns[17] = button_18;
		btns[18] = button_19;
		btns[19] = button_20;
		btns[20] = button_21;
		btns[21] = button_22;
		btns[22] = button_23;
		btns[23] = button_24;
		btns[24] = button_25;
		btns[25] = button_26;
		btns[26] = button_27;
		btns[27] = button_15;

		// palabras por advinar, para agregar una nueva palabra sera necesario
		// declararla al inicio
		msgs[0] = "Casa".toUpperCase();
		msgs[1] = "Barcelona".toUpperCase();
		msgs[2] = "Madrid".toUpperCase();
		msgs[3] = "Coronavirus".toUpperCase();
		msgs[4] = "Streaming".toUpperCase();
		msgs[5] = "Studium".toUpperCase();
		msgs[6] = "Informatica".toUpperCase();
		msgs[7] = "Filosofia".toUpperCase();
		msgs[8] = "Ahorcado".toUpperCase();
		msgs[9] = "Programacion".toUpperCase();
		msgs[10] = "Helicoptero".toUpperCase();
		msgs[11] = "Google".toUpperCase();
		msgs[12] = "Telefono".toUpperCase();
		msgs[13] = "Futbol".toUpperCase();
		msgs[14] = "Tenis de mesa".toUpperCase();
		msgs[15] = "Asno".toUpperCase();
		msgs[16] = "Bus".toUpperCase();
		msgs[17] = "Coche".toUpperCase();
		msgs[18] = "Variable".toUpperCase();
		msgs[19] = "Teclado".toUpperCase();

		// se asigna un evento a cada letra para comprobar que exista en la palabra a
		// adivinar
		for (int i = 1; i < 28; i++) {
			btns[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarLetra(e);
				}
			});
		}
		iniciar();
	}

	// funcion para comenzar los parametros del juego o iniciar una nueva partida
	public void iniciar() {
		// ERRORES EN 0
		err = 0;
		err2 = 6;
		Dibujo.setIcon(imgs[0]);
		txtPalabra.setText("");
		errores.setIcon(imgs[6]);// :___________________________________----
		// para activar las letras del tablero
		for (int i = 1; i < 28; i++) {
			btns[i].setEnabled(true);
		}
		// para generar una palabra aleatoriamente 
		ran = (int) 0 + (int) (Math.random() * ((msgs.length - 1) + 1));
		// SEPARA EL MENSAJE POR PALABRAS
		String pal[] = msgs[ran].split(" ");
		res = new String[msgs[ran].length() + 1];
		int j = 0;
		// seran los guiones que van debajo de las letras como una separacion_
		for (String pal1 : pal) {
			for (int i = 0; i < pal1.length(); i++) {
				txtPalabra.setText(txtPalabra.getText() + "_ ");
				res[j++] = "_";
			}
			txtPalabra.setText(txtPalabra.getText() + "\n");
			res[j++] = " ";
		}
	}

	// al presionar una letra, esta se buscara si pertenece a la palabra, de lo
	// contrario la marcara como error
	public void comprobarLetra(ActionEvent e) {
		Icon cp = new ImageIcon(getClass().getResource("/imagenesysonidos/copa.png"));// icono de la copa
		Icon cara = new ImageIcon(getClass().getResource("/imagenesysonidos/cara.png"));// icono de la copa
		JButton bt = (JButton) e.getSource();
		char c[];
		// busca la letra en la palabra despues de haber sido presionada
		for (int i = 1; i < 28; i++) {
			if (bt == btns[i]) {
				// la tecla es inicializada
				c = Character.toChars(64 + i);
				// busca si la letra esta en la frase
				boolean esta = false;
				for (int j = 0; j < msgs[ran].length(); j++) {
					if (c[0] == msgs[ran].charAt(j)) {
						res[j] = c[0] + "";
						esta = true;
					}
				}
				// SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
				if (esta) {
					txtPalabra.setText("");
					for (String re : res) {
						if (" ".equals(re)) {
							txtPalabra.setText(txtPalabra.getText() + "\n");
						} else {
							txtPalabra.setText(txtPalabra.getText() + re + " ");
						}
					}
					// hace una comprobacion de las letras restantes y faltantes, en caso de que ya
					// no haya letras sera ganador
					boolean gano = true;
					for (String re : res) {
						if (re.equals("_")) {
							gano = false;
							break;
						}
					}
					// al ser correcta se muestra un mensaje y se reinicia el juego

					if (gano) {
						sonido("ganador1");
						JOptionPane.showMessageDialog(this, "¡Eres un máquina, has ganado!",
								"GANADOR", JOptionPane.INFORMATION_MESSAGE, cp);

						iniciar();
						return;
					}
					// SI LA LETRA NO ESTA EN EL MENSAJE, SE INCREMENTA EL ERROR Y SE CAMBIA LA
					// IMAGEN
				} else {
					Dibujo.setIcon(imgs[++err]);
					errores.setIcon(imgs[++err2]);////////////////////////////////////// _----
					// SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAJE
					// DE:
					if (err == 5) {
						sonido("perdedor1");

						JOptionPane.showMessageDialog(this,
								"HAS PERDIDO\n Intenta con otra palabra la respuesta es: \n" + msgs[ran], "PERDEDOR",
								JOptionPane.INFORMATION_MESSAGE, cara);

						iniciar();
						return;
					}
				}
				// esta es la linea que desactiva las letras despues de ser usadas 
				bt.setEnabled(false);
				break;
			}
		}

	}


	private void Componentes() {

		jPanel1 = new JPanel(); //Panel principal
		jPanel2 = new JPanel(); //Panel de menu
		generarPalabra = new JButton(); //Botón para generar una nueva palabra
		btnResolver = new JButton(); //Botón para resolver la palabra
		btnSalir = new JButton(); // Botón para salir
		mejoresPartidas = new JButton(); //Botón para consultar los mejores jugadores con su puntuación
		jPanel3 = new JPanel(); //Panel para unificar los errores y la palabra oculta
		jPanel4 = new JPanel(); //Panel palabra oculta
		txtPalabra = new JTextField(); // Donde aparece la palabra a adivinar
		jPanel5 = new JPanel(); //Panel de errores
		errores = new JLabel(); // Donde aparecen los errores
		jPanel6 = new JPanel(); // Panel principal donde sale el muñeco ahorcado
		Dibujo = new JLabel();  // Label donde sale el muñeco ahorcado
		jPanel7 = new JPanel(); // Panel del teclado
		button_2 = new JButton();
		button_1 = new JButton();
		button_3 = new JButton();
		button_4 = new JButton();
		button_5 = new JButton();
		button_6 = new JButton();
		button_7 = new JButton();
		button_8 = new JButton();
		button_9 = new JButton();
		button_10 = new JButton();
		button_11 = new JButton();
		button_12 = new JButton();
		button_13 = new JButton();
		button_14 = new JButton();
		button_16 = new JButton();
		button_17 = new JButton();
		button_18 = new JButton();
		button_19 = new JButton();
		button_20 = new JButton();
		button_21 = new JButton();
		button_22 = new JButton();
		button_23 = new JButton();
		button_24 = new JButton();
		button_25 = new JButton();
		button_26 = new JButton();
		button_27 = new JButton();
		button_15 = new JButton();
		jMenuBar1 = new JMenuBar(); //Acerca de
		jMenu2 = new JMenu(); //Acerca de

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(null);

		jPanel1.setBackground(new Color(255, 255, 255));
		jPanel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		jPanel2.setBackground(new Color(255, 255, 255));
		jPanel2.setBorder(BorderFactory.createTitledBorder(null, "MENÚ", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 
		jPanel2.setForeground(new Color(0, 0, 0));

		generarPalabra.setBackground(new Color(255, 255, 255));
		generarPalabra.setFont(new Font("Dialog", 1, 18)); 
		generarPalabra.setForeground(new Color(0, 0, 0));
		generarPalabra.setText("GENERAR PALABRA NUEVA");
		generarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				IniciarActionPerformed(evt);
			}
		});

		btnResolver.setBackground(new Color(255, 255, 255));
		btnResolver.setFont(new Font("Dialog", 1, 18)); 
		btnResolver.setForeground(new Color(0, 0, 0));
		btnResolver.setText("RESOLVER");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnResolverActionPerformed(evt);
			}
		});

		btnSalir.setBackground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Dialog", 1, 18)); 
		btnSalir.setForeground(new Color(0, 0, 0));
		btnSalir.setText("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSalirActionPerformed(evt);
			}
		});

		mejoresPartidas.setBackground(new Color(255, 255, 255));
		mejoresPartidas.setFont(new Font("Dialog", 1, 18)); 
		mejoresPartidas.setForeground(new Color(0, 0, 0));
		mejoresPartidas.setText("MEJORES PARTIDAS");
		mejoresPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mejoresPartidasActionPerformed(evt);
			}
		});

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(generarPalabra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(btnResolver, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSalir,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(mejoresPartidas, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addComponent(generarPalabra)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnResolver).addComponent(btnSalir))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(mejoresPartidas)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel3.setBackground(new Color(255, 255, 255));
		jPanel3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

		jPanel4.setBackground(new Color(255, 255, 255));
		jPanel4.setBorder(BorderFactory.createTitledBorder(null, "PALABRA OCULTA", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		txtPalabra.setEditable(false);
		txtPalabra.setBackground(new Color(255, 255, 255));
		txtPalabra.setFont(new Font("Dialog", 0, 24)); 
		txtPalabra.setForeground(new Color(0, 0, 0));
		txtPalabra.setHorizontalAlignment(JTextField.CENTER);
		txtPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtPalabraActionPerformed(evt);
			}
		});

		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(txtPalabra).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
						.addComponent(txtPalabra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel5.setBackground(new Color(255, 255, 255));
		jPanel5.setBorder(BorderFactory.createTitledBorder(null, "ERRORES", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addGap(46, 46, 46)
						.addComponent(errores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(errores, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE));

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel5,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(7, Short.MAX_VALUE)));

		jPanel6.setBackground(new Color(255, 255, 255));
		jPanel6.setBorder(BorderFactory.createTitledBorder(null, "AHORCADO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap()
						.addComponent(Dibujo, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE).addContainerGap()));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup()
						.addComponent(Dibujo, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE).addContainerGap()));

		jPanel7.setBackground(new Color(255, 255, 255));
		jPanel7.setBorder(BorderFactory.createTitledBorder(null, "TECLADO", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(0, 0, 0))); 

		button_2.setBackground(new Color(255, 255, 255));
		button_2.setFont(new Font("Dialog", 1, 18)); 
		button_2.setForeground(new Color(0, 0, 0));
		button_2.setText("B");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_2ActionPerformed(evt);
			}
		});

		button_1.setBackground(new Color(255, 255, 255));
		button_1.setFont(new Font("Dialog", 1, 18)); 
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setText("A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_1ActionPerformed(evt);
			}
		});

		button_3.setBackground(new Color(255, 255, 255));
		button_3.setFont(new Font("Dialog", 1, 18)); 
		button_3.setForeground(new Color(0, 0, 0));
		button_3.setText("C");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_3ActionPerformed(evt);
			}
		});

		button_4.setBackground(new Color(255, 255, 255));
		button_4.setFont(new Font("Dialog", 1, 18)); 
		button_4.setForeground(new Color(0, 0, 0));
		button_4.setText("D");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_4ActionPerformed(evt);
			}
		});

		button_5.setBackground(new Color(255, 255, 255));
		button_5.setFont(new Font("Dialog", 1, 18)); 
		button_5.setForeground(new Color(0, 0, 0));
		button_5.setText("E");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_5ActionPerformed(evt);
			}
		});

		button_6.setBackground(new Color(255, 255, 255));
		button_6.setFont(new Font("Dialog", 1, 18)); 
		button_6.setForeground(new Color(0, 0, 0));
		button_6.setText("F");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_6ActionPerformed(evt);
			}
		});

		button_7.setBackground(new Color(255, 255, 255));
		button_7.setFont(new Font("Dialog", 1, 18)); 
		button_7.setForeground(new Color(0, 0, 0));
		button_7.setText("G");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_7ActionPerformed(evt);
			}
		});

		button_8.setBackground(new Color(255, 255, 255));
		button_8.setFont(new Font("Dialog", 1, 18)); 
		button_8.setForeground(new Color(0, 0, 0));
		button_8.setText("H");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_8ActionPerformed(evt);
			}
		});

		button_9.setBackground(new Color(255, 255, 255));
		button_9.setFont(new Font("Dialog", 1, 18)); 
		button_9.setForeground(new Color(0, 0, 0));
		button_9.setText("I");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_9ActionPerformed(evt);
			}
		});

		button_10.setBackground(new Color(255, 255, 255));
		button_10.setFont(new Font("Dialog", 1, 18)); 
		button_10.setForeground(new Color(0, 0, 0));
		button_10.setText("J");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_10ActionPerformed(evt);
			}
		});

		button_11.setBackground(new Color(255, 255, 255));
		button_11.setFont(new Font("Dialog", 1, 18)); 
		button_11.setForeground(new Color(0, 0, 0));
		button_11.setText("K");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_11ActionPerformed(evt);
			}
		});

		button_12.setBackground(new Color(255, 255, 255));
		button_12.setFont(new Font("Dialog", 1, 18)); 
		button_12.setForeground(new Color(0, 0, 0));
		button_12.setText("L");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_12ActionPerformed(evt);
			}
		});

		button_13.setBackground(new Color(255, 255, 255));
		button_13.setFont(new Font("Dialog", 1, 18)); 
		button_13.setForeground(new Color(0, 0, 0));
		button_13.setText("M");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_13ActionPerformed(evt);
			}
		});

		button_14.setBackground(new Color(255, 255, 255));
		button_14.setFont(new Font("Dialog", 1, 18)); 
		button_14.setForeground(new Color(0, 0, 0));
		button_14.setText("N");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_14ActionPerformed(evt);
			}
		});

		button_16.setBackground(new Color(255, 255, 255));
		button_16.setFont(new Font("Dialog", 1, 18)); 
		button_16.setForeground(new Color(0, 0, 0));
		button_16.setText("O");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_16ActionPerformed(evt);
			}


		});

		button_17.setBackground(new Color(255, 255, 255));
		button_17.setFont(new Font("Dialog", 1, 18)); 
		button_17.setForeground(new Color(0, 0, 0));
		button_17.setText("P");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_17ActionPerformed(evt);
			}
		});

		button_18.setBackground(new Color(255, 255, 255));
		button_18.setFont(new Font("Dialog", 1, 18)); 
		button_18.setForeground(new Color(0, 0, 0));
		button_18.setText("Q");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_18ActionPerformed(evt);
			}
		});

		button_19.setBackground(new Color(255, 255, 255));
		button_19.setFont(new Font("Dialog", 1, 18)); 
		button_19.setForeground(new Color(0, 0, 0));
		button_19.setText("R");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_19ActionPerformed(evt);
			}
		});

		button_20.setBackground(new Color(255, 255, 255));
		button_20.setFont(new Font("Dialog", 1, 18)); 
		button_20.setForeground(new Color(0, 0, 0));
		button_20.setText("S");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_20ActionPerformed(evt);
			}
		});

		button_21.setBackground(new Color(255, 255, 255));
		button_21.setFont(new Font("Dialog", 1, 18)); 
		button_21.setForeground(new Color(0, 0, 0));
		button_21.setText("T");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_21ActionPerformed(evt);
			}
		});

		button_22.setBackground(new Color(255, 255, 255));
		button_22.setFont(new Font("Dialog", 1, 18)); 
		button_22.setForeground(new Color(0, 0, 0));
		button_22.setText("U");
		button_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_22ActionPerformed(evt);
			}
		});

		button_23.setBackground(new Color(255, 255, 255));
		button_23.setFont(new Font("Dialog", 1, 18)); 
		button_23.setForeground(new Color(0, 0, 0));
		button_23.setText("V");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_23ActionPerformed(evt);
			}
		});

		button_24.setBackground(new Color(255, 255, 255));
		button_24.setFont(new Font("Dialog", 1, 18)); 
		button_24.setForeground(new Color(0, 0, 0));
		button_24.setText("W");
		button_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_24ActionPerformed(evt);
			}
		});

		button_25.setBackground(new Color(255, 255, 255));
		button_25.setFont(new Font("Dialog", 1, 18)); 
		button_25.setForeground(new Color(0, 0, 0));
		button_25.setText("X");
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_25ActionPerformed(evt);
			}
		});

		button_26.setBackground(new Color(255, 255, 255));
		button_26.setFont(new Font("Dialog", 1, 18)); 
		button_26.setForeground(new Color(0, 0, 0));
		button_26.setText("Y");
		button_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_26ActionPerformed(evt);
			}
		});

		button_27.setBackground(new Color(255, 255, 255));
		button_27.setFont(new Font("Dialog", 1, 18)); 
		button_27.setForeground(new Color(0, 0, 0));
		button_27.setText("Z");
		button_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_27ActionPerformed(evt);
			}
		});

		button_15.setBackground(new Color(255, 255, 255));
		button_15.setFont(new Font("Dialog", 1, 18));
		button_15.setForeground(new Color(0, 0, 0));
		button_15.setText("Ñ");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_15ActionPerformed(evt);
			}
		});

		GroupLayout jPanel7Layout = new GroupLayout(jPanel7); //Panel de las letras
		jPanel7.setLayout(jPanel7Layout);

		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addGroup(jPanel7Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(button_25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_19, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_13, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)

						.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel7Layout.createSequentialGroup()
										.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

												.addComponent(button_20, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(button_26, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

										.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)

												.addComponent(button_21, GroupLayout.DEFAULT_SIZE, 89,Short.MAX_VALUE)
												.addComponent(button_27, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(button_22, GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE))

								.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	
										.addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()

												.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 91,GroupLayout.PREFERRED_SIZE))
										.addComponent(button_24, GroupLayout.Alignment.TRAILING,GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)

										.addGroup(GroupLayout.Alignment.TRAILING,jPanel7Layout.createSequentialGroup().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()

														.addComponent(button_8,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(button_9,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(button_10,GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(button_11, GroupLayout.PREFERRED_SIZE,89, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))

												.addGroup(jPanel7Layout.createSequentialGroup()

														.addComponent(button_14,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(button_15,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
														.addComponent(button_16,GroupLayout.PREFERRED_SIZE, 83,GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

														.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addComponent(button_23,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE)
																.addComponent(button_17,GroupLayout.Alignment.TRAILING,GroupLayout.PREFERRED_SIZE, 89,GroupLayout.PREFERRED_SIZE))
														.addGap(2, 2, 2)))
												.addGroup(jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(button_18,
																GroupLayout.PREFERRED_SIZE, 89,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(button_12,
																GroupLayout.PREFERRED_SIZE, 91,
																GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel7Layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button_1).addComponent(button_2).addComponent(button_5)
						.addComponent(button_4).addComponent(button_3).addComponent(button_6))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button_7).addComponent(button_8).addComponent(button_11)
						.addComponent(button_10).addComponent(button_9).addComponent(button_12))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button_13).addComponent(button_14).addComponent(button_15)
						.addComponent(button_16).addComponent(button_17).addComponent(button_18))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button_20).addComponent(button_19).addComponent(button_21)
						.addComponent(button_22).addComponent(button_23).addComponent(button_24))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(button_26).addComponent(button_27).addComponent(button_25))
				.addGap(11, 11, 11)));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1); //Panel principal
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
		.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addGroup(
								GroupLayout.Alignment.LEADING,
								jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel7,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.LEADING,
								jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
												.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(0, 69, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(12, 12, 12)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel7,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(26, Short.MAX_VALUE)));

		getContentPane().add(jPanel1);
		jPanel1.setBounds(0, 0, 650, 680);







		jMenu2.setForeground(new Color(0, 0, 0));
		jMenu2.setText("Acerca de ");
		jMenu2.setFont(new Font("Dialog", 1, 14)); 
		jMenu2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jMenu2MouseClicked(evt);
			}
		});
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);


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



	private void btnSalirActionPerformed(ActionEvent evt) {
		sonido("click2");
		if (JOptionPane.showConfirmDialog(rootPane,
				"¿Estas seguro de querer regresar al menu principal?\n Se perdera todo su progreso..", "Ahorcado",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			sonido("click2");
			Menu m = new Menu();
			m.setVisible(true);
			this.setVisible(false);
		} else {
			sonido("click2");
			setDefaultCloseOperation(0);
		}
	}

	private void button_25ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void IniciarActionPerformed(ActionEvent evt) {
		sonido("click2");
		if (JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de querer una palabra nueva?", "Ahorcado",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			sonido("click2");
			iniciar();
		} else {
			sonido("click2");
			setDefaultCloseOperation(0);
		}

	}

	private void btnResolverActionPerformed(ActionEvent evt) 
	{sonido("click2");
	JOptionPane.showMessageDialog(this, "La palabra es: " +  msgs[ran] , "Has recurrido al método fácil...", JOptionPane.INFORMATION_MESSAGE);
	sonido("click2");
	iniciar();
	return;


	}

	private void mejoresPartidasActionPerformed(ActionEvent evt) {
		// Clasificacion de jugadores
		sonido("click2");
        if(evt.getSource().equals(mejoresPartidas)) 
        {
            new Puntuacion();
        }
		sonido("click2");
		
	}

	private void button_1ActionPerformed(ActionEvent evt) {
		sonido("teclas");

	}

	private void button_2ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_3ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}// GEN-LAST:event_button_3ActionPerformed

	private void button_4ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_5ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_6ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_7ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_8ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_9ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_10ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_11ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_12ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_13ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_14ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_17ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_18ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_19ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_20ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_21ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_22ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_23ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_24ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_26ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_27ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_15ActionPerformed(ActionEvent evt) {
		sonido("teclas");
	}

	private void button_16ActionPerformed(ActionEvent evt) {

		sonido("teclas");
	}
	private void txtPalabraActionPerformed(ActionEvent evt) {

	}



	private void jMenu2MouseClicked(MouseEvent evt) {
		sonido("click2");
		JOptionPane.showMessageDialog(null, "Hecho por: Alejandro Polinario Delgado" + "\n" + "Profesor: Jorge Rodríguez" + "\n" + "GrupoStudium 2019-2020", "Práctica final programación", JOptionPane.INFORMATION_MESSAGE);
		sonido("click2");
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

		new Ahorcado().setVisible(true);
	}


	private JLabel Dibujo;
	private JButton generarPalabra;
	private JButton btnResolver;
	private JButton btnSalir;
	private JButton button_1;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;
	private JButton button_2;
	private JButton button_20;
	private JButton button_21;
	private JButton button_22;
	private JButton button_23;
	private JButton button_24;
	private JButton button_25;
	private JButton button_26;
	private JButton button_27;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JLabel errores;
	private JButton mejoresPartidas;

	private JMenu jMenu2;
	private JMenuBar jMenuBar1;

	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel7;
	private JTextField txtPalabra;

}