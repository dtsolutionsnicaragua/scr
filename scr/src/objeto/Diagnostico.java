package objeto;

public class Diagnostico {
int iddiagnostico;
boolean completo;
boolean activo;
int idtarea;
int iddetalleingreso;

public Diagnostico() {
	super();
	this.iddiagnostico = -1;
}

public Diagnostico(int iddiagnostico, boolean completo, boolean activo,
		int idtarea, int iddetalleingreso) {
	super();
	this.iddiagnostico = iddiagnostico;
	this.completo = completo;
	this.activo = activo;
	this.idtarea = idtarea;
	this.iddetalleingreso = iddetalleingreso;
}

public int getIddiagnostico() {
	return iddiagnostico;
}

public void setIddiagnostico(int iddiagnostico) {
	this.iddiagnostico = iddiagnostico;
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

public int getIddetalleingreso() {
	return iddetalleingreso;
}

public void setIddetalleingreso(int iddetalleingreso) {
	this.iddetalleingreso = iddetalleingreso;
}

@Override
public String toString() {
	return "Diagnostico [iddiagnostico=" + iddiagnostico + "]";
}


}
