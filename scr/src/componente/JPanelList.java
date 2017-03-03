package componente;


import igui.App;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import mvc.JDataTable;

public class JPanelList extends JPanel {

	/**
	 * Metodo General para realizar mostrado de Lista y evitar la contruccion de Metodos
	 */
	private static final long serialVersionUID = 1L;
	private JSearchTextfield jsBuscarCliente;
	private JScrollPane spTabla;
	private JLabel lblEstado;
	private JDataTable dataTable;

	/**
	 * Create the panel.
	 */
	public JPanelList() {
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(new BorderLayout(0, 0));
		this.add(getSpTabla(), BorderLayout.CENTER);
		this.add(getLblEstado(), BorderLayout.SOUTH);
		this.add(getJsBuscarCliente(), BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public JScrollPane getSpTabla() {
		if (spTabla == null) {
			spTabla = new JScrollPane();
			spTabla.setViewportView(getDataTable());
		}
		return spTabla;
	}
	public JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("New label");
			lblEstado.setFont( App.fuentePrincipal);
		}
		return lblEstado;
	}
	public JDataTable getDataTable() {
		if (dataTable == null) {
			dataTable = new JDataTable();
		}
		return dataTable;
	}
	
	public JSearchTextfield getJsBuscarCliente() {
		if (jsBuscarCliente == null) {
			jsBuscarCliente = new JSearchTextfield();
		}
		return jsBuscarCliente;
	}
	

}
