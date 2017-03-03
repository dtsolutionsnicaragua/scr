package formulario;

import igui.App;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DropMode;
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
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import mvc.JDataTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import bd.BDMenu;
import bd.BDRol;

import com.toedter.calendar.JDateChooser;

import componente.EditorRol;
import componente.RolTreeCellEditor;
import componente.SincronizarTabla;
import componente.JInternalToolBarMenu;
import componente.RenderedComponentVisorRol;

import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import objeto.Menu;
import objeto.Rol;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import componente.JSearchTextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

import javax.swing.JTree;
import javax.swing.JTextArea;

public class FrmRol extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalToolBarMenu internalToolBarMenu;
	private JSplitPane pnlContainer;
	private JPanel panelTabla;
	private JPanel panelGeneral;
	private JLabel lblNombre;
	private JTextField txtNombre;
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
	private JLabel label;

	private Rol rol;
	private JTabbedPane tpPrincipal;
	private JPanel panelHistoria;
	private JTree tree;
	private JLabel lblDescripcion;
	private JScrollPane jsDescripcion;
	private JTextArea txtDescripcion;
	private DefaultMutableTreeNode root;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public FrmRol() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initComponents();
			}
		});
	}

	private void initComponents() {
		setVisible(true);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 40, 60, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
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
		// panelGeneral.setVisible(false);
		// panelTabla.setVisible(true);
		setActivo(false);
		cargarListaRoles();
		cargarListaPermiso();
		// panelGeneral.setVisible(false);
		// panelTabla.setVisible(true);
	}

	public FocusAdapter getEventListener() {
		FocusAdapter mouseListener = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				JTextField c = (JTextField) arg0.getComponent();
				c.setBorder(App.borderActivo);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				JTextField c = (JTextField) arg0.getComponent();
				c.setBorder(App.borderDesactivo);
			}
		};
		return mouseListener;
	}

	public FocusAdapter getEventListenerTextArea() {
		FocusAdapter mouseListener = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				JTextArea c = (JTextArea) arg0.getComponent();
				c.setBorder(App.borderActivo);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				JTextArea c = (JTextArea) arg0.getComponent();
				c.setBorder(App.borderDesactivo);
			}
		};
		return mouseListener;
	}

	public void cargarListaRoles() {
		dataTable.DAOModelo.ConsultaSQL = "SELECT idrol AS ID,DATE_FORMAT( fecha, '%d/%m/%Y' ) as Fecha, Nombre AS 'Rol',descripcion as 'Descripcion', activo as Vigente FROM rol";
		SincronizarTabla consulta = new SincronizarTabla(dataTable, lblEstado,
				internalToolBarMenu.getBtnActualizar(), jsBuscarCliente);
		consulta.execute();
		rol = new Rol();
	}

	public void cargarListaPermiso() {
		ArrayList<Menu> DAOmenus = BDMenu.getMenus();
		
		DefaultMutableTreeNode nodoActual;
		DefaultMutableTreeNode nodoPadre ;
		root.removeAllChildren();
		
		for (Menu menuActual : DAOmenus) {
			EditorRol visorActual = new EditorRol();
			visorActual.setMenu(menuActual);
			
			nodoActual = new DefaultMutableTreeNode(visorActual);
			nodoPadre = buscarPadre2(root, menuActual.getNivel());
			nodoPadre.add(nodoActual);
		}
		
		expandTree(tree);
		tree.setCellEditor(new RolTreeCellEditor());
		tree.setCellRenderer(new RenderedComponentVisorRol());
		tree.setEditable(true);
        tree.setDropMode(DropMode.ON_OR_INSERT);   
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);   
    

	}
	
	private void expandTree(JTree tree) 
	{   
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();   
        @SuppressWarnings("rawtypes")
		Enumeration e = root.breadthFirstEnumeration();   
        while(e.hasMoreElements()) {   
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();   
            if(node.isLeaf()) continue;   
            int row = tree.getRowForPath(new TreePath(node.getPath()));   
            tree.expandRow(row);   
        }   
    }

	private DefaultMutableTreeNode buscarPadre2(DefaultMutableTreeNode nodoActual, int indicePadre) {
		DefaultMutableTreeNode rootActual = nodoActual;
		@SuppressWarnings("rawtypes")
		Enumeration e = rootActual.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			EditorRol jem = (EditorRol) node.getUserObject();
			if (jem.getMenu().getIdmenu() == indicePadre)
				return node;
		}
		return nodoActual;
	}


	public void limpiarFormualario() {
		txtId.setText("");
		((JTextField) dcFecha.getDateEditor().getUiComponent()).setText("");
		txtNombre.setText("");
		txtDescripcion.setText("");
		cbxActivo.setSelected(false);
	}

	public void setActivo(Boolean valor) {
		// txtId.setEnabled(valor);;
		((JTextField) dcFecha.getDateEditor().getUiComponent())
				.setEnabled(valor);
		dcFecha.getCalendarButton().setEnabled(valor);
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

			internalToolBarMenu.getBtnAgregar().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							limpiarFormualario();
							dataTable.clearSelection();
							setActivo(true);
							cbxActivo.setSelected(true);
							internalToolBarMenu.getBtnGuardar()
									.setVisible(true);
							internalToolBarMenu.getBtnCancelar().setVisible(
									true);
							internalToolBarMenu.getBtnAgregar().setVisible(
									false);
							internalToolBarMenu.getBtnEditar()
									.setVisible(false);
							internalToolBarMenu.validate();
							// internalToolBarMenu.repaint();
							((JTextField) dcFecha.getDateEditor()
									.getUiComponent()).requestFocus();
							dcFecha.getCalendarButton().doClick();
						}
					});

			internalToolBarMenu.getBtnGuardar().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (verificarRequerimientos()) {

								rol.setFecha(new Date(dcFecha.getDate().getTime()));
								rol.setNombre(txtNombre.getText());
								rol.setDescripcion(txtDescripcion.getText());
								rol.setActivo(cbxActivo.isSelected());

								if (txtId.getText().isEmpty()) {
									if (BDRol.guardar(rol)) {
										if(rol.getIdRol()!=-1){
											JOptionPane
											.showMessageDialog(null,
													"Guardando Permisos");
										}
										
									}
								} else {
									rol.setIdRol(Integer.parseInt(txtId
											.getText()));
									if (BDRol.Modificar(rol)) {
										JOptionPane
												.showMessageDialog(null,
														"Registro Modificado Satisfactoriamente");
									}
								}
								internalToolBarMenu.getBtnActualizar()
										.doClick();

							} else
								JOptionPane
										.showMessageDialog(null,
												"Favor verificar los campos obligatorio");

						}

						private boolean verificarRequerimientos() {
							try {

								return (((JTextField) dcFecha.getDateEditor()
										.getUiComponent()).getForeground() == Color.BLACK)
										&& !txtNombre.getText().isEmpty();
							} catch (Exception ex) {
							}
							return false;
						}
					});

			internalToolBarMenu.getBtnEditar().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							setActivo(true);
							internalToolBarMenu.getBtnGuardar()
									.setVisible(true);
							internalToolBarMenu.getBtnCancelar().setVisible(
									true);
							internalToolBarMenu.getBtnAgregar().setVisible(
									false);
							internalToolBarMenu.getBtnEditar()
									.setVisible(false);
							internalToolBarMenu.validate();
							// internalToolBarMenu.repaint();
							txtNombre.requestFocus();
						}
					});

			internalToolBarMenu.getBtnCancelar().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							limpiarFormualario();
							dataTable.clearSelection();
							setActivo(false);
							internalToolBarMenu.getBtnAgregar()
									.setVisible(true);
							internalToolBarMenu.getBtnGuardar().setVisible(
									false);
							internalToolBarMenu.getBtnEditar()
									.setVisible(false);
							internalToolBarMenu.getBtnCancelar().setVisible(
									false);
							internalToolBarMenu.validate();
							// internalToolBarMenu.repaint();
						}
					});

			internalToolBarMenu.getBtnActualizar().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dataTable.clearSelection();
							jsBuscarCliente._reiniciarComponente();
							limpiarFormualario();
							setActivo(false);
							cargarListaRoles();
							cargarListaPermiso();
							internalToolBarMenu.getBtnAgregar()
									.setVisible(true);
							internalToolBarMenu.getBtnGuardar().setVisible(
									false);
							internalToolBarMenu.getBtnEditar()
									.setVisible(false);
							internalToolBarMenu.getBtnCancelar().setVisible(
									false);
							// panelGeneral.setVisible(false);
							// panelTabla.setVisible(true);
							internalToolBarMenu.validate();
							// internalToolBarMenu.repaint();
						}
					});

			// internalToolBarMenu.setBorder(null);
		}
		return internalToolBarMenu;
	}

	public JSplitPane getPnlContainer() {
		if (pnlContainer == null) {
			pnlContainer = new JSplitPane();
			pnlContainer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			pnlContainer.setResizeWeight(0.4);
			pnlContainer.setLeftComponent(getPanelTabla());
			pnlContainer.setRightComponent(getTpPrincipal());
		}
		return pnlContainer;
	}

	public JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
			panelTabla.setLayout(new BorderLayout(0, 0));
			panelTabla.add(getSpTabla(), BorderLayout.CENTER);
			panelTabla.add(getLblEstado(), BorderLayout.SOUTH);
			panelTabla.add(getJsBuscarCliente(), BorderLayout.NORTH);
			panelTabla.setVisible(true);
		}
		return panelTabla;
	}

	public JTabbedPane getTpPrincipal() {
		if (tpPrincipal == null) {
			tpPrincipal = new JTabbedPane(JTabbedPane.TOP);
			tpPrincipal.setFont(App.fuentePrincipal);
			// tpPrincipal.setUI(new CWTabbedPaneUI());
			tpPrincipal.addTab("General", null, getPanelGeneral(), null);
			tpPrincipal.addTab("Permisos", null, getPanelHistorial(), null);
		}
		return tpPrincipal;
	}

	public JPanel getPanelHistorial() {
		if (panelHistoria == null) {
			panelHistoria = new JPanel();
			panelHistoria.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent arg0) {
					paintComponents(getGraphics());
				}
			});
			panelHistoria.setBackground(SystemColor.activeCaption);
			panelHistoria.setBorder(null);
			panelHistoria.setLayout(new BorderLayout(0, 0));
			//panelHistoria.add(getScrollPane(), BorderLayout.NORTH);
			panelHistoria.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelHistoria;
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
			gbl_panelGeneral.columnWidths = new int[] { 80, 10, 150, 150, 0 };
			gbl_panelGeneral.rowHeights = new int[] { 40, 40, 30, 120, 40, 0 };
			gbl_panelGeneral.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
					Double.MIN_VALUE };
			gbl_panelGeneral.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
					0.0, Double.MIN_VALUE };
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
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblNombre.insets = new Insets(10, 10, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 2;
			panelGeneral.add(getLblNombre(), gbc_lblNombre);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.gridx = 1;
			gbc_label.gridy = 2;
			panelGeneral.add(getLabel(), gbc_label);
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(10, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.BOTH;
			gbc_txtNombre.gridx = 2;
			gbc_txtNombre.gridy = 2;
			panelGeneral.add(getTextFieldNombre(), gbc_txtNombre);
			GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
			gbc_lblDescripcion.anchor = GridBagConstraints.NORTH;
			gbc_lblDescripcion.insets = new Insets(10, 10, 5, 5);
			gbc_lblDescripcion.gridx = 0;
			gbc_lblDescripcion.gridy = 3;
			panelGeneral.add(getLblDescripcion(), gbc_lblDescripcion);
			GridBagConstraints gbc_jsDescripcion = new GridBagConstraints();
			gbc_jsDescripcion.gridwidth = 2;
			gbc_jsDescripcion.insets = new Insets(10, 0, 5, 0);
			gbc_jsDescripcion.fill = GridBagConstraints.BOTH;
			gbc_jsDescripcion.gridx = 2;
			gbc_jsDescripcion.gridy = 3;
			panelGeneral.add(getJsDescripcion(), gbc_jsDescripcion);
			GridBagConstraints gbc_lblEstado_1 = new GridBagConstraints();
			gbc_lblEstado_1.anchor = GridBagConstraints.WEST;
			gbc_lblEstado_1.insets = new Insets(10, 10, 0, 5);
			gbc_lblEstado_1.gridx = 0;
			gbc_lblEstado_1.gridy = 4;
			panelGeneral.add(getLblEstado_1(), gbc_lblEstado_1);
			GridBagConstraints gbc_cbxActivo = new GridBagConstraints();
			gbc_cbxActivo.anchor = GridBagConstraints.WEST;
			gbc_cbxActivo.insets = new Insets(0, 0, 0, 5);
			gbc_cbxActivo.gridx = 2;
			gbc_cbxActivo.gridy = 4;
			panelGeneral.add(getCbxActivo(), gbc_cbxActivo);
		}
		return panelGeneral;
	}

	public JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		}
		return lblNombre;
	}

	public JTextField getTextFieldNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.addFocusListener(getEventListener());
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if (keyCode == KeyEvent.VK_UP) { // Direccion arriba
						((JTextField) dcFecha.getDateEditor().getUiComponent())
								.requestFocus();
					}

					if (keyCode == KeyEvent.VK_DOWN
							|| keyCode == KeyEvent.VK_ENTER) { // Direccion
																// arriba
						txtDescripcion.requestFocus();
					}
				}
			});
			txtNombre.setFont(App.fuentePrincipal);
			txtNombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
			txtNombre.setColumns(10);
		}
		return txtNombre;
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
			txtId.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
					null, null, null));
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
			dataTable._setAnchoColumna(2, 200);
			dataTable._setAnchoColumna(3, 400);
			dataTable._setAnchoColumna(4, 0);
			dataTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			ListSelectionModel rowSelMod = dataTable.getSelectionModel();
			rowSelMod.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
					if (evt.getValueIsAdjusting())
						return;
					internalToolBarMenu.getBtnGuardar().setVisible(false);
					internalToolBarMenu.getBtnCancelar().setVisible(false);
					setActivo(false);
					if (((ListSelectionModel) evt.getSource())
							.isSelectionEmpty()) { // Si no se ha seleccionado
													// ninguna fila
						internalToolBarMenu.getBtnEditar().setVisible(false);
						internalToolBarMenu.getBtnAgregar().setVisible(true);
						return;
					} else {
						internalToolBarMenu.getBtnEditar().setVisible(true);
						internalToolBarMenu.getBtnAgregar().setVisible(true);
					}

					txtId.setText(dataTable.getValueAt(
							dataTable.getSelectedRow(), 0).toString());
					try {
						dcFecha.setDate(App.Fecha.parse(dataTable.getValueAt(
								dataTable.getSelectedRow(), 1).toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					txtNombre.setText(dataTable.getValueAt(
							dataTable.getSelectedRow(), 2).toString());
					txtDescripcion.setText(dataTable.getValueAt(
							dataTable.getSelectedRow(), 3).toString());
					cbxActivo.setSelected(Boolean.parseBoolean(dataTable
							.getValueAt(dataTable.getSelectedRow(), 4)
							.toString()));
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
			((JTextField) dcFecha.getDateEditor().getUiComponent())
					.addFocusListener(getEventListener());
			((JTextField) dcFecha.getDateEditor().getUiComponent())
					.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent arg0) {
							int keyCode = arg0.getKeyCode();

							if (keyCode == KeyEvent.VK_DOWN
									|| keyCode == KeyEvent.VK_ENTER) { // Direccion
																		// arriba
								txtNombre.requestFocus();
							}
						}
					});
			dcFecha.setFont(App.fuentePrincipal);
			dcFecha.setDateFormatString("dd/MM/yyyy");
			((JTextField) dcFecha.getDateEditor().getUiComponent())
					.requestFocus();
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
					if (keyCode == KeyEvent.VK_UP) { // Direccion arriba
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

	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel("*");
			label.setToolTipText("El campo nombre es obligatorio");
			label.setForeground(Color.RED);
		}
		return label;
	}

	public JTree getJtPermiso() {
		if (tree == null) {
			tree = new JTree();
			tree.setFont(App.fuentePrincipal);	
			
			Menu MenuRoot =new Menu();
			MenuRoot.setNombre("Definir Permisos");
			EditorRol jem = new EditorRol();
			jem.setMenu(MenuRoot);
			
			root = new DefaultMutableTreeNode(jem);
			DefaultTreeModel modelo = new DefaultTreeModel(root);
			tree.setModel(modelo);
			tree.setRowHeight(35);
			
		}
		return tree;
	}

	public JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setForeground(Color.WHITE);
			lblDescripcion.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		}
		return lblDescripcion;
	}

	public JScrollPane getJsDescripcion() {
		if (jsDescripcion == null) {
			jsDescripcion = new JScrollPane();
			jsDescripcion.setViewportView(getTxtDescripcion());
		}
		return jsDescripcion;
	}

	public JTextArea getTxtDescripcion() {
		if (txtDescripcion == null) {
			txtDescripcion = new JTextArea();
			txtDescripcion.setFont(App.fuentePrincipal);
			txtDescripcion.setLineWrap(true);
			txtDescripcion.addFocusListener(getEventListenerTextArea());
			txtDescripcion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					int keyCode = arg0.getKeyCode();
					if (keyCode == KeyEvent.VK_UP) { // Direccion arriba
						txtNombre.requestFocus();
					}

					if (keyCode == KeyEvent.VK_DOWN
							|| keyCode == KeyEvent.VK_ENTER) { // Direccion
																// arriba
						cbxActivo.requestFocus();
					}
				}
			});
		}
		return txtDescripcion;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getJtPermiso());
		}
		return scrollPane;
	}
}
