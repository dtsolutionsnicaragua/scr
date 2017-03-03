package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties{

	private static Properties ARCHIVO_GENERAL;
		
	public static String[] ListarIdiomas;
	public static boolean inicializar= false;
	
	
	public static boolean init() {
			// PROPIEDAD DONDE SE ENCUENTRA ALMACENADO LOS PATH Y ARCHIVO
			// NECESARIOS PARA LA APLICACION
		try{
			ARCHIVO_GENERAL = cargarArchivoProperties("configapp.properties");
			inicializar=true;
		}catch(Exception e){
			inicializar = false;
			return false;
		}
			
		return true;
	}
	
	private static Properties cargarArchivoProperties(String LocationFile) {
		Properties fileproperties = new Properties();
		File  archivo = new File(LocationFile);
		try {			
			InputStream oi = new FileInputStream(archivo);
			fileproperties.load(oi);
			oi.close();
		} catch (Exception ex) {
		}
		return fileproperties;
	}
	
	public static String getKey(String Key) {
		if (!inicializar)
			init();
		return ARCHIVO_GENERAL.getProperty(Key);
	}
	
}