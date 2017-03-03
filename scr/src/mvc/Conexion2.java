package mvc;

import igui.App;

import java.sql.Connection;
import java.sql.SQLException;


public class Conexion2 extends SPool{

	
	
	/**
	 * Abre coneccion a la base de datos  
	 * @return boolean
	 */
	public static Connection abrirConeccion() {
		Connection nuevaConeccion = null;
		
		 try {			
			  nuevaConeccion = App.spoolconexion.DataSource.getConnection();
		} catch (Exception ex) {
			
		}finally{
			try{
				nuevaConeccion.close();
			}catch(SQLException ex){
			}
		}
		 return nuevaConeccion;
	}
	
	public static Connection getConexion(){
		return abrirConeccion();
	}
	

	/**
	 * Cerrar la conexion  
	 * @return
	 */
	public static boolean cerrarConeccion(Connection c) {
		try {
			c.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
