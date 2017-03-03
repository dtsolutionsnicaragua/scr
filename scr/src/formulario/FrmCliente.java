package formulario;

import igui.App;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
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
import java.util.Date;

import bd.BDCliente;

import com.toedter.calendar.JDateChooser;

import componente.SincronizarTabla;
import componente.JInternalToolBarMenu;

import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import objeto.Cliente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import componente.JSearchTextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FrmCliente extends JPanel {

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
	private JLabel lblExigido1;
	private JLabel label;
	
	private Cliente cliente;
	private JScrollPane spGeneral;

	/**
	 * Create the panel.
	 */
	public FrmCliente() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	initComponents();
		    	internalToolBarMenu.getBtnActualizar().doClick();
		    }
		  });
	}
	
	private void initComponents() {
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent arg0) {
				//txtRuc.requestFocus();
			}
		});
		//setBorder(new TitledBorder(null, "Listado de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		add(getInternalToolBarMenu(), BorderLayout.NORTH);
		add(getPnlContainer(), BorderLayout.CENTER);
		//panelGeneral.setVisible(false);
		//panelTabla.setVisible(true);
		//setActivo(false);
		//cargarListaClientes();	
		//internalToolBarMenu.getBtnActualizar().doClick();
		//panelGeneral.setVisible(false);
		//panelTabla.setVisible(true);
		//setVisible(true);
	}
	
	public FocusAdapter getEventListener(){		
		 FocusAdapter mouseListener = new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					JTextField c =(JTextField) arg0.getComponent();
					c.setBorder(App.borderActivo);
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					JTextField c =(JTextField) arg0.getComponent();
					c.setBorder(App.borderDesactivo);
				}
			};
			return mouseListener;
	}
	
	public void cargarListaClientes(){
		dataTable.DAOModelo.ConsultaSQL =  "SELECT idCliente AS ID,DATE_FORMAT( fecha,  '%d/%m/%Y' ) as Fecha, ruc as 'No Ruc', Nombre AS 'Cliente',telefono as Telefono,correo as 'E-mail',web as Web,direccion as Direccion,activo as Vigente FROM cliente";
		SincronizarTabla consulta = new SincronizarTabla(dataTable,lblEstado, internalToolBarMenu.getBtnActualizar(),jsBuscarCliente);
		consulta.execute();	
		cliente = new Cliente();
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
		this.revalidate();
	}
	
	public void setActivo(Boolean valor){
		//txtId.setEnabled(valor);;
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setEnabled(valor);
		dcFecha.getCalendarButton().setEnabled(valor);
		txtRuc.setEnabled(valor);
		txtNombre.setEnabled(valor);	
		txtTelefono.setEnabled(valor);
		txtCorreo.setEnabled(valor);
		txtPaginaWeb.setEnabled(valor);
		txtDireccion.setEnabled(valor);
		cbxActivo.setEnabled(valor);
		this.revalidate();
	}

	public JInternalToolBarMenu getInternalToolBarMenu() {
		if (internalToolBarMenu == null) {
			internalToolBarMenu = new JInternalToolBarMenu();
			internalToolBarMenu.getBtnAgregar().setVisible(true);
			internalToolBarMenu.getBtnLimpiar().setVisible(false);
			internalToolBarMenu.getBtnActualizar().setVisible(true);
			internalToolBarMenu.getBtnGuardar().setVisible(false);
			
			internalToolBarMenu.getBtnAgregar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	limpiarFormualario();
							dataTable.clearSelection();
							setActivo(true);
							cbxActivo.setSelected(true);
							internalToolBarMenu.getBtnGuardar().setVisible(true);
							internalToolBarMenu.getBtnCancelar().setVisible(true);
							internalToolBarMenu.getBtnAgregar().setVisible(false);
							internalToolBarMenu.getBtnEditar().setVisible(false);
							((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();					
							dcFecha.getCalendarButton().doClick();
					    }
					  });
					
					
				}
			});
			
			internalToolBarMenu.getBtnGuardar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	if(verificarRequerimientos()){
								
								cliente.setRuc(txtRuc.getText());
								cliente.setFecha(new Date(dcFecha.getDate().getTime()));
								cliente.setNombre(txtNombre.getText());
								cliente.setDireccion(txtDireccion.getText());
								cliente.setTelefono(txtTelefono.getText());
								cliente.setWeb(txtPaginaWeb.getText());
								cliente.setCorreo(txtCorreo.getText());
								cliente.setActivo(cbxActivo.isSelected());
								
								if(txtId.getText().isEmpty()){
									if(BDCliente.guardar(cliente)){
										JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente");
									}
								}else{	
									cliente.setIdcliente(Integer.parseInt(txtId.getText()));
									if(BDCliente.Modificar(cliente)){
										JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
									}																				
								}
								internalToolBarMenu.getBtnActualizar().doClick();
								
							}else
								JOptionPane.showMessageDialog(null, "Favor verificar los campos obligatorio");
					    }
					  });
					
					
					
					
					
				}

				private boolean verificarRequerimientos() {
					try{
						
						return (((JTextField)dcFecha.getDateEditor().getUiComponent()).getForeground()==Color.BLACK) && !txtNombre.getText().isEmpty();
					}catch(Exception ex){
					}
					return false;
				}
			});
			
			internalToolBarMenu.getBtnEditar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	setActivo(true);
							internalToolBarMenu.getBtnGuardar().setVisible(true);
							internalToolBarMenu.getBtnCancelar().setVisible(true);
							internalToolBarMenu.getBtnAgregar().setVisible(false);
							internalToolBarMenu.getBtnEditar().setVisible(false);
							txtRuc.requestFocus();
					    }
					  });
				}
			});
			
			internalToolBarMenu.getBtnCancelar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	dataTable.clearSelection();
							setActivo(false);					
							internalToolBarMenu.getBtnAgregar().setVisible(true);
							internalToolBarMenu.getBtnGuardar().setVisible(false);
							internalToolBarMenu.getBtnEditar().setVisible(false);
							internalToolBarMenu.getBtnCancelar().setVisible(false);
					    }
					  });
				}
			});
			
			internalToolBarMenu.getBtnActualizar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	dataTable.clearSelection();
							jsBuscarCliente._reiniciarComponente();
							limpiarFormualario();					
							setActivo(false);
							cargarListaClientes();
							internalToolBarMenu.getBtnAgregar().setVisible(true);
							internalToolBarMenu.getBtnGuardar().setVisible(false);
							internalToolBarMenu.getBtnEditar().setVisible(false);
							internalToolBarMenu.getBtnCancelar().setVisible(false);	
					    }
					  });
					
					
				}
			});
		}
		return internalToolBarMenu;
	}
	public JSplitPane getPnlContainer() {
		if (pnlContainer == null) {
			pnlContainer = new JSplitPane();
			pnlContainer.setResizeWeight(0.1);
			pnlContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnlContainer.setLeftComponent(getPanelTabla());
			pnlContainer.setRightComponent(getspGeneral());
		}
		return pnlContainer;
	}
	public JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			GridBagLayout gbl_panelTabla = new GridBagLayout();
			gbl_panelTabla.columnWidths = new int[]{340, 0};
			gbl_panelTabla.rowHeights = new int[]{44, 359, 20, 0};
			gbl_panelTabla.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelTabla.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			panelTabla.setLayout(gbl_panelTabla);
			GridBagConstraints gbc_jsBuscarCliente = new GridBagConstraints();
			gbc_jsBuscarCliente.anchor = GridBagConstraints.NORTH;
			gbc_jsBuscarCliente.fill = GridBagConstraints.HORIZONTAL;
			gbc_jsBuscarCliente.insets = new Insets(5, 5, 5, 5);
			gbc_jsBuscarCliente.gridx = 0;
			gbc_jsBuscarCliente.gridy = 0;
			panelTabla.add(getJsBuscarCliente(), gbc_jsBuscarCliente);
			GridBagConstraints gbc_spTabla = new GridBagConstraints();
			gbc_spTabla.fill = GridBagConstraints.BOTH;
			gbc_spTabla.insets = new Insets(0, 5, 5, 5);
			gbc_spTabla.gridx = 0;
			gbc_spTabla.gridy = 1;
			panelTabla.add(getSpTabla(), gbc_spTabla);
			GridBagConstraints gbc_lblEstado = new GridBagConstraints();
			gbc_lblEstado.anchor = GridBagConstraints.NORTH;
			gbc_lblEstado.fill = GridBagConstraints.BOTH;
			gbc_lblEstado.gridx = 0;
			gbc_lblEstado.gridy = 2;
			panelTabla.add(getLblEstado(), gbc_lblEstado);
			panelTabla.setVisible(true);
		}
		return panelTabla;
	}
	
	public JScrollPane getspGeneral(){
		if (spGeneral == null) {
			spGeneral = new JScrollPane();
			spGeneral.setViewportView(getPanelGeneral());
			}
			return  spGeneral;
			
	}
	
	public JPanel getPanelGeneral() {
		if (panelGeneral == null) {
			panelGeneral = new JPanel();
			panelGeneral.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					paintComponents(getGraphics());
				}
			});
			panelGeneral.setBackground(App.BackgroudForm);
			panelGeneral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			GridBagLayout gbl_panelGeneral = new GridBagLayout();
			gbl_panelGeneral.columnWidths = new int[]{80, 10, 150, 150, 0};
			gbl_panelGeneral.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 80, 40, 40};
			gbl_panelGeneral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbc_txtId.gridx = 2;
			gbc_txtId.gridy = 0;
			panelGeneral.add(getTxtId(), gbc_txtId);
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.WEST;
			gbc_lblFecha.insets = new Insets(10, 10, 5, 5);
			gbc_lblFecha.gridx = 0;
			gbc_lblFecha.gridy = 1;
			panelGeneral.add(getLblFecha(), gbc_lblFecha);
			GridBagConstraints gbc_lblExigido1 = new GridBagConstraints();
			gbc_lblExigido1.anchor = GridBagConstraints.EAST;
			gbc_lblExigido1.insets = new Insets(0, 0, 5, 5);
			gbc_lblExigido1.gridx = 1;
			gbc_lblExigido1.gridy = 1;
			panelGeneral.add(getLblExigido1(), gbc_lblExigido1);
			GridBagConstraints gbc_dcFecha = new GridBagConstraints();
			gbc_dcFecha.insets = new Insets(10, 0, 5, 5);
			gbc_dcFecha.fill = GridBagConstraints.BOTH;
			gbc_dcFecha.gridx = 2;
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
			gbc_txtRuc.gridx = 2;
			gbc_txtRuc.gridy = 2;
			panelGeneral.add(getTextField_1(), gbc_txtRuc);
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblNombre.insets = new Insets(10, 10, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 3;
			panelGeneral.add(getLblNombre(), gbc_lblNombre);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 1;
			gbc_label.gridy = 3;
			panelGeneral.add(getLabel(), gbc_label);
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(10, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.BOTH;
			gbc_txtNombre.gridx = 2;
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
			gbc_txtTelefono.gridx = 2;
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
			gbc_txtCorreo.gridx = 2;
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
			gbc_txtPaginaWeb.gridx = 2;
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
			gbc_scrollPane.gridx = 2;
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
			gbc_cbxActivo.gridx = 2;
			gbc_cbxActivo.gridy = 8;
			panelGeneral.add(getCbxActivo(), gbc_cbxActivo);
		}
		return panelGeneral;
	}
	public JLabel getLblRuc() {
		if (lblRuc == null) {
			lblRuc = new JLabel("Ruc");
			lblRuc.setForeground(Color.WHITE);
			lblRuc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblRuc.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblRuc.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblRuc;
	}
	public JTextField getTextField_1() {
		if (txtRuc == null) {
			txtRuc = new JTextField();
			txtRuc.addFocusListener(getEventListener());
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
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblNombre;
	}
	public JTextField getTextField_1_1() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.addFocusListener(getEventListener());
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
			lblTelfono.setForeground(Color.WHITE);
			lblTelfono.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblTelfono;
	}
	public JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.addFocusListener(getEventListener());
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
			lblPginaWeb.setForeground(Color.WHITE);
			lblPginaWeb.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblPginaWeb;
	}
	public JTextField getTextField_1_2() {
		if (txtPaginaWeb == null) {
			txtPaginaWeb = new JTextField();
			txtPaginaWeb.addFocusListener(getEventListener());
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
			lblDireccion.setForeground(Color.WHITE);
			lblDireccion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
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
			txtDireccion.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					JTextArea c =(JTextArea) arg0.getComponent();
					c.setBorder(App.borderActivo);
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					JTextArea c =(JTextArea) arg0.getComponent();
					c.setBorder(App.borderDesactivo);
				}
			});
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
			//txtDireccion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtDireccion.setRows(5);
			txtDireccion.setFont(App.fuentePrincipal);
		}
		return txtDireccion;
	}
	public JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID");
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
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
			dataTable._setAnchoColumna(8, 120);	
			dataTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
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
			lblCorreo.setForeground(Color.WHITE);
			lblCorreo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCorreo;
	}
	public JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.addFocusListener(getEventListener());
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
			lblFecha.setForeground(Color.WHITE);
			lblFecha.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblFecha.setAlignmentY(1.0f);
			lblFecha.setAlignmentX(0.5f);
		}
		return lblFecha;
	}
	public JDateChooser getDcFecha() {
		if (dcFecha == null) {
			dcFecha = new JDateChooser();
			((JTextField)dcFecha.getDateEditor().getUiComponent()).addFocusListener(getEventListener());
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
			lblEstado_1.setForeground(Color.WHITE);
			lblEstado_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
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
	public JLabel getLblExigido1() {
		if (lblExigido1 == null) {
			lblExigido1 = new JLabel("*");
			lblExigido1.setToolTipText("El campo fecha es Obligatorio");
			lblExigido1.setForeground(Color.RED);
		}
		return lblExigido1;
	}
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("*");
			label.setToolTipText("El campo nombre es obligatorio");
			label.setForeground(Color.RED);
		}
		return label;
	}
}
