package objeto;

public class DetalleSalida {
	int iddetalleSalida;
	String observacion;
	boolean activo;
	int idsalida;
	int iddetalleingreso;
	Object idgarantiaxreparacion;
	
	public DetalleSalida() {
		super();
		iddetalleSalida=-1;
	}

	public DetalleSalida(int iddetalleSalida, String observacion,
			boolean activo, int idsalida, int iddetalleingreso,
			Object idgarantiaxreparacion) {
		super();
		this.iddetalleSalida = iddetalleSalida;
		this.observacion = observacion;
		this.activo = activo;
		this.idsalida = idsalida;
		this.iddetalleingreso = iddetalleingreso;
		this.idgarantiaxreparacion = idgarantiaxreparacion;
	}

	public int getIddetalleSalida() {
		return iddetalleSalida;
	}

	public void setIddetalleSalida(int iddetalleSalida) {
		this.iddetalleSalida = iddetalleSalida;
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

	public int getIdsalida() {
		return idsalida;
	}

	public void setIdsalida(int idsalida) {
		this.idsalida = idsalida;
	}

	public int getIddetalleingreso() {
		return iddetalleingreso;
	}

	public void setIddetalleingreso(int iddetalleingreso) {
		this.iddetalleingreso = iddetalleingreso;
	}

	public Object getIdgarantiaxreparacion() {
		return idgarantiaxreparacion;
	}

	public void setIdgarantiaxreparacion(Object idgarantiaxreparacion) {
		this.idgarantiaxreparacion = idgarantiaxreparacion;
	}

	@Override
	public String toString() {
		return "DetalleSalida [iddetalleSalida=" + iddetalleSalida
				+ ", observacion=" + observacion + ", activo=" + activo
				+ ", idsalida=" + idsalida + ", iddetalleingreso="
				+ iddetalleingreso + ", idgarantiaxreparacion="
				+ idgarantiaxreparacion + "]";
	}
	
	

}
