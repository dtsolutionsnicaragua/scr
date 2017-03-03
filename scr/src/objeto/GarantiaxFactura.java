package objeto;

public class GarantiaxFactura {
	int idgarantiaxfactura;
	boolean activo;
	int idequipo;
	int idgarantia;
	int idfactura;
	
	public GarantiaxFactura() {
		this.idgarantiaxfactura = -1;
	}

	public GarantiaxFactura(int idgarantiaxfactura, boolean activo,	int idequipo, int idgarantia, int idfactura) {
		super();
		this.idgarantiaxfactura = idgarantiaxfactura;
		this.activo = activo;
		this.idequipo = idequipo;
		this.idgarantia = idgarantia;
		this.idfactura = idfactura;
	}

	public int getIdgarantiaxfactura() {
		return idgarantiaxfactura;
	}

	public void setIdgarantiaxfactura(int idgarantiaxfactura) {
		this.idgarantiaxfactura = idgarantiaxfactura;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public int getIdgarantia() {
		return idgarantia;
	}

	public void setIdgarantia(int idgarantia) {
		this.idgarantia = idgarantia;
	}

	public int getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	
}
