package objeto;

import java.util.Date;

public class Factura {
	int idfactura;
	String nofactura;
	Date fecha;
	int idcliente;
	int idusuario;
	boolean activo;
	
	public Factura() {
		super();
		this.idfactura = -1;
		this.nofactura ="";
	}

	public Factura(int idfactura, String nofactura, Date fecha,boolean activo, int idcliente,int idusuario) {
		super();
		this.idfactura = idfactura;
		this.nofactura = nofactura;
		this.fecha = fecha;
		this.idcliente = idcliente;
		this.idusuario = idusuario;
		this.activo = activo;
	}

	public int getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}

	public String getNofactura() {
		return nofactura;
	}

	public void setNofactura(String nofactura) {
		this.nofactura = nofactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Factura [idfactura=" + idfactura + ", nofactura=" + nofactura
				+ ", fecha=" + fecha + ", idcliente=" + idcliente
				+ ", idusuario=" + idusuario + ", activo=" + activo + "]";
	}
	
	

	/*@Override
	public String toString() {
		return nofactura ;
	}*/
}
