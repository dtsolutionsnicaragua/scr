package objeto;

import java.util.Date;

public class Persona {
	int idpersona;
	String noidentificacion;
	Date fechanacimiento;
	String primernombre, segundonombre, primerapellido, segundoapellido;
	boolean genero;
	
	public Persona() {
		super();
		this.idpersona = -1;
		this.primernombre ="";
		this.segundonombre = "";
		this.primerapellido = "";
		this.segundoapellido = "";
	}
	
	public Persona(int idpersona, String noidentificacion,
			Date fechanacimiento, String primernombre, String segundonombre,
			String primerapellido, String segundoapellido, boolean sexo) {
		super();
		this.idpersona = idpersona;
		this.noidentificacion = noidentificacion;
		this.fechanacimiento = fechanacimiento;
		this.primernombre = primernombre;
		this.segundonombre = segundonombre;
		this.primerapellido = primerapellido;
		this.segundoapellido = segundoapellido;
		this.genero = sexo;
	}

	public int getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}

	public String getNoidentificacion() {
		return noidentificacion;
	}

	public void setNoidentificacion(String noidentificacion) {
		this.noidentificacion = noidentificacion;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getPrimernombre() {
		return primernombre;
	}

	public void setPrimernombre(String primernombre) {
		this.primernombre = primernombre;
	}

	public String getSegundonombre() {
		return segundonombre;
	}

	public void setSegundonombre(String segundonombre) {
		this.segundonombre = segundonombre;
	}

	public String getPrimerapellido() {
		return primerapellido;
	}

	public void setPrimerapellido(String primerapellido) {
		this.primerapellido = primerapellido;
	}

	public String getSegundoapellido() {
		return segundoapellido;
	}

	public void setSegundoapellido(String segundoapellido) {
		this.segundoapellido = segundoapellido;
	}

	public boolean getGenero() {
		return genero;
	}

	public void setGenero(boolean sexo) {
		this.genero = sexo;
	}

	@Override
	public String toString() {
		return "" + primernombre + " "+ segundonombre + " " + primerapellido+ " " + segundoapellido;
	}	
}
