package objeto;

import java.util.Date;


public class Detalleingreso {
	int iddetalleIngreso;
	Date fecha;
	String observacion;
	boolean activo;
	boolean completado;
	int idingreso;
	int idequipo;
	
	public Detalleingreso() {
		super();
		iddetalleIngreso=-1;
	}

	public Detalleingreso(int iddetalleIngreso, Date fecha, String observacion,
			boolean activo,boolean completado, int idingreso, int idequipo) {
		super();
		this.iddetalleIngreso = iddetalleIngreso;
		this.fecha = fecha;
		this.observacion = observacion;
		this.activo = activo;
		this.completado =completado;
		this.idingreso = idingreso;
		this.idequipo = idequipo;
	}

	public int getIddetalleIngreso() {
		return iddetalleIngreso;
	}

	public void setIddetalleIngreso(int iddetalleIngreso) {
		this.iddetalleIngreso = iddetalleIngreso;
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
	
	public boolean getCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public int getIdingreso() {
		return idingreso;
	}

	public void setIdingreso(int idingreso) {
		this.idingreso = idingreso;
	}

	public int getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	@Override
	public String toString() {
		Equipo eq = new Equipo();
		eq.setIdequipo(this.idequipo);
		//BDEquipo.cargarEquipo(eq);
		return eq.getserie();
	}
	
	
	
	
}
