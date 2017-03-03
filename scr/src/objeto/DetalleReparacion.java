package objeto;

public class DetalleReparacion {
	int iddetallereparacion;
	int idreparacion;
	int idparte;
	int cantidad;
	String observacion;
	
	public DetalleReparacion() {
		super();
		this.iddetallereparacion =-1;
	}

	public DetalleReparacion(int iddetallediagnostico, int iddiagnostico,
			int idparte, int cantidad, String observacion) {
		super();
		this.iddetallereparacion = iddetallediagnostico;
		this.idreparacion = iddiagnostico;
		this.idparte = idparte;
		this.cantidad = cantidad;
		this.observacion = observacion;
	}

	public int getIddetallereparacion() {
		return iddetallereparacion;
	}

	public void setIddetallereparacion(int iddetallediagnostico) {
		this.iddetallereparacion = iddetallediagnostico;
	}

	public int getIdreparacion() {
		return idreparacion;
	}

	public void setIdreparacion(int iddiagnostico) {
		this.idreparacion = iddiagnostico;
	}

	public int getIdparte() {
		return idparte;
	}

	public void setIdparte(int idparte) {
		this.idparte = idparte;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "DetalleDiagnostico [iddetallediagnostico="
				+ iddetallereparacion + "]";
	}
	
}
