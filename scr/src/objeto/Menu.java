package objeto;

public class Menu {
	int idmenu;
	String variable;
	String componente;
	String nombre;
	int	nivel;
	int posicion;
	boolean contenedor;
	
	public Menu(){
		this.idmenu=-1;
		this.nivel=-1;
		this.nombre = "";
	}
	
	public Menu(int idmenu, String variable, String componente, String nombre,
			int nivel, int posicion,boolean contenedor) {
		super();
		this.idmenu = idmenu;
		this.variable = variable;
		this.componente = componente;
		this.nombre = nombre;
		this.nivel = nivel;
		this.posicion = posicion;
		this.contenedor = contenedor;
	}

	public int getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public boolean getContenedor() {
		return contenedor;
	}

	public void setContenedor(boolean contenedor) {
		this.contenedor = contenedor;
	}


	@Override
	public String toString() {
		return this.nombre; //String
				//.format("Menu [idmenu=%s, variable=%s, componente=%s, nombre=%s, nivel=%s, posicion=%s]",
						//idmenu, variable, componente, nombre, nivel, posicion);
	}

	public String toString2() {
		return String
				.format("Menu [idmenu=%s, variable=%s, componente=%s, nombre=%s, nivel=%s, posicion=%s]",
						idmenu, variable, componente, nombre, nivel, posicion);
	}

	
	/*@Override
	public String toString() {
		return String.format("Menu [nombre=%s, posicion=%s]", nombre, posicion);
	}*/

	

}
