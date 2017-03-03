package respaldo;

import igui.App;

import java.awt.LayoutManager;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.JPanel;
import javax.swing.event.CellEditorListener;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;

import objeto.Menu;

import java.awt.Insets;

public class JEditorMenu extends JPanel implements CellEditor {
	private static final long serialVersionUID = 1L;
	private JLabel lblValor;
	private JCheckBox cbxVer;
	private Vector<CellEditorListener> listeners = new Vector<CellEditorListener>( );
	private Menu MiMennu=new Menu();

	@Override
	public void addCellEditorListener(CellEditorListener cel) {
		 listeners.addElement(cel);
	}

	@Override
	public void cancelCellEditing() {
		lblValor.setText("");
	}


	@Override
	public Object getCellEditorValue() {
		return lblValor.getText();
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		return false;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener cel) {
		listeners.removeElement(cel);
		
	}

	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		 return true;
	}

	@Override
	public boolean stopCellEditing() {
		return false;
	}
	
	
	
	
	/**
	 * CONTRUCTORES DEL METODO PADRE
	 */

	public JEditorMenu(LayoutManager arg0) {
		super(arg0);
		initGUI();
		// TODO Auto-generated constructor stub
	}

	public JEditorMenu(boolean arg0) {
		super(arg0);
		initGUI();
		// TODO Auto-generated constructor stub
	}

	public JEditorMenu(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		initGUI();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/****
	 * Constructor de  la super Clase
	 * @return
	 */

	public JEditorMenu() {
		initGUI();
	}
	
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.anchor = GridBagConstraints.WEST;
		gbc_lblValor.insets = new Insets(0, 5, 0, 5);
		gbc_lblValor.gridx = 0;
		gbc_lblValor.gridy = 0;
		add(getLblValor(), gbc_lblValor);
		GridBagConstraints gbc_cbxVer = new GridBagConstraints();
		gbc_cbxVer.gridx = 1;
		gbc_cbxVer.gridy = 0;
		add(getCbxVer(), gbc_cbxVer);
	}
	
	public JLabel getLblValor() {
		if (lblValor == null) {
			lblValor = new JLabel("Dato");
			lblValor.setFont(App.fuentePrincipal);
		}
		return lblValor;
	}
	public JCheckBox getCbxVer() {
		if (cbxVer == null) {
			cbxVer = new JCheckBox("Ver");
			cbxVer.setFont(App.fuentePrincipal);
		}
		return cbxVer;
	}

	public void setMenu(Menu menu) {
		this.MiMennu = menu;
		this.lblValor.setText(menu.toString());
	}
	
	public Menu getMenu(){
		return this.MiMennu;
	}

	@Override
	public String toString() {
		return lblValor.toString();
	}
	
	
}
