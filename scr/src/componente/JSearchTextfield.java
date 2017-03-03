package componente;

import igui.App;
import igui.FrmPrincipal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RowFilter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.table.TableRowSorter;

import mvc.JDataTable;
import mvc.JTableModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class JSearchTextfield extends JPanel {

	private static final long serialVersionUID = 1L;
	TableRowSorter<JTableModel> sorter=null;
	public JTextField txtBuscar;
	public JButton btnRecargarTabla;
	public JButton btnlimpiarCampo;
	public JLabel statusBar;
	
	/**
	 * Create the panel.
	 */
	public JSearchTextfield() {
		initComponents();
	}
		
	private void initComponents() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 20, 20};
		gridBagLayout.rowHeights = new int[]{40, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_txtBuscar = new GridBagConstraints();
		gbc_txtBuscar.insets = new Insets(10, 10, 10, 10);
		gbc_txtBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuscar.gridx = 0;
		gbc_txtBuscar.gridy = 0;
		add(getTxtBuscar(), gbc_txtBuscar);
		
		GridBagConstraints gbc_btnlimpiarCampo = new GridBagConstraints();
		gbc_btnlimpiarCampo.insets = new Insets(0, 0, 0, 5);
		gbc_btnlimpiarCampo.anchor = GridBagConstraints.WEST;
		gbc_btnlimpiarCampo.gridx = 1;
		gbc_btnlimpiarCampo.gridy = 0;
		add(getlimpiarTabla(), gbc_btnlimpiarCampo);
		
		GridBagConstraints gbc_btnRecargarTabla = new GridBagConstraints();
		gbc_btnRecargarTabla.insets = new Insets(0, 0, 0, 5);
		gbc_btnRecargarTabla.anchor = GridBagConstraints.WEST;
		gbc_btnRecargarTabla.gridx = 2;
		gbc_btnRecargarTabla.gridy = 0;
		add(getBtnRecargarTabla(), gbc_btnRecargarTabla);
	}	

	public JTextField getTxtBuscar() {
		if (txtBuscar == null) {
			txtBuscar = new JTextField();
			txtBuscar.setFont(App.fuentePrincipal);
			txtBuscar.setText("Buscar...");
			txtBuscar.setForeground(Color.GRAY);
			txtBuscar.setBorder(null);
			txtBuscar.setAutoscrolls(false);
			txtBuscar.setOpaque(false);
			txtBuscar.setColumns(10);
			txtBuscar.setMargin(new Insets(0, 0, 0, 0));
			
			txtBuscar.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					if(txtBuscar.getForeground()==Color.GRAY)
						txtBuscar.setText("");
				}
				
				@Override
				public void focusLost(FocusEvent e) {
					if(txtBuscar.getText().length()<=0){
						txtBuscar.setText("Buscar...");
						txtBuscar.setForeground(Color.GRAY);
					}
				}
			});
			
			txtBuscar.addKeyListener(new KeyAdapter() {				
				@Override
				public void keyReleased(KeyEvent e) {
					txtBuscar.setForeground( (txtBuscar.getText().length()>0)?Color.BLACK:Color.GRAY) ;	
					_actualizarFiltro();	
				}
			});
		}
		return txtBuscar;
	}
	/*public JButton getBtnlimpiarCampo() {
		if (btnlimpiarCampo == null) {
			btnlimpiarCampo = new JButton("limpiar");
			btnlimpiarCampo.setVisible(false);
		
			btnlimpiarCampo.setMargin(new Insets(0, 0, 0, 0));
			btnlimpiarCampo.setFocusPainted(false);
			btnlimpiarCampo.setOpaque(false);
			btnlimpiarCampo.setBorderPainted(false);
			btnlimpiarCampo.setContentAreaFilled(false);
			btnlimpiarCampo.setBackground(Color.WHITE);
		}
		btnlimpiarCampo.setToolTipText("buscar");
		return btnlimpiarCampo;
	}*/
	
	public JButton getBtnRecargarTabla() {
		if (btnRecargarTabla == null) {
			btnRecargarTabla = new JButton("");
			btnRecargarTabla.setVisible(false);
			btnRecargarTabla.setIcon(new ImageIcon(JSearchTextfield.class.getResource("/icon/general/buscar.png")));
			btnRecargarTabla.setToolTipText("Buscar");
			btnRecargarTabla.setOpaque(false);
			btnRecargarTabla.setMargin(new Insets(0, 0, 0, 0));
			btnRecargarTabla.setFocusPainted(false);
			btnRecargarTabla.setContentAreaFilled(false);
			btnRecargarTabla.setBorderPainted(false);
			btnRecargarTabla.setBackground(Color.WHITE);
		}
		return btnRecargarTabla;
	}
	
	public JButton getlimpiarTabla() {
		if (btnlimpiarCampo == null) {
			btnlimpiarCampo = new JButton("");
			btnlimpiarCampo.setVisible(false);
			btnlimpiarCampo.setIcon(new ImageIcon(JSearchTextfield.class.getResource("/icon/general/limpiar.png")));
			btnlimpiarCampo.setToolTipText("limpiar Busqueda");
			btnlimpiarCampo.setOpaque(false);
			btnlimpiarCampo.setMargin(new Insets(0, 0, 0, 0));
			btnlimpiarCampo.setFocusPainted(false);
			btnlimpiarCampo.setContentAreaFilled(false);
			btnlimpiarCampo.setBorderPainted(false);
			btnlimpiarCampo.setBackground(Color.WHITE);

		}
		return btnlimpiarCampo;
	}
	
	public void _setTableFilter(JDataTable dt,JLabel statusbar) {
		this.statusBar = statusbar;
		if(dt.getRowCount()>0){
		sorter = new TableRowSorter<JTableModel>(dt.DAOModelo);
	    dt.setRowSorter(sorter);
		}
	}
	
	public void _actualizarFiltro(){
		if (txtBuscar.getText().length() == 0 && sorter!=null) {
			txtBuscar.setForeground(Color.GRAY) ;	
	          sorter.setRowFilter(null);
	          statusBar.setIcon(null);
	          statusBar.setText(sorter.getModelRowCount()+" Registros");
	    } else {
	    	  if(txtBuscar.getForeground()==Color.BLACK && sorter!=null){
	    		  sorter.setRowFilter(RowFilter.regexFilter("(?i)"+txtBuscar.getText()));
	    		  statusBar.setText(sorter.getViewRowCount() + " Registros Filtrado de "+sorter.getModelRowCount()+" Registros");
	    		  statusBar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/icon/general/wait.gif")));
	    	        
	    	  }
	    		  
	    	  
	    }
	}
	
	public void _reiniciarComponente(){
		txtBuscar.setText("");
		_actualizarFiltro();
		txtBuscar.setText("Buscar...");
	}
}
