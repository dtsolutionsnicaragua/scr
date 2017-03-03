package bd;

import igui.App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import java.sql.PreparedStatement;

import objeto.Modelo;

public class BDModelo{
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM modelo ORDER BY modelo.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM modelo WHERE modelo.activo=1 ORDER BY modelo.nombre";	
	
	static String SQLGuardar=	" INSERT INTO modelo(fecha, nombre,descripcion, activo, idmarca ) " +
								" VALUES( ?, ?, ?,?, ?)";
	
	static String SQLMoficar= 	" UPDATE modelo SET  fecha=?, nombre=?,descripcion=?, activo=?, idmarca=?" +
								" WHERE idmodelo=?";
	
	static String SQLBuscar= 	"SELECT * FROM modelo WHERE modelo.idmodelo=?";
		
	static String SQLSeleccionarXMarca="SELECT * FROM modelo  WHERE modelo.activo=1 AND modelo.idmarca=? ORDER BY modelo.nombre";
		

	public static boolean guardar(Modelo modelo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(modelo.getFecha().getTime()));
				preparedstatement.setString(2,modelo.getNombre());	
				preparedstatement.setString(3,modelo.getDescripcion());
				preparedstatement.setBoolean(4, modelo.getActivo());
				preparedstatement.setInt(5, modelo.getIdmarca());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 modelo.setIdmodelo(rs.getInt(1)); //Establecer idGenerdo
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

	public static boolean Modificar(Modelo modelo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(modelo.getFecha().getTime()));
				preparedstatement.setString(2,modelo.getNombre());	
				preparedstatement.setString(3,modelo.getDescripcion());
				preparedstatement.setBoolean(4, modelo.getActivo());
				preparedstatement.setInt(5, modelo.getIdmarca());	
				preparedstatement.setInt(6, modelo.getIdmodelo());	
				preparedstatement.execute();
				preparedstatement.close();
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			//Mensaje=e.getMessage();
		}
		return false;		
	}
	
	public static ArrayList<Modelo> getListaModelos(String consultaSQL){
		ArrayList<Modelo> listado = new ArrayList<Modelo>();
		listado.add(new Modelo());
		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Modelo(rs.getInt(1), rs.getDate(2), rs.getString(3),rs.getString(4), rs.getBoolean(5), rs.getInt(6)));
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

	public static void cargarModelos(JComboBox<Modelo> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Modelo> modelos = getListaModelos( (activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar);
			dataCombobox.removeAllItems();
		for(int i=0; i< modelos.size(); i++)
			dataCombobox.addItem((Modelo)modelos.get(i));
	}
	
	public static void cargarModelo(Modelo c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement)nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdmodelo());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setFecha(rs.getDate(2));
					c.setNombre(rs.getString(3));
					c.setDescripcion( rs.getString(4));
					c.setActivo(rs.getBoolean(5));
					c.setIdmarca(rs.getInt(6));
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
