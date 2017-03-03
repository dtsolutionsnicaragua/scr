package objeto;

public class Ingreso {
	private int idingreso;
	private String Representante;
	private int idtarea;
	private int idcliente;
	
	public Ingreso() {
		super();
		idingreso=-1;		
	}
	
	public Ingreso(String Representante,int idtarea, int idcliente) {
		super();
		this.idingreso =-1;
		this.Representante = Representante;
		this.idtarea = idtarea;
		this.idcliente = idcliente;
	}
	
	public Ingreso(int idingreso, String Representante,int idtarea, int idcliente) {
		super();
		this.idingreso = idingreso;
		this.idingreso =-1;
		this.Representante = Representante;
		this.idtarea = idtarea;
		this.idcliente = idcliente;
	}

	public int getIdingreso() {
		return idingreso;
	}

	public void setIdingreso(int idingreso) {
		this.idingreso = idingreso;
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
		return ""+idingreso;
	}
}
