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

import objeto.Marca;

public class BDMarca{
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM marca ORDER BY marca.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM marca WHERE marca.activo=1 ORDER BY marca.nombre";	
	
	static String SQLGuardar=	" INSERT INTO marca(fecha, nombre, activo ) " +
								" VALUES( ?, ?, ?)";
	
	static String SQLMoficar= 	" UPDATE marca SET  fecha=?, nombre=?, activo=?" +
								" WHERE idmarca=?";
	
	static String SQLBuscar= 	"SELECT * FROM marca WHERE marca.idmarca=?";

	public static boolean guardar(Marca marca) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(marca.getFecha().getTime()));
				preparedstatement.setString(2,marca.getNombre());		
				preparedstatement.setBoolean(3, marca.getActivo());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 marca.setIdmarca(rs.getInt(1)); //Establecer idGenerdo
		         } 
				rs.close();
				preparedstatement.close(); 
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ""+e.getMessage());
		}
		return false;
	}

	public static boolean Modificar(Marca marca) {
		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(marca.getFecha().getTime()));
				preparedstatement.setString(2,marca.getNombre());		
				preparedstatement.setBoolean(3, marca.getActivo());
				preparedstatement.setInt(4, marca.getIdmarca());				
				preparedstatement.execute();
				preparedstatement.close();
				nuevaConeccion.close();
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ""+e.getMessage());
		}
		return false;		
	}
	
	public static ArrayList<Marca> getListoMarcas(String consultaSQL){
		ArrayList<Marca> listado = new ArrayList<Marca>();
		listado.add(new Marca());
		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Marca(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getBoolean(4)));
				}
				rs.close();
				statement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ""+e.getMessage());
		}
		return listado;		
	}

	public static void cargarCombobox(JComboBox<Marca> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Marca> clientes = getListoMarcas( (activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar);
		dataCombobox.removeAllItems();
		for(int i=0; i< clientes.size(); i++)
			dataCombobox.addItem((Marca)clientes.get(i));
	}
	
	public static void cargarMarca(Marca c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdmarca());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setFecha(rs.getDate(2));
					c.setNombre(rs.getString(3));
					c.setActivo(rs.getBoolean(4));
				}
				rs.close();
				preparedstatement.close();
				nuevaConeccion.close();
				//cerrarConeccion(nuevaConeccion);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "CargarMarca"+e.getMessage());
		}
		
	}
}
