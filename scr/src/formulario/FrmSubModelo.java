package formulario;

import igui.App;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JComboBox;
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

import bd.BDSubmodelo;

import com.toedter.calendar.JDateChooser;

import componente.SincronizarComboModelo;
import componente.SincronizarTabla;
import componente.JInternalToolBarMenu;

import java.awt.SystemColor;
import java.awt.TrayIcon.MessageType;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import objeto.Marca;
import objeto.Modelo;
import objeto.Submodelo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import componente.JSearchTextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

public class FrmSubModelo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalToolBarMenu internalToolBarMenu;
	private JSplitPane pnlContainer;
	private JPanel panelTabla;
	private JPanel panelGeneral;
	private JLabel lblRuc;
	private JComboBox<Modelo> cbxModelo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblDireccion;
	private JScrollPane scrollPane;
	private JTextArea txtDescripcion;
	private JLabel lblId;
	private JTextField txtId;
	private JScrollPane spTabla;
	private JLabel lblEstado;
	private JDataTable dataTable;
	private JLabel lblFecha;
	private JDateChooser dcFecha;
	private JLabel lblEstado_1;
	private JCheckBox cbxActivo;
	private JSearchTextfield jsBuscarCliente;
	private JLabel lblExigido1;
	private JLabel lblExigido3;
	
	private Submodelo submodelo;
	private JLabel lblMensajeModelo;
	private JLabel lblExigido2;

	/**
	 * Create the panel.
	 */
	public FrmSubModelo() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	initComponents();	
		    }
		  });		
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
		add(getInternalToolBarMenu(), gbc_internalToolBarMenu);
		GridBagConstraints gbc_pnlContainer = new GridBagConstraints();
		gbc_pnlContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlContainer.gridx = 0;
		gbc_pnlContainer.gridy = 1;
		add(getPnlContainer(), gbc_pnlContainer);
		//panelGeneral.setVisible(false);
		//panelTabla.setVisible(true);
		setActivo(false);
		cargarListaClientes();	
		//panelGeneral.setVisible(false);
		//panelTabla.setVisible(true);
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
		dataTable.DAOModelo.ConsultaSQL =  "SELECT idsubmodelo AS ID,DATE_FORMAT( submodelo.fecha, '%d/%m/%Y' ) as Fecha,modelo.Nombre As 'Modelo', submodelo.Nombre AS 'Sub Modelo',submodelo.descripcion as 'Descripcion', submodelo.activo as Vigente,submodelo.idmodelo FROM submodelo INNER JOIN modelo ON submodelo.idmodelo = modelo.idmodelo";
		SincronizarTabla consulta = new SincronizarTabla(dataTable,lblEstado, internalToolBarMenu.getBtnActualizar(),jsBuscarCliente);
		consulta.execute();			
		SincronizarComboModelo cm = new SincronizarComboModelo(cbxModelo, lblMensajeModelo);
		cm.execute();
		submodelo = new Submodelo();	
		
	}
	
	public void limpiarFormualario(){
		txtId.setText("");
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setText("");
		//txtRuc.setText("");
		txtNombre.setText("");		
		txtDescripcion.setText("");
		cbxActivo.setSelected(false);
	}
	
	public void setActivo(Boolean valor){
		//txtId.setEnabled(valor);;
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setEnabled(valor);
		dcFecha.getCalendarButton().setEnabled(valor);
		cbxModelo.setEnabled(valor);
		txtNombre.setEnabled(valor);
		txtDescripcion.setEnabled(valor);
		cbxActivo.setEnabled(valor);
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
					limpiarFormualario();
					dataTable.clearSelection();
					setActivo(true);
					cbxActivo.setSelected(true);
					internalToolBarMenu.getBtnGuardar().setVisible(true);
					internalToolBarMenu.getBtnCancelar().setVisible(true);
					internalToolBarMenu.getBtnAgregar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					//panelGeneral.setVisible(true);
					//panelTabla.setVisible(false);
					//internalToolBarMenu.paintComponents(internalToolBarMenu.getGraphics());
					internalToolBarMenu.validate();
					//internalToolBarMenu.repaint();
					//((JTextField)dcFecha.getDateEditor().getUiComponent()).paintComponents(getGraphics());
					((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();					
					dcFecha.getCalendarButton().doClick();
				}
			});
			
			internalToolBarMenu.getBtnGuardar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(verificarRequerimientos()){
						
						submodelo.setFecha(new Date(dcFecha.getDate().getTime()));
						submodelo.setNombre(txtNombre.getText());
						submodelo.setDescripcion(txtDescripcion.getText());
						submodelo.setActivo(cbxActivo.isSelected());
						submodelo.setIdmodelo( ((Modelo)cbxModelo.getSelectedItem()).getIdmodelo() );
						
						if(txtId.getText().isEmpty()){
							if(BDSubmodelo.guardar(submodelo)){
								JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente" , "Datos Guardado", JOptionPane.INFORMATION_MESSAGE);
							}
						}else{	
							submodelo.setIdsubmodelo(Integer.parseInt(txtId.getText()));
							if(BDSubmodelo.Modificar(submodelo)){
								JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente" , "Datos Guardado", JOptionPane.INFORMATION_MESSAGE);
							}																				
						}
						internalToolBarMenu.getBtnActualizar().doClick();
						
					}else
						JOptionPane.showMessageDialog(null, "Favor verificar los campos obligatorio" , "Datos Incompletos", JOptionPane.ERROR_MESSAGE);
						//JOptionPane.showMessageDialog(null, "Favor verificar los campos obligatorio");
					
				}

				private boolean verificarRequerimientos() {
					try{
											
						return (((JTextField)dcFecha.getDateEditor().getUiComponent()).getForeground()==Color.BLACK) && !txtNombre.getText().isEmpty() && cbxModelo.getItemAt(cbxModelo.getSelectedIndex()).getIdmodelo()!=-1;
					}catch(Exception ex){
					}
					return false;
				}
			});
			
			internalToolBarMenu.getBtnEditar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setActivo(true);
					internalToolBarMenu.getBtnGuardar().setVisible(true);
					internalToolBarMenu.getBtnCancelar().setVisible(true);
					internalToolBarMenu.getBtnAgregar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					//panelGeneral.setVisible(true);
					//panelTabla.setVisible(false);
					internalToolBarMenu.validate();
					//internalToolBarMenu.repaint();
					//internalToolBarMenu.paintImmediately(internalToolBarMenu.getBounds());
					cbxModelo.requestFocus();
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
					//panelGeneral.setVisible(false);
					//panelTabla.setVisible(true);
					internalToolBarMenu.validate();
					//internalToolBarMenu.repaint();
					//internalToolBarMenu.paintImmediately(internalToolBarMenu.getBounds());
					/*internalToolBarMenu.getBtnActualizar().setVisible(false);
					internalToolBarMenu.getBtnLimpiar().setText("Cancelar");
					internalToolBarMenu.getBtnLimpiar().setIcon(new ImageIcon(JInternalToolBarMenu.class.getResource("/icon/general/eliminar.png")));
					((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();*/
					//dcFecha.getCalendarButton().doClick();
				}
			});
			
			internalToolBarMenu.getBtnActualizar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dataTable.clearSelection();
					jsBuscarCliente._reiniciarComponente();
					limpiarFormualario();					
					setActivo(false);
					cargarListaClientes();
					internalToolBarMenu.getBtnAgregar().setVisible(true);
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);	
					//panelGeneral.setVisible(false);
					//panelTabla.setVisible(true);
					internalToolBarMenu.validate();
					//internalToolBarMenu.repaint();
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
			pnlContainer.setResizeWeight(0.4);
			pnlContainer.setLeftComponent(getPanelTabla());
			pnlContainer.setRightComponent(getPanelGeneral());
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
			panelGeneral.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					paintComponents(getGraphics());
				}
			});
			panelGeneral.setBackground(App.BackgroudForm);
			panelGeneral.setBorder(null);
			GridBagLayout gbl_panelGeneral = new GridBagLayout();
			gbl_panelGeneral.columnWidths = new int[]{80, 30, 200, 25, 150, 0};
			gbl_panelGeneral.rowHeights = new int[] {40, 40, 40, 40, 80, 40, 40};
			gbl_panelGeneral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelGeneral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			GridBagConstraints gbc_lblExigido2 = new GridBagConstraints();
			gbc_lblExigido2.insets = new Insets(0, 0, 5, 5);
			gbc_lblExigido2.anchor = GridBagConstraints.EAST;
			gbc_lblExigido2.gridx = 1;
			gbc_lblExigido2.gridy = 2;
			panelGeneral.add(getLblExigido2(), gbc_lblExigido2);
			GridBagConstraints gbc_cbxModelo = new GridBagConstraints();
			gbc_cbxModelo.insets = new Insets(10, 0, 5, 5);
			gbc_cbxModelo.fill = GridBagConstraints.BOTH;
			gbc_cbxModelo.gridx = 2;
			gbc_cbxModelo.gridy = 2;
			panelGeneral.add(getcbxModelo(), gbc_cbxModelo);
			GridBagConstraints gbc_lblMensajeModelo = new GridBagConstraints();
			gbc_lblMensajeModelo.anchor = GridBagConstraints.WEST;
			gbc_lblMensajeModelo.insets = new Insets(0, 0, 5, 5);
			gbc_lblMensajeModelo.gridx = 3;
			gbc_lblMensajeModelo.gridy = 2;
			panelGeneral.add(getLblMensajeModelo(), gbc_lblMensajeModelo);
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblNombre.insets = new Insets(10, 10, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 3;
			panelGeneral.add(getLblNombre(), gbc_lblNombre);
			GridBagConstraints gbc_lblExigido3 = new GridBagConstraints();
			gbc_lblExigido3.insets = new Insets(0, 0, 5, 5);
			gbc_lblExigido3.anchor = GridBagConstraints.EAST;
			gbc_lblExigido3.gridx = 1;
			gbc_lblExigido3.gridy = 3;
			panelGeneral.add(getLblExigido3(), gbc_lblExigido3);
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 3;
			gbc_txtNombre.insets = new Insets(10, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.BOTH;
			gbc_txtNombre.gridx = 2;
			gbc_txtNombre.gridy = 3;
			panelGeneral.add(getTextField_1_1(), gbc_txtNombre);
			GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
			gbc_lblDireccion.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblDireccion.insets = new Insets(10, 10, 5, 5);
			gbc_lblDireccion.gridx = 0;
			gbc_lblDireccion.gridy = 4;
			panelGeneral.add(getLblDireccion(), gbc_lblDireccion);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(10, 0, 10, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 2;
			gbc_scrollPane.gridy = 4;
			panelGeneral.add(getScrollPane(), gbc_scrollPane);
			GridBagConstraints gbc_lblEstado_1 = new GridBagConstraints();
			gbc_lblEstado_1.anchor = GridBagConstraints.WEST;
			gbc_lblEstado_1.insets = new Insets(10, 10, 0, 5);
			gbc_lblEstado_1.gridx = 0;
			gbc_lblEstado_1.gridy = 5;
			panelGeneral.add(getLblEstado_1(), gbc_lblEstado_1);
			GridBagConstraints gbc_cbxActivo = new GridBagConstraints();
			gbc_cbxActivo.anchor = GridBagConstraints.WEST;
			gbc_cbxActivo.insets = new Insets(0, 0, 0, 5);
			gbc_cbxActivo.gridx = 2;
			gbc_cbxActivo.gridy = 5;
			panelGeneral.add(getCbxActivo(), gbc_cbxActivo);
		}
		return panelGeneral;
	}
	public JLabel getLblRuc() {
		if (lblRuc == null) {
			lblRuc = new JLabel("Modelo");
			lblRuc.setForeground(Color.WHITE);
			lblRuc.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			lblRuc.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblRuc.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblRuc;
	}
	public JComboBox<Modelo> getcbxModelo() {
		if (cbxModelo == null) {
			cbxModelo = new JComboBox<Modelo>();
			cbxModelo.addFocusListener(new FocusAdapter() {
				@SuppressWarnings("unchecked")
				@Override
				public void focusGained(FocusEvent arg0) {
					((JComboBox<Marca>) arg0.getComponent()).setBorder(App.borderActivo);
				}
				@SuppressWarnings("unchecked")
				@Override
				public void focusLost(FocusEvent arg0) {
					((JComboBox<Marca>) arg0.getComponent()).setBorder(App.borderDesactivo);
				}
			});
			cbxModelo.addKeyListener(new KeyAdapter() {
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
			cbxModelo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			cbxModelo.setFont(App.fuentePrincipal);
		}
		return cbxModelo;
	}
	public JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
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
						cbxModelo.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtDescripcion.requestFocus();
					}	
				}
			});
			txtNombre.setFont(App.fuentePrincipal);
			txtNombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	public JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Descripci\u00F3n");
			lblDireccion.setForeground(Color.WHITE);
			lblDireccion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
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
		if (txtDescripcion == null) {
			txtDescripcion = new JTextArea();
			txtDescripcion.addFocusListener(new FocusAdapter() {
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
			txtDescripcion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						txtNombre.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						cbxActivo.requestFocus();
					}
				}
			});
			txtDescripcion.setLineWrap(true);
			//txtDireccion.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtDescripcion.setRows(5);
			txtDescripcion.setFont(App.fuentePrincipal);
		}
		return txtDescripcion;
	}
	public JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID");
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
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
			dataTable._setAnchoColumna(5, 0);
			dataTable._setAnchoColumna(6, 0);
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
					txtNombre.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 3).toString());
					txtDescripcion.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 4).toString());
					cbxActivo.setSelected(Boolean.parseBoolean(dataTable.getValueAt(dataTable.getSelectedRow(),5).toString()));
					int idModelo = Integer.parseInt(dataTable.getValueAt(dataTable.getSelectedRow(), 6).toString());
										
					/***********Seleccionar el item*****************/		
					
					for ( int i=0 ;i<cbxModelo.getItemCount(); i++)
						if(cbxModelo.getItemAt(i).getIdmodelo()==idModelo){
							cbxModelo.setSelectedItem( cbxModelo.getItemAt(i));
							break;
						}	
				}
			});
			
		}
		return dataTable;
	}
	
	public JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setForeground(Color.WHITE);
			lblFecha.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
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
						cbxModelo.requestFocus();
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
			lblEstado_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
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
						txtDescripcion.requestFocus();
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
	public JLabel getLblExigido3() {
		if (lblExigido3 == null) {
			lblExigido3 = new JLabel("*");
			lblExigido3.setToolTipText("El campo nombre es obligatorio");
			lblExigido3.setForeground(Color.RED);
		}
		return lblExigido3;
	}
	public JLabel getLblMensajeModelo() {
		if (lblMensajeModelo == null) {
			lblMensajeModelo = new JLabel("");
			lblMensajeModelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblMensajeModelo;
	}
	public JLabel getLblExigido2() {
		if (lblExigido2 == null) {
			lblExigido2 = new JLabel("*");
			lblExigido2.setToolTipText("El campo nombre es obligatorio");
			lblExigido2.setForeground(Color.RED);
		}
		return lblExigido2;
	}
}
