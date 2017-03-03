package bd;

import igui.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objeto.Menu;

public class BDMenu {
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Statement statement;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM menu ORDER BY posicion ASC";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM menu ORDER BY menu.idmenu";
	
	static String SQLGuardar=	" INSERT INTO menu(variable,componente,nombre,nivel,posicion,contenedor ) " +
								" VALUES(?, ?, ?, ?,?,?)";
	
	static String SQLMoficar= 	" UPDATE menu SET variable=?, componente=?, nombre=?,nivel=? ,posicion=?,contenedor=?" +
								" WHERE idmenu=?";
	
	static String SQLBuscar= 	"SELECT * FROM menu WHERE menu.idmenu=?";
	
	public static boolean guardar(Menu menu) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar);
				preparedstatement.setString(1, menu.getVariable());
				preparedstatement.setString(2,menu.getComponente());
				preparedstatement.setString(3,menu.getNombre());
				preparedstatement.setInt(4, menu.getNivel());
				preparedstatement.setInt(5, menu.getPosicion());
				preparedstatement.setBoolean(6, menu.getContenedor());
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

	public static boolean Modificar(Menu menu) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setString(1, menu.getVariable());
				preparedstatement.setString(2,menu.getComponente());
				preparedstatement.setString(3,menu.getNombre());
				preparedstatement.setInt(4, menu.getNivel());
				preparedstatement.setInt(5, menu.getPosicion());
				preparedstatement.setBoolean(6, menu.getContenedor());
				preparedstatement.setInt(7, menu.getIdmenu());				
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
	
	public static ArrayList<Menu> getMenus(){
		ArrayList<Menu> listado = new ArrayList<Menu>();
				
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				
				statement = (Statement ) nuevaConeccion.createStatement();
				rs=statement.executeQuery(SQLSeleccionar);
				
				while(rs.next()){
					listado.add(new Menu(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getBoolean(7)));
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

	public static void cargarMenu(Menu c) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscar);
				preparedstatement.setInt(1, c.getIdmenu());
				
				rs = preparedstatement.executeQuery();
				while(rs.next()){
					c.setVariable(rs.getString(2));
					c.setComponente(rs.getString(3));
					c.setNombre(rs.getString(4));
					c.setNivel(rs.getInt(5));
					c.setPosicion(rs.getInt(6));
					c.setContenedor(rs.getBoolean(7));
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
