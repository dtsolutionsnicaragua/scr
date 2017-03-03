package componente;

import formulario.FrmRol;
import igui.App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;

import bd.BDPermiso;
import objeto.Menu;
import objeto.Permiso;

public class EditorRol extends JPanel implements CellEditor {
	private static final long serialVersionUID = 1L;
	private JLabel lblValor;
	public JCheckBox cbxVer;
	private Menu MiMennu=new Menu();
	private JPanel panelpermisos;
	public JCheckBox cbxCrear;
	public JCheckBox cbxModificar;
	public JCheckBox cbxEliminar;
	private JSeparator separator;
	private JCheckBox cbxImportar;
	private JCheckBox cbxExportar;
	private JCheckBox chckbxOtroMas;
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	

	public EditorRol() {
		initGUI();
	}
	
	private void initGUI() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				setBorder(null);
			}
		});
		
		setBackground(UIManager.getColor("Tree.background"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{250, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblValor = new GridBagConstraints();
		gbc_lblValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblValor.insets = new Insets(0, 1, 0, 0);
		gbc_lblValor.gridx = 0;
		gbc_lblValor.gridy = 0;
		add(getLblValor(), gbc_lblValor);
		
		GridBagConstraints gbc_panelpermisos = new GridBagConstraints();
		gbc_panelpermisos.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelpermisos.anchor = GridBagConstraints.NORTH;
		gbc_panelpermisos.insets= new Insets(0, 1, 0, 0);
		gbc_panelpermisos.gridx = 1;
		gbc_panelpermisos.gridy = 0;
		add(getPanelpermisos(), gbc_panelpermisos);
		
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 5, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(getSeparator(), gbc_separator);
	}
	
	public JLabel getLblValor() {
		if (lblValor == null) {
			lblValor = new JLabel("Dato");
			lblValor.setBackground(UIManager.getColor("Tree.selectionBackground"));
			lblValor.setPreferredSize(new Dimension(120, 14));
			lblValor.setFont(App.fuentePrincipal);
			lblValor.setIcon(new ImageIcon(FrmRol.class.getResource("/icon/general/imgFormulario.png")));
			
		}
		return lblValor;
	}
	public JCheckBox getCbxVer() {
		if (cbxVer == null) {
			cbxVer = new JCheckBox("Ver");
			cbxVer.setBackground(UIManager.getColor("Tree.background"));
			cbxVer.setFont(App.fuentePrincipal);
		}
		return cbxVer;
	}

	public void setMenu(Menu menu) {
		this.MiMennu = menu;
		this.lblValor.setText(MiMennu.toString());
		if(MiMennu.getIdmenu()!=-1){
			System.out.println(MiMennu.getNombre()+"-->"+MiMennu.getContenedor()+"--->"+MiMennu.getComponente().equals("JMenu"));
			
			ArrayList<Permiso> permisos = BDPermiso.cargarPermiso(MiMennu.getContenedor()?false:true);
			permisos.remove(0);
			
			for (int i=0 ; i<permisos.size(); i++)
			{
				JCheckBox cbxCrear = new JCheckBox(permisos.get(i).getNombre());
				cbxCrear.setName(permisos.get(i).getIdpermiso()+"");
				cbxCrear.setBackground(UIManager.getColor("Tree.background"));
				cbxCrear.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				panelpermisos.add(cbxCrear);
				panelpermisos.validate();
			}
			
		}
	}
	
	public Menu getMenu(){
		return this.MiMennu;
	}

	@Override
	public String toString() {
		return MiMennu.toString();
	}
	public JPanel getPanelpermisos() {
		if (panelpermisos == null) {
			panelpermisos = new JPanel();
			panelpermisos.setBackground(UIManager.getColor("Tree.background"));
			panelpermisos.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			/*panelpermisos.add(getCbxVer());
			panelpermisos.add(getCbxCrear());
			panelpermisos.add(getCbxModificar());
			panelpermisos.add(getCbxEliminar());
			panelpermisos.add(getCbxImportar());
			panelpermisos.add(getCbxExportar());
			panelpermisos.add(getChckbxOtroMas());
			panelpermisos.add(getCheckBox());
			panelpermisos.add(getCheckBox_1());*/
		}
		return panelpermisos;
	}
	
	public JCheckBox getCbxCrear() {
		if (cbxCrear == null) {
			cbxCrear = new JCheckBox("Crear");
			cbxCrear.setBackground(UIManager.getColor("Tree.background"));
			cbxCrear.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return cbxCrear;
	}
	
	public JCheckBox getCbxModificar() {
		if (cbxModificar == null) {
			cbxModificar = new JCheckBox("Modificar");
			cbxModificar.setBackground(UIManager.getColor("Tree.background"));
			cbxModificar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return cbxModificar;
	}
	
	public JCheckBox getCbxEliminar() {
		if (cbxEliminar == null) {
			cbxEliminar = new JCheckBox("Eliminar");
			cbxEliminar.setBackground(UIManager.getColor("Tree.background"));
			cbxEliminar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return cbxEliminar;
	}
	
	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(UIManager.getColor("TextPane.foreground"));
			separator.setForeground(UIManager.getColor("ToggleButton.focus"));
		}
		return separator;
	}

	/*public void soloVer(boolean valor) {
		cbxVer.setVisible(valor);
		cbxCrear.setVisible(!valor);
		cbxModificar.setVisible(!valor);
		cbxEliminar.setVisible(!valor);
		cbxEliminar.setVisible(!valor);
		cbxImportar.setVisible(!valor);
		cbxExportar.setVisible(!valor);
		// TODO Auto-generated method stub
		
	}*/
	public JCheckBox getCbxImportar() {
		if (cbxImportar == null) {
			cbxImportar = new JCheckBox("Importar");
			cbxImportar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			cbxImportar.setBackground(Color.WHITE);
		}
		return cbxImportar;
	}
	public JCheckBox getCbxExportar() {
		if (cbxExportar == null) {
			cbxExportar = new JCheckBox("Exportar");
			cbxExportar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			cbxExportar.setBackground(Color.WHITE);
		}
		return cbxExportar;
	}
	public JCheckBox getChckbxOtroMas() {
		if (chckbxOtroMas == null) {
			chckbxOtroMas = new JCheckBox("Otro Mas");
			chckbxOtroMas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			chckbxOtroMas.setBackground(Color.WHITE);
		}
		return chckbxOtroMas;
	}
	public JCheckBox getCheckBox() {
		if (checkBox == null) {
			checkBox = new JCheckBox("Otro Mas");
			checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			checkBox.setBackground(Color.WHITE);
		}
		return checkBox;
	}
	public JCheckBox getCheckBox_1() {
		if (checkBox_1 == null) {
			checkBox_1 = new JCheckBox("Otro Mas");
			checkBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			checkBox_1.setBackground(Color.WHITE);
		}
		return checkBox_1;
	}
	
	/**
	 * 
	 */
	/*public void cargarPermiso() {
		if(MiMennu.getIdmenu()==-1){
			//Ocultaremos las opciones
		}else{
			if(MiMennu.getNivel()==0){
				cbxVer.setVisible(true);				
			}else{
				
			}
			
		}
		
	}*/
	
	
	
	
	
	

	public EditorRol(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EditorRol(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EditorRol(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}



}
