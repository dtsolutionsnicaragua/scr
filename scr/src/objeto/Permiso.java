/**
 * 
 */
package objeto;

/**
 * @author LP-Desarrollo
 * 
 */
public class Permiso {

	int idpermiso;
	String nombre;
	boolean unico;

	public Permiso() {
		super();
		this.idpermiso = -1;
		this.nombre="";
		// TODO Auto-generated constructor stub
	}

	public Permiso(int idpermiso, String nombre, boolean unico) {
		super();
		this.idpermiso = idpermiso;
		this.nombre = nombre;
		this.unico = unico;
	}

	public int getIdpermiso() {
		return idpermiso;
	}

	public void setIdpermiso(int idpermiso) {
		this.idpermiso = idpermiso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean getUnico() {
		return unico;
	}

	public void setUnico(boolean unico) {
		this.unico = unico;
	}

	@Override
	public String toString() {
		return String.format("%s", nombre);
	}
	
	
	

}
