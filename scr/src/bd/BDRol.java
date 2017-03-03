package bd;

import igui.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import objeto.Rol;

public class BDRol {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM rol ORDER BY rol.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM rol WHERE rol.activo=1 ORDER BY rol.nombre";
	
	static String SQLGuardar=	" INSERT INTO rol(fecha, nombre, descripcion, activo ) " +
								" VALUES(?,?, ?, ?)";
	
	static String SQLMoficar= 	" UPDATE rol SET fecha=?, nombre=?, descripcion=?, activo=? " +
								" WHERE idrol=?";
	
	static String SQLBuscar= 	"SELECT * FROM rol WHERE rol.idrol=?";
	
	public static boolean guardar(Rol rol) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(rol.getFecha().getTime()));
				preparedstatement.setString(2, rol.getNombre());
				preparedstatement.setString(3,rol.getDescripcion());
				preparedstatement.setBoolean(4, rol.getActivo());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();
				while (rs.next()) 
					rol.setIdRol(rs.getInt(1));		         
				
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

	public static boolean Modificar(Rol rol) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(rol.getFecha().getTime()));
				preparedstatement.setString(2, rol.getNombre());
				preparedstatement.setString(3,rol.getDescripcion());
				preparedstatement.setBoolean(4, rol.getActivo());
				preparedstatement.setInt(5, rol.getIdRol());				
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
	
	public static ArrayList<Rol> getListaRoles(String consultaSQL){
		ArrayList<Rol> listado = new ArrayList<Rol>();
		listado.add(new Rol());
		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Rol(rs.getInt(1),rs.getDate(2), rs.getString(3),  rs.getString(4),rs.getBoolean(5)));
				}
				rs.close();
				statement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			//Mensaje = e.getMessage();
		}
		return listado;		
	}

	public static void cargarCombobox(JComboBox<Rol> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Rol> roles = getListaRoles((activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar );
			dataCombobox.removeAllItems();
		for(int i=0; i< roles.size(); i++)
			dataCombobox.addItem((Rol)roles.get(i));
	}

	public static void cargarRol(Rol c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdRol());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setNombre(rs.getString(2));
					c.setDescripcion(rs.getString(3));
					c.setActivo(rs.getBoolean(4));
				}
				rs.close();
				preparedstatement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
		//	Mensaje = e.getMessage();
		}		
	}
}
