package componente;


import igui.FrmPrincipal;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import bd.BDModelo;
import objeto.Modelo;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */


public class SincronizarComboModelo  extends SwingWorker<Void, Void> {
	    
	     private final JLabel estado;	     
	     private JComboBox<Modelo>  tabla;
	     
	             
	     public SincronizarComboModelo(JComboBox<Modelo> combo, JLabel estado)
	     {	    	
	    	 this.tabla = combo;      
	         this.estado = estado;
	     }
	     
	     /**
	  * Metodo que realiza la tarea pesada
	  */
	     @Override
	     protected Void doInBackground() throws Exception {
	         estado.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/icon/general/wait.gif")));
	         this.estado.setText("Cargando...");
	         BDModelo.cargarModelos(tabla, false);                  
	         return null;
	     }
	 
	     /**
	  * Resultado en pantalla
	  */
	     @Override
	     protected void done(){      
	         estado.setIcon(null);
	         this.estado.setText("");
	     }
	     
	 }
