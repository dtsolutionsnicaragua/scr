package bd;

import igui.App;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Component;

import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import mvc.JDataTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import com.toedter.calendar.JDateChooser;

import componente.SincronizarTabla;
import componente.JInternalToolBarMenu;

import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import componente.JSearchTextfield;

public class FrmRepresentante extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalToolBarMenu internalToolBarMenu;
	private JSplitPane pnlContainer;
	private JPanel panelTabla;
	private JPanel panelGeneral;
	private JLabel lblRuc;
	private JTextField txtRuc;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTelfono;
	private JTextField txtTelefono;
	private JLabel lblPginaWeb;
	private JTextField txtPaginaWeb;
	private JLabel lblDireccion;
	private JScrollPane scrollPane;
	private JTextArea txtDireccion;
	private JLabel lblId;
	private JTextField txtId;
	private JScrollPane spTabla;
	private JLabel lblEstado;
	private JDataTable dataTable;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblFecha;
	private JDateChooser dcFecha;
	private JLabel lblEstado_1;
	private JCheckBox cbxActivo;
	private JSearchTextfield jsBuscarCliente;

	/**
	 * Create the panel.
	 */
	public FrmRepresentante() {

		initComponents();
	}
	private void initComponents() {
		setVisible(true);
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				//txtRuc.requestFocus();
			}
		});
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{40, 60, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		GridBagConstraints gbc_internalToolBarMenu = new GridBagConstraints();
		gbc_internalToolBarMenu.anchor = GridBagConstraints.WEST;
		gbc_internalToolBarMenu.insets = new Insets(0, 0, 5, 0);
		gbc_internalToolBarMenu.gridx = 0;
		gbc_internalToolBarMenu.gridy = 0;
		add(getInternalToolBarMenu_1(), gbc_internalToolBarMenu);
		GridBagConstraints gbc_pnlContainer = new GridBagConstraints();
		gbc_pnlContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlContainer.gridx = 0;
		gbc_pnlContainer.gridy = 1;
		add(getPnlContainer(), gbc_pnlContainer);
		//panelGeneral.setVisible(false);
		//panelTabla.setVisible(true);
		setActivo(false);
		cargarListaClientes();	
	
	}
	
	public void cargarListaClientes(){
		dataTable.DAOModelo.ConsultaSQL =  "SELECT idCliente AS ID,DATE_FORMAT( fecha,  '%d/%m/%Y' ) as Fecha, ruc as 'No Ruc', Nombre AS 'Cliente',telefono as Telefono,correo as 'E-mail',web as Web,direccion as Direccion,activo as Vigente FROM cliente";
		SincronizarTabla consulta = new SincronizarTabla(dataTable,lblEstado, internalToolBarMenu.getBtnActualizar(),jsBuscarCliente);
		consulta.execute();	
	}
	
	public void limpiarFormualario(){
		txtId.setText("");
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setText("");
		txtRuc.setText("");
		txtNombre.setText("");		
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtPaginaWeb.setText("");
		txtDireccion.setText("");
		cbxActivo.setSelected(false);
	}
	
	public void setActivo(Boolean valor){
		txtId.setEditable(valor);;
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setEditable(valor);
		txtRuc.setEditable(valor);
		txtNombre.setEditable(valor);	
		txtTelefono.setEditable(valor);
		txtCorreo.setEditable(valor);
		txtPaginaWeb.setEditable(valor);
		txtDireccion.setEditable(valor);
		cbxActivo.setEnabled(valor);
	}

	public JInternalToolBarMenu getInternalToolBarMenu_1() {
		if (internalToolBarMenu == null) {
			internalToolBarMenu = new JInternalToolBarMenu();						
			internalToolBarMenu.getBtnAgregar().setVisible(true);
			internalToolBarMenu.getBtnLimpiar().setVisible(false);
			internalToolBarMenu.getBtnActualizar().setVisible(true);
			internalToolBarMenu.getBtnGuardar().setVisible(false);
			
			internalToolBarMenu.getBtnAgregar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					limpiarFormualario();
					dataTable.clearSelection();
					setActivo(true);
					internalToolBarMenu.getBtnGuardar().setVisible(true);
					internalToolBarMenu.getBtnCancelar().setVisible(true);
					internalToolBarMenu.getBtnAgregar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();
					internalToolBarMenu.paintComponents(internalToolBarMenu.getGraphics());
					dcFecha.getCalendarButton().doClick();
				}
			});
			
			internalToolBarMenu.getBtnEditar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setActivo(true);
					internalToolBarMenu.getBtnGuardar().setVisible(true);
					internalToolBarMenu.getBtnCancelar().setVisible(true);
					internalToolBarMenu.getBtnAgregar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					internalToolBarMenu.paintComponents(internalToolBarMenu.getGraphics());
					txtRuc.requestFocus();
				}
			});
			
			internalToolBarMenu.getBtnCancelar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					limpiarFormualario();
					dataTable.clearSelection();
					setActivo(false);					
					internalToolBarMenu.getBtnAgregar().setVisible(true);
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);
					internalToolBarMenu.paintComponents(internalToolBarMenu.getGraphics());
					
					/*internalToolBarMenu.getBtnActualizar().setVisible(false);
					internalToolBarMenu.getBtnLimpiar().setText("Cancelar");
					internalToolBarMenu.getBtnLimpiar().setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/eliminar.png")));
					((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();*/
					//dcFecha.getCalendarButton().doClick();
				}
			});
			
			internalToolBarMenu.getBtnActualizar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jsBuscarCliente._reiniciarComponente();
					dataTable.clearSelection();
					limpiarFormualario();					
					setActivo(false);
					cargarListaClientes();
					internalToolBarMenu.getBtnAgregar().setVisible(true);
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);	
					internalToolBarMenu.paintComponents(internalToolBarMenu.getGraphics());
				}
			});
			//internalToolBarMenu.setBorder(null);
		}
		return internalToolBarMenu;
	}
	public JSplitPane getPnlContainer() {
		if (pnlContainer == null) {
			pnlContainer = new JSplitPane();
			pnlContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnlContainer.setDividerSize(0);
			pnlContainer.setResizeWeight(0.2);
			pnlContainer.setRightComponent(getPanelTabla());
			pnlContainer.setLeftComponent(getPanelGeneral());
		}
		return pnlContainer;
	}
	public JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelTabla.setLayout(new BorderLayout(0, 0));
			panelTabla.add(getSpTabla(), BorderLayout.CENTER);
			panelTabla.add(getLblEstado(), BorderLayout.SOUTH);
			panelTabla.add(getJsBuscarCliente(), BorderLayout.NORTH);
			panelTabla.setVisible(true);
		}
		return panelTabla;
	}
	public JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.setBackground(SystemColor.activeCaption);
			panelGeneral.setBorder(null);
			GridBagLayout gbl_panelGeneral = new GridBagLayout();
			gbl_panelGeneral.columnWidths = new int[]{80, 150, 150, 0};
			gbl_panelGeneral.rowHeights = new int[]{40, 40, 30, 30, 30, 0, 0, 80, 40, 0};
			gbl_panelGeneral.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelGeneral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelGeneral.setLayout(gbl_panelGeneral);
			GridBagConstraints gbc_lblId = new GridBagConstraints();
			gbc_lblId.anchor = GridBagConstraints.WEST;
			gbc_lblId.insets = new Insets(10, 10, 5, 5);
			gbc_lblId.gridx = 0;
			gbc_lblId.gridy = 0;
			panelGeneral.add(getLblId(), gbc_lblId);
			GridBagConstraints gbc_txtId = new GridBagConstraints();
			gbc_txtId.insets = new Insets(0, 0, 5, 5);
			gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtId.gridx = 1;
			gbc_txtId.gridy = 0;
			panelGeneral.add(getTxtId(), gbc_txtId);
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.WEST;
			gbc_lblFecha.insets = new Insets(10, 10, 5, 5);
			gbc_lblFecha.gridx = 0;
			gbc_lblFecha.gridy = 1;
			panelGeneral.add(getLblFecha(), gbc_lblFecha);
			GridBagConstraints gbc_dcFecha = new GridBagConstraints();
			gbc_dcFecha.insets = new Insets(10, 0, 5, 5);
			gbc_dcFecha.fill = GridBagConstraints.BOTH;
			gbc_dcFecha.gridx = 1;
			gbc_dcFecha.gridy = 1;
			panelGeneral.add(getDcFecha(), gbc_dcFecha);
			GridBagConstraints gbc_lblRuc = new GridBagConstraints();
			gbc_lblRuc.insets = new Insets(10, 10, 5, 5);
			gbc_lblRuc.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblRuc.gridx = 0;
			gbc_lblRuc.gridy = 2;
			panelGeneral.add(getLblRuc(), gbc_lblRuc);
			GridBagConstraints gbc_txtRuc = new GridBagConstraints();
			gbc_txtRuc.insets = new Insets(10, 0, 5, 5);
			gbc_txtRuc.fill = GridBagConstraints.BOTH;
			gbc_txtRuc.gridx = 1;
			gbc_txtRuc.gridy = 2;
			panelGeneral.add(getTextField_1(), gbc_txtRuc);
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblNombre.insets = new Insets(10, 10, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 3;
			panelGeneral.add(getLblNombre(), gbc_lblNombre);
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(10, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.BOTH;
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 3;
			panelGeneral.add(getTextField_1_1(), gbc_txtNombre);
			GridBagConstraints gbc_lblTelfono = new GridBagConstraints();
			gbc_lblTelfono.anchor = GridBagConstraints.WEST;
			gbc_lblTelfono.insets = new Insets(10, 10, 5, 5);
			gbc_lblTelfono.gridx = 0;
			gbc_lblTelfono.gridy = 4;
			panelGeneral.add(getLblTelfono(), gbc_lblTelfono);
			GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
			gbc_txtTelefono.anchor = GridBagConstraints.NORTH;
			gbc_txtTelefono.gridwidth = 2;
			gbc_txtTelefono.insets = new Insets(10, 0, 5, 0);
			gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTelefono.gridx = 1;
			gbc_txtTelefono.gridy = 4;
			panelGeneral.add(getTxtTelefono(), gbc_txtTelefono);
			GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
			gbc_lblCorreo.anchor = GridBagConstraints.WEST;
			gbc_lblCorreo.insets = new Insets(10, 10, 5, 5);
			gbc_lblCorreo.gridx = 0;
			gbc_lblCorreo.gridy = 5;
			panelGeneral.add(getLblCorreo(), gbc_lblCorreo);
			GridBagConstraints gbc_txtCorreo = new GridBagConstraints();
			gbc_txtCorreo.gridwidth = 2;
			gbc_txtCorreo.insets = new Insets(10, 0, 5, 0);
			gbc_txtCorreo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCorreo.gridx = 1;
			gbc_txtCorreo.gridy = 5;
			panelGeneral.add(getTxtCorreo(), gbc_txtCorreo);
			GridBagConstraints gbc_lblPginaWeb = new GridBagConstraints();
			gbc_lblPginaWeb.anchor = GridBagConstraints.WEST;
			gbc_lblPginaWeb.insets = new Insets(10, 10, 5, 5);
			gbc_lblPginaWeb.gridx = 0;
			gbc_lblPginaWeb.gridy = 6;
			panelGeneral.add(getLblPginaWeb(), gbc_lblPginaWeb);
			GridBagConstraints gbc_txtPaginaWeb = new GridBagConstraints();
			gbc_txtPaginaWeb.insets = new Insets(10, 0, 5, 0);
			gbc_txtPaginaWeb.gridwidth = 2;
			gbc_txtPaginaWeb.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPaginaWeb.gridx = 1;
			gbc_txtPaginaWeb.gridy = 6;
			panelGeneral.add(getTextField_1_2(), gbc_txtPaginaWeb);
			GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
			gbc_lblDireccion.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblDireccion.insets = new Insets(10, 10, 5, 5);
			gbc_lblDireccion.gridx = 0;
			gbc_lblDireccion.gridy = 7;
			panelGeneral.add(getLblDireccion(), gbc_lblDireccion);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.insets = new Insets(10, 0, 10, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 7;
			panelGeneral.add(getScrollPane(), gbc_scrollPane);
			GridBagConstraints gbc_lblEstado_1 = new GridBagConstraints();
			gbc_lblEstado_1.anchor = GridBagConstraints.WEST;
			gbc_lblEstado_1.insets = new Insets(10, 10, 0, 5);
			gbc_lblEstado_1.gridx = 0;
			gbc_lblEstado_1.gridy = 8;
			panelGeneral.add(getLblEstado_1(), gbc_lblEstado_1);
			GridBagConstraints gbc_cbxActivo = new GridBagConstraints();
			gbc_cbxActivo.anchor = GridBagConstraints.WEST;
			gbc_cbxActivo.insets = new Insets(0, 0, 0, 5);
			gbc_cbxActivo.gridx = 1;
			gbc_cbxActivo.gridy = 8;
			panelGeneral.add(getCbxActivo(), gbc_cbxActivo);
		}
		return panelGeneral;
	}
	public JLabel getLblRuc() {
		if (lblRuc == null) {
			lblRuc = new JLabel("Ruc");
			lblRuc.setFont(App.fuentePrincipal);
			lblRuc.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblRuc.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblRuc;
	}
	public JTextField getTextField_1() {
		if (txtRuc == null) {
			txtRuc = new JTextField();
			txtRuc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();
						dcFecha.getCalendarButton().doClick();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtNombre.requestFocus();
					}					
				}
			});
			txtRuc.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtRuc.setFont(App.fuentePrincipal);
			txtRuc.setColumns(10);
		}
		return txtRuc;
	}
	public JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(App.fuentePrincipal);
		}
		return lblNombre;
	}
	public JTextField getTextField_1_1() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtRuc.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtTelefono.requestFocus();
					}	
				}
			});
			txtNombre.setFont(App.fuentePrincipal);
			txtNombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	public JLabel getLblTelfono() {
		if (lblTelfono == null) {
			lblTelfono = new JLabel("Tel\u00E9fono");
			lblTelfono.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblTelfono;
	}
	public JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtNombre.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtCorreo.requestFocus();
					}
				}
			});
			txtTelefono.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtTelefono.setColumns(10);
		}
		return txtTelefono;
	}
	public JLabel getLblPginaWeb() {
		if (lblPginaWeb == null) {
			lblPginaWeb = new JLabel("P\u00E1gina Web");
			lblPginaWeb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblPginaWeb;
	}
	public JTextField getTextField_1_2() {
		if (txtPaginaWeb == null) {
			txtPaginaWeb = new JTextField();
			txtPaginaWeb.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtCorreo.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtDireccion.requestFocus();
					}
				}
			});
			txtPaginaWeb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtPaginaWeb.setColumns(10);
			txtPaginaWeb.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return txtPaginaWeb;
	}
	public JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Direccion");
			lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblDireccion;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTxtDireccion());
		}
		return scrollPane;
	}
	public JTextArea getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextArea();
			txtDireccion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtPaginaWeb.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						cbxActivo.requestFocus();
					}
				}
			});
			txtDireccion.setLineWrap(true);
			txtDireccion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtDireccion.setRows(5);
			txtDireccion.setFont(App.fuentePrincipal);
		}
		return txtDireccion;
	}
	public JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID");
			lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			lblId.setAlignmentY(1.0f);
			lblId.setAlignmentX(0.5f);
		}
		return lblId;
	}
	public JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setEnabled(false);
			txtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtId.setColumns(10);
			txtId.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return txtId;
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
			lblEstado.setFont(App.fuentePrincipal);
		}
		return lblEstado;
	}
	public JDataTable getDataTable() {
		if (dataTable == null) {
			dataTable = new JDataTable();
			dataTable._setAnchoColumna(0, 0);
			dataTable._setAnchoColumna(1, 100);
			dataTable._setAnchoColumna(2, 120);
			dataTable._setAnchoColumna(3, 300);
			dataTable._setAnchoColumna(4, 100);
			dataTable._setAnchoColumna(5, 95);
			dataTable._setAnchoColumna(6, 120);
			dataTable._setAnchoColumna(7, 250);
			dataTable._setAnchoColumna(8, 0);	
			
			ListSelectionModel rowSelMod = dataTable.getSelectionModel();
			rowSelMod.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
					if(evt.getValueIsAdjusting()) return;
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);
					setActivo(false);
					 if (((ListSelectionModel)evt.getSource()).isSelectionEmpty()){ //Si no se ha seleccionado ninguna fila
						 internalToolBarMenu.getBtnEditar().setVisible(false);
						 internalToolBarMenu.getBtnAgregar().setVisible(true);
						 return;
					 }else{
						 internalToolBarMenu.getBtnEditar().setVisible(true);
						 internalToolBarMenu.getBtnAgregar().setVisible(true);
					 }		
					
					txtId.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString());
					try {
						dcFecha.setDate(App.Fecha.parse(dataTable.getValueAt(dataTable.getSelectedRow(), 1).toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					txtRuc.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString());
					txtNombre.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString());
					txtTelefono.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 4).toString());
					txtCorreo.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 5).toString());
					txtPaginaWeb.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 6).toString());
					txtDireccion.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 7).toString());
					cbxActivo.setSelected(Boolean.parseBoolean(dataTable.getValueAt(dataTable.getSelectedRow(),8).toString()));
				}
			});
			
		}
		return dataTable;
	}

	
	public JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo");
			lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblCorreo;
	}
	public JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtTelefono.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtPaginaWeb.requestFocus();
					}
				}
			});
			txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			txtCorreo.setColumns(10);
			txtCorreo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		return txtCorreo;
	}
	
	public JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			lblFecha.setAlignmentY(1.0f);
			lblFecha.setAlignmentX(0.5f);
		}
		return lblFecha;
	}
	public JDateChooser getDcFecha() {
		if (dcFecha == null) {
			dcFecha = new JDateChooser();
			((JTextField)dcFecha.getDateEditor().getUiComponent()).addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
									
					if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtRuc.requestFocus();
					}
				}
			});
			dcFecha.setFont(App.fuentePrincipal);
			dcFecha.setDateFormatString("dd/MM/yyyy");			
			((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();
		}
		return dcFecha;
	}
	public JLabel getLblEstado_1() {
		if (lblEstado_1 == null) {
			lblEstado_1 = new JLabel("Estado");
			lblEstado_1.setFont(App.fuentePrincipal);
		}
		return lblEstado_1;
	}
	public JCheckBox getCbxActivo() {
		if (cbxActivo == null) {
			cbxActivo = new JCheckBox("Activo");
			cbxActivo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtDireccion.requestFocus();
					}
				}
			});
			cbxActivo.setHorizontalAlignment(SwingConstants.LEFT);
			cbxActivo.setFont(App.fuentePrincipal);
		}
		return cbxActivo;
	}
	public JSearchTextfield getJsBuscarCliente() {
		if (jsBuscarCliente == null) {
			jsBuscarCliente = new JSearchTextfield();
		}
		return jsBuscarCliente;
	}
}
