package objeto;

import java.util.Date;

public class Tarea {
	int idtarea;
	Date fecha;
	String observacion;
	boolean activo;
	int iddocumento;
	int idusuario;
	
	public Tarea() {
		super();
		this.idtarea =-1;			
	}

	public Tarea(int idtarea,Date fecha, String observacion,boolean activo,  int iddocumento, int idusuario) {
		super();
		this.idtarea = idtarea;
		this.fecha = fecha;
		this.observacion = observacion;
		this.activo = activo;
		this.iddocumento = iddocumento;
		this.idusuario = idusuario;
	}

	public int getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIddocumento() {
		return iddocumento;
	}

	public void setIddocumento(int iddocumento) {
		this.iddocumento = iddocumento;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@Override
	public String toString() {
		return ""+this.idtarea;
	}	

}
