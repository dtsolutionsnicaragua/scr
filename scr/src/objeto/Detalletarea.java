package objeto;

public class Detalletarea {
	int iddetalletarea;
	String descripcion;
	boolean activo;
	int idequipo;
	int idtarea;
	
	public Detalletarea() {
		super();
		this.iddetalletarea = -1;
		this.descripcion = "";
	}

	public Detalletarea(int iddetalletarea, String descripcion, boolean activo,
			int idequipo, int idtarea) {
		super();
		this.iddetalletarea = iddetalletarea;
		this.descripcion = descripcion;
		this.activo = activo;
		this.idequipo = idequipo;
		this.idtarea = idtarea;
	}

	public int getIddetalletarea() {
		return iddetalletarea;
	}

	public void setIddetalletarea(int iddetalletarea) {
		this.iddetalletarea = iddetalletarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public int getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}

	@Override
	public String toString() {
		
		return "" + iddetalletarea ;
	}
	
	
	
}
