package componente;


import igui.FrmPrincipal;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import bd.BDSubmodelo;
import objeto.Submodelo;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */


public class SincronizarComboSubModelo  extends SwingWorker<Void, Void> {
	    
	     private final JLabel estado;	     
	     private JComboBox<Submodelo>  tabla;
	     private int metodo =0;
	             
	     public SincronizarComboSubModelo(JComboBox<Submodelo> combo, JLabel estado,int Metodo)
	     {	    	
	    	 this.tabla = combo;      
	         this.estado = estado;
	         this.metodo = Metodo;
	     }
	     
	     /**
	  * Metodo que realiza la tarea pesada
	  */
	     @Override
	     protected Void doInBackground() throws Exception {
	         estado.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/icon/general/wait.gif")));
	         this.estado.setText("Cargando...");
	         if(metodo == 0)
	        	 BDSubmodelo.cargarCombobox(tabla, false); 
	         else
	        	 BDSubmodelo.cargarSubModelosXModelo(tabla, metodo);
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
