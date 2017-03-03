package bd;

import igui.App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.sql.PreparedStatement;

import objeto.Permiso;

public class BDPermiso {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;

	
	static String SQLSeleccionar = "SELECT * FROM permiso ORDER BY permiso.idpermiso";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM permiso WHERE permiso.unico=1 ORDER BY permiso.idpermiso";
	
	static String SQLGuardar=	" INSERT INTO permiso( nombre, activo ) " +
								" VALUES(?, ?)";
	
	static String SQLMoficar= 	" UPDATE permiso SET nombre=?, activo=? " +
								" WHERE idpermiso=?";
	
	static String SQLBuscar= 	"SELECT * FROM permiso WHERE permiso.idpermiso=?";

	
	public static boolean guardar(Permiso permiso) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setString(1, permiso.getNombre());
				preparedstatement.setBoolean(2, permiso.getUnico());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 permiso.setIdpermiso(rs.getInt(1)); //Establecer idGenerdo
		         } 
				rs.close();				
				preparedstatement.close();
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ""+e.getMessage());
			//Mensaje =  e.getMessage();
		}
		return false;
	}

	public static boolean Modificar(Permiso permiso) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setString(1, permiso.getNombre());
				preparedstatement.setBoolean(2, permiso.getUnico());
				preparedstatement.setInt(3, permiso.getIdpermiso());
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
	
	public static ArrayList<Permiso> cargarPermiso(Boolean activarFiltro) {		
		return getListaPermiso((activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar );		
	}
	
	public static ArrayList<Permiso> getListaPermiso(String consultaSQL){

		ArrayList<Permiso> listado = new ArrayList<Permiso>();
		listado.add(new Permiso());
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Permiso(rs.getInt(1), rs.getString(2),rs.getBoolean(3)));
				}
				rs.close();
				statement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			//Mensaje =e.getMessage();
		}
		return listado;		
	}

	public static void cargarPermiso(JComboBox<Permiso> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Permiso> clientes = getListaPermiso((activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar );
			dataCombobox.removeAllItems();
		for(int i=0; i< clientes.size(); i++)
			dataCombobox.addItem((Permiso)clientes.get(i));
	}

	public static void cargarPermiso(Permiso permiso) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, permiso.getIdpermiso());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					permiso.setNombre(rs.getString(2));
					permiso.setUnico(rs.getBoolean(3));
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
