package pruebasjuego;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Puntuacion extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1L;
	
	public static void altaUsuario(String nombreUsuario)
	{
		
	}
	
	Puntuacion()
	{
		Vista.clasificacion.setLayout(new FlowLayout());
		Vista.clasificacion.setTitle("Clasificación");
		Vista.clasificacion.setResizable(false);
		Vista.clasificacion.setLocationRelativeTo(null);
		Vista.taClasificacion.setEditable(false);
		Vista.clasificacion.setSize(300, 300);
		Vista.clasificacion.setResizable(false);
		Vista.clasificacion.add(Vista.lblclasificacion);
		Vista.clasificacion.add(Vista.taClasificacion);
		Vista.clasificacion.addWindowListener(this);
		Vista.clasificacion.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area
		{
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM usuarios";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			Vista.taClasificacion.setText("");
			while(Modelo.rs.next())
			{
				if(Vista.taClasificacion.getText().length()==0)
				{
					Vista.taClasificacion.setText(Modelo.rs.getString("nombreUsuario")+
							" --- "+Modelo.rs.getInt("puntuacionUsuario"));
				}
				else
				{
					Vista.taClasificacion.setText(Vista.taClasificacion.getText() + "\n" +
							Modelo.rs.getString("nombreUsuario")+
							" --- "+Modelo.rs.getInt("puntuacionUsuario"));
				}
			}
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

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.clasificacion.isActive())
		{
			Vista.clasificacion.setVisible(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}