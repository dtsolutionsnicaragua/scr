package objeto;

public class Reparacion {
int idreparacion;
boolean completo;
boolean activo;
int idtarea;
int iddiagnostico;

public Reparacion() {
	super();
	this.idreparacion = -1;
}

public Reparacion(int iddiagnostico, boolean completo, boolean activo,
		int idtarea, int iddetalleingreso) {
	super();
	this.idreparacion = iddiagnostico;
	this.completo = completo;
	this.activo = activo;
	this.idtarea = idtarea;
	this.iddiagnostico = iddetalleingreso;
}

public int getIdReparacion() {
	return idreparacion;
}

public void setIReparacion(int iddiagnostico) {
	this.idreparacion = iddiagnostico;
}

public boolean getCompleto() {
	return completo;
}

public void setCompleto(boolean completo) {
	this.completo = completo;
}

public boolean getActivo() {
	return activo;
}

public void setActivo(boolean activo) {
	this.activo = activo;
}

public int getIdtarea() {
	return idtarea;
}

public void setIdtarea(int idtarea) {
	this.idtarea = idtarea;
}

public int getIddiagnostico() {
	return iddiagnostico;
}

public void setIddiagnostico(int iddetalleingreso) {
	this.iddiagnostico = iddetalleingreso;
}

@Override
public String toString() {
	return "Reparacion [idreparacion=" + idreparacion + "]";
}


}
