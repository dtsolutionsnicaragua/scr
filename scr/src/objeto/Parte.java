package objeto;

import java.util.Date;

public class Parte {
	int idparte;
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	Date fecha;
	String numero;
	String nombre;
	boolean activo;
	
	public Parte(){
		this.idparte=-1;
		this.nombre = "";
	}
	
	public Parte(int idparte,Date fecha, String numero, String nombre, boolean activo) {
		super();
		this.idparte = idparte;
		this.fecha = fecha;
		this.numero = numero;
		this.nombre = nombre;
		this.activo = activo;
	}

	public int getIdparte() {
		return idparte;
	}

	public void setIdparte(int idparte) {
		this.idparte = idparte;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
