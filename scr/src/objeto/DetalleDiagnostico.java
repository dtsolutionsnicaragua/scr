package objeto;

public class DetalleDiagnostico {
	int iddetallediagnostico;
	int iddiagnostico;
	int idparte;
	int cantidad;
	String observacion;
	
	public DetalleDiagnostico() {
		super();
		this.iddetallediagnostico =-1;
	}

	public DetalleDiagnostico(int iddetallediagnostico, int iddiagnostico,
			int idparte, int cantidad, String observacion) {
		super();
		this.iddetallediagnostico = iddetallediagnostico;
		this.iddiagnostico = iddiagnostico;
		this.idparte = idparte;
		this.cantidad = cantidad;
		this.observacion = observacion;
	}

	public int getIddetallediagnostico() {
		return iddetallediagnostico;
	}

	public void setIddetallediagnostico(int iddetallediagnostico) {
		this.iddetallediagnostico = iddetallediagnostico;
	}

	public int getIddiagnostico() {
		return iddiagnostico;
	}

	public void setIddiagnostico(int iddiagnostico) {
		this.iddiagnostico = iddiagnostico;
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
				+ iddetallediagnostico + "]";
	}
	
}
