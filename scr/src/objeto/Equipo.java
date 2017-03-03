package objeto;

import java.util.Date;

public class Equipo {
	int idequipo;
	Date fecha;
	String serie;
	boolean activo;
	int idsubmodelo;
	
	public Equipo() {
		super();
		this.idequipo = -1;
		this.serie = "";
	}

	public Equipo(int idequipo, Date fecha, String serie, boolean activo,int idsubmodelo) {
		super();
		this.idequipo = idequipo;
		this.fecha = fecha;
		this.serie = serie;
		this.activo = activo;
		this.idsubmodelo = idsubmodelo;
	}

	public int getIdequipo() {
		return this.idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getserie() {
		return this.serie;
	}

	public void setserie(String no_serie) {
		this.serie = no_serie;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdsubmodelo() {
		return idsubmodelo;
	}

	public void setIdsubmodelo(int idsub_modelo) {
		this.idsubmodelo = idsub_modelo;
	}

	@Override
	public String toString() {
		return this.serie;
	}
	
	

}
