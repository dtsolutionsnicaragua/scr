package igui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;

import formulario.FrmCliente;
import formulario.FrmEquipo;
import formulario.FrmMarca;
import formulario.FrmModelo;
import formulario.FrmRepuesto;
import formulario.FrmRol;
import formulario.FrmSubModelo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.EtchedBorder;

import layout.PlasticTabbedPaneUI;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 7048211688020447955L;
	private JPanel contentPane;
	private JMenuBar MnuBar;
	private JMenu MnuMantenimiento;
	private JMenuItem MnuItemCliente;
	private JMenuItem MnuItemRepresentante;
	private JMenuItem MnuItemGarantia;
	private JMenuItem MnuItemRepuesto;
	private JMenu MnuItemEquipo;
	private JMenuItem MnuItemMarca;
	private JMenuItem MnuItemModelo;
	private JMenuItem MnuItemSubModelo;
	private JMenuItem MnuItemSerie;
	private JMenuItem MnuItemCompatibilidad;
	private JSeparator separador1;
	private JMenu MnuAdministrador;
	private JMenuItem MnuItemUsuario;
	private JMenuItem MnuItemRoles;
	private JMenuItem MnuItemConeccion;
	private JMenu MnuDocumento;
	private JMenuItem MnuItemFactura;
	private JMenuItem MnuItemIngreso;
	private JMenuItem MnuItemDiagnostico;
	private JMenuItem MnuReparacion;
	private JMenuItem MnuItemSalida;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JMenu MnuOpcion;
	private JMenuItem MnuItemConfigurarCuenta;
	private JMenuItem MnuItemCerrarSesion;
	private JMenuItem MnuItemSalir;
	private JTabbedPane tpPrincipal;
	private JPopupMenu popupMenu_2;

	public static void main(String[] args) {
		try {
			//javax.swing.plaf.nimbus.NimbusLookAndFeel
			//com.sun.java.swing.plaf.windows.WindowsLookAndFeel
			//com.apple.laf.AquaLookAndFeel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setTitle("Sistema de Reparaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 332);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTpPrincipal(), BorderLayout.CENTER);

	}

	public JMenuBar getMenuBar_1() {
		if (MnuBar == null) {
			MnuBar = new JMenuBar();
			MnuBar.add(getMnuMantenimiento());
			MnuBar.add(getMnuDocumento());
			MnuBar.add(getMnuAdministrador());
			MnuBar.add(getMnuOpcion());
		}
		return MnuBar;
	}
	
	public JMenu getMnuMantenimiento() {
		if (MnuMantenimiento == null) {
			MnuMantenimiento = new JMenu(Messages.getString("FrmPrincipal.MnuMantenimiento.text")); //$NON-NLS-1$
			MnuMantenimiento.setMnemonic('m');
			MnuMantenimiento.setFont(App.fuentePrincipal);
			MnuMantenimiento.add(getMnuItemCliente());
			MnuMantenimiento.add(getMnuItemRepresentante());
			MnuMantenimiento.add(getMnuItemGarantia());
			MnuMantenimiento.add(getMnuItemEquipo());
		}
		return MnuMantenimiento;
	}
	
	public JMenuItem getMnuItemCliente() {
		if (MnuItemCliente == null) {
			MnuItemCliente = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemCliente.text")); //$NON-NLS-1$
			MnuItemCliente.setFont(App.fuentePrincipal);
			MnuItemCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {			
					tpPrincipal.addTab("Catalogo de Cliente", null, new FrmCliente(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);					
				}
			});
			MnuItemCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		}
		return MnuItemCliente;
	}
	
	public JMenuItem getMnuItemRepresentante() {
		if (MnuItemRepresentante == null) {
			MnuItemRepresentante = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemRepresentante.text")); //$NON-NLS-1$
			MnuItemRepresentante.setFont(App.fuentePrincipal);
		}
		return MnuItemRepresentante;
	}
	public JMenuItem getMnuItemGarantia() {
		if (MnuItemGarantia == null) {
			MnuItemGarantia = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemGarantia.text")); //$NON-NLS-1$
			MnuItemGarantia.setFont(App.fuentePrincipal);
		}
		return MnuItemGarantia;
	}
	public JMenuItem getMnuItemRepuesto() {
		if (MnuItemRepuesto == null) {
			MnuItemRepuesto = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemRepuesto.text")); //$NON-NLS-1$
			MnuItemRepuesto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.addTab("Catalogo de Repuesto", null, new FrmRepuesto(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);
				}
			});
			MnuItemRepuesto.setFont(App.fuentePrincipal);
			MnuItemRepuesto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		}
		return MnuItemRepuesto;
	}
	public JMenu getMnuItemEquipo() {
		if (MnuItemEquipo == null) {
			MnuItemEquipo = new JMenu(Messages.getString("FrmPrincipal.MnuItemEquipo.text")); //$NON-NLS-1$
			MnuItemEquipo.setFont(App.fuentePrincipal);
			MnuItemEquipo.add(getMnuItemMarca());
			MnuItemEquipo.add(getMnuItemModelo());
			MnuItemEquipo.add(getMnuItemSubModelo());
			MnuItemEquipo.add(getSeparador1());
			MnuItemEquipo.add(getMnuItemSerie());
			MnuItemEquipo.add(getMnuItemRepuesto());
			MnuItemEquipo.add(getMnuItemCompatibilidad());
		}
		return MnuItemEquipo;
	}
	public JMenuItem getMnuItemMarca() {
		if (MnuItemMarca == null) {
			MnuItemMarca = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemMarca.text")); //$NON-NLS-1$
			MnuItemMarca.setFont(App.fuentePrincipal);
			MnuItemMarca.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.addTab("Catalogo de Marca", null,  new FrmMarca(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);
				}
			});
			MnuItemMarca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		}
		return MnuItemMarca;
	}
	public JMenuItem getMnuItemModelo() {
		if (MnuItemModelo == null) {
			MnuItemModelo = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemModelo.text")); //$NON-NLS-1$
			MnuItemModelo.setFont(App.fuentePrincipal);
			MnuItemModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.addTab("Catalogo de Modelo", null, new FrmModelo(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);
				}
			});
			MnuItemModelo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return MnuItemModelo;
	}
	public JMenuItem getMnuItemSubModelo() {
		if (MnuItemSubModelo == null) {
			MnuItemSubModelo = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemSubModelo.text")); //$NON-NLS-1$
			MnuItemSubModelo.setFont(App.fuentePrincipal);
			MnuItemSubModelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.addTab("Catalogo de Submodelo", null, new FrmSubModelo(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);
				}
			});
			MnuItemSubModelo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		}
		return MnuItemSubModelo;
	}
	public JMenuItem getMnuItemSerie() {
		if (MnuItemSerie == null) {
			MnuItemSerie = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemSerie.text")); //$NON-NLS-1$
			MnuItemSerie.setFont(App.fuentePrincipal);
			MnuItemSerie.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {
					    public void run() {
					    	tpPrincipal.addTab("Catalogo de Equipo", null, new FrmEquipo(), null);
							tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);	
					    }
					});
					
					
				}
			});
			MnuItemSerie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}
		return MnuItemSerie;
	}
	public JMenuItem getMnuItemCompatibilidad() {
		if (MnuItemCompatibilidad == null) {
			MnuItemCompatibilidad = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemCompatibilidad.text")); //$NON-NLS-1$
			MnuItemCompatibilidad.setFont(App.fuentePrincipal);
			MnuItemCompatibilidad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		}
		return MnuItemCompatibilidad;
	}
	public JSeparator getSeparador1() {
		if (separador1 == null) {
			separador1 = new JSeparator();
		}
		return separador1;
	}
	public JMenu getMnuAdministrador() {
		if (MnuAdministrador == null) {
			MnuAdministrador = new JMenu(Messages.getString("FrmPrincipal.MnuAdministrador.text")); //$NON-NLS-1$
			MnuAdministrador.setMnemonic('A');
			MnuAdministrador.setFont(App.fuentePrincipal);
			MnuAdministrador.setFont(App.fuentePrincipal);
			MnuAdministrador.add(getMnuItemUsuario());
			MnuAdministrador.add(getMnuItemRoles());
			MnuAdministrador.add(getMnuItemConeccion());
		}
		return MnuAdministrador;
	}
	public JMenuItem getMnuItemUsuario() {
		if (MnuItemUsuario == null) {
			MnuItemUsuario = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemUsuario.text")); //$NON-NLS-1$
			MnuItemUsuario.setFont(App.fuentePrincipal);
		}
		return MnuItemUsuario;
	}
	public JMenuItem getMnuItemRoles() {
		if (MnuItemRoles == null) {
			MnuItemRoles = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemRoles.text")); //$NON-NLS-1$
			MnuItemRoles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.addTab("Catalogo de Roles", null,  new FrmRol(), null);
					tpPrincipal.setSelectedIndex(tpPrincipal.getTabCount()-1);
				}
			});
			MnuItemRoles.setFont(App.fuentePrincipal);
		}
		return MnuItemRoles;
	}
	public JMenuItem getMnuItemConeccion() {
		if (MnuItemConeccion == null) {
			MnuItemConeccion = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemConeccion.text")); //$NON-NLS-1$
			MnuItemConeccion.setFont(App.fuentePrincipal);
		}
		return MnuItemConeccion;
	}
	public JMenu getMnuDocumento() {
		if (MnuDocumento == null) {
			MnuDocumento = new JMenu(Messages.getString("FrmPrincipal.MnuDocumento.text")); //$NON-NLS-1$
			MnuDocumento.setFont(App.fuentePrincipal);
			MnuDocumento.add(getMnuItemFactura());
			MnuDocumento.add(getSeparator_1());
			MnuDocumento.add(getMnuItemIngreso());
			MnuDocumento.add(getSeparator_2());
			MnuDocumento.add(getMnuItemDiagnostico());
			MnuDocumento.add(getMnuReparacion());
			MnuDocumento.add(getSeparator());
			MnuDocumento.add(getMnuItemSalida());
		}
		return MnuDocumento;
	}
	public JMenuItem getMnuItemFactura() {
		if (MnuItemFactura == null) {
			MnuItemFactura = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemFactura.text")); //$NON-NLS-1$
			MnuItemFactura.setFont(App.fuentePrincipal);
			MnuItemFactura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		}
		return MnuItemFactura;
	}
	public JMenuItem getMnuItemIngreso() {
		if (MnuItemIngreso == null) {
			MnuItemIngreso = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemIngreso.text")); //$NON-NLS-1$
			MnuItemIngreso.setFont(App.fuentePrincipal);
			MnuItemIngreso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		}
		return MnuItemIngreso;
	}
	public JMenuItem getMnuItemDiagnostico() {
		if (MnuItemDiagnostico == null) {
			MnuItemDiagnostico = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemDiagnostico.text")); //$NON-NLS-1$
			MnuItemDiagnostico.setFont(App.fuentePrincipal);
			MnuItemDiagnostico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		}
		return MnuItemDiagnostico;
	}
	public JMenuItem getMnuReparacion() {
		if (MnuReparacion == null) {
			MnuReparacion = new JMenuItem(Messages.getString("FrmPrincipal.MnuReparacion.text")); //$NON-NLS-1$
			MnuReparacion.setFont(App.fuentePrincipal);
			MnuReparacion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		}
		return MnuReparacion;
	}
	public JMenuItem getMnuItemSalida() {
		if (MnuItemSalida == null) {
			MnuItemSalida = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemSalida.text")); //$NON-NLS-1$
			MnuItemSalida.setFont(App.fuentePrincipal);
			MnuItemSalida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		}
		return MnuItemSalida;
	}
	public JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	public JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	public JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
		}
		return separator_2;
	}
	
	public JMenu getMnuOpcion() {
		if (MnuOpcion == null) {
			MnuOpcion = new JMenu(Messages.getString("FrmPrincipal.MnuOpcion.text")); //$NON-NLS-1$
			MnuOpcion.setMnemonic('o');
			MnuOpcion.setFont(App.fuentePrincipal);
			MnuOpcion.add(getMnuItemConfigurarCuenta());
			MnuOpcion.add(getMnuItemCerrarSesion());
			MnuOpcion.add(getMnuItemSalir());
		}
		return MnuOpcion;
	}
	
	public JMenuItem getMnuItemConfigurarCuenta() {
		if (MnuItemConfigurarCuenta == null) {
			MnuItemConfigurarCuenta = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemConfigurarCuenta.text")); //$NON-NLS-1$
			MnuItemConfigurarCuenta.setFont(App.fuentePrincipal);
		}
		return MnuItemConfigurarCuenta;
	}
	
	public JMenuItem getMnuItemCerrarSesion() {
		if (MnuItemCerrarSesion == null) {
			MnuItemCerrarSesion = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemCerrarSesion.text")); //$NON-NLS-1$
			MnuItemCerrarSesion.setFont(App.fuentePrincipal);
		}
		return MnuItemCerrarSesion;
	}
	public JMenuItem getMnuItemSalir() {
		if (MnuItemSalir == null) {
			MnuItemSalir = new JMenuItem(Messages.getString("FrmPrincipal.MnuItemSalir.text")); //$NON-NLS-1$
			MnuItemSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int reply = JOptionPane.showConfirmDialog(FrmPrincipal.this, "Desea salir del programa?", "Confirmar cerrado de programa?", JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				    	System.exit(0);
				    }
				}
			});
			MnuItemSalir.setFont(App.fuentePrincipal);
		}
		return MnuItemSalir;
	}
	public JTabbedPane getTpPrincipal() {
		if (tpPrincipal == null) {
			tpPrincipal = new JTabbedPane(JTabbedPane.TOP);
			tpPrincipal.setRequestFocusEnabled(false);
			tpPrincipal.setOpaque(true);
			
			tpPrincipal.setUI(new PlasticTabbedPaneUI());
			tpPrincipal.setFont(App.fuentePrincipal);
			tpPrincipal.setComponentPopupMenu(getPopupMenu_2());			
		}
		return tpPrincipal;
	}
	
	public JPopupMenu getPopupMenu_2() {
		if (popupMenu_2 == null) {
			final JMenuItem cerrar = new JMenuItem("Cerrar");
			cerrar.setFont(App.fuentePrincipal);
			cerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tpPrincipal.removeTabAt(tpPrincipal.getSelectedIndex());
				}
			});
			
			final JMenuItem cerrarTodo = new JMenuItem("Cerrar Todos");
			cerrarTodo.setFont(App.fuentePrincipal);
			cerrarTodo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int cont = tpPrincipal.getTabCount();
					for (int it=0; it< cont;it++)				
						tpPrincipal.removeTabAt(0);
				}
			});
			
			final JMenuItem cerrarOtro = new JMenuItem("Cerrar Otro");
			cerrarOtro.setFont(App.fuentePrincipal);
			cerrarOtro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {					
					while(tpPrincipal.getTabCount()>1){
						if(0!= tpPrincipal.getSelectedIndex())
							tpPrincipal.removeTabAt(0);
						else
							tpPrincipal.removeTabAt(1);
					}
				}
			});
			
			popupMenu_2 = new JPopupMenu();
			popupMenu_2.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent arg0) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
					popupMenu_2.removeAll();
					
					if(tpPrincipal.getTabCount()>1){
						popupMenu_2.add(cerrar);
						popupMenu_2.add(cerrarOtro);
						popupMenu_2.add(cerrarTodo);
					}else{
						if(tpPrincipal.getTabCount()==1){
							popupMenu_2.add(cerrar);
						}
					}
					
					
					/*
					
					JPopupMenu source = (JPopupMenu) arg0.getSource();
					//JOptionPane.showMessageDialog(null, source.getInvoker());
					JPanel invoker = (JPanel) tabbedPane.getSelectedComponent();
					
	                //JLabel invoker = (JLabel) source.getInvoker();
					JPanel component = (JPanel) tabbedPane.getComponentAt(Integer.parseInt(invoker.getName()));
					
	                mntmActual.setText(invoker.getName() + ":  " + component.getName());*/
				}
			});
			
		}
		return popupMenu_2;
	}
}
