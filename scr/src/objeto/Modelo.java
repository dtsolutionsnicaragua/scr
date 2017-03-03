package objeto;

import java.util.Date;

public class Modelo {
	int idmodelo;
	Date fecha;
	String nombre;
	String descripcion;
	Boolean activo;
	int idmarca;
		
	public Modelo() {
		super();
		this.idmodelo=-1;
		this.nombre="Ninguno";
		// TODO Auto-generated constructor stub
	}
	public Modelo(int idmodelo,Date fecha, String nombre,String descripcion, Boolean activo, int idmarca) {
		super();
		this.idmodelo = idmodelo;
		this.fecha = fecha;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
		this.idmarca = idmarca;
	}
	
	public int getIdmodelo() {
		return idmodelo;
	}
	public void setIdmodelo(int idmodelo) {
		this.idmodelo = idmodelo;
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
	public void setActivo(Boolean vigencia) {
		this.activo = vigencia;
	}
	public int getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return nombre;
	}/*
	@Override
	public String toString() {
		return "Modelo [idmodelo=" + idmodelo + ", fecha=" + fecha
				+ ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", activo=" + activo + ", idmarca=" + idmarca + "]";
	}*/
	
}
