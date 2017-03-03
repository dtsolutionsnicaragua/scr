package objeto;

import java.util.Date;

public class Submodelo {
	int idsubmodelo;
	Date fecha;
	String nombre;
	String descripcion;
	int idmodelo;
	boolean activo;
	
	public Submodelo() {
		super();
		this.idsubmodelo=-1;
		this.nombre ="Ninguno";
	}
	

	public Submodelo(int idsubmodelo, Date fecha, String nombre, String descripcion,boolean activo, int idmodelo) {
		super();
		this.idsubmodelo = idsubmodelo;
		this.fecha = fecha;
		this.nombre = nombre;
		this.activo = activo;
		this.descripcion = descripcion;
		this.idmodelo = idmodelo;
	}

	public int getIdsubmodelo() {
		return idsubmodelo;
	}

	public void setIdsubmodelo(int idsubmodelo) {
		this.idsubmodelo = idsubmodelo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String noparte) {
		this.nombre = noparte;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean vigencia) {
		this.activo = vigencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdmodelo() {
		return idmodelo;
	}

	public void setIdmodelo(int idmodelo) {
		this.idmodelo = idmodelo;
	}


	/*@Override
	public String toString() {
		return "Submodelo [idsubmodelo=" + idsubmodelo + ", fecha=" + fecha
				+ ", nombre=" + nombre + ", activo=" + activo
				+ ", descripcion=" + descripcion + ", idmodelo=" + idmodelo
				+ "]";
	}

	/*/@Override
	public String toString() {
		return nombre;
	}
	
}
