package objeto;

import java.util.Date;

public class Representante {
	int idrepresentante;
	Date fecha;
	String nombres;
	String apellidos;
	String telefono;
	String e_mail;
	int idcliente;
	boolean activo;
	
	public Representante() {
		super();
		this.idrepresentante=-1;
		this.nombres = "";
		this.apellidos="";
	}
	
	public Representante(Date fecha, String nombres, String apellidos, String telefonos, String e_mail,boolean activo, int idcliente) {
		super();
		this.fecha = fecha;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefonos;
		this.e_mail = e_mail;
		this.activo = activo;
		this.idcliente = idcliente;
	}

	public Representante(int idrepresentante, Date fecha, String nombres, String apellidos,	String telefonos, String e_mail,boolean activo, int idcliente) {
		super();
		this.idrepresentante = idrepresentante;
		this.fecha = fecha;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefonos;
		this.e_mail = e_mail;
		this.activo = activo;
		this.idcliente = idcliente;
	}

	public int getIdrepresentante() {
		return idrepresentante;
	}

	public void setIdrepresentante(int idrepresentante) {
		this.idrepresentante = idrepresentante;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefonos) {
		this.telefono = telefonos;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
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
		// TODO Auto-generated method stub
		return this.nombres+" "+this.apellidos;
	}


	
	
	
}
