package bd;

import igui.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import objeto.Equipo;

public class BDEquipo{
	private static PreparedStatement preparedstatement;
	private static ResultSet rs;
	private static Connection nuevaConeccion = null;
	
	static String SQLSeleccionar = "SELECT * FROM equipo ORDER BY equipo.fecha";
	
	static String SQLSeleccionarFiltro ="SELECT * FROM equipo ORDER BY equipo.activo";
	
	static String SQLGuardar=	" INSERT INTO equipo(fecha, serie, activo,idsubmodelo ) " +
								" VALUES(?, ?, ?, ?)";
	
	static String SQLMoficar= 	" UPDATE equipo SET fecha=?, serie=?, activo=?, idsubmodelo=?" +
								" WHERE idequipo=?";

	static String SQLBuscarxSerie = 	" SELECT  * FROM equipo WHERE equipo.serie=?";

	static String SQLBuscarxID = 	" SELECT  * FROM equipo WHERE equipo.idequipo=?";
	
	public static boolean guardar(Equipo equipo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLGuardar,Statement.RETURN_GENERATED_KEYS);
				preparedstatement.setDate(1, new java.sql.Date(equipo.getFecha().getTime()));
				preparedstatement.setString(2, equipo.getserie());
				preparedstatement.setBoolean(3, equipo.getActivo());
				preparedstatement.setInt(4, equipo.getIdsubmodelo());
				preparedstatement.execute();
				
				rs=preparedstatement.getGeneratedKeys();				
				while (rs.next()) {
					 equipo.setIdequipo(rs.getInt(1)); //Establecer idGenerdo
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
	
	public static boolean Modificar(Equipo equipo) {
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLMoficar);
				preparedstatement.setDate(1, new java.sql.Date(equipo.getFecha().getTime()));
				preparedstatement.setString(2, equipo.getserie());
				preparedstatement.setBoolean(3, equipo.getActivo());
				preparedstatement.setInt(4, equipo.getIdsubmodelo());
				preparedstatement.setInt(5,equipo.getIdequipo());							
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
	
	public static boolean guardarSiNoExiste(Equipo equipo) {
		Equipo temp = getEquipo(equipo.getserie());
		
		if (temp.getIdequipo()==-1){
			if(!guardar(equipo))
				return false;
		}else
			equipo.setIdequipo(temp.getIdequipo());
		
		return true;
	}
	
	public static void main(String arg[]){
		Equipo temp = new Equipo();
		temp.setFecha(new java.util.Date());
		temp.setIdsubmodelo(1);
		temp.setserie("852369741");
		BDEquipo.guardarSiNoExiste(temp);
		
	}
	
	
	
	/*public static Equipo conseguirEquipoSiExiste(String serie) {
		Equipo equipo=null;
		try {
			if (abrirConeccion()) {
				statement = (PreparedStatement) nuevaConeccion.prepareStatement("SELECT * FROM equipo WHERE equipo.serie=?");
				statement.setString(1, serie);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					equipo = new Equipo(rs.getInt(1),rs.getDate(2),rs.getString(3), rs.getBoolean(4),rs.getInt(5));
				}				
			}
			cerrarConeccion();
		} catch (SQLException e) {
			System.out.println("Error al Buscar Equipo "+e.getMessage());
			equipo= null;
		}		
		return equipo;
	}
	
	public static boolean ExisteEquipo(String noserie) {
		boolean rs=false;
		try {
			if (abrirConeccion()) {
				statement = (PreparedStatement) nuevaConeccion.prepareStatement("SELECT * FROM equipo WHERE equipo.serie=?");
				statement.setString(1, noserie);
				ResultSet rs = statement.executeQuery();
				rs.last();	//No ubicamos en la ultima fila de los datos enviados.
				rs = (rs.getRow()>0)?true: false;
			}
			cerrarConeccion();
		} catch (SQLException e) {
			System.out.println("Error al Buscar factura "+e.getMessage());
			e.printStackTrace();
			rs= false;
		}		
		return rs;
	}*/
	
	public static Equipo getEquipo (String serie){
		Equipo eq = new Equipo();
		
		try {
			nuevaConeccion = App.spoolconexion.DataSource.getConnection();
			if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscarxSerie);
				preparedstatement.setString(1, serie);
				rs=preparedstatement.executeQuery();	
				while(rs.next()){
					eq.setIdequipo(rs.getInt(1));
					eq.setFecha( rs.getDate(2));
					eq.setserie(rs.getString(3));
					eq.setActivo(rs.getBoolean(4));
					eq.setIdsubmodelo(rs.getInt(5));
				}
				rs.close();	
				preparedstatement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			eq =  new Equipo();
		}
		return eq;		
	}
	
	public static void cargarEquipo (Equipo eq){		
			try {
				nuevaConeccion = App.spoolconexion.DataSource.getConnection();
				if (  nuevaConeccion!=null) {
				preparedstatement = (PreparedStatement) nuevaConeccion.prepareStatement(SQLBuscarxID);
				preparedstatement.setInt(1, eq.getIdequipo());				
				
				rs=preparedstatement.executeQuery();				
				while(rs.next()){
					eq.setFecha( rs.getDate(2));
					eq.setserie(rs.getString(3));
					eq.setActivo(rs.getBoolean(4));
					eq.setIdsubmodelo(rs.getInt(5));
				}
				rs.close();	
				preparedstatement.close();
				nuevaConeccion.close();
			}
		} catch (SQLException e) {
			System.out.println("Error al Cargar El Equipo "+e.getMessage());
		}	
	}

}
