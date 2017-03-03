package componente;

import igui.FrmPrincipal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import mvc.JDataTable;
/**
 * @author Bismarck Salvador Traña Lopez
 */


public class SincronizarArbol  extends SwingWorker<Void, Void> {
	 

    private final JButton boton ;    
    private final JLabel estado;	     
    private JDataTable tabla;
    private JSearchTextfield buscar;
            
    public SincronizarArbol(JDataTable tabla, JLabel estado,JButton boton,JSearchTextfield bus)
    {	    	
    	this.tabla = tabla;
   	 	this.boton = boton;        
        this.estado = estado;
        this.buscar = bus;
    }
    
    /**
 * Metodo que realiza la tarea pesada
 */
    @Override
    protected Void doInBackground() throws Exception {
    	estado.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/icon/general/wait.gif")));
        this.estado.setText("Estado: Cargardo Lista, por favor espere...");
        this.boton.setEnabled(false);
        tabla._actualizar();         
        return null;
    }

    /**
 * Resultado en pantalla
 */
    @Override
    protected void done(){  
    	SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	 estado.setIcon(null);
		         boton.setEnabled(true);
		         estado.setText(tabla.DAOModelo.getRowCount()+ " Regristros");
		         buscar.btnlimpiarCampo.doClick();
		         buscar.btnRecargarTabla.doClick();
		         buscar._setTableFilter(tabla,estado);
		    }
		  });
    	
    	
       
    }
    
}