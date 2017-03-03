package objeto;

import java.util.Date;

public class Empresa {
	int idempresa;
	String ruc;
	Date fecha;
	String nombre;
	String direccion;
	String telefono;
	String correo;
	String web;	
	boolean activo;
	
	public Empresa() {
		super();
		this.idempresa=-1;
		this.nombre = "";
	}

	public Empresa(int idempresa, String ruc, Date fecha, String nombre, String direccion,
			String telefono, String email, String web, boolean activo) {
		super();
		this.idempresa = idempresa;
		this.ruc = ruc;
		this.fecha = fecha;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = email;
		this.web = web;
		this.activo = activo;
	}

	public int getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String email) {
		this.correo = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
