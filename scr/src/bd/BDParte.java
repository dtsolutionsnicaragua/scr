package bd;

import igui.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import objeto.Parte;

public class BDParte {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM parte ORDER BY parte.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM parte WHERE parte.activo=1 ORDER BY parte.nombre";
	
	static String SQLGuardar=	" INSERT INTO parte(fecha, numero,nombre, activo ) " +
								" VALUES(?, ?, ?,?)";
	
	static String SQLMoficar= 	" UPDATE parte SET fecha = ?, numero=?, nombre=?, activo=? " +
								" WHERE idparte=?";
	
	static String SQLBuscar= 	"SELECT * FROM parte WHERE parte.idparte=?";
	
	public static boolean guardar(Parte parte) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(parte.getFecha().getTime()) );
				preparedstatement.setString(2,parte.getNumero());
				preparedstatement.setString(3,parte.getNombre());
				preparedstatement.setBoolean(4, parte.getActivo());
				preparedstatement.execute();
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 parte.setIdparte(rs.getInt(1)); //Establecer idGenerdo
		         } 
				rs.close();
				preparedstatement.close();
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			//Mensaje = e.getMessage();
		}
		return false;
	}

	public static boolean Modificar(Parte parte) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(parte.getFecha().getTime()) );
				preparedstatement.setString(2,parte.getNumero());
				preparedstatement.setString(3,parte.getNombre());
				preparedstatement.setBoolean(4, parte.getActivo());	
				preparedstatement.setInt(5, parte.getIdparte());
				preparedstatement.execute();
				preparedstatement.close();
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			//Mensaje = e.getMessage();
		}
		return false;		
	}
	
	public static ArrayList<Parte> getListaPartes(String consultaSQL){
		ArrayList<Parte> listado = new ArrayList<Parte>();
		listado.add(new Parte());
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Parte(rs.getInt(1),rs.getDate(2), rs.getString(3),  rs.getString(4),rs.getBoolean(5)));
				}
				rs.close();
				statement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			//Mensaje= e.getMessage();
		}
		return listado;		
	}

	public static void cargarCombobox(JComboBox<Parte> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Parte> partes = getListaPartes((activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar );
			dataCombobox.removeAllItems();
		for(int i=0; i< partes.size(); i++)
			dataCombobox.addItem((Parte)partes.get(i));
	}

	public static void cargarParte(Parte c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdparte());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setFecha(rs.getDate(2));
					c.setNumero(rs.getString(3));
					c.setNombre(rs.getString(4));
					c.setActivo(rs.getBoolean(5));
				}
				rs.close();
				preparedstatement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			//Mensaje = e.getMessage();
		}
		
	}
}
