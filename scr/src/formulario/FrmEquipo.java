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
import javax.swing.JTabbedPane;
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

import java.awt.BorderLayout;

import mvc.JDataTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;

import bd.BDEquipo;
import com.toedter.calendar.JDateChooser;

import componente.SincronizarComboModelo;
import componente.SincronizarComboSubModelo;
import componente.SincronizarTabla;
import componente.JInternalToolBarMenu;


import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import objeto.Equipo;
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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmEquipo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalToolBarMenu internalToolBarMenu;
	private JSplitPane pnlContainer;
	private JPanel panelTabla;
	private JPanel panelGeneral;
	private JLabel lblModelo;
	private JComboBox<Modelo> cbxModelo;
	private JLabel lblSerie;
	private JTextField txtSerie;
	private JLabel lblId;
	private JTextField txtId;
	private JScrollPane spTabla;
	private JScrollPane spGeneral;
	private JLabel lblEstado;
	private JDataTable dataTable;
	private JLabel lblFecha;
	private JDateChooser dcFecha;
	private JLabel lblEstado_1;
	private JCheckBox cbxActivo;
	private JSearchTextfield jsBuscarCliente;
	private JLabel lblExigido1;
	private JLabel lblExigido3;
	
	private JTabbedPane tpPrincipal;
	
	private Equipo equipo;
	private JLabel lblMensajeModelo;
	private JLabel lblExigido2;
	private JComboBox<Submodelo> cbxSubmodelo;
	private JLabel lblMensajeSubmodelo;
	private JLabel label;
	private JLabel lblSubmodelo;
	private JPanel panelHistorial;
	
	private ItemListener CargarSubModelos_Listener=new ItemListener() {
		public void itemStateChanged(ItemEvent arg0) {
			//JOptionPane.showMessageDialog(null, arg0.getStateChange());
			if(arg0.getStateChange () == ItemEvent.SELECTED){
				SincronizarComboSubModelo cs = new SincronizarComboSubModelo(cbxSubmodelo, lblMensajeSubmodelo,((Modelo)cbxModelo.getSelectedItem()).getIdmodelo());
				cs.execute();
			}					
		}
	};
	private JScrollPane scrollPaneHistorial;
	private JSearchTextfield searchTextfield;
	private JLabel label_1;

	/**
	 * Create the panel.
	 */
	public FrmEquipo() {
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
	//	setActivo(false);
	//	cargarLista();	
		internalToolBarMenu.getBtnActualizar().doClick();
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
	
	public void cargarLista(){
		dataTable.DAOModelo.ConsultaSQL =  "SELECT idequipo AS ID, DATE_FORMAT( submodelo.fecha, '%d/%m/%Y' ) as Fecha, serie AS 'Serie',submodelo.nombre AS 'Submodelo', modelo.nombre AS 'Modelo', equipo.activo AS Vigente, submodelo.idsubmodelo, modelo.idmodelo FROM equipo Inner Join Submodelo ON equipo.idsubmodelo = submodelo.idsubmodelo INNER JOIN modelo ON modelo.idmodelo=submodelo.idmodelo";
		SincronizarTabla consulta = new SincronizarTabla(dataTable,lblEstado, internalToolBarMenu.getBtnActualizar(),jsBuscarCliente);
		consulta.execute();	
		equipo = new Equipo();			
	}
		
	public void limpiarFormualario(){
		txtId.setText("");
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setText("");
		txtSerie.setText("");		
		cbxActivo.setSelected(false);
		cbxSubmodelo.removeAllItems();
		cbxModelo.removeAllItems();
	}
	
	public void setActivo(Boolean valor){
		//txtId.setEnabled(valor);;
		((JTextField)dcFecha.getDateEditor().getUiComponent()).setEnabled(valor);
		dcFecha.getCalendarButton().setEnabled(valor);
		cbxModelo.setEnabled(valor);
		cbxSubmodelo.setEnabled(valor);
		txtSerie.setEnabled(valor);
		cbxActivo.setEnabled(valor);
	}

	public JInternalToolBarMenu getInternalToolBarMenu() {
		if (internalToolBarMenu == null) {
			internalToolBarMenu = new JInternalToolBarMenu();						
			internalToolBarMenu.setDoubleBuffered(true);
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
					((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();					
					dcFecha.getCalendarButton().doClick();
					
					cbxSubmodelo.removeAllItems();
					
					cbxModelo.removeItemListener(CargarSubModelos_Listener);
					SincronizarComboModelo cm = new SincronizarComboModelo(cbxModelo, lblMensajeModelo);
					cm.execute();
					while(!cm.isDone()){}//OBLIGA A ESPERAR EL RESULTADO DE LA CONSULTA
					cbxModelo.addItemListener(CargarSubModelos_Listener);
					dataTable.setEnabled(false);
				}
			});
			
			internalToolBarMenu.getBtnGuardar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(verificarRequerimientos()){
						
						equipo.setFecha(new Date(dcFecha.getDate().getTime()));
						equipo.setserie(txtSerie.getText());
						equipo.setActivo(cbxActivo.isSelected());
						equipo.setIdsubmodelo( ((Submodelo)cbxSubmodelo.getSelectedItem()).getIdsubmodelo() );
						
						
						if(txtId.getText().isEmpty()){
							if(BDEquipo.guardar(equipo)){
								JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente");
							}
						}else{	
							equipo.setIdequipo(Integer.parseInt(txtId.getText()));
							if(BDEquipo.Modificar(equipo)){
								JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
							}																				
						}
						internalToolBarMenu.getBtnActualizar().doClick();
						
					}else
						JOptionPane.showMessageDialog(null, "Favor verificar los campos obligatorio");
					
				}

				private boolean verificarRequerimientos() {
					try{						
						return (((JTextField)dcFecha.getDateEditor().getUiComponent()).getForeground()==Color.BLACK) && !txtSerie.getText().isEmpty() && cbxModelo.getItemAt(cbxModelo.getSelectedIndex()).getIdmodelo()!=-1 && cbxSubmodelo.getItemAt(cbxSubmodelo.getSelectedIndex()).getIdsubmodelo()!=-1;
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
					dataTable.setEnabled(false);
					cbxModelo.addItemListener(CargarSubModelos_Listener);
					cbxModelo.requestFocus();
				}
			});
			
			internalToolBarMenu.getBtnCancelar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cbxModelo.removeItemListener(CargarSubModelos_Listener);
					internalToolBarMenu.getBtnActualizar().doClick();					
				}
			});
			
			internalToolBarMenu.getBtnActualizar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dataTable.clearSelection();
					jsBuscarCliente._reiniciarComponente();
					limpiarFormualario();					
					setActivo(false);
					cargarLista();
					internalToolBarMenu.getBtnAgregar().setVisible(true);
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnEditar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);	
					dataTable.setEnabled(true);
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
			pnlContainer.setResizeWeight(0.01);
			pnlContainer.setLeftComponent(getPanelTabla());
			pnlContainer.setRightComponent(getSpGeneral());
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
			gbl_panelGeneral.columnWidths = new int[]{80, 30, 200, 25, 80, 0};
			gbl_panelGeneral.rowHeights = new int[] {40, 40, 40, 0, 40, 40, 40};
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
			GridBagConstraints gbc_lblModelo = new GridBagConstraints();
			gbc_lblModelo.insets = new Insets(10, 10, 5, 5);
			gbc_lblModelo.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblModelo.gridx = 0;
			gbc_lblModelo.gridy = 2;
			panelGeneral.add(getLblModelo(), gbc_lblModelo);
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
			GridBagConstraints gbc_lblSubmodelo = new GridBagConstraints();
			gbc_lblSubmodelo.anchor = GridBagConstraints.WEST;
			gbc_lblSubmodelo.insets = new Insets(10, 10, 5, 5);
			gbc_lblSubmodelo.gridx = 0;
			gbc_lblSubmodelo.gridy = 3;
			panelGeneral.add(getLblSubmodelo(), gbc_lblSubmodelo);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 1;
			gbc_label.gridy = 3;
			panelGeneral.add(getLabel(), gbc_label);
			GridBagConstraints gbc_cbxSubmodelo = new GridBagConstraints();
			gbc_cbxSubmodelo.insets = new Insets(10, 0, 5, 5);
			gbc_cbxSubmodelo.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxSubmodelo.gridx = 2;
			gbc_cbxSubmodelo.gridy = 3;
			panelGeneral.add(getCbxSubmodelo(), gbc_cbxSubmodelo);
			GridBagConstraints gbc_lblMensajeSubmodelo = new GridBagConstraints();
			gbc_lblMensajeSubmodelo.insets = new Insets(0, 0, 5, 5);
			gbc_lblMensajeSubmodelo.gridx = 3;
			gbc_lblMensajeSubmodelo.gridy = 3;
			panelGeneral.add(getLblMensajeSubmodelo(), gbc_lblMensajeSubmodelo);
			GridBagConstraints gbc_lblSerie = new GridBagConstraints();
			gbc_lblSerie.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblSerie.insets = new Insets(10, 10, 5, 5);
			gbc_lblSerie.gridx = 0;
			gbc_lblSerie.gridy = 4;
			panelGeneral.add(getLblSerie(), gbc_lblSerie);
			GridBagConstraints gbc_lblExigido3 = new GridBagConstraints();
			gbc_lblExigido3.insets = new Insets(0, 0, 5, 5);
			gbc_lblExigido3.anchor = GridBagConstraints.EAST;
			gbc_lblExigido3.gridx = 1;
			gbc_lblExigido3.gridy = 4;
			panelGeneral.add(getLblExigido3(), gbc_lblExigido3);
			GridBagConstraints gbc_txtSerie = new GridBagConstraints();
			gbc_txtSerie.insets = new Insets(10, 0, 5, 5);
			gbc_txtSerie.fill = GridBagConstraints.BOTH;
			gbc_txtSerie.gridx = 2;
			gbc_txtSerie.gridy = 4;
			panelGeneral.add(getTextField_1_1(), gbc_txtSerie);
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
	public JLabel getLblModelo() {
		if (lblModelo == null) {
			lblModelo = new JLabel("Modelo");
			lblModelo.setForeground(Color.WHITE);
			lblModelo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			lblModelo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			lblModelo.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return lblModelo;
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
						cbxSubmodelo.requestFocus();
					}					
				}
			});
			cbxModelo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			cbxModelo.setFont(App.fuentePrincipal);
		}
		return cbxModelo;
	}
	public JLabel getLblSerie() {
		if (lblSerie == null) {
			lblSerie = new JLabel("N\u00FAmero Serie");
			lblSerie.setForeground(Color.WHITE);
			lblSerie.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		}
		return lblSerie;
	}
	public JTextField getTextField_1_1() {
		if (txtSerie == null) {
			txtSerie = new JTextField();
			txtSerie.addFocusListener(getEventListener());
			txtSerie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						cbxSubmodelo.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						cbxActivo.requestFocus();
					}	
				}
			});
			txtSerie.setFont(App.fuentePrincipal);
			txtSerie.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtSerie.setColumns(10);
		}
		return txtSerie;
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
	
	public JScrollPane getSpGeneral() {
		if (spGeneral == null) {
			spGeneral = new JScrollPane();
			spGeneral.setViewportView(getTpPrincipal());
		}
		return spGeneral;
	}
	
	public JTabbedPane getTpPrincipal() {
		if (tpPrincipal == null) {
			tpPrincipal = new JTabbedPane(JTabbedPane.TOP);
			tpPrincipal.setFont(App.fuentePrincipal);
			//tpPrincipal.setUI(new CWTabbedPaneUI());
			tpPrincipal.addTab("General", null, getPanelGeneral(), null);
			tpPrincipal.addTab("Historial", null, getPanelHistorial(), null);
		}
		return tpPrincipal;
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
			dataTable._setAnchoColumna(3, 250);
			dataTable._setAnchoColumna(4, 200);
			dataTable._setAnchoColumna(5, 200);
			dataTable._setAnchoColumna(6, 0);
			dataTable._setAnchoColumna(7, 0);
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
					txtSerie.setText(dataTable.getValueAt(dataTable.getSelectedRow(), 2).toString());//3 label submodelo , 4 label modelo
					cbxActivo.setSelected(Boolean.parseBoolean(dataTable.getValueAt(dataTable.getSelectedRow(),5).toString()));
					int idModelo = Integer.parseInt(dataTable.getValueAt(dataTable.getSelectedRow(), 7).toString());
										
					/***********Seleccionar el item*****************/	
					
					SincronizarComboModelo cm = new SincronizarComboModelo(cbxModelo, lblMensajeModelo);
					cm.execute();
					while(!cm.isDone()){}//OBLIGA A ESPERAR EL RESULTADO DE LA CONSULTA
					
					for ( int i=0 ;i<cbxModelo.getItemCount(); i++)
						if(cbxModelo.getItemAt(i).getIdmodelo()==idModelo){
							cbxModelo.setSelectedItem( cbxModelo.getItemAt(i));
							break;
						}	
					cbxSubmodelo.removeAllItems();
					SincronizarComboSubModelo cs = new SincronizarComboSubModelo(cbxSubmodelo, lblMensajeSubmodelo, idModelo);
					cs.execute();
					while(!cs.isDone()){}//OBLIGA A ESPERAR EL RESULTADO DE LA CONSULTA
					
					int idSubModelo = Integer.parseInt(dataTable.getValueAt(dataTable.getSelectedRow(), 6).toString());
										
					for ( int i=0 ;i<cbxSubmodelo.getItemCount(); i++)
						if(cbxSubmodelo.getItemAt(i).getIdsubmodelo()==idSubModelo){
							cbxSubmodelo.setSelectedItem( cbxSubmodelo.getItemAt(i));
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
			dcFecha.setFont(App.fuentePrincipal);
			dcFecha.setDateFormatString("dd/MM/yyyy");			
			((JTextField)dcFecha.getDateEditor().getUiComponent()).requestFocus();
			((JTextField)dcFecha.getDateEditor().getUiComponent()).addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
									
					if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						cbxModelo.requestFocus();
					}
				}
			});

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
						txtSerie.requestFocus();
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
	public JComboBox<Submodelo> getCbxSubmodelo() {
		if (cbxSubmodelo == null) {
			cbxSubmodelo = new JComboBox<Submodelo>();
			cbxSubmodelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			cbxSubmodelo.setEnabled(false);
			cbxSubmodelo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			
			cbxSubmodelo.addFocusListener(new FocusAdapter() {
				@SuppressWarnings("unchecked")
				@Override
				public void focusGained(FocusEvent arg0) {
					((JComboBox<Submodelo>) arg0.getComponent()).setBorder(App.borderActivo);
				}
				@SuppressWarnings("unchecked")
				@Override
				public void focusLost(FocusEvent arg0) {
					((JComboBox<Submodelo>) arg0.getComponent()).setBorder(App.borderDesactivo);
				}
			});
			cbxSubmodelo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if(keyCode == KeyEvent.VK_UP){ //Direccion arriba
						cbxModelo.requestFocus();
					}
					
					if(keyCode == KeyEvent.VK_DOWN  || keyCode == KeyEvent.VK_ENTER){ //Direccion arriba
						txtSerie.requestFocus();
					}					
				}
			});
		}
		return cbxSubmodelo;
	}
	public JLabel getLblMensajeSubmodelo() {
		if (lblMensajeSubmodelo == null) {
			lblMensajeSubmodelo = new JLabel("");
			lblMensajeSubmodelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}
		return lblMensajeSubmodelo;
	}
	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("*");
			label.setToolTipText("El campo nombre es obligatorio");
			label.setForeground(Color.RED);
		}
		return label;
	}
	public JLabel getLblSubmodelo() {
		if (lblSubmodelo == null) {
			lblSubmodelo = new JLabel("Sub modelo");
			lblSubmodelo.setForeground(Color.WHITE);
			lblSubmodelo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
			lblSubmodelo.setAlignmentY(1.0f);
			lblSubmodelo.setAlignmentX(0.5f);
		}
		return lblSubmodelo;
	}
	public JPanel getPanelHistorial() {
		if (panelHistorial == null) {
			panelHistorial = new JPanel();
			panelHistorial.setBorder(null);
			panelHistorial.setBackground(App.BackgroudForm);
			GridBagLayout gbl_panelHistorial = new GridBagLayout();
			gbl_panelHistorial.columnWidths = new int[]{0, 0};
			gbl_panelHistorial.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panelHistorial.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelHistorial.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			panelHistorial.setLayout(gbl_panelHistorial);
			GridBagConstraints gbc_searchTextfield = new GridBagConstraints();
			gbc_searchTextfield.insets = new Insets(5, 10, 0, 10);
			gbc_searchTextfield.fill = GridBagConstraints.BOTH;
			gbc_searchTextfield.gridx = 0;
			gbc_searchTextfield.gridy = 0;
			panelHistorial.add(getSearchTextfield(), gbc_searchTextfield);
			GridBagConstraints gbc_scrollPaneHistorial = new GridBagConstraints();
			gbc_scrollPaneHistorial.insets = new Insets(0, 10, 0, 10);
			gbc_scrollPaneHistorial.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneHistorial.gridx = 0;
			gbc_scrollPaneHistorial.gridy = 1;
			panelHistorial.add(getScrollPane_1(), gbc_scrollPaneHistorial);
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.gridheight = 2;
			gbc_label_1.fill = GridBagConstraints.VERTICAL;
			gbc_label_1.gridx = 0;
			gbc_label_1.gridy = 2;
			panelHistorial.add(getLabel_1(), gbc_label_1);
		}
		return panelHistorial;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPaneHistorial == null) {
			scrollPaneHistorial = new JScrollPane();
		}
		return scrollPaneHistorial;
	}
	public JSearchTextfield getSearchTextfield() {
		if (searchTextfield == null) {
			searchTextfield = new JSearchTextfield();
			GridBagLayout gridBagLayout = (GridBagLayout) searchTextfield.getLayout();
			gridBagLayout.rowWeights = new double[]{0.0};
			gridBagLayout.rowHeights = new int[]{40};
			gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
			gridBagLayout.columnWidths = new int[]{0, 20, 20};
		}
		return searchTextfield;
	}
	public JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("New label");
			label_1.setFont(App.fuentePrincipal);
		}
		return label_1;
	}
}
