package objeto;

import java.util.Date;


public class Empleado {
	int idempleado;
	Date fecha;
	String nombres;
	String apellidos;
	boolean activo;
	
	public Empleado() {
		super();
		this.idempleado=-1;
		this.nombres = "";
	}

	public Empleado(Date fecha, String nombres, String apellidos,boolean activo) {
		super();
		this.fecha = fecha;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.activo = activo;
	}

	public Empleado(int idempleado, Date fecha, String nombres, String apellidos,boolean activo) {
		super();
		this.idempleado = idempleado;
		this.fecha = fecha;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.activo = activo;
	}

	public int getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return this.nombres+" "+this.apellidos;
	}	
	
}
