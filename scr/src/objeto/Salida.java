package objeto;

public class Salida {
	private int idsalida;
	private String Representante;
	private int idtarea;
	private int idcliente;
	
	public Salida() {
		super();
		idsalida=-1;		
	}
	
	public Salida(String Representante,int idtarea, int idcliente) {
		super();
		this.idsalida =-1;
		this.Representante = Representante;
		this.idtarea = idtarea;
		this.idcliente = idcliente;
	}
	
	public Salida(int idingreso, String Representante,int idtarea, int idcliente) {
		super();
		this.idsalida = idingreso;
		this.idsalida =-1;
		this.Representante = Representante;
		this.idtarea = idtarea;
		this.idcliente = idcliente;
	}

	public int getIdsalida() {
		return idsalida;
	}

	public void setIdsalida(int idingreso) {
		this.idsalida = idingreso;
	}

	public String getRepresentante() {
		return Representante;
	}

	public void setRepresentante(String representante) {
		Representante = representante;
	}

	public int getIdtarea() {
		return idtarea;
	}

	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	@Override
	public String toString() {
		return ""+idsalida;
	}
}
