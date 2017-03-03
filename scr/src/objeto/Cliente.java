package objeto;

import java.util.Date;


public class Cliente implements java.io.Serializable {	
	private static final long serialVersionUID = 1L;
	
	int idcliente;
	String ruc;
	Date fecha;
	String nombre;
	String direccion;
	String telefono;
	String web;
	String Correo;
	Boolean activo;
	
	public Cliente(){
		this.idcliente=-1;
		this.nombre = "Ninguno";
	}
	public Cliente(int idcliente,String ruc, Date fecha, String nombre,String direccion, String telefono, String web, String correo) {
		super();
		this.idcliente = idcliente;
		this.ruc = ruc;
		this.fecha = fecha;
		this.nombre = nombre;		
		this.telefono = telefono;
		this.direccion = direccion;
		this.Correo = correo;
		this.web =web;
		this.activo = true;
	}
	
	public Cliente(int idcliente,String ruc, Date fecha, String nombre,String direccion, String telefono, String web, String correo, Boolean activo) {
		super();
		this.idcliente = idcliente;
		this.ruc = ruc;
		this.fecha = fecha;
		this.nombre = nombre;		
		this.telefono = telefono;
		this.direccion = direccion;
		this.Correo = correo;
		this.web =web;
		this.activo = activo;
	}	
	
	public Cliente(String ruc, Date fecha, String nombre,String direccion, String telefono, String web, String correo) {
		super();
		this.ruc = ruc;
		this.fecha = fecha;
		this.nombre = nombre;		
		this.telefono = telefono;
		this.direccion = direccion;
		this.Correo = correo;
		this.web =web;
		this.activo = true;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
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
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}	
	
	
}
