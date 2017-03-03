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

import objeto.Cliente;

public class BDCliente {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;

	
	static String SQLSeleccionar = "SELECT * FROM cliente ORDER BY cliente.nombre";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM cliente WHERE cliente.activo=1 ORDER BY cliente.nombre";
	
	static String SQLGuardar=	" INSERT INTO cliente(ruc,fecha, nombre,direccion, telefono,web,correo, activo ) " +
								" VALUES(?, ?, ?, ?, ?,?, ?, ?)";
	
	static String SQLMoficar= 	" UPDATE cliente SET ruc=?, fecha=?, nombre=?,direccion=?, telefono=?, web=?,correo=?, activo=? " +
								" WHERE idcliente=?";
	
	static String SQLBuscar= 	"SELECT * FROM cliente WHERE cliente.idcliente=?";



	
	public static boolean guardar(Cliente cliente) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setString(1, cliente.getRuc());
				preparedstatement.setDate(2, new java.sql.Date(cliente.getFecha().getTime()));
				preparedstatement.setString(3,cliente.getNombre());
				preparedstatement.setString(4,cliente.getDireccion());
				preparedstatement.setString(5, cliente.getTelefono());
				preparedstatement.setString(6, cliente.getWeb());
				preparedstatement.setString(7,cliente.getCorreo());
				preparedstatement.setBoolean(8, cliente.getActivo());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 cliente.setIdcliente(rs.getInt(1)); //Establecer idGenerdo
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

	public static boolean Modificar(Cliente cliente) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setString(1, cliente.getRuc());
				preparedstatement.setDate(2, new java.sql.Date(cliente.getFecha().getTime()));
				preparedstatement.setString(3,cliente.getNombre());
				preparedstatement.setString(4,cliente.getDireccion());
				preparedstatement.setString(5, cliente.getTelefono());
				preparedstatement.setString(6, cliente.getWeb());
				preparedstatement.setString(7,cliente.getCorreo());
				preparedstatement.setBoolean(8, cliente.getActivo());
				preparedstatement.setInt(9, cliente.getIdcliente());				
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
	
	public static ArrayList<Cliente> getListaClientes(String consultaSQL){
		ArrayList<Cliente> listado = new ArrayList<Cliente>();
		listado.add(new Cliente());
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(consultaSQL);
				
				while(rs.next()){
					listado.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getBoolean(9)));
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

	public static void cargarClientes(JComboBox<Cliente> dataCombobox, Boolean activarFiltro) {		
		ArrayList<Cliente> clientes = getListaClientes((activarFiltro)?SQLSeleccionarFiltro:SQLSeleccionar );
			dataCombobox.removeAllItems();
		for(int i=0; i< clientes.size(); i++)
			dataCombobox.addItem((Cliente)clientes.get(i));
	}

	public static void cargarCliente(Cliente cliente) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, cliente.getIdcliente());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					cliente.setRuc(rs.getString(2));
					cliente.setFecha(rs.getDate(3));
					cliente.setNombre(rs.getString(4));
					cliente.setDireccion(rs.getString(5));
					cliente.setTelefono( rs.getString(6));
					cliente.setWeb(rs.getString(7));
					cliente.setCorreo(rs.getString(8));
					cliente.setActivo(rs.getBoolean(9));
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
