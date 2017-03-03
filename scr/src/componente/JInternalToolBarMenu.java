package componente;

import igui.App;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class JInternalToolBarMenu extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JButton BtnGuardar;
	private JButton BtnModificar;
	private JButton BtnLimpiar;
	private JButton BtnDesactivar;
	private JButton BtnBuscar;
	private JButton BtnExportar;
	private JButton BtnImprimir;
	private JButton BtnReporte;	
	private JButton BtnAgregar;
	private JButton BtnActivar;
	private JButton btnActualizar;
	private JButton btnCancelar;

	public JInternalToolBarMenu() {
		setBorder(UIManager.getBorder("Button.border"));
	
		setFloatable(false);
		add(getBtnAgregar());
		add(getBtnGuardar());
		add(getBtnEditar());
		add(getBtnActivar());
		add(getBtnDesactivar());
		add(getBtnLimpiar());
		add(getBtnCancelar());
		add(getBtnBuscar());
		add(getBtnExportar());
		add(getBtnImprimir());
		add(getBtnReporte());
		add(getBtnActualizar());
		setVisible(true);
	}
	
	public JButton getBtnAgregar() {
		if (BtnAgregar == null) {
			BtnAgregar = new JButton("Nuevo");
			BtnAgregar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/agregar.png")));
			BtnAgregar.setMnemonic('N');
			setOpciones(BtnAgregar);
		}
		return BtnAgregar;
	}
	
	public JButton getBtnGuardar() {
		if (BtnGuardar == null) {
			BtnGuardar = new JButton("Guardar");
			BtnGuardar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/guardar.png")));
			BtnGuardar.setMnemonic('G');		
			setOpciones(BtnGuardar);
		}
		return BtnGuardar;
	}
	public JButton getBtnEditar() {
		if (BtnModificar == null) {
			BtnModificar = new JButton("Editar");
			BtnModificar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/modificar.png")));
			BtnModificar.setMnemonic('E');
			setOpciones(BtnModificar);
		}
		return BtnModificar;
	}
	public JButton getBtnLimpiar() {
		if (BtnLimpiar == null) {
			BtnLimpiar = new JButton("Limpiar");
			BtnLimpiar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/limpiar.png")));
			BtnLimpiar.setMnemonic('L');
			//BtnLimpiar.setIcon(new ImageIcon(ConfigApp.getImage("image.limpiar")));
			setOpciones(BtnLimpiar);
		}
		return BtnLimpiar;
	}
	
	public JButton getBtnActivar() {
		if (BtnActivar == null) {
			BtnActivar = new JButton("Activar");
			BtnActivar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/activar.png")));
			setOpciones(BtnActivar);
		}
		return BtnActivar;
	}
	
	public JButton getBtnDesactivar() {
		if (BtnDesactivar == null) {
			BtnDesactivar = new JButton("Desactivar");
			BtnDesactivar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/desactivar.png")));
			BtnDesactivar.setMnemonic('B');
			//BtnDesactivar.setIcon(ConfigApp.getImageIcon("image.papelera"));
			setOpciones(BtnDesactivar);
		}
		return BtnDesactivar;
	}
	public JButton getBtnExportar() {
		if (BtnExportar == null) {
			BtnExportar = new JButton("Exportar");
			BtnExportar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/exportar.png")));
			BtnExportar.setMnemonic('E');
			//BtnExportar.setIcon(ConfigApp.getImageIcon("image.exportar"));
			setOpciones(BtnExportar);
		}
		return BtnExportar;
	}

	public JButton getBtnBuscar() {
		if (BtnBuscar == null) {
			BtnBuscar = new JButton("Buscar");
			BtnBuscar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/buscar.png")));
			BtnBuscar.setMnemonic('B');
			//BtnExportar.setIcon(ConfigApp.getImageIcon("image.exportar"));
			setOpciones(BtnBuscar);
		}
		return BtnBuscar;
	}

	public JButton getBtnImprimir() {
		if (BtnImprimir == null) {
			BtnImprimir = new JButton("Imprimir");
			BtnImprimir.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/imprimir.png")));
			BtnImprimir.setMnemonic('p');
			//BtnImprimir.setIcon(ConfigApp.getImageIcon("image.imprimir"));
			setOpciones(BtnImprimir);
		}
		return BtnImprimir;
	}
	
	public JButton getBtnReporte() {
		if (BtnReporte == null) {
			BtnReporte = new JButton("Reporte");
			BtnReporte.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/reporte.png")));
			//BtnReporte.setIcon(ConfigApp.getImageIcon("image.reporte"));
			setOpciones(BtnReporte);
		}
		return BtnReporte;
	}
	
	private void setOpciones(JButton boton){
		boton.setBorderPainted(false);
		boton.setFont(App.fuentePrincipal);
		//boton.setContentAreaFilled(false);
		boton.setFocusPainted(false);	
		boton.setVisible(false);
	}
	

	public JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/Updatetable.png")));
			//BtnReporte.setIcon(ConfigApp.getImageIcon("image.reporte"));
			setOpciones(btnActualizar);
			btnActualizar.setMnemonic('c');
		}
		return btnActualizar;
	}
	public JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/cancel.png")));
			setOpciones(btnCancelar);
			btnCancelar.setMnemonic('B');
		}
		return btnCancelar;
	}
}
