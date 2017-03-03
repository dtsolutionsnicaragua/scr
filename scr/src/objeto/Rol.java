package objeto;

import java.util.Date;

public class Rol {
	int idRol;
	Date fecha;
	String nombre;
	String descripcion;
	boolean activo;
	
	public Rol(){
		super();
		this.idRol =-1;
		this.nombre="";
	}
	
	public Rol(int idRol, Date fecha,String nombre, String descripcion, boolean activo) {
		super();
		this.idRol = idRol;
		this.fecha = fecha;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}	

}
