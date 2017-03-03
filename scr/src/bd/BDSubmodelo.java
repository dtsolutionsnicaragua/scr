package bd;

import igui.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import objeto.Submodelo;

public class BDSubmodelo {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM submodelo ORDER BY submodelo.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM submodelo WHERE submodelo.activo=1 ORDER BY submodelo.nombre";
		
	static String SQLGuardar=	" INSERT INTO submodelo(fecha, nombre, descripcion, activo, idmodelo ) " +
								" VALUES( ?, ?, ?, ?, ?)";
	
	static String SQLMoficar= 	" UPDATE submodelo SET  fecha=?, nombre=?, descripcion=?,activo=?, idmodelo=? " +
								" WHERE idsubmodelo=?";
	
	static String SQLBuscar= 	"SELECT * FROM submodelo WHERE submodelo.idsubmodelo=?";

	static String SQLSeleccionarXModelo="SELECT submodelo.idsubmodelo, submodelo.fecha, submodelo.nombre, submodelo.descripcion,submodelo.activo, submodelo.idmodelo FROM submodelo INNER JOIN modelo ON submodelo.idmodelo=modelo.idmodelo WHERE submodelo.activo=1 AND submodelo.idmodelo=? ORDER BY submodelo.nombre";
	
	
	public static boolean guardar(Submodelo submodelo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(submodelo.getFecha().getTime()));
				preparedstatement.setString(2,submodelo.getNombre());	
				preparedstatement.setString(3, submodelo.getDescripcion());
				preparedstatement.setBoolean(4, submodelo.getActivo());
				preparedstatement.setInt(5, submodelo.getIdmodelo());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();
				while (rs.next()) 
					submodelo.setIdsubmodelo(rs.getInt(1));		         
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

	public static boolean Modificar(Submodelo submodelo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(submodelo.getFecha().getTime()));
				preparedstatement.setString(2,submodelo.getNombre());	
				preparedstatement.setString(3, submodelo.getDescripcion());
				preparedstatement.setBoolean(4, submodelo.getActivo());
				preparedstatement.setInt(5, submodelo.getIdmodelo());
				preparedstatement.setInt(6, submodelo.getIdsubmodelo());				
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
	
	public static ArrayList<Submodelo> getListadoSubModelos(String consultaSQL){
		ArrayList<Submodelo> listado = new ArrayList<Submodelo>();
		listado.add(new Submodelo());		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Submodelo(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4) , rs.getBoolean(5),rs.getInt(6)));
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

	public static void cargarCombobox(JComboBox<Submodelo> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Submodelo> submodelos = getListadoSubModelos( (activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar);
		dataCombobox.removeAllItems();			
		for(int i=0; i< submodelos.size(); i++)
			dataCombobox.addItem((Submodelo)submodelos.get(i));
	}
	
	public static void cargarSubModelosXModelo(JComboBox<Submodelo> dataCombobox,int id){
		dataCombobox.removeAllItems();
		dataCombobox.addItem(new Submodelo());
		
			try {
				nuevaConeccion = App.spoolconexion.DataSource.getConnection();
				if (nuevaConeccion!=null) {
					
					preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLSeleccionarXModelo);
					preparedstatement.setInt(1, id);
					rs = preparedstatement.executeQuery();
					
					while(rs.next()){
						dataCombobox.addItem(new Submodelo(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getInt(6)));
					}
					rs.close();
					preparedstatement.close();
					nuevaConeccion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al Modificar"+e.getMessage());
				e.printStackTrace();
			}
	}
	
	public static void cargarSubmodelo(Submodelo c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdsubmodelo());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setFecha(rs.getDate(2));
					c.setNombre(rs.getString(3));
					c.setDescripcion( rs.getString(4));
					c.setActivo(rs.getBoolean(5));
					c.setIdmodelo(rs.getInt(6));
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
