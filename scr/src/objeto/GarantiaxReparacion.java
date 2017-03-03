package objeto;

import java.util.Date;

public class GarantiaxReparacion {
int idgarantiaxreparacion;
String ref;
boolean activo;
Date fechainicio;
int idgarantia;

public GarantiaxReparacion() {
	super();
	this.idgarantiaxreparacion =-1;
	// TODO Auto-generated constructor stub
}

public GarantiaxReparacion(int idgarantiaxreparacion, String ref,
		boolean activo, Date fechainicio, int idgarantia) {
	super();
	this.idgarantiaxreparacion = idgarantiaxreparacion;
	this.ref = ref;
	this.activo = activo;
	this.fechainicio = fechainicio;
	this.idgarantia = idgarantia;
}

public int getIdgarantiaxreparacion() {
	return idgarantiaxreparacion;
}

public void setIdgarantiaxreparacion(int idgarantiaxreparacion) {
	this.idgarantiaxreparacion = idgarantiaxreparacion;
}

public String getRef() {
	return ref;
}

public void setRef(String ref) {
	this.ref = ref;
}

public boolean getActivo() {
	return activo;
}

public void setActivo(boolean activo) {
	this.activo = activo;
}

public Date getFechainicio() {
	return fechainicio;
}

public void setFechainicio(Date fechainicio) {
	this.fechainicio = fechainicio;
}

public int getIdgarantia() {
	return idgarantia;
}

public void setIdgarantia(int idgarantia) {
	this.idgarantia = idgarantia;
}

@Override
public String toString() {
	return "GarantiaxReparacion [idgarantiaxreparacion="
			+ idgarantiaxreparacion + ", ref=" + ref + ", activo=" + activo
			+ ", fechainicio=" + fechainicio + ", idgarantia=" + idgarantia
			+ "]";
}

}
