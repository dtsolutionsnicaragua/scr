package objeto;

public class Garantia {
	int idgarantia;
	String Nombre; 
	String descripcion;
	int duracion;
	boolean activo;
	int idmarca;
		
	public Garantia() {
		super();
		this.idgarantia = -1;
		this.Nombre = "";
	}

	public Garantia(int idgarantia, String nombre, String descripcion,
			int duracion, boolean activo,int idmarca) {
		super();
		this.idgarantia = idgarantia;
		Nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.activo = activo;
		this.idmarca = idmarca;
	}

	public int getIdgarantia() {
		return idgarantia;
	}

	public void setIdgarantia(int idgarantia) {
		this.idgarantia = idgarantia;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}

	@Override
	public String toString() {
		return  Nombre;
	}	
}
