package objeto;

import java.util.Date;

public class Sucursal {
	int idsucursal;
	String ruc;
	Date fecha;
	String nombre;
	String direccion;
	String telefono;
	String correo;
	String web;
	boolean activo;
	int FK_idempresa;
	
	public Sucursal() {
		super();
		this.idsucursal = -1;
		this.nombre ="";
	}
	

	public Sucursal(int idsucursal, String ruc,Date fecha, String nombre,
			String direccion, String telefono,String Correo, String web, boolean activo, int fK_idempresa) {
		super();
		this.idsucursal = idsucursal;
		this.ruc = ruc;
		this.fecha =fecha;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = Correo;
		this.web =web;
		this.activo = activo;
		FK_idempresa = fK_idempresa;
	}

	public int getIdsucursal() {
		return idsucursal;
	}

	public void setIdsucursal(int idsucursal) {
		this.idsucursal = idsucursal;
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
	
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getidempresa() {
		return FK_idempresa;
	}

	public void setidempresa(int fK_idempresa) {
		FK_idempresa = fK_idempresa;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}	
}
