package objeto;

import java.util.Date;

public class Marca {
	int idmarca;
	Date fecha;
	String nombre;
	Boolean activo;
	
	public Marca() {
		super();
		this.idmarca=-1;
		this.nombre="Ninguno";
	}

	public Marca(Date fecha, String nombre, Boolean activo) {
		super();
		this.fecha = fecha;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Marca(int idmarca, Date fecha, String nombre, Boolean vigencia) {
		super();
		this.idmarca = idmarca;
		this.fecha = fecha;
		this.nombre = nombre;
		this.activo = vigencia;
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean vigente) {
		this.activo = vigente;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}
	
}
